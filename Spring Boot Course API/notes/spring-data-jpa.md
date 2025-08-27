# 📌 What is JPA?

**JPA (Java Persistence API)** is a **specification** in Java that defines how to manage relational data in Java applications.
It provides a standard way to map **Java objects (entities)** to **database tables** and perform CRUD operations without writing raw SQL.

👉 **Important:**
JPA is **just a specification**, not an implementation. To actually use it, you need an implementation library like:

* **Hibernate** (most common)
* EclipseLink
* OpenJPA

---

# 📌 Why JPA?

Traditionally, we wrote a lot of JDBC code like this:

```java
Connection con = DriverManager.getConnection(...);
PreparedStatement stmt = con.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)");
stmt.setInt(1, 1);
stmt.setString(2, "Krishna");
stmt.executeUpdate();
```

This is **verbose** and error-prone.
With **JPA**, the same can be done like this:

```java
User user = new User(1, "Krishna");
entityManager.persist(user);
```

✅ Less code
✅ Database-independent
✅ Object-oriented

---

# 📌 Key JPA Annotations

| Annotation                                             | Purpose                                                                   |
| ------------------------------------------------------ | ------------------------------------------------------------------------- |
| `@Entity`                                              | Marks a class as a database entity (table).                               |
| `@Id`                                                  | Marks the primary key field.                                              |
| `@GeneratedValue`                                      | Auto-generates primary key values.                                        |
| `@Column`                                              | Maps a field to a database column (optional if field name = column name). |
| `@Table`                                               | Specifies table name (optional, default = class name).                    |
| `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany` | Relationships between entities.                                           |

---

# 📌 Example JPA Entity

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    // Getters and Setters
}
```

---

# 📌 How JPA Works (Simplified Flow)

1. You define **entities** (`@Entity` classes).
2. JPA (through Hibernate, etc.) maps them to database tables.
3. You use an `EntityManager` or `Spring Data JPA Repository` to:

    * `persist()` → Insert
    * `find()` → Select
    * `merge()` → Update
    * `remove()` → Delete
4. SQL is generated automatically behind the scenes.

---

# 📌 JPA in Spring Boot

Spring Boot makes JPA easy with **Spring Data JPA**.
Instead of using `EntityManager` manually, you can just define a `Repository`.

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here
}
```

Then you can directly use it:

```java
@Autowired
private UserRepository userRepository;

public void addUser() {
    User user = new User();
    user.setName("Krishna");
    user.setEmail("krishna@example.com");
    userRepository.save(user);  // ✅ Insert automatically
}
```

---

👉 In short:

* **JPA** = Specification
* **Hibernate** = Most common implementation
* **Spring Data JPA** = Makes JPA easier to use with repositories
---

# Why `save()` is used for both Adding and Updating in Spring Data JPA Repository

In Spring Data JPA, the `save()` method is used for both **inserting (adding)** and **updating** entities.  
This behavior is based on how JPA (Java Persistence API) works with the **EntityManager**.

---

## 1. How `save()` Works
- When you call `save(entity)`, JPA checks if the entity already exists in the database.
- The check is usually based on the **primary key (ID)**:
  - If the **ID is `null`** (or not present in the database):
    - JPA treats it as a **new entity** → performs an **INSERT** operation.
  - If the **ID is not null** and already exists in the database:
    - JPA treats it as an **existing entity** → performs an **UPDATE** operation.

---

## 2. Example

```java
// Adding a new entity
Topic topic = new Topic();
topic.setId("101");
topic.setName("Spring Boot Basics");
topicRepo.save(topic);  // INSERT happens

// Updating an existing entity
Topic existingTopic = topicRepo.findById("101").get();
existingTopic.setName("Spring Boot Advanced");
topicRepo.save(existingTopic);  // UPDATE happens
````
---

## 3. Behind the Scenes

* Internally, Spring Data JPA delegates to the **EntityManager**:

    * `persist()` → Used for **new entities** (INSERT).
    * `merge()` → Used for **detached or existing entities** (UPDATE).
* The `save()` method intelligently decides whether to call `persist()` or `merge()`.

---

## 4. Advantages of `save()`

* Simplifies code → one method for both insert and update.
* Makes repository APIs clean and consistent.
* Reduces boilerplate code of having separate methods for insert/update.

---

## 5. Important Note

* The decision is based on the **primary key (ID)**.
* If you provide a wrong ID or try to update an entity that doesn’t exist, JPA may still try to insert it, which can lead to unexpected behavior.

---

# 📌 Spring Data JPA Repository Query Method Naming Conventions

You can create query methods in Spring Data JPA by simply defining method names following specific naming patterns. 
Spring will automatically generate the queries based on those names.

| **Method Name Pattern**                     | **Purpose**                                                        |
| ------------------------------------------- | ------------------------------------------------------------------ |
| `findBy<Property>`                          | Find entities by a specific property                               |
| `findBy<Property1>And<Property2>`           | Find entities by multiple properties                               |
| `findBy<Property>Or<Property>`              | Find entities by either of two properties                          |
| `countBy<Property>`                         | Count entities by a specific property                              |
| `deleteBy<Property>`                        | Delete entities by a specific property                             |
| `existsBy<Property>`                        | Check if an entity exists by a specific property                   |
| `findAllBy<Property>`                       | Find all entities matching a specific property                     |
| `findTopNBy<Property>`                      | Find the top N entities by a specific property                     |
| `findFirstNBy<Property>`                    | Find the first N entities by a specific property                   |
| `findBy<Property>OrderBy<Property>Asc/Desc` | Find entities ordered by a specific property                       |
| `findBy<Property>Between`                   | Find entities with a property value between two values             |
| `findBy<Property>Like`                      | Find entities with a property value matching a pattern             |
| `findBy<Property>In`                        | Find entities with a property value in a given list                |
| `findBy<Property>IsNull`                    | Find entities where a property is null                             |
| `findBy<Property>IsNotNull`                 | Find entities where a property is not null                         |
| `findBy<Property>StartingWith`              | Find entities where a property starts with a given value           |
| `findBy<Property>EndingWith`                | Find entities where a property ends with a given value             |
| `findBy<Property>Containing`                | Find entities where a property contains a given value              |
| `findBy<Property>IgnoreCase`                | Find entities where a property matches a value, ignoring case      |
| `findBy<Property>GreaterThan`               | Find entities where a property is greater than a given value       |
| `findBy<Property>LessThan`                  | Find entities where a property is less than a given value          |
| `findBy<Property>GreaterThanEqual`          | Find entities where a property is greater than or equal to a value |
| `findBy<Property>LessThanEqual`             | Find entities where a property is less than or equal to a value    |

---


