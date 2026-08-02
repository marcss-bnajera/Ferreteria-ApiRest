# Proyecto Ferreteria - API REST

> **Estado: En desarrollo** — Proyecto de portafolio para gestionar productos de una ferreteria mediante una API REST.

API REST desarrollada con **Java** y **Spring Boot** que permite gestionar el inventario de una ferreteria (productos). Actualmente expone un CRUD completo de productos en formato JSON. El frontend con **Thymeleaf** esta en planes futuros.

## Descripcion del proyecto

Este proyecto es un sistema de gestion de inventario para una ferreteria. Hoy en dia ofrece una **API REST** con operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre la entidad `Producto`:

- **Nombre** del producto
- **Marca**
- **Categoria** (herramientas, pinturas, electricidad, etc.)
- **Precio**
- **Stock** disponible
- **Descripcion**

La aplicacion se conecta a una base de datos **PostgreSQL** en la nube (Supabase) y la tabla `productos` se crea automaticamente al iniciar la aplicacion por primera vez.

## Tecnologias

### Actuales
| Tecnologia | Uso |
|---|---|
| **Java 25** | Lenguaje de programacion |
| **Spring Boot 4.1.0** | Framework principal |
| **Spring Web MVC** | Exposicion de la API REST |
| **Spring Data JPA** | Persistencia y mapeo objeto-relacional (Hibernate) |
| **PostgreSQL** | Base de datos (alojada en Supabase) |
| **Maven** | Gestion de dependencias y construccion |
| **Lombok** | Reduccion de codigo repetitivo |
| **Spring Boot DevTools** | Recarga automatica en desarrollo |

### Futuras (en desarrollo)
- **Thymeleaf**: vistas HTML para el frontend (gestion de productos desde el navegador)

## Requisitos previos

Para clonar y ejecutar el proyecto necesitas:

- **JDK 25** o superior ([descargar](https://adoptium.net/))
- **Maven** (o usar el wrapper `./mvnw` que ya viene incluido)
- **IntelliJ IDEA** (recomendado) o cualquier IDE
- Una **cuenta de Supabase** con un proyecto creado y su cadena de conexion

## Como clonar y ejecutar

### 1. Clonar el repositorio

```bash
git clone git@github.com:marcss-bnajera/Ferreteria-ApiRest.git
cd Ferreteria-ApiRest
```

### 2. Configurar las variables de entorno

La aplicacion se conecta a la base de datos mediante variables de entorno (así no se exponen credenciales en el codigo). Necesitas definir 3 variables:

| Variable | Ejemplo | Descripcion |
|---|---|---|
| `DB_URL` | `jdbc:postgresql://db.<referencia>.supabase.co:5432/postgres` | URL de conexion JDBC de tu base de datos |
| `DB_USERNAME` | `postgres` | Usuario de la base de datos |
| `DB_PASSWORD` | `tu-contraseña` | Contraseña de la base de datos |

> En Supabase: abre tu proyecto -> boton **Connect** -> copia la cadena **Session pooler / direct connection**.
> La URL `postgresql://...` se convierte a JDBC cambiando el inicio por `jdbc:postgresql://...`.

#### En Linux / macOS (terminal)

```bash
export DB_URL="jdbc:postgresql://db.<referencia>.supabase.co:5432/postgres"
export DB_USERNAME="postgres"
export DB_PASSWORD="tu-contraseña"
```

#### En IntelliJ IDEA (recomendado)

1. Abre el proyecto con IntelliJ (el boton **Run** detecta la clase `ProyectoFerreteriaApplication` automaticamente)
2. Ve a **Run → Edit Configurations...**
3. Selecciona la configuracion de la aplicacion
4. En la seccion **Environment variables** agrega:
   ```
   DB_URL=jdbc:postgresql://db.<referencia>.supabase.co:5432/postgres;DB_USERNAME=postgres;DB_PASSWORD=tu-contraseña
   ```
5. Pulsa **Run** ▶

### 3. Ejecutar

```bash
./mvnw spring-boot:run
```

La aplicacion arrancara en `http://localhost:8080`. Al iniciar por primera vez, Hibernate crea la tabla `productos` en tu base de datos.

## Endpoints de la API

| Metodo | Ruta | Descripcion |
|---|---|---|
| `GET` | `/api/productos` | Lista todos los productos |
| `GET` | `/api/productos/{id}` | Busca un producto por su id |
| `POST` | `/api/productos` | Crea un producto (cuerpo JSON) |
| `PUT` | `/api/productos/{id}` | Actualiza un producto existente |
| `DELETE` | `/api/productos/{id}` | Elimina un producto |

### Ejemplo: crear un producto

```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Martillo",
    "marca": "Truper",
    "categoria": "Herramientas",
    "precio": 125.50,
    "stock": 30,
    "descripcion": "Martillo de carpintero"
  }'
```

## Estructura del proyecto

```
src/main/java/com/apiferreteria/ProyectoFerreteria/
├── ProyectoFerreteriaApplication.java   # Punto de entrada de Spring Boot
├── controller/
│   └── ProductoController.java          # Endpoints REST de /api/productos
├── service/
│   ├── iProductoService.java            # Interfaz del servicio
│   └── ProductoService.java             # Logica de negocio
├── repository/
│   └── iProductoRepository.java         # Acceso a datos (Spring Data JPA)
└── model/
    └── Producto.java                    # Entidad Producto
```

## Base de datos

- La tabla `productos` se crea automaticamente (propiedad `spring.jpa.hibernate.ddl-auto=update`).
- La base de datos esta alojada en **Supabase** (PostgreSQL en la nube).
- El acceso esta protegido por las credenciales definidas en las variables de entorno.

## Proximos pasos

- [ ] Frontend con **Thymeleaf** para gestionar los productos desde el navegador
- [ ] Validaciones de datos (Jakarta Validation)
- [ ] Manejo global de errores
- [ ] Tests automatizados
