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

create sequence news_id_generator
    increment by 1;

alter sequence news_id_generator owner to sa;

create table news
(
    id           bigint       not null
        constraint news_pkey
            primary key,
    title        varchar(255) not null,
    body         text         not null,
    node_from_id bigint
        constraint node_from_id_nodes_fk
            references nodes,
    node_to_id   bigint
        constraint node_to_id_nodes_fk
            references nodes,
    comment      varchar(255),
    published_at timestamp    not null,
    traffic_id   bigint
        constraint traffic_id_fk
            references traffic
);

alter table news
    owner to sa;

create sequence traffic_id_generator
    increment by 1;

alter sequence traffic_id_generator owner to sa;

create table traffic
(
    id    bigint       not null
        constraint traffic_pkey
            primary key,
    name  varchar(255) not null,
    value double precision
);

alter table traffic
    owner to sa;

