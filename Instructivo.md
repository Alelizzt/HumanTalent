# Instructivo de instalación y configuración del proyecto

## Descarga del proyecto
~~~
git clone http://github.com/Alelizzt/HumanTalent
~~~

Para ejecutar el proyecto debe crear una base de datos, en el proyecto se proporciona la ejecución de un contenedor docker para ello:

nos ubicamos en la carpeta 'Infraestructure':
~~~
cd HumanTalent/Infraestructure
~~~
Procedemos a ejecutar el contenedor de postgresql con la configuración necesaria para el proyecto:
~~~
docker-compose up -d
~~~

Puede ejecutar el .jar ofrecido en el [release](https://github.com/Alelizzt/HumanTalent/releases/download/0.0.1/HumanTalent-0.0.1-SNAPSHOT.jar)
recuerde tener la base de datos disponible antes de ejecutarlo.
~~~
java -jar HumanTalent-0.0.1-SNAPSHOT.jar
~~~

En el .jar esta embebido el frontend de Angular, por lo cual se disponen las siguientes rutas para visualizar el proyecto:

FrontEnd: http://localhost:9081/human-talent/
EndPoint del Api REST/SOAP: http://localhost:9081/human-talent/api/v1/talent/employees
Documentación del API REST: http://localhost:9081/human-talent/swagger-ui/index.html
