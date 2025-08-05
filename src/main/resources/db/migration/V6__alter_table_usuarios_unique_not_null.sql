alter table usuarios
    modify column nombre_usuario varchar(50) not null,
    modify column nombre varchar(100) not null,
    modify column apellido varchar(100) not null;

alter table usuarios
    add constraint uq_usuarios_email unique (email),
add constraint uq_usuarios_nombre_usuario unique (nombre_usuario);
