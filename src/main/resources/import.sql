insert into users(email, id_number, name, birth_date, regional_direction, circuito, school, level_Of_teaches, password, role) values ('jfelipe221095@gmail.com', '207450768','Felipe Ramirez', '1995-10-22','Atenas','Jesús','Escuela Jesús','Preescolar', '$2a$10$bBq1gZF91mtw1jBXZaGW9eqVnTraGqr5UKTUHt95FvjYsL4aKaLRm', 'user');
insert into users(email, id_number, name, birth_date, regional_direction, circuito, school, level_Of_teaches, password, role) values ('jfelipe2210@yahoo.es', '207450769','Jose Vargas', '1995-10-22','Desampa','Desampa','Escuela Desampa','Preescolar', '$2a$10$bBq1gZF91mtw1jBXZaGW9eqVnTraGqr5UKTUHt95FvjYsL4aKaLRm', 'user');
insert into users(email, id_number, name, birth_date, regional_direction, circuito, school, level_Of_teaches, password) values ('jfelipe221095@yahoo.es', '207450780','Jose Vargas', '1995-10-22','Desampa','Desampa','Escuela Desampa','Preescolar', 'Piero2210');


insert into categorization(type) values ('Categorzation 1');
insert into categorization(type) values ('Categorzation 2');
insert into categorization(type) values ('Categorzation 3');

insert into like_to_know(description, categorization_id) values ('Like to know 1', 1);
insert into like_to_know(description, categorization_id) values ('Like to know 2', 2);
insert into like_to_know(description, categorization_id) values ('Like to know 3', 3);

insert into know_about_theme(description) values ('Know About Theme 1');
insert into know_about_theme(description) values ('Know About Theme 2');
insert into know_about_theme(description) values ('Know About Theme 3');

insert into Plan(theme, start_date, duration_weeks, option_work, educational_period, regional_direction, circuit, school, level_of_teaches, user_id) values ('El Mundialito', '12/12/2018', '3 weeks', 'Didáctica', 'Segundo', 'Atenas', '08', 'Jesús', 'Materno', 1);
insert into Plan(theme, start_date, duration_weeks, option_work, educational_period, regional_direction, circuit, school, level_of_teaches, user_id) values ('Bullying', '04/04/2019', '2 weeks', 'Didáctica', 'Primero', 'Atenas', '08', 'Jesús', 'Materno', 2);

insert into pre_planning(planning_id, main_like_to_know, know_about_theme_id, like_to_know_id, categorization_id) values (1, 'El Mundial', 1, 1, 1);
insert into pre_planning(planning_id, main_like_to_know, know_about_theme_id, like_to_know_id, categorization_id) values (2, 'El Bullying', 2, 2, 2);



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
