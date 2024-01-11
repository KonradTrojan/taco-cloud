create table if not exists Ingredient (
  id varchar(4) not null unique,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  created_at timestamp not null
);

CREATE SEQUENCE TACO_SEQ START WITH (select max(ID) + 1 from TACO);

create table if not exists Taco_Ingredients (
  taco_id bigint not null,
  ingredients_id varchar(4) not null
);

alter table Taco_Ingredients
    add foreign key (ingredients_id) references Ingredient(id);
alter table Taco_Ingredients
    add foreign key (taco_id) references Taco(id);

create table if not exists Taco_Order (
	id identity,
	delivery_name varchar(50) not null,
	delivery_street varchar(50) not null,
	delivery_city varchar(50) not null,
	delivery_state varchar(2) not null,
	delivery_zip varchar(10) not null,
	cc_number varchar(16) not null,
	cc_expiration varchar(5) not null,
	cc_cvv varchar(3) not null,

    placed_at timestamp not null
);

CREATE SEQUENCE TACO_ORDER_SEQ START WITH (select max(ID) + 1 from Taco_Order);

create table if not exists Taco_Order_Tacos (
	tacoOrder bigint not null,
	taco bigint not null
);

alter table Taco_Order_Tacos
    add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
    add foreign key (taco) references Taco(id);