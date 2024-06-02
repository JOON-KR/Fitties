
CREATE TABLE IF NOT EXISTS Fitty (
    user_id VARCHAR(100) NOT NULL,
    fitty_name VARCHAR(50) NOT NULL,
    status VARCHAR(10)not null,
    goal double not null,
    is_open tinyint DEFAULT 1
);

CREATE TABLE boardFitty (
    num INT AUTO_INCREMENT PRIMARY KEY,
    writer VARCHAR(100) NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    fitty_name VARCHAR(100) NOT NULL,
    limit_num INT,
    limit_level INT
);



