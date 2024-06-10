# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :

```json
{
  "username" : "pangaribuan",
  "password" : "rahasia",
  "name" : "Jerry Andrianto Pangaribuan"
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username must not blank, ???"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "pangaribuan",
  "password" : "rahasia"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 227362736 // miliseconds
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username or password wrong"
}
```

## Update User

Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "name" : "Jerry Andrianto Pangaribuan",
  "password" : "new password"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "username" : "pangaribuan",
    "name" : "Jerry Andrianto Pangaribuan"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "username" : "pangaribuan",
    "name" : "Jerry Andrianto Pangaribuan"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK" 
}
```