create table if not exists subscribers (
	id serial primary key,
	name text,
	surname text,
	subscriberNumber varchar(20),
	plateNumber varchar(20),
	companyId int
);