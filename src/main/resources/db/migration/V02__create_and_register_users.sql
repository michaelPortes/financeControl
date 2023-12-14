CREATE TABLE IF NOT EXISTS users (
    id BIGINT(20) primary key auto_increment,
    name varchar(50) not null,
    address varchar(50),
    number varchar(50),
    complement varchar(50),
    neighborhood varchar(50),
    cep varchar(50),
    city varchar(50),
    state varchar(2),
    active boolean not null
) ENGINE=innoDB DEFAULT CHARSET=utf8;
