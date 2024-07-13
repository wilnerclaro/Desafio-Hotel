# Desafio Hotel - FullStack

## Objetivo

Desenvolver uma aplicação backend para gerenciar o cadastro de hóspedes e realizar check-ins e check-outs.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (em memória)
- Swagger
- Maven

## Requisitos Funcionais

1. **CRUDL para o cadastro de hóspedes.**
2. **Check-in**: Buscar hóspedes cadastrados pelo nome, documento ou telefone.
3. **Consultar hóspedes que já realizaram o check-in e não estão mais no hotel.**
4. **Consultar hóspedes que ainda estão no hotel.**
5. **As consultas devem apresentar o valor total e o valor da última hospedagem já gasto pelo hóspede no hotel.**

## Regras de Negócio

1. **Diária no hotel de segunda à sexta: R$120,00.**
2. **Diária no hotel em finais de semana: R$150,00.**
3. **Acréscimo diário para vaga na garagem: R$15,00 de segunda à sexta e R$20,00 nos finais de semana.**
4. **Saída após às 16:30h: Cobrança de uma diária extra.**

## Endpoints

### Hóspedes

- **GET /api/hospedes/buscar**: Buscar hóspede por nome, documento ou telefone.
- **POST /api/hospedes/novo**: Cadastrar um novo hóspede.
- **GET /api/hospedes**: Listar todos os hóspedes.
- **PATCH /api/hospedes/atualizar**: Atualizar um hóspede.
- **DELETE /api/hospedes/delete**: Deletar um hóspede.

### Check-In

- **POST /api/checkin**: Fazer check-in.
- **POST /api/checkin/checkout/{checkInId}**: Fazer check-out.
- **GET /api/checkin/checkout**: Consultar hóspedes que já realizaram o check-in e não estão mais no hotel.
- **GET /api/checkin/hospedesNoHotel**: Consultar hóspedes que ainda estão no hotel.

## Configuração do Banco de Dados

O projeto está configurado para usar o banco de dados H2 em memória.

### application.properties

O arquivo `src/main/resources/application.properties` já está configurado para usar o H2:

## Executando o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu_usuario/desafio-hotel.git
   cd desafio-hotel


2. **Compile e rode a aplicação:**
   ```bash
   mvn clean install
   mvn spring-boot:run

3. **Acesse a documentação Swagger:**

- http://localhost:8080/swagger-ui.html

4. **Acesse o console do H2:**

- http://localhost:8080/h2-console
- Use o JDBC URL: jdbc:h2:mem:desafio_hotel, username: sa, password: (não tem senha).
