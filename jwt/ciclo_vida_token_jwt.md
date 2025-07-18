# 🔐 Ciclo de Vida del Token JWT en una Aplicación Web con Spring Boot

---

## 🧱 Arquitectura General

Esta aplicación web dinámica usa:

- 🧰 **Spring Boot 3.4.3**
- 🔐 **Spring Security** para autenticación y autorización
- 📦 **Maven** para gestión de dependencias
- 📄 **JWT (com.auth0:java-jwt)** para generación y validación de tokens
- 💾 **JPA + Hibernate** para persistencia
- 🔧 **Lombok** para reducir boilerplate
- 💡 **DTOs** y **records** para estructurar datos de entrada/salida
- ⚙️ **Lambdas y Streams** de Java 8+ para manejo funcional de colecciones

---

## 📦 ¿Qué son los DTOs y los Records?

### 📬 DTO (Data Transfer Object)
Un DTO se usa para **transportar datos entre capas** de la aplicación, separando las entidades de negocio de los objetos enviados o recibidos en el API.

### 📘 Record en Java
Los records (`record`) son una forma moderna e inmutable de definir DTOs. Incluyen automáticamente:

- Constructor
- Métodos `equals`, `hashCode` y `toString`
- Getters implícitos

```java
public record AuthLoginRequestDTO(@NotBlank String username, @NotBlank String password) {}
public record AuthResponseDTO(String username, String message, String jwt, boolean status) {}
```

---

## 🔄 Ciclo de Vida del Token JWT

### 1️⃣ Login - Generación del Token

1. El usuario envía una solicitud a `/auth/login` con credenciales.
2. El controlador `AuthenticationController` recibe y pasa el DTO al servicio.
3. `UserDetailsServiceImp.loginUser(...)`:
   - Valida usuario y contraseña.
   - Usa `JwtUtils.createToken(...)` para generar el JWT.

🧠 **Contiene claims:**
- `sub` → nombre de usuario
- `authorities` → roles y permisos separados por coma
- `iat`, `exp`, `jti`, `iss` → fecha de emisión, expiración, UUID y emisor

📤 El token se devuelve al cliente dentro de `AuthResponseDTO`.

---

### 2️⃣ Validación del Token en cada petición

1. El cliente agrega el token en cada petición usando el header:
```
Authorization: Bearer <jwt_token>
```

2. El filtro `JwtTokenValidator`:
   - Excluye `/auth/login`.
   - Extrae el token del header y elimina el prefijo `"Bearer "`.
   - Llama a `jwtUtils.validateToken(token)`:
     - Verifica firma y expiración.
   - Extrae `username` y `authorities` del JWT.
   - Convierte los `authorities` en `GrantedAuthority` y setea el `SecurityContextHolder`.

---

### 3️⃣ Acceso a recursos protegidos

En los controladores usás anotaciones como:

```java
@PreAuthorize("hasRole('ADMIN')")
```

📋 Esto hace que Spring Security valide si el usuario autenticado tiene los permisos requeridos (roles o permisos) **decodificados desde el token**.

---

### 4️⃣ Expiración del Token

⏳ Los tokens tienen un tiempo de expiración (exp). Si el token:
- ❌ Está vencido → Se lanza excepción → Spring responde con 401 Unauthorized.
- ✅ Es válido → Se permite continuar con la petición.

---

## 🧠 Funciones Lambda y Streams

### 🎯 Fragmento 1:

```java
userSec.getRolesList()
       .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRole())));
```

🔍 Recorre cada rol y lo agrega a la lista de autoridades con prefijo `ROLE_`.

---

### 🎯 Fragmento 2:

```java
userSec.getRolesList().stream()
       .flatMap(role -> role.getPermissionsList().stream())
       .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));
```

🔍 Aquí:
- `stream()` inicia un flujo de roles.
- `flatMap(...)` aplana el stream de sets de permisos en un único stream.
- `forEach(...)` agrega cada permiso a la lista de autoridades.

✅ Beneficio: evita bucles anidados y mejora la legibilidad del código.

---

## 🧭 Diagrama del Flujo JWT

```mermaid
flowchart TD
  A[🧑 Cliente envía /auth/login] --> B[🔐 AuthenticationController]
  B --> C[✅ loginUser()]
  C --> D[🔍 Validación de credenciales]
  D --> E[🧾 Crear Token con JwtUtils]
  E --> F[📤 Respuesta con JWT]

  G[🔁 Cliente accede a recurso protegido con JWT] --> H[🛡️ JwtTokenValidator]
  H --> I[✅ Validar token y extraer datos]
  I --> J[🧠 Set Authentication Context]
  J --> K[🔓 Acceso a Controlador con @PreAuthorize]
```

