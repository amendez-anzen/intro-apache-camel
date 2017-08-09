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

1. *Camel context*: Sistema de tiempo de ejecución que mantiene estruturadas y unidas las piezas de camel.
2. *Message*: Entidad fundamental, consiste de _Headers_, _Body_ y _Attachments_.
3. *Exchange*: Contenedor para mensajes, es una abstracción de lo que realmente se envía en el sistema; Contiene mensajes de entrada *In* y, opcionalmente mensajes de salida *Out*.
4. *Route*: Cadena de _Processors_, un _route_ es un grafo, donde un nodo es representado por un _Processor_ y una línea es representada por un canal.
5. *Processor*: Usa/modifica _exchanges_ entrantes; la salida de un _Processor_ es conectada a la entrada de otro.
6. *Endpoint*: Modela el fin de un canal, se configura usando _URI_, por ejemplo: _file:data/inbox?delay=5000
_
7. *Component*: Fábrica de _endpoints_, referido con prefijos, (jms:, file:, etc).
