# Kaspper - TestCase

Simple API(Rest) for password validation following specifications
#### Tecnologias
* Java 11 
* Springboot 2.5.2
* Lombok
* Junit
* Swagger
* Docker
#### Solução
A solução consiste num arquitetura simples de projeto, definidas por camada.
As principais funções da api consistem em salvar ou atualizar um objeto, usei o mesmo endpoint para isso.
Listar baseado em paginação.
Lister usando filtros de todos os campos envolvidos na entidade. Para isso usei alguns recursos como reflection e recursão.

Usei uma classe a ***ApiExceptionHandlerAdvice*** para capturar as exceptions das chamadas ou classes que sejam anotadas com ***@RestController***
Com isso consigo gerar um feedback mais amigável para o consumidor da API.

####### Obs
```bash
O campo curriculum, antes de inserir, precisa converter para base64 o arquivo em questão
```

## Instalação
```bash
git clone https://github.com/Arthur-Diego/kaspper-test.git

cd builders-testcase/

./mvnw clean package -DskipTests && docker-compose up --build
```

## Uso
```bash
http://localhost:8080/swagger-ui.html#
```

