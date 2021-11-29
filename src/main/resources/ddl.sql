-- ----------------------------------------------------------------------------
-- + creates a database "law_office" if not already created 
-- ----------------------------------------------------------------------------
create database if not exists law_firm;

-- ----------------------------------------------------------------------------
-- + changes the database context to the law_firm
-- ----------------------------------------------------------------------------
use law_firm;

-- ----------------------------------------------------------------------------
-- + create table "Law_offices"
-- ----------------------------------------------------------------------------
create table if not exists Law_offices (
 id serial,
 name varchar(150) not null,
 address varchar(150) not null,
 primary key(id)
);

-- ----------------------------------------------------------------------------
-- + create table "Lawyer_activity_spheres"
-- ----------------------------------------------------------------------------
create table if not exists Lawyer_activity_spheres (
 id serial,
 name varchar(150) not null,
 primary key(id)
);

----------------------------------------------------------------------------
-- + create table "Lawyers"
----------------------------------------------------------------------------
create table if not exists Lawyers (
 id serial,
 law_office_id bigint unsigned not null,
 surname varchar(45) not null,
 name varchar(45) not null,
 patronymic varchar(45) null default null,
 dob timestamp not null,
 experience_since timestamp not null,
 primary key(id),
 constraint fk_office_lawyer foreign key(law_office_id) references Law_offices(id)
 	on update no action
 	on delete cascade
);


-- ----------------------------------------------------------------------------
-- + create table "Orientations"
-- ----------------------------------------------------------------------------
create table if not exists Orientations (
 id serial,
 lawyer_id bigint unsigned not null,
 activity_sphere_id bigint unsigned not null,
 primary key(id),
 constraint fk_lawyers_lawyer_id foreign key(lawyer_id) references Lawyers(id)
 	on update no action
 	on delete cascade,
 constraint fk_lawyers_activity_sphere_id foreign key(activity_sphere_id) references Lawyer_activity_spheres(id)
 	on update no action
 	on delete cascade
);
  
-- ----------------------------------------------------------------------------
-- + create table "Service_types"
-- ----------------------------------------------------------------------------
create table if not exists Service_types (
 id serial,
 name varchar(150) not null,
 primary key(id)
);

-- ----------------------------------------------------------------------------
-- + create table "Clients"
-- ----------------------------------------------------------------------------
create table if not exists Clients (
 id serial,
 surname varchar(30) not null,
 name varchar(30) not null,
 patronymic varchar(30) null,
 dob timestamp not null,
 telephone varchar(18) not null,
 primary key(id)
);

-- ----------------------------------------------------------------------------
-- + create table "Client_folders"
-- ----------------------------------------------------------------------------
create table if not exists Client_folders (
 id serial,
 client_id bigint unsigned not null,
 status enum('ARCHIVED', 'ACTIVE') not null default 'ACTIVE',
 primary key(id),
 constraint fk_client_folder foreign key(client_id) references Clients(id)
 	on update no action
 	on delete cascade
);

----------------------------------------------------------------------------
-- + create table "Paperwork_types"
----------------------------------------------------------------------------
create table if not exists Paperwork_types (
 id serial,
 name varchar(150) not null,
 primary key(id)
);

----------------------------------------------------------------------------
-- + create table "Court_types"
----------------------------------------------------------------------------
create table if not exists Court_types (
 id serial,
 name varchar(150) not null,
 primary key(id)
);

----------------------------------------------------------------------------
-- + create table "Judges"
----------------------------------------------------------------------------
create table if not exists Judges (
 id serial,
 surname varchar(45) not null,
 name varchar(45) not null,
 patronymic varchar(45) null default null,
 dob timestamp not null,
 experience_since timestamp not null,
 primary key(id)
);

----------------------------------------------------------------------------
-- + create table "Courts"
----------------------------------------------------------------------------
create table if not exists Courts (
 id serial,
 court_type_id bigint unsigned not null,
 name varchar(150) not null,
 address varchar(150) not null,
 primary key(id),
 constraint fk_court_types_court  foreign key(court_type_id) references Court_types(id)
 	on update restrict
 	on delete cascade
);

----------------------------------------------------------------------------
-- + create table "Paperworks"
----------------------------------------------------------------------------
create table if not exists Paperworks (
 id serial,
 type_id bigint unsigned not null,
 client_folder_id bigint unsigned not null,
 court_id bigint unsigned null,
 judge_id bigint unsigned null,
 title varchar(150) not null,
 url varchar(150) not null,
 primary key(id),
 constraint fk_paperwork_types_paperwork foreign key (type_id) references Paperwork_types(id)
 	on update no action
 	on delete cascade,
 constraint fk_folders_paperwork foreign key(client_folder_id) references Client_folders(id)
 	on update no action
 	on delete cascade,
 constraint fk_paperwork_courts_paperwork foreign key(court_id) references  Courts(id)
 	on update no action
 	on delete cascade,
 constraint fk_paperwork_judges_paperwork foreign key(judge_id) references Judges(id)
 	on update no action
 	on delete cascade
);


----------------------------------------------------------------------------
-- + create table "Services"
-- the name field was removed due to lack of semantic meaning
----------------------------------------------------------------------------
create table if not exists Services (
 id serial,
 type_id bigint unsigned not null,
 lawyer_id bigint unsigned not null,
 paperwork_id bigint unsigned null,
 cost decimal(10, 2) not null,
 primary key(id),
 constraint fk_types_service foreign key(type_id) references Service_types(id)
 	on update no action
 	on delete cascade,
 constraint fk_lawyers_service foreign key(lawyer_id) references Lawyers(id)
 	on update no action
 	on delete cascade,
 constraint fk_paperworks_service foreign key(paperwork_id) references Paperworks(id)
 	on update no action
 	on delete cascade
);
