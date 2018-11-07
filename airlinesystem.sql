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
    firstname varchar(30) not null ,
    lastname varchar(30) not null,
    user_category_id varchar(30) not null,
    email varchar(50) not null,
    phonenumber varchar(10) not null,
    address varchar(50) not null,
    age int not null,
    

    primary key(user_id)
    

);
create table  if not exists admin (
    user_id int auto_increment,
    username varchar(30) not null unique,
    password varchar(30) not null,
    firstname varchar(30) not null,
    lastname varchar(30) not null,
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
    airplane_id varchar(30) not null,
    type_id varchar(30) not null,
 
    primary key(airplane_id),
    foreign key (type_id) references airplane_type(type_id)

);

insert into airplane values("KL 445","type1");
insert into airplane values("KN 334","type1");
insert into airplane values("RM 64","type1");
insert into airplane values("MG 64","type1");

create table  if not exists plane_seats (
    airplane_id varchar(30) not null,
	seat_no int(30) not null,
    type varchar(30) not null,
 
    primary key(airplane_id, seat_no),
    foreign key (airplane_id) references airplane(airplane_id)

);


create table  if not exists price (
    price_id varchar(30) not null,
	econ_price float(10,3),
	business_price float(10,3),
	platinum_price float(10,3),
 
    primary key(price_id)
   
);

insert into price values('1',334,556,788);
insert into price values('2',3500,5500,7000);
insert into price values('3',1000,5500,7400);
insert into price values('4',1000,5500,7400);

create table  if not exists delay (
    delay_id int not null auto_increment,
	new_departure_date date,
	new_departure_time time,
	new_arrival_date date,
	new_arrival_time time,
	reson varchar(30),
 
    primary key(delay_id)
   
);

insert into delay values(1,'2018-11-10','13:00:00','2018-11-10','14:00:00',"rain");
insert into delay values(2,'2018-11-11','2:00:00','2018-11-11','5:00:00',"rain");
insert into delay values(3,'2018-11-12','5:00:00','2018-11-12','5:50:00',"ice");
insert into delay values(4,'2018-11-13','8:00:00','2018-11-13','8:20:00',"ice");

create table  if not exists location (
    location_id varchar(30) not null,
	name varchar(30) not null,
	parent_id varchar(30),
 
    primary key(location_id)
   
);

insert into location values ("loc1","fsd","ff");
insert into location values ("loc2","fsd","ff");
insert into location values ("loc3","fsd","ff");
insert into location values ("loc4","fsd","ff");

create table  if not exists airport (
    airport_code varchar(30) not null,
	primary_location_id varchar(30) not null,
	name varchar(30) not null,
 
    primary key(airport_code),
	foreign key (primary_location_id) references location(location_id)
   
);

INSERT INTO airport VALUES (12,"loc1","Mattala");
INSERT INTO airport VAlUES (24,"loc2","Colombo");
INSERT INTO airport VAlUES (30,"loc3","India");
INSERT INTO airport VAlUES (10,"loc4","China");

create table if not exists route (
    route_id varchar(30) not null,
	from_port_id varchar(30) not null,
	to_port_id varchar(30) not null,
 
    primary key(route_id),
	foreign key (from_port_id) references airport(airport_code),
	foreign key (to_port_id) references airport(airport_code)
   
);

INSERT INTO route VALUES ("r1",12,24);
INSERT INTO route VALUES ("r2",12,30);
INSERT INTO route VALUES ("r3",10,30);
INSERT INTO route VALUES ("r4",12,24);

create table  if not exists flight (
    flight_id varchar(30) not null,
	date_of_week ENUM('Monday','Tuesday','Wednesday','Thursady','Friday','Saturday','Sunday') not null,
	departure_time time not null,
	arrival_time time not null,
	route_id varchar(30) not null,
 
    primary key(flight_id),
	foreign key (route_id) references route(route_id)
   
);

insert into flight values ('AA-1',"Monday",'12:10:00','11:00:00',"r1");
insert into flight values ('AB-1',"Tuesday",'2:10:00','2:50:00',"r2");
insert into flight values ('EB-12',"Wednesday",'1:00:00','1:50:00',"r3");
insert into flight values ('WZ-11',"Thursday",'7:00:00','7:45:00',"r4");


create table if not exists schedule(
    schedule_id varchar(30) not  null,
    date date not null,
	flight_id varchar(30) not null,
    booked_seats_econ int,
    booked_seats_business int,
    booked_seats_platinum int,
    price_id varchar(30) not null,
    airplane_id varchar(10) not null,
    delay_id int,

    primary key(schedule_id),
	foreign key (flight_id) references flight(flight_id),
	foreign key (price_id) references price(price_id),
	foreign key (airplane_id) references airplane(airplane_id),
	foreign key (delay_id) references delay(delay_id)
	
);

insert into schedule values ('0','2018-11-10','AA-1',40,30,30,'1',"KL 445",1);
insert into schedule values ('1','2018-11-11','AB-1',40,30,30,'2',"KN 334",2);
insert into schedule values ('2','2018-11-12','EB-12',50,50,50,'3',"RM 64",3);
insert into schedule values ('3','2018-11-13','WZ-11',80,10,10,'4',"MG 64",4);


create table if not exists booking(
    booking_id int not  null auto_increment,
    user_id int not null,
    schedule_id varchar(30) not null ,
    price float (5,3) not null ,
    payment_status boolean not null,
    seat_no int not null,
    

    primary key(booking_id),
	foreign key (user_id) references user(user_id),
	foreign key (schedule_id) references schedule(schedule_id)

); 



