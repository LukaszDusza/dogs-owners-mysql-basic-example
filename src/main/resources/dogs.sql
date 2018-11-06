create database animals;
drop table dogs;
drop table owners;

create table dogs(id int(9) not null auto_increment primary key, name varchar(255) not null, age int(2));

-- tworzenie pojedynczego rekordu
insert into dogs(name, age) values('Maximillian', 3);

-- tworzenie wielu rekodrów jednocześnie
insert into dogs(name, age) values
('Azorek', 1),
('Burek', 2),
('Saba', 4);

-- wybranie wszystkich rekordów oraz wszystkich (*) kolumn.
select * from dogs;

-- wybranie konkretnej kolumny
select name from dogs;

-- iteracja po rekordach i zwrot liczby rekordów.
select count(name) from dogs;

-- wyszukiwanie rekordów o tej samej nazwie (name)
select count(name) from dogs where name = 'Burek';

-- dodawanie kolumny do istniejacej tabeli
alter table dogs add breed varchar(20); 

-- aktualizacja rekordu, wylaczyc SafeUpdate in preferencess -> editor -> na samym dole
update dogs set age = 11 where name = 'Burek';


-- wybranie konkretnej kolumny zmieniając jej alias na inny. Nie zmienia nazwy kolumny na stałe.
select name as 'dogs names' from dogs;
select name as 'dogs name', age as 'dogs age' from dogs;

-- wyszukujemy najstarszego i najmlodszego psa.
select * from dogs where age = (select max(age) from dogs);
select * from dogs where age = (select min(age) from dogs);

-- sumuje wartosci w podanej kolumnie
select sum(age) from dogs;

-- sumuje wartosci podaje kolumny w praciu o warunek w nawiasie.
select sum(age *2) from dogs;

-- sumuje wartosci z podanej kolumny na podstawie zlozonego warunku (select);
select sum(age) from (select age from dogs where age between 1 and 10) age;

-- tworzenie pojedynczego rekordu
insert into dogs(name, age) values('Burek', 3);

-- sumuje wartosci z podanej kolumny na podstawie zlozonego warunku (tym razem warunek name);
select sum(age) from (select age from dogs where name = 'Burek') age;

-- dodanie kolumny typu data z domyslna wartoscia daty - metoda now()
alter table dogs add born_date timestamp default now();

-- podaje unikalne wartosci
select distinct name from dogs;

-- tworzenie pojedynczego rekordu
insert into dogs(name, age) values('Burek', 3);

-- podaje unikalne wartosci
select distinct name,age from dogs;

-- podaje unikalne wartosci w oparciu o warunek
select distinct name,age from dogs where age < 11;

-- aktualizacja rekorsu wedlug warunku (where).
update dogs set age = 10 where name = 'Burek';
update dogs set age = 10 where age between 1 and 3;



-- tworzenie tabeli owners --

-- tworzenie tabeli owners z kluczem obcym do tablei dogs;
create table owners
(id int not null primary key auto_increment, 
name varchar(20) not null, dog_id int, 
foreign key(dog_id) references dogs(id));


-- tworzenie tabeli owners bez klucza do tabeli dogs;
create table owners
(id int not null primary key auto_increment,
 name varchar(20) not null, dog_id int);

-- dodanie wartosci do tabeli.
insert into owners(name, dog_id) values
('Adam Ciszewski', 1),
('Ania Kowalska', 2),
('Zbyszek Python', 2);

select * from owners;

-- funkcja join podajaca wlascicieli psów na bazie przypisanych do nich id psów.
select owners.name, dogs.name from owners join dogs on owners.dog_id = dogs.id;

-- iloczyn kartezjański - permutacja.
select owners.name, dogs.name from owners join dogs;

-- porzadkowanie wartosci wedlug klucza (name);
select * from owners order by name;

-- kasowanie rekordu po id.
delete from dogs where id = 1;


-- usuwanie klucza obcego (powiązania).
alter table owners drop foreign key owners_ibfk_1;

-- dodawanie kolumny owner_id do tabeli dogs
alter table dogs add owner_id int; 

-- dodawanie klucza obcego do dogs.
alter table dogs add constraint owner_id foreign key(owner_id) references owners(id);

-- usuwanie i dodawanie klucza obcego z ustawieniem kaswoania pochodnych rekordów.
ALTER TABLE dogs DROP FOREIGN KEY owner_id;
ALTER TABLE dogs ADD CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES owners (id) ON UPDATE CASCADE;

-- -------------------------------------------------------------
-- ALTER TABLE owners DROP FOREIGN KEY dogs_id;

-- dodawanie tabeli owner --

-- 1
create table owners
(id int not null primary key auto_increment,
 name varchar(20) not null, dog_id int);

-- 2
alter table owners add dogs_id int;

-- 3
ALTER TABLE owners ADD CONSTRAINT dogs_id FOREIGN KEY (dogs_id) REFERENCES dogs (id) ON UPDATE CASCADE;

-- pomocnicze
select owners.name, dogs.name from dogs join owners on dogs.owner_id = owners.id;

-- wyszukiwanie id wlasciciela psa wedlug imienia wlasciciela.
select name from dogs where dogs.owner_id = (select id from owners where owners.name = 'Jacek Kowalski');

select distinct name from owners;












