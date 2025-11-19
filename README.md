## 🎓 Universidade API: Gerenciamento de Alunos e Cursos

Este é um projeto de **API REST** simples e eficiente, desenvolvido com **Spring Boot**, focado no gerenciamento das entidades **Alunos** e **Cursos** em um ambiente universitário. A persistência dos dados é realizada utilizando o banco de dados relacional **MariaDB**.

-----

### 🧠 Stack Tecnológica

| Componente | Detalhe |
| :--- | :--- |
| **Framework** | Spring Boot |
| **Linguagem** | Java |
| **Banco de Dados** | MariaDB |
| **Persistência** | Spring Data JPA (Hibernate) |
| **Ferramenta de Build** | Maven |
| **Auxiliar** | Lombok |

### ⚙️ Guia de Execução

Para colocar a API em funcionamento, siga os passos abaixo:

#### 1\. Configuração do Banco de Dados

1.  **Criação do Schema:** Certifique-se de que o schema `universidade` esteja criado em sua instância MariaDB ou ajuste o nome do schema no `application.properties`.
2.  **Credenciais:** Edite o arquivo `src/main/resources/application.properties` e atualize as configurações de usuário e senha do MariaDB.
    > 💡 **Nota:** O Hibernate está configurado via `spring.jpa.hibernate.ddl-auto=update` para criar ou atualizar automaticamente as tabelas (`Alunos` e `Cursos`).

#### 2\. Compilação e Inicialização

Execute os seguintes comandos no terminal, na pasta raiz do projeto:

```bash
# 1. Limpa, compila e empacota o projeto
mvn clean package

# 2. Inicia a aplicação Spring Boot
mvn spring-boot:run
```

A API estará online e acessível em: `http://localhost:8080`.

-----

### 🌐 Endpoints da API REST (CRUD)

Os endpoints seguem o padrão RESTful e estão prefixados com `/api/`.

#### 📚 Rotas de Cursos (`/api/cursos`)

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| **`POST`** | `/api/cursos` | Cria um novo curso. |
| **`GET`** | `/api/cursos` | Lista todos os cursos. |
| **`GET`** | `/api/cursos/{id}` | Busca um curso pelo ID. |
| **`PUT`** | `/api/cursos/{id}` | Atualiza um curso existente. |
| **`DELETE`** | `/api/cursos/{id}` | Deleta um curso. |

#### 🧑‍🎓 Rotas de Alunos (`/api/alunos`)

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| **`POST`** | `/api/alunos` | Cria um novo aluno (requer `cursoId` para vínculo). |
| **`GET`** | `/api/alunos` | Lista todos os alunos. |
| **`GET`** | `/api/alunos/{id}` | Busca um aluno pelo ID. |
| **`PUT`** | `/api/alunos/{id}` | Atualiza um aluno existente. |
| **`DELETE`** | `/api/alunos/{id}` | Deleta um aluno. |

### 🧩 Exemplos de Uso (cURL)

#### 1\. Criar um novo Curso

```bash
curl -X POST http://localhost:8080/api/cursos -H "Content-Type: application/json" -d '{"nome":"Engenharia","cargaHoraria":4000}'
```

#### 2\. Criar um novo Aluno (Vinculando ao Curso de ID 1)

```bash
curl -X POST http://localhost:8080/api/alunos -H "Content-Type: application/json" -d '{"nome":"João","email":"joao@ex.com","dataNascimento":"2000-01-01","cursoId":1}'
```

-----

### ⚠️ Observações de Desenvolvimento

  * **Lombok:** O projeto faz uso intenso do Lombok para simplificar o código (getters, setters, construtores). Certifique-se de que sua IDE (VS Code, IntelliJ, etc.) tem o plugin Lombok instalado para evitar erros de compilação na interface.

-----
**Arthur Fernandes**
