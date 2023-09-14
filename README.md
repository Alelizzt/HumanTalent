# HumanTalent
<p style='text-align: justify'>
Dentro de los procesos de Talento Humano, la empresa requiere de un sistema que le permita registrar el ingreso y la salida de sus empleados, así como administrar su información. Actualmente el proceso se realiza de manera manual sobre una hoja de cálculo, lo cual funciona pero impide alimentar otros procesos para los cuales es importante esta información, así como llevar de manera óptima el registro y administración de la misma.esta información, así como llevar de manera óptima el registro y administración de la misma.
</p>

## Requerimientos
Dado lo anterior, se requiere tu ayuda para tener la mejor solución posible, a continuación se listan las funcionalidades requeridas:

### Registro de empleados
El usuario podrá registrar un empleado nuevo, para ello
deberá registrar la siguiente información:

- **Primer Apellido:** Solo permite caracteres de la A a la Z, mayúscula, sin acentos ni Ñ. Es requerido y su longitud máxima será de 20 letras.
- **Segundo Apellido:** Solo permite caracteres de la A a la Z, mayúscula, sin acentos ni Ñ. Es requerido y su longitud máxima será de 20 letras.
- **Primer Nombre:** Solo permite caracteres de la A a la Z, mayúscula, sin acentos ni Ñ. Es requerido y su longitud máxima será de 20 letras.
- **Otros Nombres:** Solo permite caracteres de la A a la Z, mayúscula, sin acentos ni Ñ. Es requerido y su longitud máxima será de 50 letras.
- **País del empleo:** País para es para el empleado prestará sus servicios, podrá ser Colombia o Estados Unidos.
- **Tipo de Identificación:** Será el tipo de identificación, por ejemplo, Cédula de Ciudadanía, Cédula de Extranjería, Pasaporte, Permiso Especial. Debe ser una lista desplegable en la que el usuario seleccione el tipo de identificación correspondiente.
- **Número de Identificación:** Es el número de identificación del empleado, debe ser alfanumérico permitiendo los siguientes conjuntos de caracteres (a-z / A-Z / 0-9 / -). No podrán existir dos empleados con el mismo número y tipo de identificación. Su longitud máxima será de 50 letras.
- **Correo electrónico:** Se debe generar aurar automáticamente, sin intervención del usuario, con el siguiente formato:
  ```xml
  <PRIMER_NOMBRE>.<PRIMER_APELLIDO>.<ID> @ <DOMINIO>
  ```
  El DOMINIO será company.com.co para Colombia y company.com.us para Estados Unidos.

  No podrán existir 2 empleados con el mismo correo electrónico, en caso que ya exista se debe agregar un valor numérico adicional secuencial (ID).

  Pueden existir valores intermedios siempre y cuando se garantice la unicidad, por ejemplo:

  <div align="center">

  | Nombre completo            | Correo                      |
  | :----:                     |    :----:                   |
  | JUAN FELIPE PEREZ MONTILLA | juan.perez@company.com.co   |
  | JUAN CARLOS PEREZ GOMEZ    | juan.perez.1@company.com.co |
  | JUAN ANDRES PEREZ JIMENEZ  | juan.perez.2@company.com.co |
  | JUAN DAVID PEREZ CALLE     | juan.perez.3@company.com.co |

  </div>

  Para apellidos compuestos deberá tratarlos como un único apellido, por ejemplo:

  <div align="center">

  | Nombre completo                 | Correo                          |
  | :----:                          |    :----:                       |
  | JUAN JOSE DE LA CALLE MORA      | juan.delacalle@company.com.co   |
  | JUAN GUILLERMO DE LA CALLE MESA | juan.delacalle.1@company.com.co |

  </div>

  El correo electrónico debe tener un formato válido desde su generación automática, no puede ser intervenido por el usuario para ajustar su formato y su longitud máxima será de 300 caracteres. Su longitud máxima será de 300 caracteres.
- **Fecha de ingreso:** No podrá será ser superior a la fecha actual, pero sí poro sí podrá ser hasta un mes menor, ya que es posible que el usuario no haya podido registrarlo el día en que ingresó.
- **Área:** Será el área para la cual fue contratado el empleado, puede ser, Administración, Financiera, Compras, Infraestructura, Operación, Talento Humano, Servicios Varios, etc. Debe ser una lista desplegable en la que el usuario seleccione el área correspondiente.
- **Estado:** Siempre será Activo, no podrá ser modificad por el usuario.
- **Fecha y hora de registro:** Mostrará la fecha y hora del registro en formato DD/MM/YYYY HH:mm:ss, no puede ser editado.

### Consulta de empleados
Esta pantalla será la pantalla de inicio del sistema.

Debe ser una pantalla en la cual se listen todos los empleados, hasta 10 por página.
Se podrán filtrar los empleados por Primer Nombre, Otros Nombres,por página.
Primer Apellido, Segundo Apellido, Tipo de Identificación, Número de Identificación, País del empleo, Correo electrónico y/o Estado.
Cada registro de empleado tendrá la opción de ser editado mediante la funcionalidad de edición de empleado, o de ser eliminado previa confirmación del sistema (“Está seguro de que desea eliminar el empleado?Sí / No”).

### Edición de empleados
Una vez seleccionada la opción de editar el empleado en la funcionalidad de consulta de empleados se abrirá la funcionalidad de edición de empleados, la cual tendrá los mismos campos de la funcionalidad de registro de empleados, permitiendo modificarlos todos (excepto la fecha de registro), incluso su tipo y número de identificación, en caso de que se modifiquen los nombres y/o apellidos, el sistema re-generará su dirección de correo electrónico. A diferencia de la funcionalidad de registro de empleados, no se tendrá fecha de registro sino de edición.

### Versión móvil (opcional)
See debe crear una funcionalidad móvil que realice los requerimientos anteriores, en la sección de consulta de los empleados, se deben mostrar hasta 10 por página.

## Aclaraciones técnicas
- Debe utilizar tecnologías **Angular, Spring / Spring Boot y Flutter**, esta última solamente para la funcionalidad de consulta móvil.

## Entrega
- Análisis del problema con los correspondientes diagramas:
  - Diagrama de clases.
  - Diagrama de secuencia.
- Código fuente documentado
- Instructivo de instalación y configuración de la solución para ejecución y revisión de la misma.

## Ítems requeridos
- Código fuente limpio, estructurado y documentado.
- Comprensión de la necesidad.
- Validaciones y coherencia en la implementación.
- Funcionamiento ante flujos básicos.
- Funcionamiento ante flujos alternos o inesperados.
- Separación de capas.
- Manejo de excepciones.
- Manejo de logs.

## Ítems de valor agregado
- Apariencia agradable.
- Usabilidad.
- Uso de servicios web (REST/SOAP).
- Pruebas unitarias y de integración.
- Acoplar en docker el backend.
- Acoplar Jenkins y SonarQube para generar reporte.
- Funcionalidad móvil en Flutter.
