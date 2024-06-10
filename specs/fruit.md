# Fruit API Spec

## Search Fruit

Enpoint : GET /api/fruits

Query Param :
- name : String, fruit name, using like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : [
    {
      "id"    : "1sHiw29hs",
      "name"  : "Apple",
      "color" : "Red",
      "origin": "Indonesia",
      "price" : 1000000
    },
    {
      "id"    : "1sHiw30hs",
      "name"  : "Grape",
      "color" : "Purple",
      "origin": "United States",
      "price" : 2000000
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "Fruit not found"
}
```

## Create Fruit

Enpoint : POST /api/fruits

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name"  : "Apple",
  "color" : "Red",
  "origin": "Indonesia",
  "price" : 1000000
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id"    : "1sHiw29hs",
    "name"  : "Apple",
    "color" : "Red",
    "origin": "Indonesia",
    "price" : 1000000
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "name cannot be empty!"
}
```

## Update Fruit

Enpoint :

Request Header : PUT /api/fruits/{idFruit}

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name"  : "Apple",
  "color" : "Red",
  "origin": "Indonesia",
  "price" : 1000000
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id"    : "1sHiw29hs",
    "name"  : "Apple",
    "color" : "Red",
    "origin": "Indonesia",
    "price" : 1000000
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "name cannot be empty!"
}
```

## Get Fruit By Id

Enpoint : GET /api/fruits/{idFruits}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id"    : "1sHiw29hs",
    "name"  : "Apple",
    "color" : "Red",
    "origin": "Indonesia",
    "price" : 1000000
  }
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "fruit not found!"
}
```

## Delete Fruit

Enpoint : DELETE /api/fruits/{idFruits}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "fruit not found!"
}
```