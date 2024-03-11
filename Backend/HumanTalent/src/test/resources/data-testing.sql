-- Schema
CREATE TABLE IF NOT EXISTS persons (
    id INT PRIMARY KEY,
    country VARCHAR(255),
    first_name VARCHAR(255),
    first_last_name VARCHAR(255),
    id_type VARCHAR,
    identification_num VARCHAR(50),
    other_names VARCHAR(255),
    second_last_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY,
    email VARCHAR(255),
    entry_date DATE,
    reg_datetime TIMESTAMP,
    state BOOLEAN,
    work_area VARCHAR(255),
    person_id INT,
    FOREIGN KEY (person_id) REFERENCES persons(id)
);
-- Data
INSERT INTO public.persons (country,first_last_name,first_name,id_type,identification_num,other_names,second_last_name) VALUES
	 ('CO','Benton','Xander','PASSPORT','2898888','Donovan Woodard','Hurley'),
	 ('EU','Cash','Alfonso','FOREIGNER_ID','52523423423432','Lavinia Bell','Bullock'),
	 ('CO','Torres','Holly','FOREIGNER_ID','969121212','Reece Combs','Farmer'),
	 ('CO','Gonzalez','Mallory','PASSPORT','28612312','Aphrodite Boyd','Gill'),
	 ('EU','Morse','Maxwell','FOREIGNER_ID','12312364','Christine Wells','Rojas'),
	 ('EU','Stafford','Wendy','CITIZENSHIP_CARD','58435345','Sonya Benton','Meyers'),
	 ('EU','Floyd','Orson','PASSPORT','6081231414232','Burke Best','Christensen'),
	 ('EU','Levine','Gage','CITIZENSHIP_CARD','4152122','Winifred Downs','Rivera'),
	 ('CO','Shaw','Dana','SPECIAL_PERMISSION','3642222','Laurel Newton','Dennis'),
	 ('EU','Henson','Kareem','CITIZENSHIP_CARD','469222','Tanya Elliott','Gibbs');
INSERT INTO public.persons (country,first_last_name,first_name,id_type,identification_num,other_names,second_last_name) VALUES
	 ('CO','Riddle','Selma','PASSPORT','6351222','Ali Stephenson','Hansen'),
	 ('CO','Diaz','Chadwick','PASSPORT','75233','Dakota Ford','Brooks'),
	 ('CO','Solomon','Cade','CITIZENSHIP_CARD','84811','Amanda Patel','Stafford'),
	 ('EU','Humphrey','Aimee','SPECIAL_PERMISSION','40111','Kimberley Hoffman','Alvarez'),
	 ('EU','Wade','Todd','CITIZENSHIP_CARD','33411','Scarlett Schwartz','Rodriquez'),
	 ('EU','Robinson','Dakota','CITIZENSHIP_CARD','352211','Cullen Alston','Gonzales'),
	 ('EU','Bishop','Elvis','FOREIGNER_ID','76512','Heather Wagner','Schmidt'),
	 ('CO','Poole','Josiah','SPECIAL_PERMISSION','42899877','Cole Johns','Mcfadden'),
	 ('EU','Martinez','Mollie','PASSPORT','7224433','Wade Chen','Schneider'),
	 ('EU','Conway','Alma','CITIZENSHIP_CARD','916643','Isabelle Bush','Bauer');
INSERT INTO public.persons (country,first_last_name,first_name,id_type,identification_num,other_names,second_last_name) VALUES
	 ('CO','Mclean','Cynthia','PASSPORT','256123','Dominic Henderson','Rosales'),
	 ('EU','Ellis','Sean','FOREIGNER_ID','396432323','Daryl Ward','Thompson'),
	 ('EU','Burt','Sheila','PASSPORT','44445632','Grant Mullen','Molina'),
	 ('EU','Finley','Lesley','CITIZENSHIP_CARD','387322345','Cade Donaldson','Forbes'),
	 ('EU','Silva','Bruce','SPECIAL_PERMISSION','33132135','Alexandra Douglas','Duran'),
	 ('EU','Wolf','Lysandra','CITIZENSHIP_CARD','510565767','Myles Clayton','Carlson'),
	 ('EU','Kim','Marcia','FOREIGNER_ID','350556','Mallory Curtis','Acevedo'),
	 ('EU','Ford','Angelica','SPECIAL_PERMISSION','959090','Hope Wooten','Dalton'),
	 ('EU','Oconnor','Brielle','CITIZENSHIP_CARD','437432','Colton Pate','Bowman'),
	 ('EU','Norris','Davis','CITIZENSHIP_CARD','74144332','Macaulay Elliott','Meyers');
INSERT INTO public.persons (country,first_last_name,first_name,id_type,identification_num,other_names,second_last_name) VALUES
	 ('EU','Pruitt','Keely','PASSPORT','1000223','Griffith Marquez','Malone'),
	 ('CO','Knight','Athena','PASSPORT','9849865','Herrod Osborn','Bradley'),
	 ('EU','Newman','Zeus','FOREIGNER_ID','5815342','Avye Bush','Nichols'),
	 ('EU','Gordon','Brock','FOREIGNER_ID','6245253','Imelda Best','Thomas'),
	 ('EU','Hood','Vernon','FOREIGNER_ID','47542532','Carla Cobb','Snider'),
	 ('EU','Farmer','Dante','FOREIGNER_ID','795432','Gray Bishop','Valentine'),
	 ('EU','Hammond','Jaquelyn','FOREIGNER_ID','66465364','Ray Rich','Freeman'),
	 ('EU','Bowen','Roary','CITIZENSHIP_CARD','47265364','Bert Harrison','Patel'),
	 ('CO','Perez','Colin','FOREIGNER_ID','4954324','Shea Nelson','Holden'),
	 ('CO','Ramsey','Brett','FOREIGNER_ID','3224333','Ainsley Delgado','Woodard');
INSERT INTO public.persons (country,first_last_name,first_name,id_type,identification_num,other_names,second_last_name) VALUES
	 ('CO','Riley','Maggy','CITIZENSHIP_CARD','7653243','Xanthus Wagner','Abbott'),
	 ('CO','Sanchez','Sara','FOREIGNER_ID','465432','Bethany Mcdowell','Mayo'),
	 ('EU','Booth','Joshua','CITIZENSHIP_CARD','2125434','Ruth Hubbard','Waters'),
	 ('CO','Hicks','Ulric','SPECIAL_PERMISSION','39032456','Lucy Stewart','Johnson'),
	 ('EU','Clemons','Quin','FOREIGNER_ID','33754565','Jermaine Hall','Vance'),
	 ('CO','Putt','Marina','SPECIAL_PERMISSION','11223333','Petro','Mess'),
	 ('CO','Putt','Arina','SPECIAL_PERMISSION','112213333','Aero','Messa'),
	 ('EU','Lu','Arina','SPECIAL_PERMISSION','1120033','Daniela','Jen');

INSERT INTO public.employees (email,entry_date,reg_datetime,state,work_area,person_id) VALUES
	 ('xander.benton@company.com.co','2024-02-29','29/02/2024 12:22:21',true,'OPERATIONS',1),
	 ('alfonso.cash@company.com.eu','2024-02-29','29/02/2024 12:25:29',true,'OPERATIONS',2),
	 ('holly.torres@company.com.co','2024-02-29','29/02/2024 12:29:30',true,'ADMINISTRATION',3),
	 ('mallory.gonzalez@company.com.co','2024-02-29','29/02/2024 12:32:20',true,'INFRASTRUCTURE',4),
	 ('maxwell.morse@company.com.eu','2024-02-29','29/02/2024 12:32:34',true,'MISCELLANEOUS_SERVICES',5),
	 ('wendy.stafford@company.com.eu','2024-02-29','29/02/2024 12:33:14',true,'ADMINISTRATION',6),
	 ('orson.floyd@company.com.eu','2024-02-29','29/02/2024 12:33:43',true,'MISCELLANEOUS_SERVICES',7),
	 ('gage.levine@company.com.eu','2024-02-29','29/02/2024 12:34:31',true,'OPERATIONS',8),
	 ('dana.shaw@company.com.co','2024-02-29','29/02/2024 12:34:40',true,'INFRASTRUCTURE',9),
	 ('kareem.henson@company.com.eu','2024-02-29','29/02/2024 12:34:55',true,'PROCUREMENT',10);
INSERT INTO public.employees (email,entry_date,reg_datetime,state,work_area,person_id) VALUES
	 ('selma.riddle@company.com.co','2024-02-29','29/02/2024 12:35:04',true,'PROCUREMENT',11),
	 ('chadwick.diaz@company.com.co','2024-02-29','29/02/2024 12:38:36',true,'MISCELLANEOUS_SERVICES',12),
	 ('cade.solomon@company.com.co','2024-02-29','29/02/2024 12:38:44',true,'HUMAN_RESOURCES',13),
	 ('aimee.humphrey@company.com.eu','2024-02-29','29/02/2024 12:39:34',true,'PROCUREMENT',14),
	 ('todd.wade@company.com.eu','2024-02-29','29/02/2024 12:44:29',true,'HUMAN_RESOURCES',15),
	 ('dakota.robinson@company.com.eu','2024-02-23','29/02/2024 12:48:08',true,'ADMINISTRATION',16),
	 ('elvis.bishop@company.com.eu','2024-02-01','29/02/2024 12:48:35',true,'FINANCE',17),
	 ('josiah.poole@company.com.co','2024-02-12','04/03/2024 18:25:35',true,'ADMINISTRATION',18),
	 ('mollie.martinez@company.com.eu','2024-02-23','04/03/2024 18:25:59',true,'OPERATIONS',20),
	 ('alma.conway@company.com.eu','2024-02-17','04/03/2024 18:26:14',true,'PROCUREMENT',21);
INSERT INTO public.employees (email,entry_date,reg_datetime,state,work_area,person_id) VALUES
	 ('cynthia.mclean@company.com.co','2024-02-08','04/03/2024 18:26:31',true,'MISCELLANEOUS_SERVICES',22),
	 ('sean.ellis@company.com.eu','2024-02-14','04/03/2024 18:26:42',true,'MISCELLANEOUS_SERVICES',23),
	 ('sheila.burt@company.com.eu','2024-02-22','04/03/2024 18:27:09',true,'MISCELLANEOUS_SERVICES',24),
	 ('lesley.finley@company.com.eu','2024-02-28','04/03/2024 18:27:21',true,'FINANCE',25),
	 ('bruce.silva@company.com.eu','2024-03-02','04/03/2024 18:28:07',true,'MISCELLANEOUS_SERVICES',26),
	 ('lysandra.wolf@company.com.eu','2024-02-26','04/03/2024 18:28:18',true,'MISCELLANEOUS_SERVICES',27),
	 ('marcia.kim@company.com.eu','2024-02-04','04/03/2024 18:28:34',true,'ADMINISTRATION',28),
	 ('angelica.ford@company.com.eu','2024-02-28','04/03/2024 18:28:43',true,'ADMINISTRATION',29),
	 ('brielle.oconnor@company.com.eu','2024-02-26','04/03/2024 18:29:03',true,'INFRASTRUCTURE',30),
	 ('davis.norris@company.com.eu','2024-02-16','04/03/2024 18:29:14',true,'INFRASTRUCTURE',31);
INSERT INTO public.employees (email,entry_date,reg_datetime,state,work_area,person_id) VALUES
	 ('keely.pruitt@company.com.eu','2024-02-11','04/03/2024 18:29:23',true,'OPERATIONS',32),
	 ('athena.knight@company.com.co','2024-02-26','04/03/2024 18:29:52',true,'INFRASTRUCTURE',33),
	 ('zeus.newman@company.com.eu','2024-02-18','04/03/2024 18:30:08',true,'MISCELLANEOUS_SERVICES',34),
	 ('brock.gordon@company.com.eu','2024-02-16','04/03/2024 18:30:16',true,'PROCUREMENT',35),
	 ('vernon.hood@company.com.eu','2024-02-06','04/03/2024 18:30:25',true,'INFRASTRUCTURE',36),
	 ('dante.farmer@company.com.eu','2024-02-17','04/03/2024 18:30:44',true,'FINANCE',37),
	 ('jaquelyn.hammond@company.com.eu','2024-02-26','04/03/2024 18:30:56',true,'HUMAN_RESOURCES',38),
	 ('roary.bowen@company.com.eu','2024-02-24','04/03/2024 18:31:09',true,'FINANCE',39),
	 ('colin.perez@company.com.co','2024-02-19','04/03/2024 18:31:28',true,'PROCUREMENT',40),
	 ('brett.ramsey@company.com.co','2024-02-29','04/03/2024 18:31:48',true,'HUMAN_RESOURCES',41);
INSERT INTO public.employees (email,entry_date,reg_datetime,state,work_area,person_id) VALUES
	 ('maggy.riley@company.com.co','2024-02-15','04/03/2024 18:32:00',true,'MISCELLANEOUS_SERVICES',42),
	 ('sara.sanchez@company.com.co','2024-02-11','04/03/2024 18:32:08',true,'MISCELLANEOUS_SERVICES',43);
