drop database if exists catfield;
create database catfield;
use catfield;

create table Dipendente(

	matricola varchar(100) primary key,
    stipendio float not null,
    ore int not null,
    quotaOraria int not null

);

create table Volontario(

	matricola varchar(100) primary key,
    punti int not null,
    ore int not null,
    quotaOraria int not null

);

create table Buono(

	codiceBuono varchar(100) primary key,
    dataScadenza date not null,
    dataErogazione date not null,
    matricola varchar(100) not null,
    quota int not null,
    
    foreign key (matricola) references Volontario(matricola) on delete cascade

);

create table Gatto(
	
    codiceidentificativounico varchar(100) primary key,
    dataAdozione date,
    età int not null,
    razza varchar(100) not null,
    genere varchar(100) not null,
    matricolaDipendente varchar(100) not null,
    matricolaVolontario varchar(100) not null,
    
    foreign key (matricolaDipendente) references Dipendente(matricola) on delete cascade,
    foreign key (matricolaVolontario) references Volontario(matricola) on delete cascade

);

create table Veterinario(

	matricolaMedica varchar(100) primary key,
    nome varchar(100) not null,
    cap varchar(100) not null,
    via varchar(100) not null,
    cognome varchar(100) not null,
    citta varchar(100) not null

);

create table Visita(

	id int auto_increment primary key,
    dataVisita date not null,
    codiceidentificativounico varchar(100) not null,
    matricolaMedica varchar(100) not null,
    
    foreign key (matricolaMedica) references Veterinario(matricolaMedica) on delete cascade,
    foreign key (codiceidentificativounico) references Gatto(codiceidentificativounico) on delete cascade

);

create table numeroTelefono(

	numero varchar(100) not null,
	id int auto_increment primary key,
    matricolaMedica varchar(100) not null,
    
	foreign key (matricolaMedica) references Veterinario(matricolaMedica) on delete cascade
    
);
