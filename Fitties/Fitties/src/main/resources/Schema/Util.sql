CREATE TABLE uploadfile (
    file_id SERIAL PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL
);
select * from uploadfile;
CREATE TABLE ranking (
    user_id VARCHAR(100) PRIMARY KEY,
    nickname VARCHAR(50),
    tier_rank INT,
    level_rank INT,
    streak_rank INT
);
select * from ranking;

