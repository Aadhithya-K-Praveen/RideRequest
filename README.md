
# Ride Request Application using SpringBoot w/Postgres

A simple and intuitive application enables a user to request for a cab ride.


## API Reference

#### Get all items

```http
  GET /rides
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| None | None | None |


#### Search for rides 

```http
  GET /rides/search
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Optional**. Id of a ride to search|
| `source`      | `String` | **Optional**. Pickup point of rides to search|
| `destination`      | `String` | **Optional**. Drop point of rides to search|
| `name`      | `String` | **Optional**. Gives all the rides from a particular customer|


#### Edit a ride detail
```http
  PUT /rides/edit
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Id of a ride to edit a ride|

| Request Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `String` | **Optional**. Name of the customer|
| `source`      | `String` | **Optional**. Source of the ride|
| `destination`      | `String` | **Optional**. Destination of the ride|
| `time`      | `String` | **Optional**. Time of the ride|
| `date`      | `String` | **Optional**. Date of the ride|



#### Request a ride 
```http
  POST /rides/request
```

| Request Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `String` | **Required**. Name of the customer|
| `source`      | `String` | **Required**. Source of the ride|
| `destination`      | `String` | **Required**. Destination of the ride|
| `time`      | `String` | **Required**. Time of the ride|
| `date`      | `String` | **Required**. Date of the ride|



#### Cancel a ride

```http
  DELETE /rides/cancel
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| id | `Long` | **Required** Id of a ride to be cancelled |


#### Delete all the ride

```http
  DELETE /rides/cancelAll
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| None | None | None|



 


## Documentation

[Spring Boot](https://spring.io/guides/gs/spring-boot/)

[Spring Boot with Postgres](https://www.javaguides.net/2021/08/spring-boot-postgresql-crud-example.html)

[Postgresql](https://www.postgresql.org/)

[JOOQ](https://www.jooq.org/)
