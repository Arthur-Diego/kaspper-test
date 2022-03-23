# Kaspper - TestCase

#### Tecnologias
* Java 11 
* Springboot 2.5.2
* Lombok
* Junit
* Swagger
* Docker
#### Solução
A solução consiste num arquitetura simples de projeto, definidas por camada (Package by layer).

Listar baseado em paginação.
Lister usando filtros de todos os campos envolvidos na entidade. Para isso usei alguns recursos como reflection.

Usei uma classe a ***ApiExceptionHandlerAdvice*** para capturar as exceptions das chamadas ou classes que sejam anotadas com ***@RestController***
Com isso consigo gerar um feedback mais amigável para o consumidor da API.

###### Obs
```bash
O campo curriculum, antes de inserir, precisa converter para base64 o arquivo em questão
```

## Instalação
```bash
1 - Necessário JRE 11 e docker instalado.

git clone https://github.com/Arthur-Diego/kaspper-test.git

cd kaspper-test/

./mvnw clean package -DskipTests && docker-compose up --build
```

## Uso
```bash
http://localhost:8080/swagger-ui.html#
```

