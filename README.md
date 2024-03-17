# README

Este repositório contém uma configuração de Docker Compose para implantar um conjunto composto por PostgreSQL, OpenLDAP, phpLDAPadmin, Keycloak, API Rest Spring Boot e Nginx.
## Services

### PostgreSQL

- **Image:** postgres:latest
- **Environment Variables:**
    - `POSTGRES_USER`: postgres
    - `POSTGRES_PASSWORD`: postgres
- **Ports:** 5432
- **Volumes:** `./init.sql:/docker-entrypoint-initdb.d/init.sql`, `db-data:/var/lib/postgresql/data/`
- **Health Check:** Checks if PostgreSQL is ready using `pg_isready -U postgres`

### OpenLDAP

- **Image:** osixia/openldap:1.5.0
- **Environment Variables:**
    - `LDAP_LOG_LEVEL`: "256"
    - `LDAP_ORGANISATION`: "Example Inc."
    - `LDAP_DOMAIN`: "example.org"
    - `LDAP_ADMIN_PASSWORD`: "admin"
    - `LDAP_CONFIG_PASSWORD`: "config"
    - `LDAP_READONLY_USER`: "false"
    - LDAP_TLS configuration
- **Ports:** 389, 636
- **Volumes:** `/var/lib/ldap`, `/etc/ldap/slapd.d`, `/container/service/slapd/assets/certs/`
- **Domainname:** example.org
- **Hostname:** ldap-server

### phpLDAPadmin

- **Image:** osixia/phpldapadmin:latest
- **Environment Variables:**
    - `PHPLDAPADMIN_LDAP_HOSTS`: "openldap"
    - `PHPLDAPADMIN_HTTPS`: "false"
- **Ports:** 8082
- **Depends On:** openldap

### Keycloak

- **Image:** quay.io/keycloak/keycloak:latest
- **Ports:** 8081
- **Volumes:** `./keycloak/config:/opt/keycloak/data/import`
- **Environment Variables:**
    - Keycloak configuration
- **Entrypoint:** "/opt/keycloak/bin/kc.sh start-dev --import-realm --verbose"
- **Depends On:** postgres (with health check)

### App

- **Build:** ./
- **Environment Variables:**
    - `DB_HOST`: postgres
- **Ports:** 8080
- **Depends On:** postgres, keycloak

### Nginx

- **Build:** ./nginx
- **Image:** nginx
- **Ports:** 80
- **Depends On:** app

## Networks

- **Name:** my-network
- **Driver:** bridge

## Volumes

- **Name:** db-data

---
Feel free to modify or extend this setup according to your needs.

### Construir os Contêineres

Certifique-se de estar no diretório onde está localizado o arquivo `docker-compose.yml`.

```bash
docker-compose build
