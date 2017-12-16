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
	username varchar(50) not null,
    password varchar(200) not null,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(200),
    primary key (Username)
);

insert User (username, password, firstname, lastname, email) values
('jdoe', 'jdoe', 'John', 'Doe', 'jdoe@onemail.com');

drop table if exists HomeOwner;
create table HomeOwner(
	ownerId int not null auto_increment,
	firstName varchar(200) not null,
	lastName varchar(200) not null,
	phoneNumber varchar(20) null,
	email varchar(100) null,
	propertyId int not null,
	primary key (ownerId)
);

insert HomeOwner (firstName, lastName, phoneNumber, email, propertyId) values
('Mike', 'Johnson', '569-254-1198', 'mikej@awesome.com', 1),
('Han', 'Filbur', '479-221-1638', 'han.filbur@gmail.com', 2);

drop table if exists Property;
create table Property(
	propertyId int not null auto_increment,
	street varchar(200) not null,
	city varchar(100) not null,
	state char(2) not null,
	zipcode varchar(10) null,
	primary key (propertyId)
);

insert Property (street, city, state, zipcode) values
('1013 Rosedale Ave', 'Chicago', 'IL', '60646'),
('1015 Rosedale Ave', 'Chicago', 'IL', '60646');

drop table if exists Vehicle;
create table Vehicle(
	vehicleId int not null auto_increment,
	numberPlate varchar(20) not null,
	stateRegistered char(2) not null,
	Year int null,
	Make varchar(100) null,
	Model varchar(100) null,
	Color varchar(10) null,
	ownerId int not null,
    primary key (vehicleId)
	#primary key (numberPlate, stateRegistered)
);

insert Vehicle (numberPlate, stateRegistered, Year, Make, Model, Color, OwnerId) values
('GR W 234', 'IL', 2013, 'Chevrolet', 'Impala', 'Red', 1);

drop table if exists Assessment;
create table Assessment(
	assmtId int not null auto_increment,
    Amount int not null,
    propertyId int not null,
    primary key (assmtId)
);

insert Assessment (Amount, propertyId) values
(540, 1), (600,2);