# PuntosAccesoCDMX
 Examen para los puntos de acceso de los distintos wifis que hay en la CDMX.
 
## Introducción.
El siguiente proyecto es un proyecto en Java que muestra los distintos puntos de acceso de Wifi que se tienen para la Ciudad de México en donde podremos utilizar los datos que se tienen por parte del gobierno para nuestro proyecto.
Este proyecto está basado en [SpringBoot](https://spring.io/projects/spring-boot) y utiliza [Graphql](https://graphql.org/) para la ejecución de consultas hacia una base de datos conterizada de MySQL que se despliega y se puebla automáticamente cuando se carga el proyecto. 
La lista de datos se obtiene desde la página del [gobierno de la CDMX](https://datos.cdmx.gob.mx/dataset/puntos-de-acceso-wifi-en-la-ciudad-de-mexico).
 
## Dependencias y Versiones
Para este proyecto se tienen las siguientes dependencias:
1. spring-boot-starter-data-jpa
	- Para ejecutar el proyecto de forma independiente
		- version 3.4.4-SNAPSHOT
2. spring-boot-starter-graphql
	- Necesario para crear consultas dinámicas
3. spring-boot-docker-compose
	- Para levantar y configurar el poroyecto dentro de un contenedor.
4. spring-boot-starter-web
	- Para hacer que el proyecto tenga sus servicios REST y sea MVC.
5. mysql-connector-j
	- Para conectar a la base de datos de MySQL.
6. lombok
	- Para optimizar y facilitar la creación de getters y setters.
7. spring-boot-testcontainers
	- Para pruebas en contenedores.
8. junit-jupiter
	- Para realizar pruebas unitarias.
9. mysql
	- Para pruebas y manejo de datos.
10. spring-webflux
	- Para construir aplicaciones MVC clásicas. 
11. spring-graphql-test
	- Para las pruebas de GraphQL.
12. graphql-spring-boot-starter
	- Para levantar el proyecto MVC para GraphQL y que se tenga un endpoint dedicado.
13. graphql-java-tools
	- Herramientas de GraphQL para Java.
14. h2
	- Para bases de datos H2
15. Java
	- versión 17
16. MysSQL
	- Versión 9.2.0  
## Instrucciones de despliegue
Requerimientos
* Tener instalado y ejecutándose [Docker](https://www.docker.com/)
* Tener instalado Java 17
* Tener algún [IDE de desarrollo](https://spring.io/tools) con el plugin de SpringBoot para importar el proyecto o compilarlo con Java (En mi caso use STS, pero pueden usar el que gusten).
* Tener instalado [Maven](https://maven.apache.org/download.cgi).
* Tener instalado lombok (se puede usar este [tutorial](https://projectlombok.org/setup/eclipse) o este [otro tutorial](https://www.baeldung.com/lombok-ide) o [aquí](https://stackoverflow.com/questions/22310414/how-to-configure-lombok-in-eclipse-luna) )

Para desplegar este proyecto se debe de ejecutar desde CMD o desde consola, el siguiente comando:

**mvn clean compile spring-boot:run**

Esto se debe de hacer dentro de la carpeta del proyecto y en donde se encuentre el archivo **pom.xml**.

> [!NOTE]
> Hay veces que no levanta el proyecto porque arroja un error de configuración. Es necesario ejecutar el comando un par de veces para que compile correctamente.

Una vez que levante el proyecto, se ejecutará el archivo **compose.yaml**  que contiene la información necesaria para levantar un contenedor Docker con la base de datos de MySQL (ahí mismo vienen los datos de la base, el usuario y password).
Springboot va a tomar los archivos **schema-mysql.sql** y **data.sql** para ejecutar los scripts que viene ahí y que se insertaran en la imagen de MySQL dentro de Docker.
* **schema-mysql.sql** Contiene la estructura de la base de datos que se va a utilizar para el proyecto (que en este caso es la **tabla wifi_access_point**).
* **data.sql** Contiene todos los inserts para poblar la base. Estos inserts se obtuvieron depuranto el archivo CSV **2024-06-30-puntos_de_acceso_wifi.csv** que se obtuvo de la página oficial del [gobierno de la CDMX](https://datos.cdmx.gob.mx/dataset/puntos-de-acceso-wifi-en-la-ciudad-de-mexico).  

La configuración que se tiene para subir esta información, se encuentra en el archivo de **application.properties** y se componen de las siguientes líneas:
* **spring.jpa.defer-datasource-initialization=true** 
	* Propiedad para inicializar la base de datos ya que levante la aplicación.
* **spring.sql.init.data-locations=classpath:data.sql**
	* Configuración para decirle a springboot en donde se encuentra el archivo SQL con los inserts hacia la tabla (generalmente toma el archivo por defecto, pero asignándole una ruta, podemos cargar uno u otro archivo según las necesidades de nuestro proyecto).
* **spring.jpa.hibernate.ddl-auto=create-drop**
	* Esta parte es importante, ya que le decimos a Spring que tire todas las bases de datos utilizadas y las haga desde cero, utilizando los archivos mencionados. Si no queremos hacer eso y dejar la base con los datos existentes, podemos utilizar la opción de **update**.
* **spring.batch.jdbc.initialize-schema=always**
	* Para siempre inicializar la base de datos.
* **spring.sql.init.mode=always**
	* Para siempre inicializar la base de datos.

> [!WARNING]
> El proyecto tarda unos 5 minutos en ejecutarse por primera vez debido a la carga de datos en la base de MySQL.

Ya que el proyecto se encuentre arriba, podemos acceder a la [URL de GraphQL local](http://localhost:8080/graphiql) en donde podremos ver la estructura de los queries de consulta que podemos ejecutar:
![image](https://github.com/user-attachments/assets/a1532224-dde7-4513-ad1e-243fc0a6c0f5)

Las consultas quye se tienen (y que se encuentran en el archivo **schema.graphqls**)son las siguientes:

* **wifiAccessPointById**
	* Query para obtener todos los puntos de acceso por nombre.
* **wifiAccessPoints**
	* Query para obtener todos los puntos de acceso. Regresa un arreglo.
* **wifiAccessPointsPaginated**
	* Query para obtener todos los puntos de acceso pero paginados.
* **wifiAccessPointsByColoniaPaginated**
	* Query para obtener todos los puntos de acceso por colonia.



## Diagrama general de la solución (servicios y bd)
### Estructura general de la aplicación
![image](https://github.com/user-attachments/assets/321df2d3-5fd3-41bd-ad26-bd68b006414f)

### Estructura básica de la arquitectura

Esta aplicación utiliza el archivo de **compose.yaml** para levantar y/o conectarse a una imagen de docker que contiene la ultima versión de MySQL.
![image](https://github.com/user-attachments/assets/55202402-f785-42ee-a944-4a343baf0a0c)



## Desarrollo de la solución
El código de la solución se encuentra en este [repositorio](https://github.com/juanitomemelas/PuntosAccesoCDMX).
