# Spring Boot with Grafana Stack

Proyecto `Spring Boot` implementando:
* Dos microservicios, uno llamado `loan-service` con el cual se comunica con `fraud-detection-service`.
* Grafana y Prometheus para los métricas.
* Loki para los logs o registros.
* Tempo para los traces o rastros.

![Main image](grafana.png)

Componentes Clave de Grafana:
* Dashboards: Contenedores que agrupan paneles y permiten la visualización de datos mediante gráficos, tablas y otros widgets.
* Panels: Elementos dentro de un dashboard que visualizan datos en diferentes formatos, como gráficos de líneas, barras, tablas, mapas, etc.
* Data Sources: Conectores que permiten a Grafana recuperar datos de diversas bases de datos y servicios, como Prometheus, Elasticsearch, MySQL, entre otros.
* Queries: Consultas configurables que extraen datos de las fuentes de datos y definen cómo se deben visualizar.
* Alerts: Reglas que permiten monitorizar datos y generar notificaciones cuando se cumplen ciertos criterios.
* Plugins: Extensiones que añaden funcionalidades adicionales a Grafana, como nuevos paneles, fuentes de datos o integraciones.
* Variables: Parámetros dinámicos que permiten personalizar y filtrar la información en los dashboards y paneles.
* Annotations: Marcadores visuales en los gráficos que muestran eventos o notas importantes en el tiempo.
* Organizations: Entidades que permiten la segmentación de recursos y configuración de permisos en Grafana para equipos o departamentos distintos.
* Users and Roles: Gestión de accesos y permisos para usuarios dentro de Grafana, permitiendo diferentes niveles de acceso y administración.

## Tabla de Contenidos

1. [Herramientas](#herramientas-usadas)
2. [Testeo](#testeo)
3. [Endpoints](#endpoints)

## Herramientas usadas

- Spring Boot
    - Spring Boot Actuator
    - Spring Data JDBC
    - Spring Web
    - Validation
    - Flyway Migration
    - MySQL Driver
    - Lombok
    - Prometheus
    - Spring Boot DevTools

## Testeo

Para ejecutar el proyecto, necesitas antes tener Docker y Docker Compose instalado.

Luego, cambiar usuario y contraseña de base de datos mysql: 

* [docker-compose.yml](docker-compose.yml)
* fraud-detection-service: [application.properties](fraud-detection-service/src/main/resources/application.properties)
* loan-service: [application.properties](loan-service/src/main/resources/application.properties) 

Ejecutar Docker Compose:
``` bash
docker compose up -d
```

Por último, ejecutar:
``` bash
LoanServiceApplication
```
``` bash
FraudDetectionServiceApplication
```

Para acceder a los servicios:
1. Grafana: http://localhost:3000
2. Prometheus: http://localhost:9090

[//]: # (3. Tempo: http://localhost:3110)

[//]: # (4. Loki: http://localhost:3100)

## Endpoints

Desde http://localhost:8080 (loan-service)

- **GET /loan**
  - **Descripción:** Obtiene un listado de todos los préstamos.
  - **Respuesta:**
    - Estado 200 OK
    - Cuerpo de la respuesta: `[ { "customerName": "Nombre del cliente", "customerId": "ID del cliente", "amount": "monto del préstamo solicitado", "loanStatus": "APPROVED o REJECTED" }, ... ]`

- **POST /loan**
  - **Descripción:** Permite crear un nuevo préstamo.
  - **Cuerpo de la solicitud:**
    - `{ "customerName": "Nombre del cliente", "customerId": "ID del cliente", "amount": "monto del préstamo solicitado", "loanStatus": "APPROVED o REJECTED" }`
  - **Respuesta:**
    - Estado 201 Created si el préstamo se crea exitosamente.
    - Estado 400 Bad Request si hay un error en la solicitud.
    - Cuerpo de la respuesta: `{ "customerName": "Nombre del cliente", "customerId": "ID del cliente", "amount": "monto del préstamo solicitado", "loanStatus": "APPROVED o REJECTED" }`

Desde http://localhost:8081 (loan-service)

- **GET /fraud/check?customerId=**
  - **Descripción:** Verifica si existe un registro de fraude para un cliente específico.
  - **Respuesta:**
    - Estado 200 OK.
    - Estado 400 Bad Request si hay un error en la solicitud.
    - Cuerpo de la respuesta: `"REJECTED" o "APPROVED"`

## Authors

- [@Juan Ignacio Caprioli (ChanoChoca)](https://github.com/ChanoChoca)
