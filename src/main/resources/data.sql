insert into user_details(id, name, date_of_birth)
values (1001, 'Jacky', current_date());

insert into user_details(id, name, date_of_birth)
values (1002, 'Jeff', current_date());

insert into user_details(id, name, date_of_birth)
values (1003, 'Jim', current_date());

insert into post(id, description, user_id)
values (2001, 'I want to learn AWS', 1001);

insert into post(id, description, user_id)
values (2002, 'I want to learn DevOps', 1001);

insert into post(id, description, user_id)
values (2003, 'I want to Get AWS Certified', 1002);

insert into post(id, description, user_id)
values (2004, 'I want to Get Multi Cloud', 1002);