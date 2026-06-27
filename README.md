# Agendamento de Transferencias

Sistema de agendamento de transferencias financeiras desenvolvido como avaliação técnica

## Tecnologias

### Backend

- Java 11
- Spring Boot 2.7.2
- Spring Data JPA 
- H2 Database
- Lombok

### Frontend

- Angular 20
- Angular Material

## Regras de Negócio

- Agendamento de transferências financeiras
- Cálculo automático da taxa conforme prazo da transferência
- Consulta de agendamentos realizados

## Decisões Arquiteturais

### Backend

O Backend foi estruturado seguindo uma arquitetura por feature, com suas camadas agrupadas dentro de um mesmo pacote, visando coesão entre os componentes relacionados ao mesmo domínio, separação de responsabilidades e facilidade de evolução do sistema.

- **Controller:** responsável por receber as requisições HTTP, validar entradas e retornar as respostas adequadas.
- **Service:** responsável pela orquestração dos casos de uso da aplicação.
- **Repository:** responsável pelo acesso aos dados utilizando Spring Data JPA.
- **DTOs:** utilizados para definir os contratos de entrada e saída da API, evitando exposição direta das entidades de persistência.

### Encapsulamento das regras de negócio

As regras de cálculo de taxas foram isoladas na enum `TaxRule`, que possui comportamento próprio para identificar a regra aplicável e realizar o cálculo da tarifa.

Essa abordagem evita que o serviço principal fique responsável por múltiplas condições de negócio e facilita a manutenção caso novas faixas de transferência ou alterações de cálculo sejam necessárias.

O `TransferService` permanece responsável pelo fluxo do processo de criação da transferência, enquanto a definição de como a taxa é calculada fica concentrada na própria regra de negócio.


### Tratamento de valores financeiros

Os valores monetários foram implementados utilizando `BigDecimal`, evitando problemas de precisão comuns em operações financeiras realizadas com tipos de ponto flutuante.

O cálculo das taxas utiliza `RoundingMode.HALF_UP` para garantir consistência no arredondamento dos valores calculados.


### Mapeamento de objetos

Foi utilizada uma camada de mapeamento através de `TransferMapper`, mantendo a separação entre as entidades utilizadas na persistência e os objetos expostos pela API.

Essa decisão reduz o acoplamento entre banco de dados e contratos externos, permitindo alterações internas sem impacto direto nos consumidores da API.

### 🛡️ Tratamento de Erros e Validação de Taxas

Conforme a especificação do teste (*"Caso não haja taxa aplicável, lançar um alerta sobre o erro e não permitir transferência"*), o fluxo de exceção foi tratado de forma centralizada e elegante:

1. **Exceção de Negócio Dedicada:** Foi criada a classe `NoApplicableTaxException`, que estende `RuntimeException` e encapsula a lógica de formatação da mensagem dinâmica, recebendo os dias de antecedência inválidos.
2. **Interceptação Global (Backend):** Utilizou-se o padrão `@RestControllerAdvice` através da classe `GlobalExceptionHandler`. O método anotado com `@ExceptionHandler(NoApplicableTaxException.class)` intercepta o erro em qualquer ponto da camada de controle, devolvendo um HTTP Status `400 Bad Request` com o texto amigável diretamente no corpo (`body`) da resposta.
3. **Feedback no Frontend (Angular):** O componente `Transfers` captura esse corpo de erro síncronamente no bloco `error` do `.subscribe()` e exibe o alerta gerado pelo backend em tempo real na tela do usuário por meio do `MatSnackBar`.


## Execução

### Pré-requisitos

- Java 11
- Maven 3.8+
- Node.js 20+
- Angular CLI 20+

---

### Backend

Acesse a pasta do backend:

```bash
cd backend
```

```bash
./mvnw spring-boot:run
```

Ou, caso o Maven esteja instalado:

```bash
mvn spring-boot:run
```

O Backend será iniciado por padrão em:
```bash
http://localhost:8080
```

A aplicação utiliza banco H2 em memória, portanto não é necessário realizar nenhuma configuração adicional de banco de dados.

### Frontend

Acesse a pasta do frontend:
```bash
cd frontend
```
Instale as dependencias:
```bash
npm install
```

Execute a aplicação Angular:
```bash
ng serve
```

O Frontend estará disponível em:
```bash
http://localhost:4200
```

### Fluxo de Execução
1. Inicie o backend Spring Boot.
2. Inicie o frontend Angular.
3. Acesse a aplicação pelo navegador através do endereço:

## Autor

Bruno Queiroz