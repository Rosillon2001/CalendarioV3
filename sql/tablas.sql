CREATE TABLE Usuario (
	id_usuario numeric(15) primary key,
	nombre_usuario varchar(30),
	clave varchar(150)
);

CREATE TABLE Calendario (
	id_calendario numeric(15) primary key,
	nombre_calendario varchar(50)
);

CREATE TABLE Acceso (
	id_usuario numeric(15),
	id_calendario numeric (15),
	privilegios varchar(20), 
	foreign key (id_usuario) references Usuario (id_usuario),
	foreign key (id_calendario) references Calendario (id_calendario)
);

Create table Actividad(
	id_actividad numeric(15) primary key, 
	nombre_actividad varchar(50),
	descripcion_actividad varchar(150),
	color_actividad varchar(80),
	fecha_actividad date,
	hora_inicio varchar(20),
	hora_fin varchar(20),
	duracion varchar(20),
	estado varchar(20), 
	id_calendario numeric(15), 
	foreign key (id_calendario) references Calendario (id_calendario)
);