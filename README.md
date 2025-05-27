Este proyecto implementa una API REST segura utilizando: ✅ Arquitectura Hexagonal (Ports & Adapters) ✅ Spring Security + JWT para autenticación ✅ MySQL como base de datos ✅ Spring Data JPA para persistencia

Características clave:

Dominio Aislado (Hexagonal) Usuario.java: Entidad de negocio sin anotaciones JPA.
UsuarioRepository: Puerto (interfaz) para persistencia.
Infraestructura UsuarioRepositoryJpa: Adaptador de MySQL usando Spring Data JPA.
JwtTokenProvider: Generación/validación de tokens JWT.
UserPrincipalAdapter: Adapta Usuario → UserDetails para Spring Security.
Spring Security Stateless JWT: Sin sesiones.

Servicios:
AuthService: Lógica de autenticación (registro, login).
DespensaService: Si producto existe en la despensa, agregar producto a despensa, actualizar producto en despensa, obtener despensa de usuario.
ProductoService: obtener composicion de producto, guardar en consumos.
RecomendacionNutriService: obtener por usuario, calcular recomendacion.
UsuarioService: regustrar usuario, registrar recomendacion.



