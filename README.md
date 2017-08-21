# intro-apache-camel
Conceptos básicos de apache camel y patrones de integración.

**Requisitos**
* [IoC  e Inyección de dependencias con Spring Framework](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html)
* XML
* JSON
* [Maven 3.x](https://maven.apache.org/)


## Introducción
Apache Camel es un framework de integración open source basado en [patrones de integración empresariales](http://camel.apache.org/enterprise-integration-patterns.html) o EIP (por sus siglas en inglés); su enfoque es hacer las integraciones más
fáciles y más accesibles para los desarrolladores. Esto a través de:

1. Implementaciones concretas de EIPs.
2. Conectividad a gran diversidad de protocolos de transporte y APis.
3. Uso sencillo de [lenguajes de dominio específico](https://es.wikipedia.org/wiki/Lenguaje_de_dominio_espec%C3%ADfico) (DSL) para acoplar EIPs con protocolos de transporte. 

Camel fomenta el uso de reglas de enrutamiento (routing) y utiliza URIs para trabajar directamente sobre cualquier clase de modelos de transporte o mensajería, algunos
ejemplos: MQ, HTTP, CXF. Camel al ser una librería pequeña con mínimas dependencias, facilita sea embebida en cualquier aplicación Java.

## Conceptos básicos

Estos son algunos conceptos que se utilizan en Camel, es importante tenerlos en mente, ya que ayudan a entender como se estructura el framework.

1. **Camel context**: Sistema de tiempo de ejecución que mantiene estructuradas y unidas las piezas de camel.
2. **Message**: Entidad fundamental, consiste de _Headers_, _Body_ y _Attachments_.
3. **Exchange**: Contenedor para mensajes, es una abstracción de lo que realmente se envía en el sistema; Contiene mensajes de entrada *In* y, opcionalmente mensajes de salida *Out*.
4. **Route**: Cadena de _Processors_, un _route_ es un grafo, donde un nodo es representado por un _Processor_ y una línea es representada por un canal; 
5. **Processor**: Usa/modifica _exchanges_ entrantes; la salida de un _Processor_ es conectada a la entrada de otro.
6. **Endpoint**: Modela el fin de un canal, se configura usando _URI_, por ejemplo: _file:data/inbox?delay=5000_
7. **Component**: Fábrica de _endpoints_, referido con prefijos, (jms:, file:, etc).

## Primeros pasos

Para la ejecución de Camel, es necesaria la interfaz **CamelContext**, la cual es responsable
del procesamiento de _messages_ y _routes_.

La sentencia _from(...)_ al inicio de un _route_ define un _endpoint_ o una ubicación específica a
una tecnología que el motor de enrutamiento de camel utiliza para obtener los _messages_. Los _enpoints_ son definidos USANDO _URIs_, la primer parte de una _URI_ especifica el componente que
está siendo usado para para consumir un mensaje y, el resto es el conjunto de instrucciones para
ese componente en específico.

El motor de ruteo de Camel se encarga de lo siguiente:

* Consumir _exchanges_ de los _endpoints_ y procesarlos secuencialmente a través de cada paso
definido en el _route_.
* Manejo de hilos.
* Manejo de transacciones.
* Manejo de errores.
* Copia de mensajes cuando se requiere.
* Otros detalles.

El **CamelContext** es un objeto de larga ejecución, por lo que debe vivir tanto como la aplicación
lo haga por lo que su inicio y apagado es usualmente ligado al ciclo de vida de la aplicación. Como
ejemplo, el contexto de camel es desplegado/ejecutado en el método _main()_ en aplicaciones
standalone, como una variable de instancia en dentro de _javax.servlet.ServletContextListener_ en
una aplicación web, iniciándola y deteniéndola con la aplicación; en un objeto ligado al ciclo de
vida de un bundle de _OSGi_; un objeto dentro del contexto de Spring, etc.

* Revisar ejemplo: [simplespring-context.xml](https://github.com/hereje/intro-apache-camel/blob/master/src/main/resources/META-INF/spring/simplespring-context.xml)


Un _component_ es una fábrica para crear endpoints que pueden ser _Producers_, _Consumers_ o ambos. Una implementación tiene típicamente propiedades, por ejemplo:

```xml
<bean id="myFavouriteMQ" class="org.apache.camel.component.jms.JmsComponent">
    <property name="connectionFactory" ref="myConnectionFactory"/>
</bean>
```
Todos los componentes implementan un método usado para procesar un endpoint URI.

```java
Endpoint createEndpoint(String uri) throws Exception;
```

Un _endpoint_ se compone de :
* _scheme_: La parte que antes de los dos puntos (:), que indica la implementación del componente.
* _properties_: La parte después del los dos puntos (:), indica la configuración específica de la tecnología.

Ya que el _endpoint_ también es una fábrica o _Factory_, Camel lo usa para para crear _Producers_ y _Consumers_,
esto depende del contexto donde se usa el _URI_. Los siguientes métodos del _Factory_ son definidos en la interfaz:

```java
Producer createProducer() throws Exception;
Consumer createConsumer(Processor processor) throws Exception;
```
Las clases anteriores se encargan de la comunicación con la tecnología correspondiente. Si el _endpoint_ es usado en la sentencia _from(...)_ Camel creará un _Consumer_, si es usado en el bloque _to(...)_ se creará un _Producer_.

> Nota: Un mismo _Component_ puede ser instanciado múltiples ocasiones con diferente _id_.

### Uso de componentes

Camel abstrae código de integración encapsulándolo en _Components_, lo que permite
enfocarse en la lógica de negocio de tu integración sin que entres en el detalle
fino del "transporte".

* Ejemplo : [simplespring-context.xml](https://github.com/hereje/intro-apache-camel/blob/master/src/main/resources/META-INF/spring/simpleSpring-context.xml)
* Ejemplo con _@Component_: [simplespringcomponent-context.xml](https://github.com/hereje/intro-apache-camel/blob/master/src/main/resources/META-INF/spring/simplespringcomponent-context.xml)

Ejecutar
> mvn test


### Servicios REST

Apache Camel provee distintos componentes para definir servicios REST (Representational State Transfer); _REST_ es una arquitectura
para aplicaciones distribuidas que se centra en la transmisión de datos a través del protocolo _HTTP_, usando sólo los 4 verbos
básicos de _HTTP_: **GET**, **POST**, **PUT** y **DELETE**. La arquitectura _REST_ explota  _HTTP_ directamente.

Ya que la arquitectura _REST_ está construida sobre los verbos _HTTP_ estándar, en muchos casos puedes usar un browser como cliente
_REST_.


#### Definición de servicios con REST DSL

_REST DSL_ provee una sintaxis simplificada para definición de servicios _REST_ en Java DSL o XML DSL. _REST DSL_ no provee
propiamente una implementación _REST_ es un _wrapper_ de una implementación de _REST_ existente, de las cuales hay varias en
Apache Camel.

Algunas ventajas de _REST DSL_

* Sintaxis moderna fácil de usar.
* Compatible con múltiples componentes de Camel.
* integración con _Swagger_ (a través del componente _camel-swagger_).

Ya que _REST DSL_ no es una implementación de _REST_, se debe elegir alguna de las implementaciones 
disponibles, los siguientes componentes están integrados con _REST DSL_.

* Servlet (_camel-servlet_).
* Netty HTTP (_camel-netty-http_).
* Netty4 HTTP (_camel-netty4-http_).
* Jetty (_camel-jetty_).
* Restlet (_camel-restlet_).

> NOTA: El componente _Rest_ (_camel-core_) no es una implementación _REST_. Al igual que _REST DSL_ es sólo un _facade_, por lo que requiere una implementación.

Ejemplo configuración _camel-restlet_: [camel-config.xml](https://github.com/hereje/intro-apache-camel/blob/master/src/main/resources/META-INF/spring/camel-config.xml)

**Ejecutar**
> mvn jetty:run

>NOTA: Para probar los servicios, es necesario enviar el header **"Content-Type: application/json"** .

> curl -X GET localhost:8080/intro-apache-camel/rs/v1/users/188
> curl -H "Content-Type: application/json" -X POST -d '{"id": 1, "firstName":"xyz","lastName":"xyz"}' localhost:8080/intro-apache-camel/rs/v1/users



# Referencias
* [Apache Camel Developer's cookbook, Scott Cranton](https://github.com/CamelCookbook/camel-cookbook-examples)
* [Sitio oficial de Apache Camel](http://camel.apache.org)
* [Definición de servicios REST](http://www.restapitutorial.com/lessons/whatisrest.html)
* [Recomendaciones para creación de servicios REST](http://www.restapitutorial.com/lessons/restquicktips.html)
* [Servicio REST con Apache Camel](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/RestServices.html)
* [Ejemplos repositorio Apache Camel](https://github.com/apache/camel#examples)
