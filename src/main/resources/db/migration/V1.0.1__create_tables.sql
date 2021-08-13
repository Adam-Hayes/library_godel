create table director
(
    id         serial primary key,
    first_name character varying(255),
    last_name  character varying(255),
    birth_date date
);

create table movie
(
    id           serial primary key,
    director_id  integer references director (id),
    name         character varying(255),
    release_date date,
    genre        character varying(255)
)
