-- Login users
/*	1. (Select) Pedir usuario y contraseña.
    Procedimiento de prueba para registrarse, devuelve el nombre de usuario, su contraseña y el rol */
DELIMITER $ $
CREATE PROCEDURE `Login_User_Prueba`(
    IN `Id_Username` VARCHAR(30),
    IN `Id_Pwd` VARCHAR(30)
)
SELECT usr,
       pwd,
       rol
FROM users
WHERE usr = Id_Username
  AND pwd = Id_Pwd $ $ DELIMITER ;

-- Login registrado
/*	2. (Select) Pedir usuario y contraseña.
    Procedimiento que pide el código de usuario, el username y la contraseña */
DELIMITER $ $
CREATE PROCEDURE `Login_User`(
    IN `Id_Username` VARCHAR(30),
    IN `Id_Contrasena` VARCHAR(30)
)
SELECT cod_registrado,
       username,
       contrasena
FROM registrado
WHERE username = Id_Username
  AND contrasena = Id_Contrasena $ $ DELIMITER ;

-- Crear cuenta
/*	3. (Insert) Enviar datos de registro.
    Procedimiento para que el usuario se pueda registrar */
DELIMITER $ $
CREATE PROCEDURE `Registrar_User`(
    IN `Id_Username` VARCHAR(32),
    IN `Id_Nombre` VARCHAR(32),
    IN `Id_Apellido1` VARCHAR(32),
    IN `Id_Apellido2` VARCHAR(32),
    IN `Id_FechaNacimiento` DATE,
    IN `Id_Email` VARCHAR(50),
    IN `Id_Contrasena` VARCHAR(32)
)
INSERT INTO registrado (username,
                        nombre,
                        apellido1,
                        apellido2,
                        fecha_nacimiento,
                        email,
                        contrasena)
VALUES (Id_Username,
        Id_Nombre,
        Id_Apellido1,
        Id_Apellido2,
        Id_FechaNacimiento,
        Id_Email,
        Id_Contrasena) $ $ DELIMITER ;

-- Barra lateral
/*	4. (Select) Solicitar nombre y apellidos del usuario.
    Procedimiento que solicita el nombre y los apellidos del usuario tras recibir por parámetro el código de usuario */
DELIMITER $ $
CREATE PROCEDURE `Nombre_Apellidos_User`(IN `Id_User` INT)
SELECT nombre,
       apellido1,
       apellido2
FROM registrado
WHERE cod_registrado = id_user $ $ DELIMITER ;

/*	5. (Select) Solicitar si el usuario administra clubs
    Procedimiento que se utiliza a la hora de construir la barra lateral para saber si el usuario es administrador de club */
DELIMITER $ $
CREATE PROCEDURE `Is_Admin_Club`(IN `Id_User` INT)
SELECT *
FROM rep_club
WHERE REGISTRADO_COD_REGISTRADO = Id_User $ $ DELIMITER ;

/*	6. (Select) Solicitar si el usuario administra proyectos
    Procedimiento que se utiliza a la hora de construir la barra lateral para saber si el usuario es administrador de proyecto */

DELIMITER $ $
CREATE PROCEDURE `Is_Admin_Proyecto`(IN `Id_User` INT)
SELECT *
FROM rep_proyecto
WHERE REGISTRADO_COD_REGISTRADO = Id_User $ $ DELIMITER ;

-- Mi Perfil
/*	7. (Select) Solicitar los datos del usuario
    Procedimiento para solicitar los datos de usuario tras pasarle el cod_registrado por parámetro */
DELIMITER $ $
CREATE PROCEDURE `Perfil_User`(IN `Id_User` INT)
SELECT username,
       nombre,
       apellido1,
       apellido2,
       email,
       estudios,
       foto
FROM registrado
WHERE cod_registrado = Id_User $ $ DELIMITER ;

/*	8. (Update) Cambiar contraseña
    Procedimiento para cambiar la contraseña del usuario tras pasarle el cod_registrado por parámetro */
DELIMITER $ $
CREATE PROCEDURE `Cambiar_contrasena`(IN `Id_User` INT, IN `Id_Contrasena` VARCHAR(32))
UPDATE
    registrado
SET contrasena = Id_Contrasena
WHERE cod_registrado = Id_User $ $ DELIMITER ;

/*	9. (Update) Cambiar email y estudios
    Procedimiento para actualizar el email y los estudios del usuario tras pasarle el cod_registrad por parámetro */
DELIMITER $$
CREATE PROCEDURE `Cambiar_Email_Estudios`(IN `Id_User` INT, IN `Id_Email` VARCHAR(50), IN `Id_Estudios` VARCHAR(32))
UPDATE registrado
SET registrado.email    = Id_Email,
    registrado.estudios = Id_Estudios
WHERE registrado.cod_registrado = Id_User$$
DELIMITER ;

/*	10. (Delete) Eliminar usuario
    Procedimiento para eliminar al usuario de la base de datos */
DELIMITER $ $
CREATE PROCEDURE `Eliminar_User_Club`(IN `Id_User` INT, IN `Id_Club` INT)
DELETE
FROM pert_club
WHERE registrado_cod_registrado = Id_User
  AND club_cod_club = Id_Club $ $ DELIMITER ;

-- Mis clubs
/*	11. (Select) Solicitar todos los clubs a los cuales pertenece el usuario.
   Procedimiento que solicita los clubs a los cuales pertenece el usuario pasando por parámetro el cod_registrado */
DELIMITER $ $
CREATE PROCEDURE `Mis_Clubs`(IN Id_User int)
SELECT cod_club,
       nombre
FROM club
WHERE cod_club IN (
    SELECT club_cod_club
    FROM pert_club
    WHERE fecha_alta IS NOT NULL
      AND registrado_cod_registrado IN (
        SELECT cod_registrado
        FROM registrado
        WHERE cod_registrado = Id_User
    )
) $ $ DELIMITER ;

/*  12. (Select) Solicitar dos últimas noticias de club
    Procedimiento que se debe ejecutar después del de Mis_Clubs para añadir las dos últimas noticias de cada club al que
    pertenece el usuario, para ello enviamos por parámetro el cod_club.  */
DELIMITER $ $
CREATE PROCEDURE `Mis_Clubs_Noticias`(IN Id_Club int, IN Id_User int)
SELECT titulo,
       contenido,
       fecha,
       (
           SELECT username
           FROM registrado
           WHERE cod_registrado = registrado_cod_registrado
       ) AS autor
FROM noticia_club
WHERE CLUB_COD_CLUB = Id_Club
LIMIT 2 $ $ DELIMITER ;

/*	13. (Delete) Abandonar club & (Delete) Eliminar solicitud al club
   Procedimiento para eliminar al usuario de la tabla de pert_club, sirve para que el usuario abandone el club,
   para que el usuario pueda cancelar la solicitud de unión y para que el administrador pueda eliminar al usuario del club */
DELIMITER $ $
CREATE PROCEDURE `Eliminar_User_Club`(IN `Id_User` INT, IN `Id_Club` INT)
DELETE
FROM pert_club
WHERE registrado_cod_registrado = Id_User
  AND club_cod_club = Id_Club $ $ DELIMITER ;

-- Mis proyectos
/*	14. (Select) Solicitar todos los proyectos a los cuales pertenece el usuario.
   Procedimiento que solicita los proyectos a los cuales pertenece el usuario pasando por parámetro el cod_registrado */
DELIMITER $ $
CREATE PROCEDURE `Mis_Proyectos`(IN Id_User int)
SELECT cod_proyecto,
       Nombre,
       (
           SELECT nombre
           FROM club
           WHERE cod_club = CLUB_COD_CLUB
       ) "Club_Propietario"
FROM proyecto
WHERE cod_proyecto IN (
    SELECT proyecto_cod_proyecto
    FROM pert_proyecto
    WHERE fecha_alta IS NOT NULL
      AND registrado_cod_registrado IN (
        SELECT cod_registrado
        FROM registrado
        WHERE cod_registrado = Id_User
    )
) $ $ DELIMITER ;

/*  15. (Select) Solicitar dos últimas noticias del proyecto
    Procedimiento que se debe ejecutar después del de Mis_Proyectos para añadir las dos últimas noticias de cada proyecto
    al que pertenece el usuario, para ello enviamos por parámetro el cod_proyecto.  */
DELIMITER $ $
CREATE PROCEDURE `Mis_Proyectos_Noticias`(IN Id_Proyecto int, IN Id_User int)
SELECT titulo,
       contenido,
       fecha,
       (
           SELECT username
           FROM registrado
           WHERE cod_registrado = registrado_cod_registrado
       ) AS autor
FROM noticia_proyecto
WHERE proyecto_cod_proyecto = Id_Proyecto
LIMIT 2 $ $ DELIMITER ;

/*	16. (Delete) Abandonar proyecto & (Delete) Eliminar solicitud al proyecto
    Procedimiento para eliminar al usuario de la tabla de pert_proyecto, sirve para que el usuario abandone el proyecto,
   para que el usuario pueda cancelar la solicitud de unión y para que el administrador pueda eliminar al usuario del proyecto */
DELIMITER $ $
CREATE PROCEDURE `Eliminar_User_Proyecto`(IN `Id_User` INT, IN `Id_Proyecto` INT)
DELETE
FROM pert_proyecto
WHERE registrado_cod_registrado = Id_User
  AND proyecto_cod_proyecto = Id_Proyecto $ $ DELIMITER ;

-- Club único
/*  17. (Select) Ver si usuario es administrador del club
    Procedimiento para saber si se debe construir la página de club único
    para un Administrador en caso la columna usuario devuelva (A) */
DELIMITER $$
CREATE PROCEDURE `Es_Admin_Club`(IN `Id_User` INT, IN `Id_Club` INT)
SELECT CASE
           WHEN cod_registrado IN ((SELECT registrado_cod_registrado
                                    FROM rep_club
                                    WHERE rep_club.club_cod_club = Id_Club
           ))
               THEN 'A'
           ELSE ''
           END
           AS "usuario"
FROM registrado
WHERE cod_registrado = Id_User$$
DELIMITER ;

/*   18. (Select) Ver si usuario es miembro del club
     Procedimiento que se ejecuta despues de Es_Admin_Club, para saber si se debe construir la página de club único
     para un Miembro (M) o un Registrado (R)*/
DELIMITER $$
CREATE PROCEDURE `Es_Miembro_Club`(IN `Id_User` INT, IN `Id_Club` INT)
SELECT CASE
           WHEN cod_registrado IN ((SELECT registrado_cod_registrado
                                    FROM pert_club
                                    WHERE pert_club.club_cod_club = Id_Club
                                      AND pert_club.fecha_alta IS NOT NULL))
               THEN 'M'
           WHEN cod_registrado IN ((SELECT registrado_cod_registrado
                                    FROM pert_club
                                    WHERE pert_club.club_cod_club = Id_Club
                                      AND pert_club.fecha_alta IS NULL))
               THEN 'S'
           ELSE 'R'
           END
           AS "usuario"
FROM registrado
WHERE cod_registrado = Id_User$$
DELIMITER ;

/*	19. (Select) Solicitar los datos del club
   Procedimiento para solicitar todos los datos de un club tras pasar por parámetro el cod_club */
DELIMITER $$
CREATE PROCEDURE `Datos_Club`(IN `Id_Club` INT)
SELECT DISTINCT cod_club,
                nombre,
                descripcion,
                contacto,
                banner,
                localizacion,
                (Select COUNT(*)
                 FROM pert_club
                 WHERE pert_club.club_cod_club = Id_Club
                   AND pert_club.fecha_alta IS NOT NULL) As num_integrantes
FROM club,
     pert_club
WHERE cod_club = Id_Club$$
DELIMITER ;

/*	20. (Select) Solicitar los proyectos del club (0 es privado y 1 es público)
   Procedimiento para solicitar los proyectos que tiene el club, se mostrará dentro de la página del club único
   en caso de que el usuario sea Miembro o Administrador */
DELIMITER $ $
CREATE PROCEDURE `Proyectos_Club`(IN `Id_Club` INT)
SELECT cod_proyecto,
       Nombre,
       Descripcion,
       Contacto,
       privado
FROM proyecto
WHERE club_cod_club = Id_Club $ $ DELIMITER ;

/*	21. (Insert) Enviar solicitud al club del usuario
    Procedimiento para que el usuario solicite entrar en un club, necesario enviar por parámetro el cod_registrado
    y el cod_club */
DELIMITER $ $
CREATE PROCEDURE `Solicitar_Union_Club`(IN `Id_User` INT, IN `Id_Club` INT)
INSERT INTO pert_club (registrado_cod_registrado, club_cod_club)
VALUES (Id_User, Id_Club) $ $ DELIMITER ;

/*	22. (Select) Solicitar las noticias del club
   Procedimiento que solicita todas las noticias de un club para mostrarlas en su página, necesario enviar por
   parámetro el cod_club */
DELIMITER $ $
CREATE PROCEDURE `Noticias_Club`(IN `Id_Club` INT)
SELECT titulo,
       contenido,
       fecha,
       (
           SELECT username
           FROM registrado
           WHERE cod_registrado = registrado_cod_registrado
       ) as "autor"
FROM noticia_club
WHERE CLUB_COD_CLUB = Id_Club $ $ DELIMITER ;

-- Club único admin
/*	23. (Insert) Crear proyecto
   Procedimiento para crear un proyecto desde la página de club único */
DELIMITER $ $
CREATE PROCEDURE `Crear_Proyecto`(
    IN `Id_Nombre` VARCHAR(32),
    IN `Id_Descripcion` VARCHAR(500),
    IN `Id_Contacto` VARCHAR(32),
    IN `Id_Privado` BOOLEAN,
    IN `Id_Club` INT
)
INSERT INTO proyecto (nombre,
                      descripcion,
                      contacto,
                      fecha_alta,
                      privado,
                      club_cod_club)
VALUES (Id_Nombre,
        Id_Descripcion,
        Id_Contacto,
        NOW(),
        Id_Privado,
        Id_Club) $ $ DELIMITER ;

/*	24. (Insert) Vicular usuario al proyecto como miembro
   Procedimiento que se debe ejecutar después del de creación de proyecto para agregar como miembro al usuario que
   ha creado el proyecto */
DELIMITER $$
CREATE PROCEDURE `Crear_Proyecto_Añadir_Miembro`(IN `Id_User` INT)
INSERT INTO pert_proyecto (registrado_cod_registrado, proyecto_cod_proyecto, fecha_alta)
VALUES (Id_User, (SELECT proyecto.cod_proyecto FROM proyecto ORDER BY proyecto.cod_proyecto DESC LIMIT 1), now())$$
DELIMITER ;

/*	25. (Insert) Vicular usuario al proyecto como admimistrador
    Procedimiento que se debe ejecutar después del de creación de proyecto para agregar como administrador al usuario que
   ha creado el proyecto */
DELIMITER $$
CREATE PROCEDURE `Crear_Proyecto_Añadir_Admin`(IN `Id_User` INT)
INSERT INTO rep_proyecto (registrado_cod_registrado, proyecto_cod_proyecto)
VALUES (Id_User, (SELECT proyecto.cod_proyecto FROM proyecto ORDER BY proyecto.cod_proyecto DESC LIMIT 1))$$
DELIMITER ;

-- Proyecto único sin registrar
/* 26. (Select) Ver si usuario es administrador del proyecto
    Procedimiento qpara saber si se debe construir la página de proyecto único
    para un Administrador en caso la columna usuario devuelva (A) */
DELIMITER $$
CREATE PROCEDURE `Es_Admin_Proyecto`(IN `Id_User` INT, IN `Id_Proyecto` INT)
SELECT CASE
           WHEN cod_registrado IN ((SELECT registrado_cod_registrado
                                    FROM rep_proyecto
                                    WHERE rep_proyecto.proyecto_cod_proyecto = Id_Proyecto
           ))
               THEN 'A'
           ELSE ''
           END
           AS "usuario"
FROM registrado
WHERE cod_registrado = Id_User$$
DELIMITER ;

/* 27. (Select) Ver si usuario es miembro del proyecto
    Procedimiento para saber si se debe construir la página de proyecto único para un Registrado en caso de que la
   columna devuelva (R) o para un Miembro en caso la columna usuario devuelva (M) */
DELIMITER $$
CREATE PROCEDURE `Es_Miembro_Proyecto`(IN `Id_User` INT, IN `Id_Proyecto` INT)
SELECT CASE
           WHEN registrado.cod_registrado IN (SELECT pert_proyecto.registrado_cod_registrado
                                              FROM pert_proyecto
                                              WHERE pert_proyecto.proyecto_cod_proyecto = Id_Proyecto
                                                AND pert_proyecto.fecha_alta IS NOT NULL)
               THEN 'M'
           WHEN registrado.cod_registrado IN (SELECT pert_proyecto.registrado_cod_registrado
                                              FROM pert_proyecto
                                              WHERE pert_proyecto.proyecto_cod_proyecto = Id_Proyecto
                                                AND pert_proyecto.fecha_alta IS NULL)
               THEN 'S'
           ELSE 'R'
           END
           AS "usuario"

FROM registrado
WHERE cod_registrado = Id_User$$
DELIMITER ;

/*	28. (Select) Solicitar los datos del proyecto
   Procedimiento para solicitar todos los datos del proyecto pasando por parámetro el cod_proyecto */
DELIMITER $$
CREATE PROCEDURE `Datos_Proyecto`(IN `Id_Proyecto` INT)
SELECT cod_proyecto,
       nombre,
       descripcion,
       contacto,
       (
           SELECT nombre
           FROM club
           WHERE cod_club = CLUB_COD_CLUB
       )                                            AS Club,
       (Select COUNT(*)
        FROM pert_proyecto
        WHERE pert_proyecto.proyecto_cod_proyecto = Id_Proyecto
          AND pert_proyecto.fecha_alta IS NOT NULL) As num_integrantes

FROM proyecto
WHERE cod_proyecto = Id_Proyecto$$
DELIMITER ;

/*	29. (Insert) Enviar solicitud al proyecto del usuario
   Procedimiento para que el usuario pueda enviar la solicitud al proyecto, necesario parámetro con
   cod_registrado y cod_proyecto */
DELIMITER $ $
CREATE PROCEDURE `Solicitar_Union_Proyecto`(IN `Id_User` INT, IN `Id_Proyecto` INT)
INSERT INTO pert_proyecto (registrado_cod_registrado, proyecto_cod_proyecto)
VALUES (Id_User, Id_Proyecto) $ $ DELIMITER ;

/*	30. (Select) Solicitar las noticias del proyecto
    Procedimiento para solicitar todas las noticias del proyecto pasando por parámetro el cod_proyecto */
DELIMITER $ $
CREATE PROCEDURE `Noticias_Proyecto`(IN `Id_Proyecto` INT)
SELECT titulo,
       contenido,
       fecha,
       (
           SELECT username
           FROM registrado
           WHERE cod_registrado = registrado_cod_registrado
       ) as "autor"
FROM noticia_proyecto
WHERE proyecto_cod_proyecto = Id_Proyecto $ $ DELIMITER ;

-- Solicitudes pendientes clubs
/*	31. (Select) Solicitar información de los clubs con solicitud pendiente
    Procedimiento para mostrar todas las solicitudes pendientes de club del usuario, pasando por
   parámetro su cod_registrado */
DELIMITER $ $
CREATE PROCEDURE `Solicitudes_Pendientes_Club`(IN `Id_User` INT)
SELECT cod_club,
       nombre,
       descripcion
FROM club
WHERE cod_club IN (
    SELECT club_cod_club
    FROM pert_club
    WHERE fecha_alta IS NULL
      AND registrado_cod_registrado = Id_User
) $ $ DELIMITER ;

-- Solicitudes pendientes proyectos
/*	32. (Select) Solicitar información de los proyectos con solicitud pendiente
    Procedimiento para mostrar todas las solicitudes pendientes de proyecto del usuario, pasando por
   parámetro su cod_registrado */
DELIMITER $ $
CREATE PROCEDURE `Solicitudes_Pendientes_Proyecto`(IN `Id_User` INT)
SELECT cod_proyecto,
       nombre,
       descripcion,
       (
           SELECT nombre
           FROM club
           WHERE cod_club = CLUB_COD_CLUB
       )
FROM proyecto
WHERE cod_proyecto IN (
    SELECT proyecto_cod_proyecto
    FROM pert_proyecto
    WHERE fecha_alta IS NULL
      AND registrado_cod_registrado = Id_User
) $ $ DELIMITER ;

-- Clubs universidad
/*	33. (Select) Solicitar lista de clubs
   Procedimiento para solicitar la información de todos los clubs de la universidad */
DELIMITER $ $
CREATE PROCEDURE `Todos_Clubs`()
SELECT cod_club,
       nombre,
       descripcion,
       banner
FROM club $ $ DELIMITER ;

-- Proyectos abiertos
/*	34. (Select) Solicitar información de proyectos abiertos
   Procedimiento para solicitar la información de todos los proyectos abiertos de la universidad */
DELIMITER $$
CREATE PROCEDURE `Todos_Proyectos_Abiertos`(IN `Id_User` INT)
    NO SQL
SELECT cod_proyecto, nombre, descripcion, (SELECT nombre FROM club where cod_club = club_cod_club) AS autor
FROM proyecto
WHERE privado = 0$$
DELIMITER ;

-- Contacto
/*	35. (Insert) Enviar solicitud de contacto
   Procedimiento para enviar la solicitud de contacto en las FAQS */
DELIMITER $$
CREATE PROCEDURE `Solicitud_Contacto`(IN `Id_Email` INT, IN `Id_Contenido` INT)
    NO SQL
INSERT INTO consultas_usuarios (email_usuario, contenido_consulta, fecha_consulta)
VALUES (Id_Email, Id_Contenido, now()) $$
DELIMITER ;

-- Nuevo post
/*	36. (Insert) Enviar información del post club
   Procedimiento para crear el post de un club */
DELIMITER $$
CREATE PROCEDURE `Nuevo_Post_Club`(IN `Id_Titulo` INT, IN `Id_Contenido` INT, IN `Id_Club` INT, IN `Id_Registrado` INT)
    NO SQL
INSERT INTO noticia_club (fecha, titulo, contenido, club_cod_club, registrado_cod_registrado)
VALUES (now(), Id_Titulo, Id_Contenido, Id_Club, Id_Registrado) $$
DELIMITER ;

/*	37. (Insert) Enviar información del post proyecto
   Procedimiento para crear el post de un proyecto */
DELIMITER $$
CREATE PROCEDURE `Nuevo_Post_Proyecto`(IN `Id_Titulo` INT, IN `Id_Contenido` INT, IN `Id_Proyecto` INT,
                                       IN `Id_Registrado` INT)
    NO SQL
INSERT INTO noticia_proyecto (fecha, titulo, contenido, proyecto_cod_proyecto, registrado_cod_registrado)
VALUES (now(), Id_Titulo, Id_Contenido, Id_Proyecto, Id_Registrado)$$
DELIMITER ;

-- Solicitudes de unión club
/*	38. (Select) Solicitar información de las solicitudes de todos los clubs que administra el usuario
   Procedimiento para mostrar todas las solicitudes de unión de los clubs que administra el usuario */
DELIMITER $$
CREATE PROCEDURE `Solicitudes_Union_Club`(IN `Id_User` INT)
    NO SQL
SELECT c.nombre                                            club,
       r.username,
       CONCAT(r.nombre, ' ', apellido1, ' ', apellido2) as "Nombre",
       estudios,
       cod_registrado,
       cod_club
FROM club c,
     registrado r,
     pert_club pc,
     rep_club rc
WHERE cod_club = pc.club_cod_club
  AND cod_registrado = pc.registrado_cod_registrado
  AND pc.fecha_alta IS NULL
  AND rc.registrado_cod_registrado = Id_User
  AND rc.club_cod_club = cod_club$$
DELIMITER ;

/*	39. (Select) Solicitar información de las solicitudes de todos los proyecto que administra el usuario
    Procedimiento para mostrar todas las solicitudes de unión de los proyectos que administra el usuario */
DELIMITER $$
CREATE PROCEDURE `Solicitudes_Union_Proyecto`(IN `Id_User` INT)
    NO SQL
SELECT c.nombre                                            proyecto,
       r.username,
       CONCAT(r.nombre, ' ', apellido1, ' ', apellido2) as "Nombre",
       estudios,
       cod_registrado,
       cod_proyecto
FROM proyecto c,
     registrado r,
     pert_proyecto pc,
     rep_proyecto rc
WHERE cod_proyecto = pc.proyecto_cod_proyecto
  AND cod_registrado = pc.registrado_cod_registrado
  AND pc.fecha_alta IS NULL
  AND rc.registrado_cod_registrado = Id_User
  AND rc.proyecto_cod_proyecto = cod_proyecto$$
DELIMITER ;

/*	40. (Upadate) Aceptar solicitudes de usuarios en club
   Procedimiento para que el administrador pueda aceptar solicitudes de unión al club, se realiza al poner fecha_alta
   del momento en que se acepta al usuario tras pasar por parámetro el cod_registrado y el cod_club */
DELIMITER $$
CREATE PROCEDURE `Aceptar_Miembro_Club`(IN `Id_User` INT, IN `Id_Club` INT)
    NO SQL
UPDATE pert_club
SET fecha_alta = now()
WHERE registrado_cod_registrado = Id_User
  AND club_cod_club = Id_Club$$
DELIMITER ;

/*	41. (Upadate) Aceptar solicitudes de usuarios en proyecto
    Procedimiento para que el administrador pueda aceptar solicitudes de unión al proyecto, se realiza al poner fecha_alta
   del momento en que se acepta al usuario tras pasar por parámetro el cod_registrado y el cod_proyecto */
DELIMITER $$
CREATE PROCEDURE `Aceptar_Miembro_Proyecto`(IN `Id_User` INT, IN `Id_Proyecto` INT)
    NO SQL
UPDATE pert_proyecto
SET fecha_alta = now()
WHERE registrado_cod_registrado = Id_User
  AND pert_proyecto.proyecto_cod_proyecto = Id_Proyecto$$
DELIMITER ;

-- Administrar usuarios
/*	42. (Select) Solicitar los clubs que administra el usuario
   Procedimiento para pedir el nombre del club y su código de todos los clubs que el usuario administra */
DELIMITER $ $
CREATE PROCEDURE `Todos_Clubs_Administrados`(IN `Id_User` INT)
    NO SQL
SELECT (
           SELECT nombre
           FROM club
           WHERE cod_club = club_cod_club
       ),
       club_cod_club
FROM rep_club
WHERE REGISTRADO_COD_REGISTRADO = Id_User $ $ DELIMITER ;

/*	43. (Select) Solicitar los proyectos que administra el usuario
    Procedimiento para pedir el nombre del proyecto y su código de todos los proyectos que el usuario administra */
DELIMITER $ $
CREATE PROCEDURE `Todos_Proyectos_Administrados`(IN `Id_User` INT)
    NO SQL
SELECT (
           SELECT nombre
           FROM proyecto
           WHERE cod_proyecto = proyecto_cod_proyecto
       ),
       proyecto_cod_proyecto
FROM rep_proyecto
WHERE registrado_cod_registrado = Id_User $ $ DELIMITER ;

/*	44. (Select) Solicitar todos los usuarios que pertenecen al club
    Procedimiento para mostrar los usuarios que pertenecen al club, si son administradores, la columna es_admin
   devolverá un 1, si no lo son, un 0 */
DELIMITER $$
CREATE PROCEDURE `Usuarios_Pertenecen_Club`(IN `Id_Club` INT)
    NO SQL
SELECT r.username,
       CONCAT(r.nombre, ' ', apellido1, ' ', apellido2) AS "nombre",
       (SELECT EXISTS
                   (SELECT registrado_cod_registrado
                    FROM rep_club
                    WHERE REGISTRADO_COD_REGISTRADO = cod_registrado
                      AND club_cod_club = Id_Club))     AS es_admin,
       p.fecha_alta,
       cod_registrado
FROM club c,
     pert_club p,
     registrado r
WHERE cod_club = club_cod_club
  AND registrado_cod_registrado = cod_registrado
  AND p.fecha_alta IS NOT NULL
  AND cod_club = Id_Club$$
DELIMITER ;

/*	45. (Select) Solicitar todos los usuarios que pertenecen al proyecto
   Procedimiento para mostrar los usuarios que pertenecen al prooyecto, si son administradores, la columna es_admin
   devolverá un 1, si no lo son, un 0 */
DELIMITER $$
CREATE PROCEDURE `Usuarios_Pertenecen_Proyecto`(IN `Id_Proyecto` INT)
    NO SQL
SELECT r.username,
       CONCAT(r.nombre, ' ', apellido1, ' ', apellido2)           AS "nombre",
       (SELECT EXISTS(SELECT registrado_cod_registrado
                      FROM rep_proyecto
                      WHERE REGISTRADO_COD_REGISTRADO = cod_registrado
                        AND proyecto_cod_proyecto = Id_Proyecto)) AS es_admin,
       p.fecha_alta,
       cod_registrado
FROM proyecto c,
     pert_proyecto p,
     registrado r
WHERE cod_proyecto = proyecto_cod_proyecto
  AND registrado_cod_registrado = cod_registrado
  AND p.fecha_alta IS NOT NULL
  AND cod_proyecto = Id_Proyecto$$
DELIMITER ;

/*	46. (Insert) Ascender a usuarios a administradores del club
   Procedimiento para ascender un miembro a administrador de club, pasando por parámetro el
   cod_usuario y el cod_club */
DELIMITER $$
CREATE PROCEDURE `Ascender_A_Admin_Club`(IN `Id_User` INT, IN `Id_Club` INT)
    NO SQL
INSERT INTO rep_club (registrado_cod_registrado, club_cod_club)
VALUES (Id_User, Id_Club)$$
DELIMITER ;

/*	47. (Insert) Ascender a usuarios a administradores del proyecto
   Procedimiento para ascender un miembro a administrador de proyecto, pasando por parámetro el
   cod_usuario y el cod_proyecto */
DELIMITER $$
CREATE PROCEDURE `Ascender_A_Admin_Proyecto`(IN `Id_User` INT, IN `Id_Proyecto` INT)
    NO SQL
INSERT INTO rep_proyecto (registrado_cod_registrado, proyecto_cod_proyecto)
VALUES (Id_User, Id_Proyecto)$$
DELIMITER ;

/* 48. Subir Imagen
   Procedimiento interno para Administrador de la aplicación para subir las imágenes de los clubs
   desde la clase ActualizarInformacion en la carpeta de SCRIPTS_SQL */
DELIMITER $ $
CREATE PROCEDURE `Subir_Imagen`(IN `Id_Club` INT, IN `Id_Img` BLOB)
    NO SQL
UPDATE
    club
SET banner = Id_Img
WHERE cod_club = Id_Club $ $ DELIMITER ;

/* 49. Cambiar contraseña aleatoria
   Procedimiento para que el usuario pueda solicitar recuperar su contraseña, dentro del procedimiento se
   llamará a una funcion que genera automáticamente una contraseña para el usuario.
   Finalmente, el segundo procedimiento devolverá la nueva contraseña.
 */
-- Procedimiento 1
DELIMITER $$
CREATE PROCEDURE `Cambiar_Contrasena_Random`(IN `Id_Email` VARCHAR(50))
    NO SQL
UPDATE registrado
SET contrasena = Generar_Claves()
WHERE registrado.email = Id_Email$$
DELIMITER ;

-- Función
DELIMITER $$
CREATE FUNCTION `Generar_Claves`() RETURNS varchar(50) CHARSET utf8
    NO SQL
BEGIN
    declare alfa VARCHAR(62) default 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890';
    declare clave varchar(50) default '';
    set clave = concat(
            SUBSTRING(alfa, rand() * 62, 1),
            SUBSTRING(alfa, rand() * 62, 1),
            SUBSTRING(alfa, rand() * 62, 1),
            SUBSTRING(alfa, rand() * 62, 1),
            SUBSTRING(alfa, rand() * 62, 1),
            SUBSTRING(alfa, rand() * 62, 1),
            SUBSTRING(alfa, rand() * 62, 1),
            SUBSTRING(alfa, rand() * 62, 1),
            SUBSTRING(alfa, rand() * 62, 1)
        );
    RETURN clave;
END$$
DELIMITER ;

-- Procedimiento 2

DELIMITER $$
CREATE
    DEFINER = `uflpbetwquqnmjoz`@`%` PROCEDURE `Devolver_Nueva_Contrasena`(IN `Id_Email` VARCHAR(50))
    NO SQL
SELECT registrado.contrasena
FROM registrado
WHERE registrado.email = Id_Email$$
DELIMITER ;

/* 50. Solicitar contraseña del usuario
 */
DELIMITER $$
CREATE PROCEDURE `Solicitar_contrasena`(IN `Id_Email` VARCHAR(50))
    NO SQL
SELECT registrado.contrasena
FROM registrado
WHERE registrado.email = Id_Email$$
DELIMITER ;