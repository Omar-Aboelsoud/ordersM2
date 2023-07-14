This API provides endpoints for managing orders and performing calculations.

## Prerequisites

- Java 17
- Maven
- Postman (for testing the API)

## Getting Started

1. Clone the repository:
   ```git clone <repository_url>```
2. Build the project:
   ```mvn clean install```
3. Run the application:

The API will be available at `http://localhost:8080`.

## Test case
 To run the unit tests, use the following command:
 ```mvn test```
## API Endpoints

**Your API Collection.postman_collection.json:**

```json
{
  "info": {
    "name": "Your API Collection",
    "description": "Collection of APIs for managing orders",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Create Order",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"id\": 123,\n  \"status\": \"open\",\n  \"quantity\": 10,\n  \"currency\": {\n    \"name\": \"BTC\",\n    \"currentUsdPrice\": 40000\n  },\n  \"creationDate\": \"2022-01-01T12:00:00Z\"\n}"
        },
        "url": {
          "raw": "http://localhost:8080/orders",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "orders"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Find Order by ID",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/orders/123",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "orders",
            "123"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Find Orders",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/orders?status=open&startDate=2022-01-01T00:00:00Z&endDate=2022-12-31T23:59:59Z",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "orders"
          ],
          "query": [
            {
              "key": "status",
              "value": "OPEN"
            },
            {
              "key": "startDate",
              "value": "2023-01-01T00:00:00Z"
            },
            {
              "key": "endDate",
              "value": "2023-12-31T23:59:59Z"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Add Fee to Amount",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"amount\": 100,\n  \"feePercentage\": 0.1\n}"
        },
        "url": {
          "raw": "http://localhost:8080/calculate/add-fee",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "calculate",
            "add-fee"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Remove Fee to Find Amount Before Fee",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"amountWithFee\": 110,\n  \"feePercentage\": 0.1\n}"
        },
        "url": {
          "raw": "http://localhost:8080/calculate/remove-fee",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "calculate",
            "remove-fee"
          ]
        }
      },
      "response": []
    }
  ]
}
```

### Create Order

- **Endpoint**: `POST /orders`
- **Description**: Create a new order.
- **Request Body**:
    - `id`: Order ID (number)
    - `status`: Order status (string)
    - `quantity`: Order quantity (number)
    - `currency`: Order currency (object)
        - `name`: Currency name (string)
        - `currentUsdPrice`: Currency current USD price (number)
    - `creationDate`: Order creation date (string, format: ISO 8601)
- **Example Request**:

```json
{
  "quantity": 10,
  "currency": {
    "name": "BTC",
    "currentUsdPrice": 40000
  }
}
```

Example Response:

```json
{
  "id": 123,
  "creationDate": "2022-01-01T12:00:00Z"
}
```

### Find Order by ID

- **Endpoint**: `GET /orders/{orderId}`
- **Description** : Find an order by its ID.
    - ** Path Parameter**:
        - `orderId`: Order ID (number)
        - Example Request: GET /orders/123
        - Example Response:
        ```json
          {
          "status": "open",
          "quantity": 10,
          "currency": {
          "name": "BTC",
          "currentUsdPrice": 40000
          },
          "creationDate": "2022-01-01T12:00:00Z"
          }
       ```
### Find Orders
- **Endpoint**: `GET /orders`
- **Description**: Find orders filtered by status and creation date range.
  - **Query Parameters**:
      - `status (optional)`: Order status (string)
      - `startDate (optional)`: Start date of the creation date range (string, format: ISO 8601)
      - `endDate (optional)`: End date of the creation date range (string, format: ISO 8601)
      - Example Request: `GET /orders?status=open&startDate=2022-01-01T00:00:00Z&endDate=2022-12-31T23:59:59Z`
      - Example Response:

### List Orders by Status and Date Range
- **Endpoint**: `GET /orders`
- **Description**: Find orders filtered by status and creation date range.
    - **Query Parameters**:
      - `status (optional)`: Order status (string)
      - `startDate (optional)`: Start date of the creation date range (string, format: ISO 8601)
      -  `endDate (optional)`: End date of the creation date range (string, format: ISO 8601)
      - Example Request: `GET /orders?status=open&startDate=2022-01-01T00:00:00Z&endDate=2022-12-31T23:59:59Z`
      - Example Response:
        - ```json
          [{
          "id": 123,
          "status": "open",
          "quantity": 10,
          "currency": {
          "name": "BTC",
          "currentUsdPrice": 40000
          },
          "creationDate": "2022-01-01T12:00:00Z"
          },
          {
          "id": 456, 
          "status": "filled",
          "quantity": 5,
          "currency": {
          "name": "ETH",
          "currentUsdPrice": 2000
          },
          "creationDate": "2022-02-01T10:00:00Z"
          }
          ]
    ```

### Calculation Functions

I could make apis for it , but i enough wit test cases 
