CREATE TABLE type (
  id serial primary key,
  name varchar(200)
);

CREATE TABLE rules (
  id serial primary key,
  name varchar(200)
);

CREATE TABLE accident (
  id serial primary key,
  name varchar(200),
  text varchar(2000),
  address varchar(2000),
  acc_type_id int references type(id)
  );