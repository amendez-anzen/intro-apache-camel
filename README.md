# intro-apache-camel
Conceptos básicos de apache camel y patrones de integración.


## Introducción
Apache Camel es un framework de integración open source basado en [patrones de integración empresariales](http://camel.apache.org/enterprise-integration-patterns.html) o EIP (por sus siglas en inglés); su enfoque es hacer las integraciones más
fáciles y más accesibles para los desarrolladores. Esto a través de:

1. Implementaciones concretas de EIPs.
2. Conectividad a gran diversidad de protocolos de transporte y APis.
3. Uso sencillo de [lenguajes de dominio específico](https://es.wikipedia.org/wiki/Lenguaje_de_dominio_espec%C3%ADfico)] (DSL) para acoplar EIPs con protocolos de transporte. 

Camel fomenta el uso de reglas de enrutamiento (routing) y utiliza URIs para trabajar directamente sobre cualquier clase de modelos de transporte o mensajería, algunos
ejemplos: MQ, HTTP, CXF. Camel al ser una librería pequeña con mínimas dependencias, facilita sea embebida en cualquier aplicación Java.

