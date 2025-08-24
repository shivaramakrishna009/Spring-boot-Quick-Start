# What is Spring?

**Spring** is a powerful, open-source Java framework designed to simplify building enterprise-grade applications.  
It provides tools, libraries, and abstractions to help you write clean, testable, and maintainable code — without
reinventing the wheel.

# What is Maven?

**Maven** is a build automation and dependency management tool for Java projects.  
It helps developers:

- Manage dependencies (libraries)
- Compile, test, package, and run their applications
- Maintain consistent project structure

Think of Maven as the project manager for Java apps — it tells your app how to build, what libraries to include, and how
to package/run it.

# `@RestController` Annotation:
## What is `@RestController`?

`@RestController` is a specialized version of the `@Controller` annotation in **Spring MVC** and **Spring Boot**.  
It is used to create RESTful web services and APIs.
When you annotate a class with `@RestController`, it indicates that the class will handle HTTP requests and return data directly as
the response body, typically in formats like JSON or XML.

# `@RequestMapping` Annotation:

## What is `@RequestMapping`?

`@RequestMapping` is a core annotation in **Spring MVC** and **Spring Boot** that maps incoming HTTP requests to specific controller classes and handler methods.

It is used to route web requests, making sure the right method handles the requested **URL**, **HTTP method**, **headers**, or **parameters**.

# Common Attributes of `@RequestMapping`:
- `value` or `path`: Specifies the URL path to map to.
- `method`: Specifies the HTTP method (GET, POST, PUT, DELETE, etc.)
- `params`: Specifies request parameters that must be present.
- `headers`: Specifies request headers that must be present.

# `@PostMapping` Annotation:
## What is `@PostMapping`?
`@PostMapping` is a specialized version of the `@RequestMapping` annotation in **Spring MVC** and **Spring Boot**.
It is specifically used to handle HTTP `POST` requests, which are typically used to create new resources on the server.

# `@PutMapping` Annotation:
## What is `@PutMapping`?

`@PutMapping` is a specialized version of the `@RequestMapping` annotation in **Spring MVC** and **Spring Boot**.  
It is specifically used to handle HTTP `PUT` requests, which are typically used to update existing resources on the server.

# `@GetMapping` Annotation:
## What is `@GetMapping`?
`@GetMapping` is a specialized version of the `@RequestMapping` annotation in **Spring MVC** and **Spring Boot**.
It is specifically used to handle HTTP `GET` requests, which are typically used to retrieve data from the server.


# Bill of Materials (BOM) in Spring

## What is a BOM?
- A **Bill of Materials (BOM)** is a special kind of Maven `pom.xml` file.
- It is used to **manage dependency versions in one place**.
- Instead of specifying versions for each dependency, you import a BOM and Maven automatically aligns the versions.

---

## Why use a BOM?
1. **Version consistency**  
   Ensures all Spring modules (Spring Boot, Data JPA, Security, etc.) use **compatible versions**.

2. **Cleaner `pom.xml`**  
   No need to manually declare versions for every Spring dependency.

3. **Centralized version management**  
   If you want to upgrade, just update the BOM version.

---

## Example: Without BOM
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>3.2.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <version>3.2.0</version>
    </dependency>
</dependencies>
```

### Example: With BOM
In Maven, you define the BOM under the `<dependencyManagement>` section, and then add your actual dependencies in the `<dependencies>` section.

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>3.2.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <!-- Spring Boot Web Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Data JPA Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
```

# Annotations in Spring Controllers to Get Values from the URL

In Spring MVC / Spring Boot, controller methods often need values from the URL, query parameters, headers, or body.  
Spring provides several annotations for this purpose:

---

## 1. `@PathVariable`
- Extracts **values from the URI path**.
- Example URL: `/users/101`

```java
@GetMapping("/users/{id}")
public String getUserById(@PathVariable("id") int userId) {
    return "User ID: " + userId;
}
```
