# Product and Category API 📦

> *Read this in [Portuguese](README-pt.md)*

A robust REST API built with Java and Spring Boot to manage product registration and inventory, as well as their categories. This system provides complete CRUD operations, advanced search capabilities, and data validation, simulating the backend of a real corporate inventory.

## 🚀 Technologies Used

* **Java**
* **Spring Boot** (Web, Data JPA)
* **Jakarta Validation** (for input data validation)
* **Lombok** (to reduce boilerplate code)
* **Maven** (Dependency management)

## 🛠️ Architecture and Patterns

The project was structured following a layered pattern to ensure separation of responsibilities:

* **Controllers:** Input layer that manages HTTP requests (GET, POST, PUT, DELETE).
* **Services:** Layer where business logic, rules, and validations reside.
* **Repositories:** Direct communication with the database using Spring Data JPA and custom Query Methods (such as searches by name prefix).
* **DTOs (Data Transfer Objects):** Standardization of data traffic, ensuring security by not exposing the real database entities.
* **Models/Entities:** Representation of the database tables mapped with the appropriate relationships (One-to-Many between Category and Product).

## ✨ Main Features

* **DTO (Data Transfer Objects) Standard:** Encapsulation of entities to avoid exposing the database to the client.
* **Intelligent Search and Filters:** Find products by exact ID, filter by Category ID, or perform a partial search by Name (prefix search algorithm).
* **Business Rule Validation:**
    * Prevents the registration of categories with duplicate names.
    * Prevents the deletion of a category that still has linked products.
    * Validates the existence of the category before creating or updating any product.
* **Centralized Error Handling:** Clean and friendly JSON error responses via custom exceptions and `@RestControllerAdvice`.
* **Standardized HTTP Responses:** Correct use of semantic status codes (`200 OK`, `201 Created` with `Location` Headers, `204 No Content`).
* **Performance:** Strategic use of `@Transactional(readOnly = true)` to optimize database read operations.

## 🛣️ API Endpoints

### 🗂️ Categories
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/categories` | Lists all categories |
| `GET` | `/categories/{id}` | Searches for a specific category by ID |
| `POST` | `/categories` | Creates a new category |
| `PUT` | `/categories/{id}` | Updates an existing category |
| `DELETE` | `/categories/{id}` | Deletes a category |

### 🏷️ Products
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/products` | Lists all products |
| `GET` | `/products/{id}` | Searches for a specific product by ID |
| `GET` | `/products/category/{id}` | Lists all products in a Category |
| `GET` | `/products/search?name=` | Search for products by name prefix |
| `POST` | `/products` | Create a new product |
| `PUT` | `/products/{id}` | Update an existing product |
| `DELETE` | `/products/{id}` | Delete a product |

## 🛡️ Error Handling Example

The application uses a Global Exception Handler, ensuring that any error returns a standardized JSON and a semantic HTTP status code:

```json
{
  "status":  409,
  "message": "Category name already registered"
}
```

## 📜 Development Practices

* Conventional Commits: Organized and semantic commit history for better project traceability (e.g., feat:, fix:, docs:).

* RESTful Pattern: Correct use of HTTP semantics.

## 🛠️  How to run

- Clone the git repository:
```bash
git clone https://github.com/giovannithamasia/product-category-api.git
```
- Build the project:
```
$ ./mvnw clean package
```
- Run the application:
```
$ java -jar target/crud-system-0.0.1-SNAPSHOT.jar
```

The API can be accessed at [localhost:8082](http://localhost:8082)

Swagger can be viewed at [localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)