﻿# API Rest Franchises. 🏢
Esta es una API RESTful construida con Spring Boot que permite gestionar Franquicias, Sucursales y Productos.

Proporciona endpoints para crear, actualizar y eliminar los diferentes modelos.

También permite asociar Sucursales con Franquicias y Productos con Sucursales.

## Sitio web del proyecto
[santiago-ochoa.webhop.me:8080](santiago-ochoa.webhop.me:8080)

## Requisitos. ✏️

- Tener Java 17 instalado o una version mayor.
- Tener Maven instalado

## Tecnologías utilizadas. 💻

- Java 17.
- Spring boot.
- Swagger.
- Maven.
- Mysql.
- Docker.
- Terraform.

## Clonar repositorio. ⬇️
 ```bash
  git clone https://github.com/sochoa885/franquicias-crud.git
 ```

## Configuración del proyecto. 🔩

> [!NOTE]
> En Windows, si no tienes Maven instalado globalmente, asegúrate de utilizar `.\mvnw` en lugar de `mvn`
para todos los comandos de Maven en este proyecto. En Linux y macOS puedes usar `./mvnw` .

1. Navegar al directorio del proyecto.
      ```bash
      cd your_path/franquicias-crud
      ```
2. Editar la configuracion de la Base de Datos

   Debes ir a la ruta `src/main/resources/application.properties` en tu proyecto y configurar los datos de tu BD
3. Instalar las dependencias de Maven.
      ```bash
      mvn clean install
      ```
4. Ejecutar la aplicación.
   ```bash
   mvn spring-boot:run
      ```

## Endpoints 🌐

- `http://localhost:8080` - URL local de la API.

- `/swagger-ui/index.html` - Documentación Swagger

- `/api/franchises` - CRUD para Franquicias.
    - **POST** `/create` : Crea una nueva Franquicia.
    - **PATCH** `/{ID}/update-name` : Actualiza el nombre de una Franquicia.

- `/api/branches` - CRUD para Sucursales.
    - **POST** `/create` : Crea una nueva Sucursal.
    - **PATCH** `/{ID}/update-name` : Actualiza el nombre de una Sucursal.

- `/api/products` - CRUD para Productos.
    - **POST** `/create` : Crea un nuevo Producto.
    - **PATCH** `/{ID}/update-name` : Actualiza el nombre de un Producto.
    - **PUT** `/{ID}/update-stock` : Actualiza el stock de un Producto.
    - **DELETE** `/{ID}/delete` : Elimina un Producto.
    - **GET** `/get-best-stock/{ID_FRANQUICIA}` : Obtiene el producto con mas stock por Sucursal según una Franquicia.
