drop table if exists tbl_customers;

create table tbl_customers(
    id      int auto_increment primary key,
    name    varchar(100) not null
);
