create table topicos
(
    id            bigint       not null auto_increment,
    titulo        varchar(100) not null,
    mensaje  text not null,
    fecha_creacion  datetime default current_timestamp,
    estado varchar(50) not null,
    curso_id bigint not null ,
    autor_id bigint not null,
    primary key (id),
    constraint fk_topicos_curso_id foreign key (curso_id) references cursos(id),
    constraint fk_topicos_autor_id foreign key (autor_id) references usuarios(id)
);