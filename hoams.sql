# Sushil Singh
# Homeowner Association Management System

drop database if exists db_hoams;
create database db_hoams;

drop user if exists 'student'@'localhost';
create user 'student'@'localhost' identified by 'student';
grant all privileges on db_hoams.* to 'student'@'localhost';
flush privileges;

use db_hoams;

drop table if exists User;
create table User(
	username varchar(50) primary key,
    password varchar(200) not null,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(200)
);

insert User (username, password, firstname, lastname, email) values
('jdoe', SHA('jdoe'), 'John', 'Doe', 'jdoe@onemail.com');

drop table if exists Property;
create table Property(
	propertyId int primary key auto_increment,
	street varchar(200) not null,
	city varchar(100) not null,
	state char(2) not null,
	zipcode varchar(10) null
);

insert Property (street, city, state, zipcode) values
('1013 Rosedale Ave', 'Chicago', 'IL', '60646'),
('1015 Rosedale Ave', 'Chicago', 'IL', '60646'),
('1017 Rosedale Ave', 'Chicago', 'IL', '60646'),
('1019 Rosedale Ave', 'Chicago', 'IL', '60646'),
('1022 Rosedale Ave', 'Chicago', 'IL', '60646'),
('1024 Rosedale Ave', 'Chicago', 'IL', '60646');

drop table if exists HomeOwner;
create table HomeOwner(
	ownerId int primary key auto_increment,
	firstName varchar(200) not null,
	lastName varchar(200) not null,
	phoneNumber varchar(20) null,
	email varchar(100) null,
	propertyId int not null,
	foreign key (propertyId) references Property (propertyId) on delete cascade
);

insert HomeOwner (firstName, lastName, email, phoneNumber, propertyId) values
('Maddie', 'Sonnnie', 'sknowlton0@wunderground.com', '712-412-8734', 1),
('Leicester', 'Baryram', 'bgiaomozzo1@typepad.com', '482-896-0730', 2),
('Francyne', 'Lorie', 'lbaise2@ftc.gov', '828-718-5137', 2),
('Franny', 'Gabie', 'gdominichelli3@diigo.com', '524-403-9046', 3),
('Glyn', 'Vikki', 'vbudgey4@tumblr.com', '980-481-9625', 4),
('Aldis', 'Port', 'phannant5@eventbrite.com', '577-990-3552', 5),
('Agna', 'Tracie', 'tcoaster6@hatena.ne.jp', '291-842-1443', 5),
('Fidela', 'Odell', 'omccrossan7@geocities.com', '441-327-9776', 6),
('Laurie', 'Damita', 'dsenten8@ucla.edu', '172-167-5704', 6),
('Mathilde', 'Granthem', 'gsidwick9@sitemeter.com', '998-848-0968', 6);

drop table if exists Vehicle;
create table Vehicle(
	vehicleId int primary key auto_increment,
	numberPlate varchar(20) not null,
	stateRegistered char(2) not null,
	Year int null,
	Make varchar(100) null,
	Model varchar(100) null,
	Color varchar(10) null,
	ownerId int not null,
    foreign key (ownerId) references homeowner (ownerId) on delete cascade
);

insert Vehicle (numberPlate, stateRegistered, Year, Make, Model, Color, OwnerId) values
('GR W 234', 'IL', 2013, 'Chevrolet', 'Impala', 'Red', 1),
('AN 42T', 'IL', 2016, 'Nissam', 'Altima', 'White', 2),
('AN 42T', 'IL', 2016, 'Nissam', 'Altima', 'White', 2);

drop table if exists Assessment;
create table Assessment(
	assmtId int primary key auto_increment,
    amount int not null,
    frequency varchar(10) null,
    propertyId int not null,
    foreign key (propertyId) references property (propertyId) on delete cascade
);

insert Assessment (Amount, frequency, propertyId) values
(540, 'monthly', 1), (600, 'monthly', 3),
(540, 'monthly', 2), (600, 'monthly', 5),
(540, 'monthly', 4), (600, 'monthly', 6);