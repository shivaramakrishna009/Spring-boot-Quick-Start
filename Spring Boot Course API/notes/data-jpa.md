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


