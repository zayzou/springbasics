drop table  if exists "books";
drop table if exists "authors";

create table "authors" (
    "id" bigint not null ,
    "name" text,
    "age" integer,
    constraint "authors_pkey" primary key ("id")

);
create  table "books" (
    "isbn"  text not null,
    "name" text,
    "author_id" bigint,
    constraint "books_pkey" primary key ("isbn"),
    constraint "fk_authors" foreign key ("author_id")
        references "authors"("id")
);