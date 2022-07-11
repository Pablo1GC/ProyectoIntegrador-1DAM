DROP TABLE IF EXISTS consultas_usuarios ;

DROP TABLE IF EXISTS noticia_club ;

DROP TABLE IF EXISTS noticia_proyecto ;

DROP TABLE IF EXISTS pert_proyecto ;

DROP TABLE IF EXISTS pert_club ;

DROP TABLE IF EXISTS rep_proyecto ;

DROP TABLE IF EXISTS rep_club ;

DROP TABLE IF EXISTS proyecto ;

DROP TABLE IF EXISTS registrado ;

DROP TABLE IF EXISTS users ;

DROP TABLE IF EXISTS club ;

CREATE TABLE users (
    usr VARCHAR(32) NOT NULL,
    pwd VARCHAR(32) NOT NULL,
    rol VARCHAR(32) NOT NULL
);

ALTER TABLE
    users
ADD
    CONSTRAINT users_pk PRIMARY KEY (usr);


CREATE TABLE consultas_usuarios (
    id_consulta INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email_usuario VARCHAR(40) NOT NULL,
    contenido_consulta VARCHAR(600) NOT NULL,
    fecha_consulta DATE NOT NULL
);


CREATE TABLE registrado (
    cod_registrado INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    nombre VARCHAR(32) NOT NULL,
    apellido1 VARCHAR(32) NOT NULL,
    apellido2 VARCHAR(32),
    fecha_nacimiento DATE NOT NULL,
    email VARCHAR(50) NOT NULL,
    contrasena VARCHAR(32) NOT NULL,
    estudios VARCHAR(50),
    foto LONGBLOB
);

ALTER TABLE
    registrado
ADD
    CONSTRAINT username_un UNIQUE KEY (username);
ALTER TABLE
    registrado
ADD
    CONSTRAINT email_un UNIQUE KEY (email);


CREATE TABLE club (
    cod_club INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(32) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    contacto VARCHAR(50) NOT NULL,
    fecha_alta DATE NOT NULL,
    fecha_baja DATE,
    banner BLOB,
    localizacion VARCHAR (32) NOT NUll
);

ALTER TABLE
    club
ADD
    CONSTRAINT nom_club_un UNIQUE (nombre);


CREATE TABLE noticia_club (
    cod_noti_club INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    titulo VARCHAR (60) NOT NULL,
    contenido VARCHAR(4000) NOT NULL,
    club_cod_club INTEGER NOT NULL,
    registrado_cod_registrado INTEGER NOT NULL
);


CREATE TABLE pert_club (
    registrado_cod_registrado INTEGER NOT NULL,
    club_cod_club INTEGER NOT NULL,
    fecha_alta DATE
);

ALTER TABLE
    pert_club
ADD
    CONSTRAINT pert_club_pk PRIMARY KEY (
        registrado_cod_registrado,
        club_cod_club
    );


CREATE TABLE rep_club (
    registrado_cod_registrado INTEGER NOT NULL,
    club_cod_club INTEGER NOT NULL
);

ALTER TABLE
    rep_club
ADD
    CONSTRAINT rep_club_pk PRIMARY KEY (
        registrado_cod_registrado,
        club_cod_club
    );


CREATE TABLE proyecto (
    cod_proyecto INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(32) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    contacto VARCHAR(32) NOT NULL,
    fecha_alta DATE NOT NULL,
    privado CHAR(1) NOT NULL,
    club_cod_club INTEGER NOT NULL,
    fecha_baja DATE
);

ALTER TABLE
    proyecto
ADD
    CONSTRAINT proyecto_un UNIQUE (nombre);


CREATE TABLE noticia_proyecto (
    cod_noti_proyecto INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    titulo VARCHAR (60) NOT NULL,
    contenido VARCHAR(4000) NOT NULL,
    proyecto_cod_proyecto INTEGER NOT NULL,
    registrado_cod_registrado INTEGER NOT NULL
);


CREATE TABLE pert_proyecto (
    registrado_cod_registrado INTEGER NOT NULL,
    proyecto_cod_proyecto INTEGER NOT NULL,
    fecha_alta DATE
);

ALTER TABLE
    pert_proyecto
ADD
    CONSTRAINT pert_proyecto_pk PRIMARY KEY (
        registrado_cod_registrado,
        proyecto_cod_proyecto
    );


CREATE TABLE rep_proyecto (
    registrado_cod_registrado INTEGER NOT NULL,
    proyecto_cod_proyecto INTEGER NOT NULL
);

ALTER TABLE
    rep_proyecto
ADD
    CONSTRAINT rep_proyecto_pk PRIMARY KEY (
        registrado_cod_registrado,
        proyecto_cod_proyecto
    );


ALTER TABLE
    noticia_club
ADD
    CONSTRAINT noticia_club_club_fk FOREIGN KEY (club_cod_club) REFERENCES club(cod_club) ON DELETE CASCADE;

ALTER TABLE
    noticia_club
ADD
    CONSTRAINT noticia_club_registrado_fk FOREIGN KEY (registrado_cod_registrado) REFERENCES registrado (cod_registrado) ON DELETE CASCADE;

ALTER TABLE
    noticia_proyecto
ADD
    CONSTRAINT noticia_proyecto_proyecto_fk FOREIGN KEY (proyecto_cod_proyecto) REFERENCES proyecto(cod_proyecto) ON DELETE CASCADE;

ALTER TABLE
    noticia_proyecto
ADD
    CONSTRAINT noticia_proyecto_registrado_fk FOREIGN KEY (registrado_cod_registrado) REFERENCES registrado(cod_registrado) ON DELETE CASCADE;

ALTER TABLE
    pert_club
ADD
    CONSTRAINT pert_club_club_fk FOREIGN KEY (club_cod_club) REFERENCES club (cod_club) ON DELETE CASCADE;

ALTER TABLE
    pert_club
ADD
    CONSTRAINT pert_club_registrado_fk FOREIGN KEY (registrado_cod_registrado) REFERENCES registrado(cod_registrado) ON DELETE CASCADE;

ALTER TABLE
    pert_proyecto
ADD
    CONSTRAINT pert_proyecto_proyecto_fk FOREIGN KEY (proyecto_cod_proyecto) REFERENCES proyecto (cod_proyecto) ON DELETE CASCADE;

ALTER TABLE
    pert_proyecto
ADD
    CONSTRAINT pert_proyecto_registrado_fk FOREIGN KEY (registrado_cod_registrado) REFERENCES registrado(cod_registrado) ON DELETE CASCADE;

ALTER TABLE
    proyecto
ADD
    CONSTRAINT proyecto_club_fk FOREIGN KEY (club_cod_club) REFERENCES club (cod_club) ON DELETE CASCADE;

ALTER TABLE
    rep_club
ADD
    CONSTRAINT rep_club_club_fk FOREIGN KEY (club_cod_club) REFERENCES club (cod_club) ON DELETE CASCADE;

ALTER TABLE
    rep_club
ADD
    CONSTRAINT rep_club_registrado_fk FOREIGN KEY (registrado_cod_registrado) REFERENCES registrado(cod_registrado) ON DELETE CASCADE;

ALTER TABLE
    rep_proyecto
ADD
    CONSTRAINT rep_proyecto_proyecto_fk FOREIGN KEY (proyecto_cod_proyecto) REFERENCES proyecto (cod_proyecto) ON DELETE CASCADE;

ALTER TABLE
    rep_proyecto
ADD
    CONSTRAINT rep_proyecto_registrado_fk FOREIGN KEY (registrado_cod_registrado) REFERENCES registrado (cod_registrado) ON DELETE CASCADE;
