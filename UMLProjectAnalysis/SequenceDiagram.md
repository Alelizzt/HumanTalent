> Puede renderizar los diagramas UML utilizando [Mermaid](https://mermaidjs.github.io/).
```mermaid
---
title: Diagrama de secuencia
---
sequenceDiagram
actor HumanResources
participant WebPage/MobileApp
participant Service
participant DB

HumanResources ->> WebPage/MobileApp: 1: Visualizar: lista de empleados
HumanResources ->> WebPage/MobileApp: 2: Ingresar: datos de nuevo empleado
WebPage/MobileApp ->> Service: 3: Transferir: datos de nuevo empleado
Service ->> DB: 4: Registrar: datos de empleado
DB ->> Service: 5: Validar: registro de empleado
Service ->> WebPage/MobileApp: 6: Mostrar: datos de empleado
HumanResources ->> WebPage/MobileApp: 7: Editar: datos de empleado
WebPage/MobileApp ->> Service: 8: Transferir: datos nuevos del empleado
Service ->> DB: 9: Registrar: datos modificados del empleado
DB ->> DB: 10: Actualizar: datos modificados del empleado
DB ->> Service: 11: Notificar: cambio en el registro de empleado
Service ->> WebPage/MobileApp: 12: Mostrar: datos de empleado



```