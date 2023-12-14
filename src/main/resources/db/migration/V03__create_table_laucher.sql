CREATE TABLE IF NOT EXISTS launch (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    date_maturity date not null,
    date_payment date,
    cost DECIMAL(10, 2) not null,
    observation VARCHAR(50),
    type VARCHAR(20) not null,
    category_id BIGINT(20) not null,
    users_id BIGINT(20) not null,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (users_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;