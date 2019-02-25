package com.employees.com.employees.employees;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class EmployeesApi {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public ArrayList<EmployeeGetResponse> getEmployees() {
        ArrayList<EmployeeGetResponse> employeeGetResponses = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeRepository.findAll()) {
            EmployeeGetResponse employeeGetResponse = new EmployeeGetResponse();
            BeanUtils.copyProperties(employeeEntity, employeeGetResponse);
            employeeGetResponses.add(employeeGetResponse);
        }
        return employeeGetResponses;
    }

    @PostMapping
    public String createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeCreateRequest, employeeEntity);
        //employeeEntity.setPassword(new BCryptPasswordEncoder().encode(employeeCreateRequest.getPassword()));
        employeeRepository.save(employeeEntity);
        return "saved";
    }

    @PutMapping
    public String updateEmployee(@RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(employeeUpdateRequest.getId());
        EmployeeEntity employeeEntity;

        if (employeeEntityOptional.isPresent()) {
            employeeEntity = employeeEntityOptional.get();
        } else {
            return "Could not complete this action as there is no employee with this ID";
        }

        boolean arePasswordsOkay = true;
        boolean shouldUpdatePassword = false;
        if (employeeUpdateRequest.getOldPassword() != null) {
            if (employeeUpdateRequest.getPassword() == null) {
                shouldUpdatePassword = true;
            } else {
                arePasswordsOkay = false;
            }
        } else {
            if (employeeUpdateRequest.getPassword() != null) {
                arePasswordsOkay = false;
            } else {
                // ignore
            }
        }
        if (arePasswordsOkay == false) {
            return "Could not complete this action as both passwords (old and new) not provided";
        }
        if (shouldUpdatePassword) {
            //String oldPassword = new BCryptPasswordEncoder().encode(employeeUpdateRequest.getOldPassword());
            if (employeeEntity.getPassword().equals(employeeUpdateRequest.getOldPassword())) {
                employeeEntity.setPassword(employeeUpdateRequest.getPassword());
            } else {
                return "Could not complete this action as invalid old password";
            }
        }
        if (employeeUpdateRequest.getName() != null) {
            employeeEntity.setName(employeeUpdateRequest.getName());
        }

        employeeRepository.save(employeeEntity);
        return "saved";
    }

    @DeleteMapping
    public String deleteEmployee(@RequestBody EmployeeDeleteRequest employeeDeleteRequest) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(employeeDeleteRequest.getId());
        EmployeeEntity employeeEntity;
        if (employeeEntityOptional.isPresent()) {
            employeeEntity = employeeEntityOptional.get();
        } else {
            return "Could not complete this action as there is no employee with this ID";
        }
        employeeRepository.delete(employeeEntity);
        return "deleted";
    }

}
