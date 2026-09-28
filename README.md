# Sistema Telefónico

Aplicación cliente-servidor desarrollada en Java para consultar información de personas mediante un número telefónico.

## Descripción

Este proyecto implementa una arquitectura cliente-servidor mediante sockets TCP. El cliente solicita al usuario un número telefónico y lo envía al servidor. El servidor recibe la solicitud, consulta la información correspondiente en una base de datos PostgreSQL y devuelve el resultado al cliente.

El proyecto fue desarrollado como parte de una actividad académica del módulo de Sistemas Distribuidos.

## Tecnologías utilizadas

- Java 27
- PostgreSQL 18
- JDBC
- PostgreSQL JDBC Driver 42.7.13
- Maven
- IntelliJ IDEA
- pgAdmin 4
- Sockets TCP

## Arquitectura del sistema

```text
                    Socket TCP
Cliente Java ------------------------> Servidor Java
    |                                      |
    |                                      |
    |                                      | JDBC
    |                                      ↓
    |                               PostgreSQL
    |                                      |
    |                                      ↓
    |                              sistema_telefonico
    |                                      |
    |<--------- Respuesta -----------------|