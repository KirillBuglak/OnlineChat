<h1 align="center">Групповой чат</h1>

----
## Stack
Java, Spring Boot, Maven, JDBC, Hibernate, SQL.
____
## Краткое описание
Функциональность данного Spring Boot приложение содержит:
<li>осуществление регистрации/входа;</li>
<li>просмотр сообщений;</li>
<li>создание и отправка сообщений.</li>

____
# Интерфейс
## Форма входа/регистрации
<p align="center">
<img src="imagesForReadme/1.png"></p>

## Общий вид
<p align="center">
<img src="imagesForReadme/2.png"></p>
<p align="center">
<img src="imagesForReadme/3.png"></p>

___
## API
___
### GET /init
<details>
<summary>JSON Response</summary> 

{\
&emsp;'result': true\
}
</details> 

### GET /message
<details>
<summary>JSON Response</summary> 

[\
&emsp;{\
&emsp;&emsp;'text': "Привет",\
&emsp;&emsp;'username': "Кирилл",\
&emsp;&emsp;'datetime': "2023-02-22 13:13:11"\
&emsp;},\
&emsp;...\
]
</details> 

### POST /message
<details>
<summary>JSON Response</summary> 

{\
&emsp;'result': true\
}
</details> 

### POST /auth
<details>
<summary>JSON Response</summary> 

{\
&emsp;'result': true\
}
</details> 

### GET /user
<details>
<summary>JSON Response</summary> 

[\
&emsp;{\
&emsp;&emsp;'id': "4",\
&emsp;&emsp;'name': "Кирилл",\
&emsp;},\
&emsp;...\
]
</details>

___
## Как запустить
Для работы приложения необходимо:
<li>установить MySQL (8.0);</li>
<li>настроить соединение с базой данных по конфигурации - application.yaml.</li>

```
server:
  port: 8080

spring:
  datasource:
    username: root
    password: password
    url: jdbc:mysql://localhost:3306/mychat?useSSL=false&requireSSL=false&allowPublicKeyRetrieval=true
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
```
____