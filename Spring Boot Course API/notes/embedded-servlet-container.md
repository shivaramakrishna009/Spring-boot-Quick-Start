## Embedded Tomcat Servlet Container in Spring Boot

Spring Boot applications typically run with an **embedded servlet container**, meaning you don’t need to deploy your app as a WAR file on an external Tomcat server. Instead, **Tomcat is packaged inside your application** (along with Jetty or Undertow as alternatives).

### Key Points:
- By default, **Spring Boot uses Tomcat** as the embedded servlet container.
- This makes applications **self-contained** and runnable via `java -jar`.
- You don’t need to install or configure Tomcat separately.
- Supports easy **microservice deployment** and **containerization (Docker/Kubernetes)**.

### Example Dependency
If you include the Web starter, Tomcat is automatically brought in:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
