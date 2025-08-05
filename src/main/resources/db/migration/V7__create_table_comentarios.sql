create table comentarios
(
    id            bigint       not null auto_increment,
    mensaje  text not null,
    fecha_creacion  datetime default current_timestamp,
    topico_id bigint not null ,
    autor_id bigint not null,
    primary key (id),
    constraint fk_comentarios_topicos_id foreign key (topico_id) references topicos(id),
    constraint fk_comentarios_autor_id foreign key (autor_id) references usuarios(id)
);