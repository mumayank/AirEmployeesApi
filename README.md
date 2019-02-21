# AirEmployeesApi


#### 1
Create user in mysql
```mysql
CREATE USER 'x'@'localhost'
IDENTIFIED BY 'password';
```

***

#### 2
Grant all privileges to the user

```mysql
GRANT ALL PRIVILEGES
TO *.*
TO 'x'@'localhost';
```

***

#### 3
Flush changes

```mysql
FLUSH PRIVILEGES;
```

***

#### 4
Create database

```mysql
CREATE DATABASE XXX;
```

***

#### 5
Create new project via [start.spring.io](https://start.spring.io) and select:

`Web` for REST API dependency

`MySQL` for DB dependency

`JPA` for ORM dependency

Download the project, Extract it, import it in your IDE, and open `Application` class and select `Run` from mouse right click menu

***

#### 6
In `application.properties` file, type:

```java
spring.datasource.username=x
spring.datasouoce.password=y
spring.datasource.url=jdbc:mysql://localhost:3306/xyz
spring.jpa.hibernate.ddl-auto=update
```

***

#### 7
- Create the feature's package
- Create the feature's API class:
- Annotate `@RestController`
- Annotate `@RequestMapping("feature") // http://localhost:8080/users

```java
public class FeatureApi {

  @GetMapping
  public String getX() {
    return "";
  }
  
  // Post, Put, Delete
}
```


***

#### 8
- For sending Objects in API, simply use POJO classes
- For accepting Objects in API, simply use POJO classes, and use annotation `@RequestBody` in param list like:

```java
@PostMapping
public String createEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest) {
  //
}
```


***

#### 9
For representing tables of mysql, create Entity classes
- POJO
- implements Serializable
- Annotate as `@Entity(name="tableName")`
- Must have a member as:

```java
@Id
@GeneratedValue
private long id
```
(id for keeping it unique + non null, generated value to let the system generate it automatically)

- Constrain other fields like:

```java
@Column(nullable=false, length=100)
```
- For boolean fields, define defaults like:
```java
private Boolean email=false
```

***


#### 10
To interact with DB, create a repository class:
- It is an interface
- extends CrudRepository<EntityClass, Long>
- Annotate `@Repository`
- Only define special methods in it if required, like:

```java
UserEntity findUserByEmail(String email);
```

(note that since this is an interface, only define function definition. No need to implement as the system will implement based on fields defined in method name and params)

***

#### 11
Wherever you want to use repository (generally your API class), define on class top:

```java
@Autowired
XxxRepository xxxRepository;
```

***

#### That's all folks!
