# intro-apache-camel
Conceptos básicos de apache camel y patrones de integración.


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






# Referencias

* Apache Camel Developer's cookbook, Scott Cranton.
* http://camel.apache.org
