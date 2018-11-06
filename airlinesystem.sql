create database if not exists airlinereservationsys  ;

use airlinereservationsys;

create table if not exists user_category(
    category_id varchar(30) not null,
    category_name varchar(30) not null,
    discount float (5,3),
	
	primary key (category_id)
);

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

create table  if not exists airplane_type (
    type_id varchar(30) not null,
	type varchar(30) not null,
	no_of_seats_econ int(30),
	no_of_seats_business int(30),
	no_of_seats_platinum int(30),
 
    primary key(type_id)
   
);

insert into airplane_type values ("type1","aaaa",45,35,35);

create table  if not exists airplane (
    airplane_id varchar(10) not null,
    type_id varchar(30) not null,
 
    primary key(airplane_id),
    foreign key (type_id) references airplane_type(type_id)

);

insert into airplane values("KL 445","type1");

create table  if not exists plane_seats (
    airplane_id varchar(10) not null,
	seat_no int(30) not null,
    type varchar(30) not null,
 
    primary key(airplane_id),
    foreign key (airplane_id) references airplane(airplane_id)

);


create table  if not exists price (
    price_id int(30) not null auto_increment,
	econ_price float(5,3),
	business_price float(5,3),
	platinum_price float(5,3),
 
    primary key(price_id)
   
);

insert into price values(1,334,556,788);

create table  if not exists delay (
    delay_id int not null auto_increment,
	new_departure_time time,
	new_arrival_time time,
	reson varchar(30),
 
    primary key(delay_id)
   
);

insert into delay values(1,13:00:00,14:00:00,"rain");

create table  if not exists location (
    location_id varchar(30) not null,
	name varchar(30) not null,
	parent_id varchar(30),
 
    primary key(location_id)
   
);

insert into location values ("loc1","fsd","ff");
insert into location values ("loc2","fsd","ff");

create table  if not exists airport (
    airport_code varchar(30) not null,
	primary_location_id varchar(30) not null,
	name varchar(30) not null,
 
    primary key(airport_code),
	foreign key (primary_location_id) references location(location_id)
   
);

INSERT INTO airport VALUES (12,"loc1","Galle");
INSERT INTO airport VAlUES (24,"loc2","Colombo");

create table if not exists route (
    route_id varchar(30) not null,
	from_port_id varchar(30) not null,
	to_port_id varchar(30) not null,
 
    primary key(route_id),
	foreign key (from_port_id) references airport(airport_code),
	foreign key (to_port_id) references airport(airport_code)
   
);

INSERT INTO route VALUES ("r1",12,24);


create table  if not exists flight (
    flight_id int not null,
	date_of_week varchar(30) not null,
	departure_time time not null,
	arrival_time time not null,
	route_id varchar(30) not null,
 
    primary key(flight_id),
	foreign key (route_id) references route(route_id)
   
);

insert into flight values (1,"Mon",12:10:00,11:00:00,"r1");

create table if not exists schedule(
    schedule_id int not  null auto_increment,
    date date not null,
	flight_id int not null,
    booked_seats_econ int,
    booked_seats_business int,
    booked_seats_platinum int,
    price_id int not null,
    airplane_id varchar(10) not null,
    delay_id int not null,

    primary key(schedule_id),
	foreign key (flight_id) references flight(flight_id),
	foreign key (price_id) references price(price_id),
	foreign key (airplane_id) references airplane(airplane_id),
	foreign key (delay_id) references delay(delay_id)
	
);

insert into schedule values (0,2018-05-20,1,40,30,30,1,"KL 445",1);


create table if not exists booking(
    booking_id int not  null auto_increment,
    user_id int not null,
    schedule_id int not null ,
    price float (5,3) not null ,
    booked_seats_platinum int ,
    payment_status boolean not null,
    seat_no int not null,
    

    primary key(booking_id),
	foreign key (user_id) references user(user_id),
	foreign key (schedule_id) references schedule(schedule_id)

); 



select  date,flight_id,airplane_id,b.name,c.name,departure_time,new_departure_time from schedule left join delay using (delay_id)  left join flight using(flight_id) left join route using (route_id),airport as b, airport as c where b.airport_code= from_port_id and c.airport_code = to_port_id;