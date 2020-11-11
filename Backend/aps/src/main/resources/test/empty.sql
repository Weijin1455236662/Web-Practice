DROP TABLE IF EXISTS t_team;
CREATE TABLE t_team (
   teamid   INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(30),
   num  INT,
   begin_day  INT,
   end_day  INT,
   begin_time  INT,
   end_time  INT
);


DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order (
   orderid  INT AUTO_INCREMENT PRIMARY KEY,
   material_code   INT,
   quantity   INT,
   delivery_date datetime
);


DROP TABLE IF EXISTS t_equipment;
CREATE TABLE t_equipment (
   equipmentid   INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(30),
   type VARCHAR(30),
   amount  INT
);


DROP TABLE IF EXISTS t_craft;
CREATE TABLE t_craft (
   craftid   INT AUTO_INCREMENT PRIMARY KEY,
   material_code  INT,
   human_num  INT,
   capacity  INT,
   human_res VARCHAR(200),
   equipment_res VARCHAR(30)
);


COMMIT;