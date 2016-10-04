
/*tipousuario*/
INSERT INTO tipousuario(codTipoUsuario,nombreTipoUsuario) VALUES(1,"ADMINISTRADOR");
INSERT INTO tipousuario(codTipoUsuario,nombreTipoUsuario) VALUES(2,"DOCENTE");
INSERT INTO tipousuario(codTipoUsuario,nombreTipoUsuario) VALUES(3,"ALUMNO");
/*carrera*/
INSERT INTO carrera(nombreCarrera) values('Hotelería y Administración');
INSERT INTO carrera(nombreCarrera) values('Turismo y Administración');
INSERT INTO carrera(nombreCarrera) values('Gastronomía y Gestión Culinaria');
INSERT INTO carrera(nombreCarrera) values('Arquitectura');
INSERT INTO carrera(nombreCarrera) values('Escuela de Música');
INSERT INTO carrera(nombreCarrera) values('Medicina');
INSERT INTO carrera(nombreCarrera) values('Nutrición y Dietética');
INSERT INTO carrera(nombreCarrera) values('Odontología');
INSERT INTO carrera(nombreCarrera) values('Terapia Física');
INSERT INTO carrera(nombreCarrera) values('Traducción e Interpretación');
INSERT INTO carrera(nombreCarrera) values('Psicología');
INSERT INTO carrera(nombreCarrera) values('Comunicación Audiovisual y Medios Interactivos');
INSERT INTO carrera(nombreCarrera) values('Comunicación e Imagen Empresarial');
INSERT INTO carrera(nombreCarrera) values('Comunicación y Marketing');
INSERT INTO carrera(nombreCarrera) values('Comunicación y Periodismo');
INSERT INTO carrera(nombreCarrera) values('Comunicación y Publicidad');
INSERT INTO carrera(nombreCarrera) values('Derecho');
INSERT INTO carrera(nombreCarrera) values('Diseño Profesional de Interiores');
INSERT INTO carrera(nombreCarrera) values('Diseño Profesional Gráfico');
INSERT INTO carrera(nombreCarrera) values('Diseño y Gestión en Moda');
INSERT INTO carrera(nombreCarrera) values('Economía y Finanzas');
INSERT INTO carrera(nombreCarrera) values('Economía y Negocios Internacionales');
INSERT INTO carrera(nombreCarrera) values('Economía Gerencial');
INSERT INTO carrera(nombreCarrera) values('Educación y Gestión del Aprendizaje');
INSERT INTO carrera(nombreCarrera) values('Ingeniería Civil');
INSERT INTO carrera(nombreCarrera) values('Ingeniería de Gestión Empresarial');
INSERT INTO carrera(nombreCarrera) values('Ingeniería de Gestión Minera');
INSERT INTO carrera(nombreCarrera) values('Ingeniería de Sistemas de Información');
INSERT INTO carrera(nombreCarrera) values('Ingeniería de Software');
INSERT INTO carrera(nombreCarrera) values('Ingeniería Electrónica');
INSERT INTO carrera(nombreCarrera) values('Ingeniería Industrial');
INSERT INTO carrera(nombreCarrera) values('Ingeniería Mecatrónica');
INSERT INTO carrera(nombreCarrera) values('Ciencias de la Computación');
INSERT INTO carrera(nombreCarrera) values('Administración y Agronegocios');
INSERT INTO carrera(nombreCarrera) values('Administración y Finanzas');
INSERT INTO carrera(nombreCarrera) values('Administración y Marketing');
INSERT INTO carrera(nombreCarrera) values('Administración y Negocios del Deporte');
INSERT INTO carrera(nombreCarrera) values('Administración y Negocios Internacionales');
INSERT INTO carrera(nombreCarrera) values('Administración y Recursos Humanos');
INSERT INTO carrera(nombreCarrera) values('Contabilidad y Administración');
/*distrito*/
INSERT INTO distrito(nombreDistrito) values('Ancón');
INSERT INTO distrito(nombreDistrito) values('Santa Rosa');
INSERT INTO distrito(nombreDistrito) values('Ventanilla');
INSERT INTO distrito(nombreDistrito) values('Mi Perú');
INSERT INTO distrito(nombreDistrito) values('Callao');
INSERT INTO distrito(nombreDistrito) values('La Punta');
INSERT INTO distrito(nombreDistrito) values('Carmen de La Legua-Reynoso');
INSERT INTO distrito(nombreDistrito) values('Bellavista');
INSERT INTO distrito(nombreDistrito) values('La Perla');
INSERT INTO distrito(nombreDistrito) values('Carabayllo');
INSERT INTO distrito(nombreDistrito) values('Puente Piedra');
INSERT INTO distrito(nombreDistrito) values('San Martín de Porres');
INSERT INTO distrito(nombreDistrito) values('Los Olivos');
INSERT INTO distrito(nombreDistrito) values('Comas');
INSERT INTO distrito(nombreDistrito) values('Independencia');
INSERT INTO distrito(nombreDistrito) values('San Juan de Lurigancho');
INSERT INTO distrito(nombreDistrito) values('Lima');
INSERT INTO distrito(nombreDistrito) values('Breña');
INSERT INTO distrito(nombreDistrito) values('Rímac');
INSERT INTO distrito(nombreDistrito) values('El Agustino');
INSERT INTO distrito(nombreDistrito) values('San Miguel');
INSERT INTO distrito(nombreDistrito) values('Pueblo Libre');
INSERT INTO distrito(nombreDistrito) values('Jesús María');
INSERT INTO distrito(nombreDistrito) values('Magdalena del Mar');
INSERT INTO distrito(nombreDistrito) values('Lince');
INSERT INTO distrito(nombreDistrito) values('La Victoria');
INSERT INTO distrito(nombreDistrito) values('San Luis');
INSERT INTO distrito(nombreDistrito) values('San Isidro');
INSERT INTO distrito(nombreDistrito) values('Miraflores');
INSERT INTO distrito(nombreDistrito) values('Surquillo');
INSERT INTO distrito(nombreDistrito) values('Barranco');
INSERT INTO distrito(nombreDistrito) values('San Borja');
INSERT INTO distrito(nombreDistrito) values('Santiago de Surco');
INSERT INTO distrito(nombreDistrito) values('Chorrillos');
INSERT INTO distrito(nombreDistrito) values('Santa Anita');
INSERT INTO distrito(nombreDistrito) values('Ate');
INSERT INTO distrito(nombreDistrito) values('La Molina');
INSERT INTO distrito(nombreDistrito) values('Lurigancho-Chosica');
INSERT INTO distrito(nombreDistrito) values('Chaclacayo');
INSERT INTO distrito(nombreDistrito) values('Cieneguilla');
INSERT INTO distrito(nombreDistrito) values('Pachacámac');
INSERT INTO distrito(nombreDistrito) values('San Juan de Miraflores');
INSERT INTO distrito(nombreDistrito) values('Villa María del Triunfo');
INSERT INTO distrito(nombreDistrito) values('Villa El Salvador');
INSERT INTO distrito(nombreDistrito) values('Lurín');
INSERT INTO distrito(nombreDistrito) values('Punta Hermosa');
INSERT INTO distrito(nombreDistrito) values('Punta Negra');
INSERT INTO distrito(nombreDistrito) values('San Bartolo');
INSERT INTO distrito(nombreDistrito) values('Santa María del Mar');
INSERT INTO distrito(nombreDistrito) values('Pucusana');
/*tipoproducto*/
INSERT INTO tipoproducto(nombreTipoProd) VALUES('CERTIFICADO');
INSERT INTO tipoproducto(nombreTipoProd) VALUES('CONSTANCIA');
INSERT INTO tipoproducto(nombreTipoProd) VALUES('CARTA DE PRESENTACIÓN');
/*usuario*/
INSERT INTO usuario(codUsuario,nombre,apellido,dni,fechaNacimiento,sexo,numCelular,correo,contrasenia,flgActivo,codTipoUsuario) 
VALUES('ADNAPE','nombre','apellido','76963852','1995-08-07',0,'998017572','correo@correo.com','12345',1,1);

/*INSERT INTO sede(codSede,direccion,descripcion,flgActivo,codDistrito,nombreSede)
VALUES('SI','dir','descripcion',1,51,'San isidro');
*/
