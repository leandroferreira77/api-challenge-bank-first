# API Challenge upload de arquivo .xlsx para banco de dados H2 e cadastro de clientes e contas - Bank F1rst 

Intrucoes a seguir:
> Api criada usando JDK 8 

> Banco de dados H2

> Spring Boot versao 2.6.2

> Maven para organizacao das Bibliotecas

## Executar Spring Boot, digite o comando abaixo na pasta do projeto
```
mvn spring-boot:run
```

Importação de arquivo xlsx:

> Usar o Postman para envio do arquivo, method POST 
> acesse a URL [/v1/api/upload](http://localhost:8080/v1/api/upload)

>No Body altere a Key para tipo (file) e coloque a chave com o mesmo nome (file) 

>Selecionar o aquivo desejado para importação

Criação e Manutenção de clientes ou busca pelo numero da conta:
> Basta acessar o swagger da API na seguinte URL
> [Swagger](http://localhost:8080/swagger-ui/index.html)



