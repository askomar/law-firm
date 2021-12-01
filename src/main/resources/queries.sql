-- //////////////////////////////////////////////////////////////////////////////////////
-- ------------------------------------- UPDATE -----------------------------------------
-- //////////////////////////////////////////////////////////////////////////////////////

-- --------------------------------------------------------------------------
-- + change address of law_office with index 4
-- --------------------------------------------------------------------------
update Law_offices set address = 'улица Богдановича, дом 80' where id = 4;

-- --------------------------------------------------------------------------
-- + update patronymic of layers with id equals 1
-- --------------------------------------------------------------------------
update Lawyers set surname = 'Макаревич' where id = 1;

-- --------------------------------------------------------------------------
-- + set  layer_id equal 5 if activity_sphere_id = 1
-- --------------------------------------------------------------------------
update Orientations set lawyer_id = 5 where activity_sphere_id = 1;

-- --------------------------------------------------------------------------
-- + change name of service_types in services that have id = 5
-- --------------------------------------------------------------------------
update Service_types set name = 'Подача ходатайства' where id = 5;


-- --------------------------------------------------------------------------
-- + change telephone for 5 client in clients
-- --------------------------------------------------------------------------
update Clients set telephone = '555-35-35' where id = 5;

-- --------------------------------------------------------------------------
-- + update all services paperwork_id to 2
-- --------------------------------------------------------------------------
update Services set paperwork_id = 2;

-- --------------------------------------------------------------------------
-- + update service paperwork with id = 3 to 4
-- --------------------------------------------------------------------------
update Services set paperwork_id = 4 where paperwork_id = 3;

-- --------------------------------------------------------------------------
-- + lowercase all symbols in activity spheres
-- --------------------------------------------------------------------------
update Lawyer_activity_spheres set name = concat(lcase(left(name, 1)), substring(name, 2));

-- --------------------------------------------------------------------------
-- + delete all patronymics of judges
-- --------------------------------------------------------------------------
update Judges set patronymic = null;

-- --------------------------------------------------------------------------
-- + update telephone number to 777-77-77 of 3st client
-- --------------------------------------------------------------------------
update Clients set telephone = '777-77-77' where id = 3;

-- /////////////////////////////////////////////////////////////////////////////////////////
--------------------------------------- DELETE -----------------------------------------
-- //////////////////////////////////////////////////////////////////////////////////////

-- --------------------------------------------------------------------------
-- + delete paperwork_type with name 'Иск'
-- --------------------------------------------------------------------------
delete from Paperwork_types where name='Иск';

-- --------------------------------------------------------------------------
-- + delete paperwork with id = 8
-- --------------------------------------------------------------------------
delete from Paperwork where id = 8;

-- --------------------------------------------------------------------------
-- + delete client with surname = 'Чижик'
-- --------------------------------------------------------------------------
delete from Clients where surname = 'Чижик';

-- --------------------------------------------------------------------------
-- + delete court with  name contains 'город'
-- --------------------------------------------------------------------------
delete from Courts where name like '%город%';

-- --------------------------------------------------------------------------
-- + delete service type 'Подача иска'
-- --------------------------------------------------------------------------
delete from Service_types where name like '%подача иска%';

-- --------------------------------------------------------------------------
-- + delete judge with name Артем
-- --------------------------------------------------------------------------
delete from Judges where name like '%артем%';

-- --------------------------------------------------------------------------
-- + delete spheres that contained in list
-- --------------------------------------------------------------------------
delete from Lawyer_activity_spheres where name in ('гражданское право', 'страховое право', 'административное право');

-- --------------------------------------------------------------------------
-- + delete orientations using "and"
-- --------------------------------------------------------------------------
delete from Orientations where lawyer_id = 4 and activity_sphere_id = 5;

-- --------------------------------------------------------------------------
-- + delete paperwork with title contains "базу"
-- --------------------------------------------------------------------------
delete from Paperworks where title like '%базу%';

-- --------------------------------------------------------------------------
-- + delete all clients
-- --------------------------------------------------------------------------
delete from Clients;

-- //////////////////////////////////////////////////////////////////////////////////////
-- ------------------------------------- SELECT --------------------------------
-- //////////////////////////////////////////////////////////////////////////////////////

-- ----------------------------------------------------------------------------
-- + select all data from the table "Law_offices"
-- ----------------------------------------------------------------------------
select * from Law_offices;

-- ----------------------------------------------------------------------------
-- + select concrete cols from the table "Law_offices"
-- ----------------------------------------------------------------------------
select id, `name`, address from Law_offices;

-- ----------------------------------------------------------------------------
-- + select concrete cols from the table "Law_offices" with column alias
-- ----------------------------------------------------------------------------
select id as law_office_id, `name`, address from Law_offices;

-- ----------------------------------------------------------------------------
-- + select data from the Lawyers with where office equals 3
-- ----------------------------------------------------------------------------
select id, law_office_id, surname, name, patronymic, dob, experience_since from Lawyers where law_office_id = 3;

-- ----------------------------------------------------------------------------
-- + select data from the table "Lawyers" where surname equals "Переселяк"
-- ----------------------------------------------------------------------------
select id, law_office_id as office_id, surname, name, dob from Lawyers where surname = 'Переселяк';


-- ----------------------------------------------------------------------------
-- + select data from the table "Lawyers" with table alias
-- ----------------------------------------------------------------------------
select l.id, l.law_office_id, l.surname, l.name, l.patronymic, l.dob from Lawyers l;

-- ----------------------------------------------------------------------------
-- + select data from the table "Lawyers" with like keyword
-- ----------------------------------------------------------------------------
select id, law_office_id, surname, name, patronymic, dob from Lawyers where surname like '%жик%';

-- ----------------------------------------------------------------------------
-- + select data from the table "Clients" using in operator
-- ----------------------------------------------------------------------------
select id, surname, name, patronymic, dob, telephone from Clients as c where c.surname in ('Клундук', 'Чижик', 'Гурский');

-- ----------------------------------------------------------------------------
-- + select left join data from the lawyes and their offices
-- ----------------------------------------------------------------------------
select l.id as lawyer_id, l.surname, l.name, o.name as office from Lawyers l left join Law_offices o on (o.id = l.law_office_id);

-- ----------------------------------------------------------------------------
-- + select using double condition
-- ----------------------------------------------------------------------------
select c.surname as surname, c.name as name from Clients c where TIMESTAMPDIFF(YEAR, c.dob, CURDATE()) > 17 and name != 'Чижик';

-- ----------------------------------------------------------------------------
-- + select using concat
-- ----------------------------------------------------------------------------
select l.id, concat(l.surname, ' ', l.name) as full_name from Lawyers l;

-- ----------------------------------------------------------------------------
-- + count paperworks
-- ----------------------------------------------------------------------------
select count(*) as paperworks_amount from Paperworks;

-- ----------------------------------------------------------------------------
-- + select amount of client_folders grouped by amount
-- ----------------------------------------------------------------------------
select f.status, count(*) as amount from Client_folders f group by f.status;

-- ----------------------------------------------------------------------------
-- + select amount of client_folders grouped by amount and only active
-- ----------------------------------------------------------------------------
select f.status, count(*) as amount from Client_folders f group by f.status having f.status = 'ACTIVE';

-- ----------------------------------------------------------------------------
-- + select paperworks using order by cost
-- ----------------------------------------------------------------------------
select s.id as service_id, t.name, concat(l.surname, ' ', l.name) as lawyer, concat(p.title, ' № ',p.id) as paperwork, s.cost from Services s
 left join Service_types t on (s.type_id = t.id)
 left join Lawyers l on (s.lawyer_id = 	l.id)
 left join Paperworks p on (s.paperwork_id = p.id)
 order by s.cost;

 -- ----------------------------------------------------------------------------
 -- + select paperworks using limit
 -- ----------------------------------------------------------------------------
 select p.id, p.type_id, p.client_folder_id, p.court_id, p.judge_id, p.title, p.url from Paperworks p limit 0, 4;

 -- ----------------------------------------------------------------------------
 -- + select paperwork_types of paperworks that we already have in db using distinct
 -- ----------------------------------------------------------------------------
 select distinct t.name from Paperworks p join Paperwork_types t on (p.type_id = t.id);

  -- ----------------------------------------------------------------------------
 -- + get sum of cost of all services of law firm
  -- ----------------------------------------------------------------------------
 select sum(s.cost) as sum_of_costs from Services s;
--
  -- ----------------------------------------------------------------------------
-- + get all persons in database using union
  -- ----------------------------------------------------------------------------
select surname, name, patronymic from Lawyers union select surname, name, patronymic from Clients union select surname, name, patronymic from Judges;
