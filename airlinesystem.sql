create database if not exists airlinereservationsys  ;

use airlinereservationsys;

create table if not exists user_category(
    category_id varchar(30) not null,
    category_name varchar(30) not null,
    discount float (5,3),
	
	primary key (category_id)
);

create table  if not exists user (
    user_id int(30) not null auto_increment,
    firstname varchar(30) not null,
    lastname varchar(30) not null,
    user_category_id varchar(30) not null ,
    email varchar(50) not null,
    phonenumber varchar(10) not null,
    address varchar(50) not null,

    primary key(user_id),
    foreign key (user_category_id) references user_category(category_id)

);

create table  if not exists airplane_type (
    type_id varchar(30) not null,
	type varchar(30) not null,
	no_of_seats_econ int(30),
	no_of_seats_business int(30),
	no_of_seats_platinum int(30),
 
    primary key(type_id)
   
);

create table  if not exists airplane (
    airplane_id varchar(10) not null,
    type_id varchar(30) not null,
 
    primary key(airplane_id),
    foreign key (type_id) references airplane_type(type_id)

);

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

create table  if not exists delay (
    delay_id int not null auto_increment,
	new_departure_time time,
	new_arrival_time time,
	reson varchar(30),
 
    primary key(delay_id)
   
);

create table  if not exists location (
    location_id varchar(30) not null,
	name varchar(30) not null,
	parent_id varchar(30),
 
    primary key(location_id)
   
);

create table  if not exists airport (
    airport_code varchar(30) not null,
	primary_location_id varchar(30) not null,
	name varchar(30) not null,
 
    primary key(airport_code),
	foreign key (primary_location_id) references location(location_id)
   
);

create table if not exists route (
    route_id varchar(30) not null,
	from_port_id varchar(30) not null,
	to_port_id varchar(30) not null,
 
    primary key(route_id),
	foreign key (from_port_id) references airport(airport_code),
	foreign key (to_port_id) references airport(airport_code)
   
);


create table  if not exists flight (
    flight_id int not null,
	date_of_week varchar(30) not null,
	departure_time time not null,
	arrival_time time not null,
	route_id varchar(30) not null,
 
    primary key(flight_id),
	foreign key (route_id) references route(route_id)
   
);

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


