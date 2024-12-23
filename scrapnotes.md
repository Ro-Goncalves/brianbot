Opções adicionais:

docker-compose up (sem -d) para ver os logs em primeiro plano
docker-compose down para parar e remover os containers
docker-compose ps para ver o status dos containers
docker-compose logs para ver os logs
docker-compose logs [nome-do-serviço] para logs de um serviço específico

Após subir, você poderá acessar:

API: http://localhost:8080
PgAdmin: http://localhost:4000
Postgres: localhost:5432
Redis: localhost:6379

Dica: Sempre verifique se todos os containers subiram corretamente com docker-compose ps ou docker ps.

## PROMPTS

Me ajude e refatorar esse código aí.

Quero criar um projeto que utilize DDD e Port and Adapter. Para isso ele deve conter 3 pastas

Application -> Aqui fica a home da API, só retorna um bem vindo
Domain -> Ficam os dominios.
Infrastruture -> Fica a criação das classes, configuração do spring
Cada dominio deve ter a seguinte estrutura

Aplication -> Camada em que encontraremos a controller
Core -> Camada que cotém a regra de negócio.
Infrastructure -> Camada que o dominío usa para se comunicar com o mundo exterior. Cotém a classe que implemente as intrefaces de saída
Dentro de Core encontramos model -> diretório com as classe de modelo port/incoming -> interfaces de entrata port/outcoing -> interfaces de saída Classe DomainFacade, implementa as intefaces de entrada.

Tendo isso em mente, refatore postMessagesUpsert(@RequestBody JsonNode payload) para se enquadrar nessa estrutura.
