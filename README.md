# AirEmployeesApi

#### 1. MySQL:

```mysql
CREATE DATABASE XXX;

CREATE USER 'x'@'localhost'
IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES
ON *.*
TO 'x'@'localhost';

FLUSH PRIVILEGES;
```


***

#### 2. Spring Boot Project:

- [start.spring.io](https://start.spring.io)
- `Web`, `MySQL`, `JPA`
- Download
- Extract
- Import in IDE
- Run `Application` class



***


#### 3. `application.properties`:

```java
spring.datasource.username=x
spring.datasource.password=y
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



***


#### 9. Security
Note that after adding security feature, none of the APIs would work without auth
- Add to project `Security`, `Json Web Token`(from [here](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt/0.6.0))
- Add to `Application` class:
```java
@Bean
public BCryptPasswordEncoder bCryptPasswordEncoder() {
  return new BCryptPasswordEncoder();
}
```
(defining it as `@Bean` enables us to `Autowire` it later when a use is required)
- Use it to encrypt the password when new user is created (at `Post`), and save that in the DB as:

on top of the class:
```java
@Autowire
BCryptPasswordEncoder bCryptPasswordEncoder;
```

during use:
```java
bCryptPasswordEncoder.encode(userProvidedPassword);
```
