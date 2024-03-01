# User Microservice

This User Microservice project serves as a user system, offering functionalities such as user creation, login, update, and retrieval from a database.

It was part of `Challenge - 3` from my internship at [compassuol](https://compass.uol/pt/home/)

| Technologies Used  |            |
|--------------------|------------|
| Spring Boot        |            |
| Java               |            |
| OpenFeign          |            |
| Swagger            |            |

| Additional Tools   |            |
|--------------------|------------|
| MySQL              |            |
| RabbitMQ           |            |

Contributors: Vinicius Guerra

## Features
- User saving and handling
- OpenFeign support
- RabbitMq support
- External API calls

## How to Use

1. **Setup Environment:**
    - Ensure you have Java and Maven installed on your system.

2. **Clone the Repository:**
   ```bash
   git clone <repository_url>

3. **Setup Database:**
    - You might want to look into `applications.yml` to make changes to the port and set up your database.

### Add User

Creates a new user in the system.

```http
POST http://localhost:8080/v1/users
Content-Type: application/json

{
   "firstName": "nome",
   "lastName": "sobrenome",
   "cpf": "000.000.000-00",
   "birthdate": "0000-00-00",
   "email": "teste@gmail.com",
   "cep": "00000-000",
   "password" : "12345678",
   "active": true
}
```

### Login

To log in a new user in the system and obtain a JWT token
```http
POST http://localhost:8080/v1/login
Content-Type: application/json

{
    "email" : "teste@gmail.com",
    "password" : "12345678"
}
```

### Get by id

Returns fields from a specific user in the database

```http
GET http://localhost:8080/v1/users/2
Authorization: Bearer {token}
```


### Update Information

Lets you update most fields of a user

```http
PUT http://localhost:8080/v1/users/2
Authorization: Bearer {token}
Content-Type: application/json

{
    "firstName": "nome",
    "lastName": "sobrenome",
    "cpf": "000.000.000-00",
    "birthdate": "0000-00-00",
    "email": "teste@gmail.com",
    "cep": "00000-000",
    "active": true
}
```

### Update Password

Lets you update the password of a user

```http
PUT http://localhost:8080/v1/users/2/password
Authorization: Bearer {token}
{
    "password": "12345678"
}
```

## Documentation

The `swagger` documentation can be found here:
```http
 http://localhost:8080/swagger-ui/index.html

```


