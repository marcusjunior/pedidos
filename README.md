# API de Pedidos

Esta é uma API para criação e consulta de pedidos

## Tecnologias utilizadas

* Java 1.8
* MySQL 8.0.15
* Spring Boot 2.2.5
* Hibernate 5
* Lombok
* Swagger
* JUnit

## Instalação

Antes de realizar o build do projeto, é necessário ajustar a configuração do banco de dados.

O projeto já está configurado para utilizar um banco sandbox de demonstração configurado na AWS.

URL do banco:

```pedidos.ceiqbsjm6j7u.sa-east-1.rds.amazonaws.com```

Para configurar um novo banco na aplicação basta atualizar a url e as credenciais do banco no arquivo:

```application.properties```

Após isso, basta realizar o build do projeto e toda a estrutura de DDL será criada automaticamente pelo Hibernate:

```mvn clean install```

## Utilização

Para executar a aplicação, basta executar o arquivo: 

```ClientesApplication.java```

Após o servidor ser iniciado, a API está disponível para testes via swagger na url:

[http://localhost:8080/swagger-ui.html]

Os endpoints disponíveis na API são:

* `POST - /v1/pedido` - cria uma novo pedido indivisdual

* `GET - /v1/pedidos` - consuta pedidos

* `GET - /v1/pedidos/{id}` - consulta pedidos por id

* `POST - /v1/pedidos/lote` - cria pedidos em lote, limitado a 10 pedidos por arquivo

É possível chamar todos serviços tanto no formato JSON quanto XML.

Há também um arquivo Postman disponível na raiz do projeto com algumas requests de exemplo.

Para executar os testes unitários, basta executar o comando:

`mvn clean test`

## Autor

Marcus Júnior
