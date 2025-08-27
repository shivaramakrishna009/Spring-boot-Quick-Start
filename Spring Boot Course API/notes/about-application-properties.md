## `application.properties` in Spring Boot

Spring Boot uses the `application.properties` (or `application.yml`) file to **externalize configuration**.  
This allows you to configure your app without modifying Java code, enabling easier **environment-specific setups**.

---

### 🔧 Common Use Cases

- **Server Configuration**
  ```properties
  server.port=8081
  server.servlet.context-path=/myapp
  ```

## 🔗 Official Reference

For the full list of available properties, refer to the official Spring documentation:  
[Spring Boot - Common Application Properties](https://docs.spring.io/spring-boot/appendix/application-properties/index.html)
