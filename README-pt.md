# API de Produtos e Categorias 📦

> *Read this in [English](README.md)*

Uma API REST robusta construída com Java e Spring Boot para gerenciar o cadastro e o estoque de produtos e suas categorias. Este sistema fornece operações CRUD completas, capacidades avançadas de busca e validação de dados, simulando o backend de um estoque corporativo real.

## 🚀 Tecnologias Utilizadas

* **Java**
* **Spring Boot** (Web, Data JPA)
* **Jakarta Validation** (para validação de dados de entrada)
* **Lombok** (para reduzir código boilerplate)
* **Maven** (Gerenciamento de dependências)

## 🛠️ Arquitetura e Padrões

O projeto foi estruturado seguindo o padrão de camadas para garantir a separação de responsabilidades:

* **Controllers:** Camada de entrada que gerencia as requisições HTTP (GET, POST, PUT, DELETE).
* **Services:** Camada onde a lógica de negócio, regras e validações residem.
* **Repositories:** Comunicação direta com o banco de dados utilizando Spring Data JPA e Query Methods customizadas (como buscas por prefixo de nome).
* **DTOs (Data Transfer Objects):** Padronização do tráfego de dados, garantindo a segurança ao não expor as entidades reais do banco de dados.
* **Models/Entidades:** Representação das tabelas do banco de dados mapeadas com os devidos relacionamentos (One-to-Many entre Categoria e Produto).

## ✨ Principais Funcionalidades

* **Padrão DTO (Data Transfer Objects):** Encapsulamento das entidades para evitar a exposição do banco de dados ao cliente.
* **Busca Inteligente e Filtros:** Encontre produtos pelo ID exato, filtre pelo ID da Categoria ou faça uma busca parcial pelo Nome (algoritmo de busca por prefixo).
* **Validação de Regras de Negócio:**
    * Impede o cadastro de categorias com nomes duplicados.
    * Impede a exclusão de uma categoria que ainda possui produtos vinculados.
    * Valida a existência da categoria antes de criar ou atualizar qualquer produto.
* **Tratamento Centralizado de Erros:** Respostas de erro em JSON limpas e amigáveis via exceções customizadas e `@RestControllerAdvice`.
* **Respostas HTTP Padronizadas:** Uso correto dos status codes semânticos (`200 OK`, `201 Created` com Headers `Location`, `204 No Content`).
* **Performance:** Uso estratégico do `@Transactional(readOnly = true)` para otimizar as operações de leitura no banco de dados.

## 🛣️ Endpoints da API

### 🗂️ Categorias
| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/categories` | Lista todas as categorias |
| `GET` | `/categories/{id}` | Busca uma categoria específica pelo ID |
| `POST` | `/categories` | Cria uma nova categoria |
| `PUT` | `/categories/{id}` | Atualiza uma categoria existente |
| `DELETE` | `/categories/{id}` | Deleta uma categoria |

### 🏷️ Produtos
| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/products` | Lista todos os produtos |
| `GET` | `/products/{id}` | Busca um produto específico pelo ID |
| `GET` | `/products/category/{id}` | Lista todos os produtos de uma Categoria |
| `GET` | `/products/search?name=` | Busca produtos pelo prefixo do nome |
| `POST` | `/products` | Cria um novo produto |
| `PUT` | `/products/{id}` | Atualiza um produto existente |
| `DELETE` | `/products/{id}` | Deleta um produto |

## 🛡️ Exemplo de Tratamento de Erros

A aplicação utiliza um Manipulador Global de Exceções (Global Exception Handler), garantindo que qualquer erro retorne um JSON padronizado e um status code HTTP semântico:

```json
{
  "status": 409,
  "message": "Category name already registered"
}
```

## 📜 Práticas de Desenvolvimento

* Conventional Commits: Histórico de commits organizado e semântico para melhor rastreabilidade do projeto (ex: feat:, fix:, docs:).

* Padrão RESTful: Uso correto da semântica HTTP.

## 🛠️  Como Executar

- Clonar repositório git
```bash
git clone https://github.com/giovannithamasia/product-category-api.git
```
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/crud-system-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8082](http://localhost:8082)

O Swagger poderá ser visualizado em [localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

