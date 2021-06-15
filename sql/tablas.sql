CREATE TABLE Usuario (
	id_usuario numeric(15) primary key,
	nombre_usuario varchar(30),
	clave varchar(150)
);

CREATE TABLE Calendario (
	id_calendario numeric(50) primary key,
	nombre_calendario varchar(50),  
	color varchar(30)
);

CREATE TABLE Acceso (
	id_usuario numeric(15),
	id_calendario numeric(50),
	privilegios varchar(20), 
	foreign key (id_usuario) references Usuario (id_usuario) ON DELETE CASCADE AND ON UPDATE CASCADE,
	foreign key (id_calendario) references Calendario (id_calendario) ON DELETE CASCADE AND ON UPDATE CASCADE
);

Create table Actividad(
	id_actividad numeric(15) primary key, 
	nombre_actividad varchar(50),
	descripcion_actividad varchar(150),
	color_actividad varchar(40),
	fecha_actividad date,
	hora_inicio varchar(20),
	hora_fin varchar(20),
	duracion varchar(20),
	estado varchar(20), 
	id_calendario numeric(50), 
	foreign key (id_calendario) references Calendario (id_calendario) ON DELETE CASCADE AND ON UPDATE CASCADE
);