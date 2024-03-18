# README

Este repositório fornece uma configuração base para iniciar um projeto em Spring Boot com Keycloak como servidor de autenticação e autorização, com a possibilidade de adicionar uma federação de usuários a um servidor LDAP para gerenciamento de usuários e grupos.

<p>
  <img src="https://gitlab.ungp.softplan.com.br/cetesb/docs/-/raw/main/Tutoriais%20Devs/images/download-kubeconfigs.jpg?ref_type=heads" alt="Diagram">
</p>

### Frameworks utilizados:

## Docker Compose
O Docker Compose é uma ferramenta que permite definir e gerenciar aplicações Docker multi-contêiner em um único arquivo de configuração. Ele simplifica o processo de definir os serviços, redes e volumes necessários para implantar uma aplicação composta por múltiplos contêineres.

## PostgreSQL
O PostgreSQL é um sistema de gerenciamento de banco de dados relacional de código aberto e altamente extensível. Neste repositório, o PostgreSQL é usado como banco de dados principal para armazenar dados persistentes. É um componente essencial para muitas aplicações que necessitam de armazenamento de dados.

## OpenLDAP e phpLDAPadmin
O OpenLDAP é um servidor de diretórios de código aberto, que implementa o protocolo LDAP (Lightweight Directory Access Protocol). Ele é usado para armazenar e gerenciar informações de diretório, como informações de usuário e grupo. O phpLDAPadmin é uma interface web para administrar servidores LDAP de forma intuitiva e simplificada. Juntos, o OpenLDAP e o phpLDAPadmin fornecem um meio poderoso de gerenciar informações de diretório em um ambiente de rede.

## Keycloak
O Keycloak é uma solução de código aberto para gerenciamento de identidade e acesso baseada em padrões como OAuth 2.0 e OpenID Connect. Ele oferece recursos de autenticação, autorização e gerenciamento de identidade para aplicativos web e serviços. Neste repositório, o Keycloak é utilizado para fornecer serviços de autenticação e autorização para a API Rest Spring Boot e outros componentes da aplicação.

## API Rest Spring Boot
A API Rest Spring Boot é uma aplicação baseada em Spring Boot que fornece uma API RESTful para interagir com os dados armazenados no PostgreSQL. O Spring Boot é um framework Java popular para criar aplicativos web e serviços com facilidade. Neste contexto, a API Rest Spring Boot é responsável por fornecer acesso aos dados da aplicação de forma segura e eficiente.

## Nginx
O Nginx é um servidor web de alto desempenho e proxy reverso. Ele é usado neste repositório para servir arquivos estáticos e atuar como um proxy para rotear solicitações para a API Rest Spring Boot e outros componentes da aplicação. O Nginx é conhecido por sua eficiência e escalabilidade, sendo uma escolha comum para implantar e balancear cargas em aplicações web.

### Construir os Contêineres

Certifique-se de estar no diretório onde está localizado o arquivo `docker-compose.yml`.

```bash
docker-compose build
