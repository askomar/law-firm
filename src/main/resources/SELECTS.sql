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
