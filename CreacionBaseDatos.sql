create database pruebauno;
use pruebauno;
create table empleados
( idEmp int primary key
, nombre varchar(20)
, sueldo numeric(6,2)
);
insert into empleados (idEmp, nombre, sueldo) values (1, 'Pepe', 1500.20);
insert into empleados (idEmp, nombre, sueldo) values (2, 'Francisco', 2000.20);
insert into empleados (idEmp, nombre, sueldo) values (3, 'Nicasio', 1200.20);
insert into empleados (idEmp, nombre, sueldo) values (4, 'Mike', 3500.20);
insert into empleados (idEmp, nombre, sueldo) values (5, 'Niceto', 1800.20);