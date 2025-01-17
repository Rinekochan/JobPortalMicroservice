CREATE DATABASE IF NOT EXISTS employer_db;

USE employer_db;

-- Company Table
create table IF NOT EXISTS company
(
    id         varchar(36)  not null
    primary key,
    name       varchar(50)  not null,
    industry   varchar(50)  null,
    location   varchar(50)  null,
    website    varchar(100) null,
    created_at date         default null,
    updated_at date         default null
    );

create table IF NOT EXISTS employer
(
    id         varchar(36) not null
    primary key,
    company_id varchar(36) not null,
    constraint FK_EMPLOYER_COMPANY
    foreign key (company_id) references company(id)
    );


