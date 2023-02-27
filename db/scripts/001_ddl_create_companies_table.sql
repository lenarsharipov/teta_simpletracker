create table if not exists companies (
	id serial primary key,
	company text,
	phone varchar(20),
	address text
)