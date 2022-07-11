# PROYECTO INTEGRADOR - THE INSTALOCKERS
Samuel Ortega, Brian Da Silva, José Mora, Franco Collins y Pablo García

## MANUAL MYSQL
```
Las tablas de creación, los datos de inserción y los procedimientos se encuentran en la carpeta SCRIPTS_SQL dentro del SRC.
También hay un archivo, ConexionDataBase con la información para conectase a la base de datos como administrador.
```

### Usuarios Administradores de club
```
Usuario: a     Contraseña: a
Usuario: b     Contraseña: b
```

### Usuarios Administradores de proyecto
```
Usuario: c     Contraseña: c
Usuario: d     Contraseña: d
```

### Usuarios No administradores
```
Usuario: e     Contraseña: e
Usuario: f     Contraseña: f
```

## Listado Ventana To Funcionalidad
_Listado de funcionalidades según cada ventana del proyecto_

### 0 Funcionalidad común
```
-> (0) Seleccionar tipo de usuario (Registrado, miembro, administrador).
```
### 1.1 Página principal - Inicio Sesión
```
-> (1) Loguear usuario.
-> (2) Permanecer logueado.
```
### 1.2 Página principal - Recuperar Contraseña
```
-> (3) Solicitar recuperación de contraseña.
```
### 1.3 Página principal - Registro
```
-> (4) Registrar usuario.
```
### 2 FAQS
```
-> Sin funcionalidades.
```
### 3.1 Página Clubs - Sin Registro
```
-> (5) Mostrar tarjetas de clubs.
```
### 3.2 Página Clubs - Registrado
```
-> (5) Mostrar tarjetas de clubs.
-> (9) Cerrar sesión usuario.
```
### 4 Perfil Usuario
```
-> (6) Mostrar datos de usuario.
-> (7) Editar datos de usario.
-> (8) Eliminar usuario.
-> (9) Cerrar sesión usuario.
```
### 4.1 Perfil Usuario - Formulario cambiar contraseña (Ventana emergente)
```
-> (10) Cambiar contraseña.
```
### 5.1 Mis clubs y proyectos - Clubs
```
-> (11) Mostrar clubs del usuario.
-> (12) Eliminar usuario del club.
-> (9) Cerrar sesión usuario.
```
### 5.2 Mis clubs y proyectos - Proyectos
```
-> (13) Mostrar proyectos del usuario.
-> (14) Eliminar usuario del proyecto.
-> (9) Cerrar sesión usuario.
```
### 6.1 Solicitudes pendientes - Clubs
```
-> (15) Mostrar solicitudes de unión a club del usuario.
-> (16) Eliminar solicitud de unión a club del usuario.
-> (9) Cerrar sesión usuario.
```
### 6.2 Solicitudes pendientes - Proyectos
```
-> (17) Mostrar solicitudes de unión a proyecto del usuario.
-> (18) Eliminar solicitud de unión a proyecto del usuario.
-> (9) Cerrar sesión usuario.
```
### 7.1 Club único - Sin membresía
```
-> (19) Mostrar datos del club.
-> (20) Mostrar proyectos abiertos del club.
-> (21) Enviar solicitud de unión al club.
-> (9) Cerrar sesión usuario.
```
### 7.2 Club único - Miembro y administrador
```
-> (19) Mostrar datos del club.
-> (22) Mostrar todos los proyectos del club.
-> (23) Mostrar noticias del club.
-> (9) Cerrar sesión usuario.
```
### 8 Formulario creación club
```
-> (24) Enviar formulario de solicitud de creación de club.
-> (9) Cerrar sesión usuario.
```
### 9 Formulario creación proyecto - Administrador
```
-> (25) Formulario creación de proyecto.
-> (9) Cerrar sesión usuario.
```
### 10 Proyectos abiertos
```
-> (26) Mostrar proyectos abiertos.
-> (9) Cerrar sesión usuario.
```
### 11.1 Proyecto único - Sin miembro
```
-> (27) Mostrar datos del proyecto.
-> (28) Enviar solicitud de unión al proyecto.
-> (9) Cerrar sesión usuario.
```
### 11.2 Proyecto único - Miembro y administrador
```
-> (27) Mostrar datos del proyecto.
-> (29) Mostrar noticias del proyecto.
-> (9) Cerrar sesión usuario.
```
### 12 Contacto (Ventana emergente)
```
-> (30) Enviar formulario de ayuda.
```
### 13 Nuevo Post - Administrador
```
-> (31) Enviar formulario creación noticia.
-> (32) Mostrar todos los clubs y proyectos administrados.
-> (9) Cerrar sesión usuario.
```
### 14.1 Solicitudes de unión - Clubs - Administrador
```
-> (33) Mostrar todas las solicitudes de unión de los clubs administrados.
-> (34) Eliminar solicitud/es de unión a club.
-> (9) Cerrar sesión usuario.
```
### 14.2 Solicitudes de unión - Proyectos - Administrador
```
-> (35) Mostrar todas las solicitudes de unión de los proyectos administrados.
-> (36) Eliminar solicitud/es de unión a proyecto.
-> (9) Cerrar sesión usuario.
```
### 15 Administrar usuarios - Administrador
```
-> (37) Mostrar todas los clubs y proyectos administrados.
-> (38) Mostrar todos los usuarios del club o proyecto.
-> (39) Eliminar usuario del club o proyecto.
-> (40) Ascender a administrador del club o proyecto.
-> (9) Cerrar sesión usuario.
```
## Listado Funcionalidad To Ventana
_Listado de funcionalidades según cada ventana del proyecto_

### (1) Loguear usuario.
```
-> 1.1 Página principal - Inicio Sesión
```
### (2) Permanecer logueado.
```
-> 1.1 Página principal - Inicio Sesión
```
### (3) Solicitar recuperación de contraseña.
```
-> 1.2 Página principal - Recuperar Contraseña
```
### (4) Registrar usuario.
```
-> 1.3 Página principal - Registro
```
### (5) Mostrar tarjetas de clubs.
```
-> 3.1 Página Clubs - Sin Registro
-> 3.2 Página Clubs - Registrado
```
### (6) Mostrar datos de usuario.
```
-> 4 Perfil Usuario
```
### (7) Editar datos de usario.
```
-> 4 Perfil Usuario
```
### (8) Eliminar usuario.
```
-> 4 Perfil Usuario
```
### (9) Cerrar sesión usuario.
```
-> 3.2 Página Clubs - Registrado
-> 4 Perfil Usuario
-> 5.1 Mis clubs y proyectos - Clubs
-> 5.2 Mis clubs y proyectos - Proyectos
-> 6.1 Solicitudes pendientes - Clubs
-> 6.2 Solicitudes pendientes - Proyectos
-> 7.1 Club único - Sin membresía
-> 7.2 Club único - Miembro y administrador
-> 8 Formulario creación club
-> 9 Formulario creación proyecto - Administrador
-> 10 Proyectos abiertos
-> 11.1 Proyecto único - Sin miembro
-> 11.2 Proyecto único - Miembro y administrador
-> 13 Nuevo Post - Administrador
-> 14.1 Solicitudes de unión - Clubs - Administrador
-> 14.2 Solicitudes de unión - Proyectos - Administrador
-> 15 Administrar usuarios - Administrador
```
### (10) Cambiar contraseña.
```
-> 4.1 Perfil Usuario - Formulario cambiar contraseña (Ventana emergente)
```
### (11) Mostrar clubs del usuario.
```
-> 5.1 Mis clubs y proyectos - Clubs
```
### (12) Eliminar usuario del club.
```
-> 5.1 Mis clubs y proyectos - Clubs
```
### (13) Mostrar proyectos del usuario.
```
-> 5.2 Mis clubs y proyectos - Proyectos
```
### (14) Eliminar usuario del proyecto.
```
-> 5.2 Mis clubs y proyectos - Proyectos
```
### (15) Mostrar solicitudes de unión a club del usuario.
```
-> 6.1 Solicitudes pendientes - Clubs
```
### (16) Eliminar solicitud de unión a club del usuario.
```
-> 6.1 Solicitudes pendientes - Clubs
```
### (17) Mostrar solicitudes de unión a proyecto del usuario.
```
-> 6.2 Solicitudes pendientes - Proyectos
```
### (18) Eliminar solicitud de unión a proyecto del usuario.
```
-> 6.2 Solicitudes pendientes - Proyectos
```
### (19) Mostrar datos del club.
```
-> 7.1 Club único - Sin membresía
-> 7.2 Club único - Miembro y administrador
```
### (20) Mostrar proyectos abiertos del club.
```
-> 7.1 Club único - Sin membresía
```
### (21) Enviar solicitud de unión al club.
```
-> 7.1 Club único - Sin membresía
```
### (22) Mostrar todos los proyectos del club.
```
-> 7.2 Club único - Miembro y administrador
```
### (23) Mostrar noticias del club.
```
-> 7.2 Club único - Miembro y administrador
```
### (24) Enviar formulario de solicitud de creación de club.
```
-> 8 Formulario creación club
```
### (25) Formulario creación de proyecto.
```
-> 9 Formulario creación proyecto - Administrador
```
### (26) Mostrar proyectos abiertos.
```
-> 10 Proyectos abiertos
```
### (27) Mostrar datos del proyecto.
```
-> 11.1 Proyecto único - Sin miembro
-> 11.2 Proyecto único - Miembro y administrador
```
### (28) Enviar solicitud de unión al proyecto.
```
-> 11.1 Proyecto único - Sin miembro
```
### (29) Mostrar noticias del proyecto.
```
-> 11.2 Proyecto único - Miembro y administrador
```
### (30) Enviar formulario de ayuda.
```
-> 12 Contacto (Ventana emergente)
```
### (31) Enviar formulario creación noticia.
```
-> 13 Nuevo Post - Administrador
```
### (32) Mostrar todos los clubs y proyectos administrados.
```
-> 13 Nuevo Post - Administrador
```
### (33) Mostrar todas las solicitudes de unión de los clubs administrados.
```
-> 14.1 Solicitudes de unión - Clubs - Administrador
```
### (34) Eliminar solicitud/es de unión a club.
```
-> 14.1 Solicitudes de unión - Clubs - Administrador
```
### (35) Mostrar todas las solicitudes de unión de los proyectos administrados.
```
-> 14.2 Solicitudes de unión - Proyectos - Administrador
```
### (36) Eliminar solicitud/es de unión a proyecto.
```
-> 14.2 Solicitudes de unión - Proyectos - Administrador
```
### (37) Mostrar todas los clubs y proyectos administrados.
```
-> 15 Administrar usuarios - Administrador
```
### (38) Mostrar todos los usuarios del club o proyecto.
```
-> 15 Administrar usuarios - Administrador
```
### (39) Eliminar usuario del club o proyecto.
```
-> 15 Administrar usuarios - Administrador
```
### (40) Ascender a administrador del club o proyecto.
```
-> 15 Administrar usuarios - Administrador
```
