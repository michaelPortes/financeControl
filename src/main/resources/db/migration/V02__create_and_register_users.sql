CREATE TABLE IF NOT EXISTS users (
    id BIGINT(20) primary key auto_increment,
    name varchar(50) not null,
    address varchar(50),
    contact varchar(50),
    complement varchar(50),
    cep varchar(50),
    state varchar(2)
) ENGINE=innoDB DEFAULT CHARSET=utf8;
