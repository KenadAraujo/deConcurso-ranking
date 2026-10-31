# Executar com Docker Compose

Requisito: Docker com o plugin Compose (por exemplo, Docker Desktop).
Não é necessário instalar Java, Maven ou PostgreSQL na máquina.

A partir da raiz do projeto:

```sh
cp docker/.env.example docker/.env
```

Edite `docker/.env` e preencha `POSTGRES_PASSWORD` com uma senha.
O arquivo é ignorado pelo Git. Se a senha contiver `$`, coloque o valor
entre aspas simples para evitar interpolação pelo Compose.

```sh
docker compose --env-file docker/.env -f docker/docker-compose.yaml up --build -d --wait
```

O Compose compila a aplicação com Maven e Java 21, inicia o PostgreSQL 17 e
aguarda o banco ficar disponível antes de iniciar o Spring Boot. O Flyway
executa as migrações automaticamente; não é necessário importar SQL.
Os testes não são executados durante a construção da imagem, pois o teste
de contexto Spring depende de um banco disponível.

| Recurso | Endereço padrão |
| --- | --- |
| API | http://localhost:8080/organizadora |
| Swagger UI | http://localhost:8080/docs |
| OpenAPI | http://localhost:8080/api-docs |
| PostgreSQL | localhost:5432 (banco `ranking`, usuário `admin`) |

As portas ficam acessíveis somente na máquina local. Ajuste `APP_PORT` ou
`POSTGRES_PORT` no arquivo `.env` se houver conflito com serviços locais.
A aplicação sempre acessa o banco pela rede interna, em `postgres:5432`,
independentemente da porta publicada.

Para consultar o estado, acompanhar os logs e parar os serviços:

```sh
docker compose --env-file docker/.env -f docker/docker-compose.yaml ps
docker compose --env-file docker/.env -f docker/docker-compose.yaml logs -f ranking
docker compose --env-file docker/.env -f docker/docker-compose.yaml down
```

Os dados permanecem no volume `postgres_data` após o `down`. As variáveis
`POSTGRES_DB`, `POSTGRES_USER` e `POSTGRES_PASSWORD` inicializam apenas um
volume vazio; alterá-las não modifica um banco já criado.

Para apagar o banco e reiniciar do zero (**remove todos os dados**):

```sh
docker compose --env-file docker/.env -f docker/docker-compose.yaml down -v
```

Esta configuração é voltada à execução local. Para produção, utilize gestão
de segredos, backups e uma política de exposição de rede apropriada.
