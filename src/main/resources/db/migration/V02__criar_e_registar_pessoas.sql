create table pessoa(
    codigo bigint(20) primary key auto_increment,
    nome varchar(50) not null,
    ativo boolean not null,
    
    logradouro varchar(50),
    numero varchar(50),
    bairro varchar(50),
    cep varchar(50),
    cidade varchar(50),
    estado varchar(50)    
)engine=InnoDB default charset=utf8;

insert into pessoa values
(default, 'Tiago Ventura', true, 'Zamba', '+244 930673687', 'Viana', '00520046LA32', 'Luanda', 'Luanda');
