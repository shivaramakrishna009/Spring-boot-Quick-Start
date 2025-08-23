# `@Service` and `@Autowired` in Spring

## 1. `@Service`
- `@Service` is a **Spring stereotype annotation**.
- It is used to mark a **class as a Service component** in the Service Layer.
- It tells Spring that this class holds **business logic**.
- Spring will automatically detect this class during **component scanning** and register it as a Spring Bean.

### Example
```java
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public void processPayment() {
        System.out.println("Processing payment...");
    }
}
```

## 2 `@Autowired` in Spring

## Overview
- `@Autowired` is an annotation in Spring used for **automatic dependency injection (DI)**.
- It tells Spring to **resolve and inject** the required bean automatically.
- By default, Spring performs **injection by type** (matches the class type of the dependency).

---

## Where Can It Be Used?
1. **On Fields** → Directly injects a bean into a class variable.
2. **On Setters** → Injects a bean through a setter method.
3. **On Constructors** → Injects a bean through the constructor (recommended approach).

---

## Examples

### 1. Field Injection
```java
@Component
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    public void placeOrder() {
        paymentService.processPayment();
    }
}
```
### 2. Setter Injection
```java
@Component
public class OrderService {
    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment();
    }
}
```
### 3. Constructor Injection (Recommended)
```java
@Component
public class OrderService {
    private final PaymentService paymentService;
    @Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    public void placeOrder() {
        paymentService.processPayment();
    }
}
```

### Summary

- @Autowired enables automatic dependency injection.
- Can be applied on fields, setters, or constructors.
- Helps reduce boilerplate code and promotes loose coupling in Spring applications.