CREATE DATABASE ProjectDB;

USE ProjectDB;

Create table Users(
id int AUTO_INCREMENT PRIMARY KEY,
username varchar(255), first_name varchar(255), second_name varchar(255), DOB varchar(255), 
email varchar(255), event_name varchar(255), firebase_Id varchar (255)
);
Create table Events (
id int AUTO_INCREMENT PRIMARY KEY,
event_name varchar(255), event_type varchar(255), event_date varchar(255), 
time_left int, username varchar(255)
);
Create table purchase (
id int AUTO_INCREMENT PRIMARY KEY,
purchase_name varchar(255), category varchar(255), event_name varchar(255),
username varchar(255)
);
Create table checklist (
id int AUTO_INCREMENT PRIMARY KEY,
target varchar(255), time_remaining int, date varchar(255), 
username varchar(255), event_name varchar(255)
);
Create table guestlist (
id int AUTO_INCREMENT PRIMARY KEY,
guest_name varchar(255), guest_email varchar(255), guest_phone int, 
event_name varchar(255), username varchar(255)
);

