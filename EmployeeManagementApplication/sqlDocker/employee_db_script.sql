
create database if not exists employee_db;
use employee_db;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS tax_threshold (
  id int primary key,
  min decimal NOT NULL,
  max decimal NOT NULL,
  additional_amount decimal NOT NULL,
  tax_percent_multiplier double NOT NULL
);


INSERT INTO tax_threshold values (1, 0, 18200, 0, 0);
INSERT INTO tax_threshold values (2, 18201, 37000, 0, 0.19);
INSERT INTO tax_threshold values (3, 37001, 87000, 3572, 0.325);
INSERT INTO tax_threshold values (4, 87001, 180000, 19822, 0.37);
INSERT INTO tax_threshold values (5, 180001, 99999999999999999999999999, 54232, 0.45);

commit;

select * from tax_threshold;
 