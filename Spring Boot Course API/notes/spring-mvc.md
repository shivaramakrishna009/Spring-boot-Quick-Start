## How Spring MVC Works

Spring MVC (Model-View-Controller) is a **web framework** in the Spring ecosystem. It helps build web applications by separating **concerns** into three layers:
- **Model** → Application data and business logic
- **View** → UI layer (JSP, Thymeleaf, JSON, etc.)
- **Controller** → Handles requests and responses

---

### Workflow of Spring MVC

1. **Client Request**
    - The user sends an HTTP request to the server.

2. **DispatcherServlet (Front Controller)**
    - Acts as the **central entry point** for all web requests.
    - Configured in `web.xml` (classic) or autoconfigured in Spring Boot.
    - Delegates request handling to appropriate controllers.

3. **Handler Mapping**
    - DispatcherServlet consults **Handler Mappings** to find the correct `@Controller` and `@RequestMapping` method.

4. **Controller**
    - The chosen Controller processes the request.
    - It interacts with the **Model** (business/data layer).
    - Returns a **ModelAndView** object (data + view name).

5. **View Resolver**
    - DispatcherServlet uses the **ViewResolver** to map the logical view name to an actual view (JSP, Thymeleaf, JSON, etc.).

6. **Render View**
    - The view is rendered with the **model data**.
    - The response is sent back to the client.

---

### MVC Flow Diagram (Mermaid)

```mermaid
sequenceDiagram
    participant Client
    participant DispatcherServlet
    participant HandlerMapping
    participant Controller
    participant Model
    participant ViewResolver
    participant View

    Client->>DispatcherServlet: Sends HTTP Request
    DispatcherServlet->>HandlerMapping: Find handler
    HandlerMapping-->>DispatcherServlet: Return Controller
    DispatcherServlet->>Controller: Call handler method
    Controller->>Model: Process business logic
    Controller-->>DispatcherServlet: Return ModelAndView
    DispatcherServlet->>ViewResolver: Resolve view name
    ViewResolver-->>DispatcherServlet: Return View
    DispatcherServlet->>View: Render with Model
    View-->>Client: Send Response
