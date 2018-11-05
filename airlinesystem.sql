create database if not exists airlinereservationsys  ;

use airlinereservationsys;



create table  if not exists user (
    user_id int auto_increment,
    username varchar(30) not null unique,
    password varchar(30) not null,
    firstname varchar(30) not null,
    lastname varchar(30) not null,
    user_category_id varchar(30) not null ,
    email varchar(50) not null,
    phonenumber varchar(10) not null,
    address varchar(50) not null,

    primary key(user_id)
    

);


