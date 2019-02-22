# AirEmployeesApi

- Step-wise instructions given in [wiki](https://github.com/mumayank/AirEmployeesApi/wiki/0.-Home) (outdated)
- All instructions in one shot given below:

***

#### 1. MySQL:

```mysql
CREATE USER 'x'@'localhost'
IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES
TO *.*
TO 'x'@'localhost';

FLUSH PRIVILEGES;

CREATE DATABASE XXX;
```


***

#### 2. Spring Boot Project:

- [start.spring.io](https://start.spring.io)
- `Web`, `MySQL`, `JPA`, `Security`
- Download
- Extract
- Import in IDE
- Run `Application` class



***


#### 3. `application.properties`:

```java
spring.datasource.username=x
spring.datasouoce.password=y
spring.datasource.url=jdbc:mysql://localhost:3306/xyz
spring.jpa.hibernate.ddl-auto=update
```


***



#### 4. FeatureAPI class:

```java
@RestController
@RequestMapping("feature") // http://localhost:8080/users
public class FeatureApi {

  @GetMapping
  public String getX() {
    return "";
  }
  
  // Post, Put, Delete
}
```


***



#### 5. 

- Sending response as Object -> Use POJO
- Receiving response as Object -> Use POJO and `@RequestBody` as:

```java
@PostMapping
public String createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest) {
  //
}
```


***



#### 6. Entity class:

- POJO
- implements Serializable
- Annotate as `@Entity(name="tableName")`
- Must have a member as:

```java
@Id
@GeneratedValue
private long id
```
- Constrain other fields:

```java
@Column(nullable=false, length=100)
```

- Constrain boolean fields:
```java
private Boolean email=false
```



***



#### 7. Repository class:

- It is an interface
- extends CrudRepository<EntityClass, Long>
- Annotate `@Repository`
- Only define special methods in it if required, like:

```java
UserEntity findUserByEmail(String email);
```



***



#### 8. Use repository in API class:

Wherever you want to use repository (generally your API class), define on class top:

```java
@Autowired
XxxRepository xxxRepository;
```
