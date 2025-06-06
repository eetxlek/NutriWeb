Aplicación Web de Gestión Nutricional
Una aplicación web que facilita el control de tu alimentación diaria, ayudándote a gestionar tu despensa, consultar datos nutricionales de productos, registrar consumos y recibir recomendaciones nutricionales personalizadas.

Funcionalidades principales
📊 Recomendación nutricional personalizada basada en tus consumos y perfil.
🥫 Gestión de despensa: añade, actualiza y consulta los productos disponibles.
🍽️ Registro y consulta de consumos diarios para un seguimiento fácil y detallado.
🔍 Consulta de datos nutricionales de productos para tomar decisiones informadas.

Tecnologías y arquitectura
✅ Arquitectura Hexagonal (Ports & Adapters) para mantener el dominio aislado y facilitar mantenimiento y escalabilidad.
✅ Spring Security + JWT para autenticación segura y stateless.
✅ MySQL como base de datos relacional.
✅ Spring Data JPA para la persistencia de datos.
✅ Java, Spring Boot, Thymeleaf y JavaScript en el desarrollo backend y frontend.

Detalles técnicos (para desarrolladores)
Dominio aislado: Entidades del negocio sin dependencias directas a frameworks (ej. Usuario.java).
Persistencia: Puerto UsuarioRepository con adaptador UsuarioRepositoryJpa usando Spring Data JPA.
Seguridad: Generación y validación de tokens JWT mediante JwtTokenProvider. Adaptación de usuario para Spring Security con UserPrincipalAdapter.

Servicios clave:
AuthService: Registro y login de usuarios.
DespensaService: Gestión de productos en la despensa.
ProductoService: Consulta y registro de productos y consumos.
RecomendacionNutriService: Cálculo de recomendaciones nutricionales personalizadas.
UsuarioService: Gestión general de usuarios y recomendaciones.
