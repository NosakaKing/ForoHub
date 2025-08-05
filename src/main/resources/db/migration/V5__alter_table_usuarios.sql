alter table usuarios
add column nombre_usuario varchar(50),
add column nombre varchar(100),
add column apellido varchar(100),
add column github varchar(100),
add column linkedin varchar(100),
add column rol_id bigint,
add constraint fk_usuario_rol foreign key (rol_id) references roles(id);
