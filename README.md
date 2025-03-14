# Projeto Spring Boot - Transação

Este projeto implementa um desafio de transações utilizando o Spring Boot e Kafka, com integração ao MySQL e OAuth2.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para criação de aplicações Java.
- **Spring Data JPA**: Para interagir com bancos de dados relacionais usando JPA.
- **Spring Web**: Para criar uma API REST.
- **Spring Cloud Stream Kafka**: Para integração com o Apache Kafka.
- **OAuth2**: Para autenticação e autorização.
- **MySQL**: Banco de dados relacional para persistência.
- **H2 Database**: Banco de dados em memória para testes.

## Requisitos

- Java 11
- Apache Maven
- MySQL (para ambiente de produção)

## Como Rodar o Projeto

1. **Clonando o Repositório**

   Clone o repositório com o seguinte comando:

   ```bash
   git clone https://github.com/usuario/transacao.git
   cd transacao
   
### Compilando o projeto
./mvnw clean install

### Executando o projeot
./mvnw spring-boot:run

### Executando os testes
./mvnw test
