# Literalura

Este es un programa desarrollado en Java que permite la busqueda de libros de diferentes idiomas y realiza la persistencia de algunos datos a través de una base de datos relacional en PostgreSQL.

## Características

- **Busqueda por medio de consumo de una API**: Puedes buscar un libro por medio de su titulo, al hacer esto este libro se guarda automáticamente en la base de datos
- **Consultas a la DB**: Puedes consultar los libros y autores que se han guardado desde el menú de opciones
- **Manejo de errores**: El programa cuenta con validaciones de entrada y manejo de errores para garantizar una experiencia fluida.

## Menú de opciones

El programa ofrece las siguientes opciones en su menú principal:


- **Opción 1:** Buscar libro por titulo
- **Opción 2:** Listar libros registrados
- **Opción 3:** Listar autores registrados
- **Opción 4:** Listar autores vivos en un determinado año
- **Opción 5:** Listar libros por idioma
- **Opción 6:** Convierte de Euros (EUR) a Pesos Mexicanos (MXN).
- **Opción 7:** (0) Termina la ejecución del programa

## Estructura del proyecto

El proyecto está dividido de acuerdo a los siguiente paquetes:

1. **model**: 
   Paquete que contiene todas las clases encargadas de  modelar los datos y contiene las clases con las entidades Autor y Libro que realizan el mapeo en la Base de datos
   
2. **principal**:
   Paquete que contiene la clase Principal encargada de mostrar el nmenú y de contener y llamar a los métodos
   encargado de interactuar con la base de datos dependiendo de la opción del usuario

3. **Repository**:
   Paquete que contiene los repositorios de Autor y Libro para poder acceder a la BD a través de la extensión de JpaRepository, cada uno de estos contienen los métodos personalizados que se usan en el programa.

4 **Service**:
   Paquete que contiene las clases encargadas de consumir la API y de convertir los datos de tipo JSON a alguna clase

5. **Clase LiteraluraApplication**:
   Punto de entrada de la aplicación e instanciación de Principal así como inyección de dependencias.

## Ejecución del programa

Para ejecutar el programa, simplemente ejecuta la clase `LiteraluraApplication`. El programa mostrará el menú de opciones y te permitirá seleccionar la opción deseada.

### Opción 1: Buscar por titulo

Al seleccionar la opción 1 el programa pide ingresar el titulo del libro, en caso que no se encuentre un libro o el libro ya haya sido registrado se mostrará la información correspondiente, en caso contrario se registrará el libro en la BD

![image](https://github.com/user-attachments/assets/016a465e-d1e0-4845-baeb-55aa1634b3dd)

### Opción 2: Listar libros registrados

Al ingresar el digito correspondiente de la ocpión 2 el programa mostrará la información almacenada de los libros registrados

![image](https://github.com/user-attachments/assets/4755d91d-25c2-4611-b9e3-a8b0974f6a3f)

### Opción 3: Listar autores registrados

Similar a la opción 2, mostrará la información de los autores registrados en la DB

![image](https://github.com/user-attachments/assets/da0e8a21-958c-428a-980a-aa59abf56820)

### Opción 4: Listar autores vivos en un determinado año

Muestra la información de los autores que estén vivos en el año dado.

![image](https://github.com/user-attachments/assets/80cda418-4548-45cc-86fd-a525374adf4a)

### Opción 5: Listar libros por idioma

Muestra los libros cuyo idioma coincida con el código proporcionado por el usuario

![image](https://github.com/user-attachments/assets/01652ad6-4f61-40da-be76-e557b0825dc7)


## API utilizada

Este programa utiliza la API de Gutendex ([[https://www.exchangerate-api.com/](https://www.exchangerate-api.com/](https://gutendex.com/))

## Requisitos

- Java 17 o superior
- Conexión a Internet para realizar las consultas a la API

## Autor

Desarrollado por [J. Abraham Ochoa Velásquez].
