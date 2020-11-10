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

INSERT INTO t_team (teamid,name,num,begin_day,end_day,begin_time,end_time) VALUES ('1', '小刚', '5', '1', '5', '9', '22');
INSERT INTO t_team (teamid,name,num,begin_day,end_day,begin_time,end_time) VALUES ('2', '小红', '4', '1', '5', '7', '20');
INSERT INTO t_team (teamid,name,num,begin_day,end_day,begin_time,end_time) VALUES ('3', '小明', '3', '1', '5', '19', '7');

DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order (
   orderid  INT AUTO_INCREMENT PRIMARY KEY,
   material_code   INT,
   quantity   INT,
   delivery_date datetime
);

INSERT INTO t_order (orderid,material_code,quantity,delivery_date) VALUES ('1', '1', '500', '2020-10-25');
INSERT INTO t_order (orderid,material_code,quantity,delivery_date) VALUES ('2', '2', '400', '2020-10-26');

DROP TABLE IF EXISTS t_equipment;
CREATE TABLE t_equipment (
   equipmentid   INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(30),
   type VARCHAR(30),
   amount  INT
);

INSERT INTO t_equipment (equipmentid,name,type,amount) VALUES ('1', 'lineA', 'line', '1');
INSERT INTO t_equipment (equipmentid,name,type,amount) VALUES ('2', 'lineB', 'line', '1');

DROP TABLE IF EXISTS t_craft;
CREATE TABLE t_craft (
   craftid   INT AUTO_INCREMENT PRIMARY KEY,
   material_code  INT,
   human_num  INT,
   capacity  INT,
   human_res VARCHAR(200),
   equipment_res VARCHAR(30)
);

INSERT INTO t_craft (craftid,material_code,human_num,capacity,human_res,equipment_res) VALUES ('1', '1', '5', '50', '1_2_3', '1_2');
INSERT INTO t_craft (craftid,material_code,human_num,capacity,human_res,equipment_res) VALUES ('2', '2', '7', '100', '1_2_3', '1_2');

COMMIT;