create sequence connection_id_generator
    increment by 1;

alter sequence connection_id_generator owner to sa;

create sequence node_id_generator
    increment by 1;

alter sequence node_id_generator owner to sa;

create table nodes
(
    id        bigint           not null
        constraint nodes_pkey
            primary key,
    latitude  double precision not null,
    longitude double precision not null,
    name      varchar(255)     not null,
    node_type varchar(255)     not null,
    group_num bigint,
    color     varchar(7)       not null,
    pointX    double precision,
    pointY    double precision,
    textX     double precision,
    textY     double precision
);

alter table nodes
    owner to sa;

create table connections
(
    id           bigint not null
        constraint connections_pkey
            primary key,
    node_from_id bigint
        constraint node_from_id_nodes_fk
            references nodes,
    node_to_id   bigint
        constraint node_to_id_nodes_fk
            references nodes
);

alter table connections
    owner to sa;

