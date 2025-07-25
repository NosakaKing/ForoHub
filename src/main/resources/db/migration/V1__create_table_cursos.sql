create table cursos
(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    descripcion text,
    estado varchar(50) not null,
    primary key (id)
)