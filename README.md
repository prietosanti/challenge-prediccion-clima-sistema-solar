# Predicción Climática de Sistema Solar

Predice por día, y hasta 10 años, los diferentes climas que atraviesan 3 planetas de un sistema solar de acuerdo a su desplazamiento y posición.

## Tecnologías
- Spring Boot 2.0.5
- Spring Framwork 5.0.9
- Maven 3.5.2
- Java 8
- Rest
- Google App Engine
- Hibernate 5.2.17
- H2
- IntelliJ

## Estructura del proyecto
```
challenge-prediccion-clima-sistema-solar
    ├── README.md
    ├── pom.xml
    └── src
        ├── main
        │   ├── appengine
        │   │   └── app.yaml
        │   ├── java
        │   │   └── com
        │   │       └── prietosanti
        │   │           ├── PrediccionClimaSistemaSolarApplication.java
        │   │           ├── ServletInitializer.java
        │   │           ├── component
        │   │           │   └── ClimaConverter.java
        │   │           ├── configuration
        │   │           │   ├── InicioPrediccion.java
        │   │           │   └── PredictorConfiguration.java
        │   │           ├── controller
        │   │           │   └── PrediccionClimaticaController.java
        │   │           ├── entity
        │   │           │   └── Clima.java
        │   │           ├── model
        │   │           │   ├── ClimaModel.java
        │   │           │   ├── DesplazamientoCiruclar.java
        │   │           │   ├── InformeModel.java
        │   │           │   ├── ObjetoOrbitable.java
        │   │           │   ├── Planeta.java
        │   │           │   ├── PredictorClimatico.java
        │   │           │   ├── Punto.java
        │   │           │   ├── SistemaSolar.java
        │   │           │   ├── Sol.java
        │   │           │   ├── TipoClima.java
        │   │           │   └── TipoDesplazamiento.java
        │   │           ├── repository
        │   │           │   └── PrediccionClimaticaRepository.java
        │   │           ├── service
        │   │           │   ├── PrediccionClimaticaService.java
        │   │           │   └── impl
        │   │           │       └── PrediccionClimaticaServiceImpl.java
        │   │           └── util
        │   │               └── CalulosTrigonometricos.java
        │   ├── resources
        │   │   └── application.yml
        │   └── webapp
        │       └── WEB-INF
        │           └── appengine-web.xml
        └── test
            └── java
                └── com
                    └── prietosanti
                        └── model
                            ├── CalulosTrigonometricosTest.java
                            ├── PlanetaTest.java
                            └── PuntoTest.java
```

## Condiciones de Diseño
Debido a la precisión decimal, por ser un punto flotante, del valor en X e Y que se genera para cada planeta en un día determinado, se redondeó el valor devuelto para que tenga 1 sólo decimal. Por ejemplo, una posición que genere un nuevo punto `P(6.25342, 9.243415)` será  devuelto como `P(6.3, 6.2)`

La alineación de los planetas se calcula utilizando la ecuación de la recta que pasa por 2 puntos.

### Tolerancia de Error
La tolerancia de error permite que la alineación de los planetas sea más o menos precisa. Así, una tolerancia de error de **0%** sólo reconocerá como alineados a los planetas cuya posición se encuentre exactamente en la misma recta que los demás planetas.
Una mayor tolerancia permitirá que la recta que une 3 planetas, con o sin el centro, sea menos exacta.

La tolerancia se ajustó al 10% por defecto. Para modificarla se debe editar en la clase `PredictorConfiguration.java`
<img width="435" alt="tolerancia_error" src="https://user-images.githubusercontent.com/5465152/46789598-37db6d00-cd13-11e8-972b-6a2989c50b90.png">

### Periodo de Sequía
Cuando los 3 planetas están alineados entre sí y con el Sol

<img width="432" alt="sequia" src="https://user-images.githubusercontent.com/5465152/46789663-5d687680-cd13-11e8-8251-80e9205dc611.png">

### Condiciones Óptimas de Presión y Temperatura
Cuando los 3 planetas están alineados entre sí pero no con el Sol

<img width="399" alt="pyt_optima" src="https://user-images.githubusercontent.com/5465152/46789653-58a3c280-cd13-11e8-890c-7e31b12850f3.png">

### Periodo de Lluvia
Cuando el Sol está contenido dentro del triángulo que forman los 3 planetas y su perímetro no es máximo

<img width="432" alt="lluvia" src="https://user-images.githubusercontent.com/5465152/46881521-2d5bc900-ce22-11e8-90ab-f0e597ff99f6.png">

### Periodo de Lluvia Intensa
Cuando el Sol está contenido dentro del triángulo que forman los 3 planetas y su perímetro es máximo

<img width="432" alt="lluvia_intensa" src="https://user-images.githubusercontent.com/5465152/46881801-efab7000-ce22-11e8-9224-6b016a2ad397.png">

### Despejado o Nada
Cuando el Sol no está contenido dentro del triángulo que forman los 3 planetas

<img width="425" alt="despejado_nada" src="https://user-images.githubusercontent.com/5465152/46789631-4b86d380-cd13-11e8-9048-c902da5bdd30.png">

## Correr la Aplicación
Para correr de forma local se utiliza el plugin Spring Boot de Maven
`mvn spring-boot:run`
y se accede mediante `http://localhost:8080`

Para desplegar en Google App Engine
`mvn appengine:deploy`
y se accede mediante `https://climaservice-219008.appspot.com`

## Acceso a los recursos
### Informe de cantidad de períodos de cada tipo de clima
https://climaservice-219008.appspot.com/informe
```
{
  "periodosPorTipoClima" : {
    "LLUVIA" : 1975,
    "LLUVIA_INTENSA" : 40,
    "SEQUIA" : 41,
    "PYT_OPTIMA" : 41,
    "DESPEJADO" : 1554
  }
}
```
### Listar todos los días con su respectivo tipo de clima
https://climaservice-219008.appspot.com/climas
```
{
  "dia" : 46,
  "tipoClima" : {
    "descripcion" : "Cielo Despejado"
  }
}, {
  "dia" : 47,
  "tipoClima" : {
    "descripcion" : "Presión y Temperatura Óptima"
  }
}, {
  "dia" : 48,
  "tipoClima" : {
    "descripcion" : "Lluvia"
  }
}, {
  "dia" : 49,
  "tipoClima" : {
    "descripcion" : "Lluvia"
  }
}
```
### Listar un día en particular
https://climaservice-219008.appspot.com/climas/dias/72
```
{
  "dia" : 72,
  "tipoClima" : {
    "descripcion" : "Lluvia Intensa"
  }
}
```
### Listar los tipos de clima
https://climaservice-219008.appspot.com/climas/tipos
```
[ "LLUVIA", "LLUVIA_INTENSA", "SEQUIA", "PYT_OPTIMA", "DESPEJADO" ]
```
### Listar dias de un tipo de clima en particular
https://climaservice-219008.appspot.com/climas/tipos/LLUVIA
```
{
  "dia" : 20,
  "tipoClima" : {
    "descripcion" : "Lluvia"
  }
}, {
  "dia" : 21,
  "tipoClima" : {
    "descripcion" : "Lluvia"
  }
}, {
  "dia" : 22,
  "tipoClima" : {
    "descripcion" : "Lluvia"
  }
}
```

## Author
* **Santiago Prieto**
