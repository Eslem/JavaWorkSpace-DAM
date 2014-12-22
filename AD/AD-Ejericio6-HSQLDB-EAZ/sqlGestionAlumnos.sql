
CREATE TABLE alumno (
	id INTEGER IDENTITY,
	dni VARCHAR(50) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(150) NOT NULL,
	edad INT NOT NULL
);

CREATE TABLE asignatura (
	id INTEGER IDENTITY,
	nombre VARCHAR(50) NOT NULL	
);

CREATE TABLE alumno_asignatura (
	id INT IDENTITY,
	idAlumno INT NOT NULL,
	idAsignatura INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (idAlumno) REFERENCES alumno(id),
	FOREIGN KEY (idAsignatura) REFERENCES asignatura(id)
);

