
insert into users(email, id_number, name, birth_date, regional_direction, circuito, school, level_Of_teaches, password, role) values ('prueba@mep.go.cr', '207450768','Felipe Ramirez', '1995-10-22','Atenas','Jesús','Escuela Jesús','Preescolar', '$2a$10$w.8SltnBOFXXp5fAIq1Xqu.9Io2d8elApDVheaEBb9tAhzIgrlzxS', 'user');
insert into users(email, id_number, name, birth_date, regional_direction, circuito, school, level_Of_teaches, password, role) values ('jfelipe221095@gmail.com', '207450769','Jose Vargas', '1995-10-22','Desampa','Desampa','Escuela Desampa','Preescolar', '$2a$10$bBq1gZF91mtw1jBXZaGW9eqVnTraGqr5UKTUHt95FvjYsL4aKaLRm', 'user');
insert into users(email, id_number, name, birth_date, regional_direction, circuito, school, level_Of_teaches, password, role) values ('jfelipe2210@yahoo.es', '207450780','Jose Vargas', '1995-10-22','Desampa','Desampa','Escuela Desampa','Preescolar', '$2a$10$bBq1gZF91mtw1jBXZaGW9eqVnTraGqr5UKTUHt95FvjYsL4aKaLRm', 'user');
insert into users(email, id_number, name, birth_date, regional_direction, circuito, school, level_Of_teaches, password, role) values ('laaf93cr@gmail.com', '304750404','Luis Arce', '1993-11-19','Turrialba','02','Las Américas','Preescolar', '$2a$10$bBq1gZF91mtw1jBXZaGW9eqVnTraGqr5UKTUHt95FvjYsL4aKaLRm', 'user');


insert into categorization(categs) values ('Categorzation 1');
insert into categorization(categs) values ('Categorzation 2');
insert into categorization(categs) values ('Categorzation 3');

insert into like_to_know(description, categorization_id) values ('Like to know 1', 1);
insert into like_to_know(description, categorization_id) values ('Like to know 2', 2);
insert into like_to_know(description, categorization_id) values ('Like to know 3', 3);

insert into know_about_theme(description) values ('Know About Theme 1');
insert into know_about_theme(description) values ('Know About Theme 2');
insert into know_about_theme(description) values ('Know About Theme 3');

insert into plan(theme, start_date, duration_weeks, option_work, educational_period, regional_direction, circuit, school, level_of_teaches, user_id) values ('El Mundialito', '12/12/2018', '3', 'Didáctica', 'Segundo', 'Atenas', '08', 'Jesús', 'Materno', 1);
insert into plan(theme, start_date, duration_weeks, option_work, educational_period, regional_direction, circuit, school, level_of_teaches, user_id) values ('Bullying', '04/04/2019', '2', 'Didáctica', 'Primero', 'Atenas', '08', 'Jesús', 'Materno', 2);
insert into plan(theme, start_date, duration_weeks, option_work, educational_period, regional_direction, circuit, school, level_of_teaches, user_id) values ('Numeros', '06/06/2019', '1', 'Didáctica', 'Primero', 'Turrialba', '02', 'Las Américas', 'Materno', 4);

insert into pre_planning(planning_id, main_like_to_know, know_about_theme_id, like_to_know_id, categorization_id) values (1, 'El Mundial', 1, 1, 1);
insert into pre_planning(planning_id, main_like_to_know, know_about_theme_id, like_to_know_id, categorization_id) values (2, 'El Bullying', 2, 2, 2);
insert into pre_planning(planning_id, main_like_to_know, know_about_theme_id, like_to_know_id, categorization_id) values (3, 'Numeros', 3, 3, 3);

insert into units(name, description, created_at, modified_at, identity_version, unit_order) values ('Conocimiento de sí mismo', 'Desarrollo progresivo del propio yo', '2017-04-25', '2018-02-02', 34656,45423);
insert into units(name, description, created_at, modified_at, identity_version, unit_order) values ('Interacción social y cultural', 'Vivencia social', '2016-08-22', '2018-02-02', 56554,32546);
insert into units(name, description, created_at, modified_at, identity_version, unit_order) values ('Interacción con el medio', 'Desarrollo del pensamiento', '2016-11-15', '2018-02-02', 23563,12856);
insert into units(name, description, created_at, modified_at, identity_version, unit_order) values ('Comunicación, expresión y representación', 'Lenguajes artísticos', '2017-07-05', '2018-02-02', 635445,23434);

insert into correlation(concept, level, unit_id, pre_planning_id) values ('Descubrir aspectos de nutrición y salud', 'preescolar', 1, 1);
insert into correlation(concept, level, unit_id, pre_planning_id) values ('Numeros del 1 al 10', 'preescolar', 4, 3);

insert into educational_region(region_name, cant_circuits) values ('SAN JOSÉ CENTRAL', 6);
insert into educational_region(region_name, cant_circuits) values ('SAN JOSÉ NORTE', 6);
insert into educational_region(region_name, cant_circuits) values ('SAN JOSÉ OESTE', 5);
insert into educational_region(region_name, cant_circuits) values ('DESAMPARADOS', 7);
insert into educational_region(region_name, cant_circuits) values ('PURISCAL', 7);
insert into educational_region(region_name, cant_circuits) values ('PEREZ ZELEDÓN', 10);
insert into educational_region(region_name, cant_circuits) values ('LOS SANTOS', 3);
insert into educational_region(region_name, cant_circuits) values ('ALAJUELA', 10);
insert into educational_region(region_name, cant_circuits) values ('OCCIDENTE', 9);
insert into educational_region(region_name, cant_circuits) values ('SAN CARLOS', 14);
insert into educational_region(region_name, cant_circuits) values ('ZONA NORTE', 8);
insert into educational_region(region_name, cant_circuits) values ('CARTAGO', 8);
insert into educational_region(region_name, cant_circuits) values ('TURRIALBA', 9);
insert into educational_region(region_name, cant_circuits) values ('HEREDIA', 7);
insert into educational_region(region_name, cant_circuits) values ('SARAPIQUÍ', 5);
insert into educational_region(region_name, cant_circuits) values ('LIBERIA', 5);
insert into educational_region(region_name, cant_circuits) values ('NICOYA', 8);
insert into educational_region(region_name, cant_circuits) values ('SANTA CRUZ', 7);
insert into educational_region(region_name, cant_circuits) values ('CAÑAS', 5);
insert into educational_region(region_name, cant_circuits) values ('PUNTARENAS', 8);
insert into educational_region(region_name, cant_circuits) values ('COTO', 14);
insert into educational_region(region_name, cant_circuits) values ('AGUIRRE', 6);
insert into educational_region(region_name, cant_circuits) values ('GRANDE DE TÉRRABA', 13);
insert into educational_region(region_name, cant_circuits) values ('PENÍNSULAR', 4);
insert into educational_region(region_name, cant_circuits) values ('LIMÓN', 9);
insert into educational_region(region_name, cant_circuits) values ('GUÁPILES', 8);
insert into educational_region(region_name, cant_circuits) values ('SULA', 6);




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

