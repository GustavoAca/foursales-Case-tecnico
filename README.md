# Desafio Técnico - Desenvolvedor Pleno

## Passo a passo para execução

### Dump banco de dados

O arquivo se encontra na pasta `docker`

### Execução de endpoints

Decidi criar a estrutura pelo postman e compartilhar os endpoints, environments estão na pasta `enviroments_postman`

### Modo de exportação

Clique em importar
![img.png](imagens/img-importar.png)

Selecione arquivo
![img.png](imagens/selecionar-arquivo.png)

### Modo de execução do projeto

#### Docker

Optando por utilizar o docker, basta entrar no arquivo `docker/docker-compose.yml` e executar o container da imagem do
mysql com o comando abaixo:

```bash
docker compose up -d mysql 
```

Com isso, temos a conexão do banco disponivel, então iremos executar o projeto

#### Intelij
<br/>

Configuration

![img.png](imagens/img-opcao-intelij.png)

Criando application
<br/>
![img.png](imagens/criando-application.png)

Configurando application e executando
![img.png](img.png)

Assim deve iniciar o projeto corretamente

## Usuarios cadastrados

ADMIN -

``` 
email: galasdalas1@gmail.com
senha: 1234
```

USER -

``` 
email: galasdalas2@gmail.com
senha: 1234
```