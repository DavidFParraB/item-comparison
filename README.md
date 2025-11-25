# API de Comparación de Artículos

## Objetivo

Esta API de backend simplificada está diseñada para proporcionar detalles de productos que se pueden utilizar en una función de comparación de artículos. La implementación sigue las mejores prácticas de backend, proporcionando puntos finales claros y eficientes para recuperar los datos necesarios para las comparaciones de productos.

## Funcionalidades

La API permite:

- Crear, obtener, filtrar y eliminar artículos.
- Recuperar detalles de artículos individuales o una lista completa de artículos.
- Filtrar artículos basados en categorías y criterios específicos.
- Obtener opciones de filtro basadas en las características de los artículos.

## Servicios

### Crear un nuevo artículo

- **URL**: `/item`
- **Método**: `POST`
- **Descripción**: Crea un nuevo artículo con los detalles proporcionados.
- **Códigos de respuesta**:
  - `201`: Artículo creado exitosamente.
  - `400`: Ya existe un artículo con el mismo ID.

### Obtener todos los artículos

- **URL**: `/item`
- **Método**: `GET`
- **Descripción**: Recupera todos los artículos de la base de datos.
- **Códigos de respuesta**:
  - `200`: Lista de artículos.

### Obtener un artículo por ID

- **URL**: `/item/{id}`
- **Método**: `GET`
- **Descripción**: Recupera un artículo por su ID.
- **Códigos de respuesta**:
  - `200`: Artículo encontrado.
  - `404`: Artículo no encontrado.

### Filtrar artículos

- **URL**: `/item/filter`
- **Método**: `GET`
- **Descripción**: Filtra artículos basados en la categoría y los criterios especificados.
- **Códigos de respuesta**:
  - `200`: Lista filtrada de artículos.
  - `400`: Criterios de filtro inválidos.

### Obtener opciones de filtro

- **URL**: `/item/filter-options`
- **Método**: `GET`
- **Descripción**: Recupera las opciones de filtro disponibles basadas en las características de los artículos.
- **Códigos de respuesta**:
  - `400`: Parámetro de solicitud requerido.

### Eliminar todos los artículos

- **URL**: `/erase`
- **Método**: `DELETE`
- **Descripción**: Elimina todos los artículos de la base de datos.
- **Códigos de respuesta**:
  - `200`: Todos los artículos eliminados exitosamente.

### Eliminar un artículo por ID

- **URL**: `/item/{id}`
- **Método**: `DELETE`
- **Descripción**: Elimina un artículo por su ID.
- **Códigos de respuesta**:
  - `200`: Artículo eliminado exitosamente.
  - `404`: Artículo no encontrado.

## Funcionamiento del filtro

### Creación y Clasificación

Los productos se crean y se clasifican por categoría. Cada producto tiene atributos como nombre, URL de imagen, descripción, precio, calificación y características específicas (por ejemplo, tamaño, tecnología, marca, resolución). Estos atributos permiten segmentar los productos para facilitar su búsqueda y comparación.

### Opciones de Filtro

La API proporciona un punto final (/item/filter-options?category) para obtener las opciones de filtro disponibles para una categoría específica. Esto permite a los usuarios ver qué características están disponibles para filtrar dentro de una categoría.

#### Estructura del Filtro
El filtro se construye especificando una serie de criterios separados por punto y coma (;). Cada criterio tiene la siguiente estructura:


* Atributo: El campo del producto que se desea filtrar (por ejemplo, price, technology, brand).
* Operador: El tipo de comparación que se desea realizar. Los operadores disponibles son:
  * GREATER_THAN (>): Para encontrar productos con un valor mayor al especificado.
  * LESS_THAN (<): Para encontrar productos con un valor menor al especificado.
  * EQUALS_TO (=): Para encontrar productos con un valor igual al especificado.
* Valor: El valor contra el cual se compara el atributo.

```text
<atributo>,<operador>,<valor>
```

- **Ejemplo**:

Al consultar los atributos por los que podemos realizar los filtros para los articulos cuya caracteristica es "Monitores" obtenemos
 ```json 
 {
  "size": ["32"],
  "technology": ["LED"],
  "brand": ["HISENSE"],
  "resolution": ["HD"]
  }
  ```

Lo que nos permite filtrar y obtener la lista de articulos que cumplen con estas condiciones

Filtro Aplicado:
price,LESS_THAN,500.0;technology,EQUALS_TO,LED;brand,EQUALS_TO,HISENSE
```json
[
  {
    "id": "MLA415",
    "name": "Smart TV Básico 32",
    "url": "...",
    "description": "Modelo compacto, ideal para cocinas o dormitorios.",
    "price": 199.99,
    "rating": 3.9,
    "category": "Monitores",
    "characteristics": [
      {
        "clave": "size",
        "valor": "32"
      },
      {
        "clave": "resolution",
        "valor": "HD"
      },
      {
        "clave": "technology",
        "valor": "LED"
      },
      {
        "clave": "brand",
        "valor": "HISENSE"
      }
    ]
  }
]
```

## Compilación de la aplicación
1. Genere el jar de la aplicacion con cualquiera de estos comandos
   
mvn clean package

2. si cuenta con docker instalado puede ejecutar el comando

- docker build -t item-comparison:1.0 .
- docker run -d -p 8080:8080 item-comparison:1.0

3. si no cuenta con docker compose instalado  ejecute el comando

java -jar target/app-1.0.0.jar

podra entonces probar el api con el comando

### curl
* Creación de Item

curl --location 'http://127.0.0.1:8080/item' \
--header 'Content-Type: application/json' \
--data '{
"id": "MLA415",
"name": "Smart TV Básico 32",
"url": "...",
"description": "Modelo compacto, ideal para cocinas o dormitorios.",
"price": 199.99,
"rating": 3.9,
"category": "Monitores",
"characteristics": [
{
"clave": "size",
"valor": "32"
},
{
"clave": "resolution",
"valor": "HD"
},
{
"clave": "technology",
"valor": "LED"
},
{
"clave": "brand",
"valor": "HISENSE"
}
]
}'

* Consulta de Filtros

curl --location 'http://127.0.0.1:8080/item/filter-options?category=Monitores'

* Consulta de Items por filtro y categoria:

curl --location 'http://127.0.0.1:8080/item/filter?criteria=price%2CLESS_THAN%2C500.0%3Btechnology%2CEQUALS_TO%2CLED%3Bbrand%2CEQUALS_TO%2CHISENSE&category=Monitores'
