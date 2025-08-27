# Spring JPA Entity Relationships - Quick Reference Guide

## 🧩 What Are Entity Relationships?

In a relational database, data is often split into **multiple tables** to reduce redundancy and improve data integrity. These tables are **related** to each other via **foreign keys**. In JPA, we represent these table relationships using **annotations** in our Java entities (classes).

---

## 🔗 Types of Relationships in JPA

There are **four main types** of entity relationships:

| Relationship Type | Description                                                 | Real-World Example                 |
| ----------------- | ----------------------------------------------------------- | ---------------------------------- |
| **One-to-One**    | One entity is associated with exactly one of another entity | A person has one passport          |
| **One-to-Many**   | One entity is associated with many of another entity        | A teacher has many students        |
| **Many-to-One**   | Many entities are associated with one entity                | Many orders placed by one customer |
| **Many-to-Many**  | Many entities are associated with many of another entity    | Students enrolled in many courses  |

---

## 🔍 1. One-to-One Relationship

### 🛠️ Setup

```java
@Entity
public class Person {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "passport_id") // foreign key
    private Passport passport;
}

@Entity
public class Passport {
    @Id
    private Long id;

    private String number;
}
```

### ✅ Notes

* `@JoinColumn` creates the foreign key in the `Person` table.
* Bidirectional: Add `mappedBy` on the other side if needed.

---

## 🔍 2. One-to-Many and Many-to-One

### 🛠️ One-to-Many (Parent side)

```java
@Entity
public class Teacher {
    @Id
    private Long id;

    @OneToMany(mappedBy = "teacher")
    private List<Student> students;
}
```

### 🛠️ Many-to-One (Child side)

```java
@Entity
public class Student {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id") // foreign key
    private Teacher teacher;
}
```

### ✅ Notes

* The `@ManyToOne` side owns the relationship (foreign key is here).
* `mappedBy` tells JPA who owns the relationship.
* Avoid `@OneToMany` without `mappedBy` – it creates unnecessary join tables.

---

## 🔍 3. Many-to-Many

### 🛠️ Example: Student and Course

```java
@Entity
public class Student {
    @Id
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}

@Entity
public class Course {
    @Id
    private Long id;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
```

### ✅ Notes

* A **join table** (e.g. `student_course`) maps the many-to-many relationship.
* Use `mappedBy` to avoid infinite loops and unnecessary tables.

---

## 🧠 Tips and Best Practices

| Practice                                | Explanation                                            |
| --------------------------------------- | ------------------------------------------------------ |
| ❗ **Always define owning side**         | The side with the foreign key is the owning side.      |
| ✅ Use `mappedBy` wisely                 | Only use it on the inverse (non-owning) side.          |
| ⚠️ Avoid bidirectional where not needed | It adds complexity and can cause circular references.  |
| 💡 Consider DTOs                        | Don’t expose entire entity graphs in APIs.             |
| ⚙️ Use `fetch = FetchType.LAZY`         | Prevents loading entire object graphs unintentionally. |

---

## 🔄 Cascade and Orphan Removal

* `cascade = CascadeType.ALL` → Save/delete child when parent is saved/deleted.
* `orphanRemoval = true` → Deletes children when removed from parent’s collection.

```java
@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Student> students;
```

---

## 🧪 Bonus: Fetching Strategies

| Type                               | Meaning                                   |
| ---------------------------------- | ----------------------------------------- |
| `LAZY` (default for `@OneToMany`)  | Loads related entities only when accessed |
| `EAGER` (default for `@ManyToOne`) | Loads related entities immediately        |

> ⚠️ EAGER fetching can lead to performance issues and `N+1` problems.

---

## 📘 Summary Table

| Relationship | Annotation Used | Owning Side               | Default Fetch |
| ------------ | --------------- | ------------------------- | ------------- |
| One-to-One   | `@OneToOne`     | Entity with `@JoinColumn` | EAGER         |
| One-to-Many  | `@OneToMany`    | The `@ManyToOne` side     | LAZY          |
| Many-to-One  | `@ManyToOne`    | This side (foreign key)   | EAGER         |
| Many-to-Many | `@ManyToMany`   | Side with `@JoinTable`    | LAZY          |

---

## ✅ Topic: JPA Entity Relationships — What We Covered in the Course Controller

---

### 🔹 1. **Entity Relationship Types**

* `@ManyToOne` — Many `Course`s belong to one `Topic`
* This sets up a **foreign key** (`topic_id`) in the `Course` table

---

### 🔹 2. **Why Your GET Returned an Empty List**

* Your `POST` request **wasn’t saving** the `Course` because:

    * You forgot to call `courseService.addCourse(course)`
    * You didn't set the `Topic` before saving, so `topic_id` was `null`

---

### 🔹 3. **Why You Have to Set the Topic in the Controller**

* Even if you set the topic in your constructor, Spring **doesn’t use your constructor** when using `@RequestBody`
* Jackson deserializes JSON by:

    * Calling the **default constructor**
    * Then setting fields individually
* So you **must set the `topic` manually** in the controller:

  ```java
  course.setTopic(new Topic(topicId, "", ""));
  ```

---

### 🔹 4. **Why Just Setting the Topic ID is Enough**

* When saving a `Course`, JPA only needs the **`topic.id`** to set up the foreign key
* The name and description of the topic are **ignored** during save
* On fetch (`GET`), JPA uses the `topic_id` to **automatically join** and fetch the full `Topic` object

---

### 🔹 5. **How JPA Fetches the Full Topic**

* Because of the `@ManyToOne` mapping and default **EAGER fetching**
* Even if you saved only the topic ID, JPA fetches the full topic from DB when you do a `GET`

---

### 🔹 6. **When You Need to Provide More than Just the ID**

* Only if:

    * You're creating a **new referenced entity**
    * You're updating the referenced entity
    * You're using **cascade operations** (like `CascadeType.ALL`) — use cautiously

---

### 🔹 7. **Alternatives for Cleaner Design**

* Use a **DTO class** (`CourseRequest`) with only `id`, `name`, `description`
* Use the constructor inside the controller to create the `Course` and set the `Topic`

---

### 🔹 8. **Optional Optimization (Advanced)**

* Use `EntityManager.getReference()` for performance:

  ```java
  course.setTopic(entityManager.getReference(Topic.class, topicId));
  ```

---

## ✅ Code Snippet Recap

**Controller:**

```java
@PostMapping("/topics/{topicId}/courses")
public void addCourse(@PathVariable String topicId, @RequestBody Course course) {
    course.setTopic(new Topic(topicId, "", "")); // required to link to existing Topic
    courseService.addCourse(course);
}
```

**Repository:**

```java
List<Course> findByTopicId(String topicId); // JPA will auto-implement
```

---

## 🧠 Key Takeaways

| Concept                             | Explanation                                                  |
| ----------------------------------- | ------------------------------------------------------------ |
| JPA only needs the ID               | When referencing an existing entity, set just the ID         |
| Jackson skips your constructor      | Spring uses default constructor + setters for `@RequestBody` |
| Full object returned on GET         | Because of `@ManyToOne` and automatic join via `topic_id`    |
| Don’t set full object unless needed | It may overwrite or save partial data                        |
| Use DTO for clean separation        | Optional but recommended for bigger projects                 |

---