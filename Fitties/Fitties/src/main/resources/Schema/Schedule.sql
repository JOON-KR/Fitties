
 CREATE TABLE schedules (
    user_id VARCHAR(100) NOT NULL PRIMARY key,
	start_date timestamp default CURRENT_TIMESTAMP,
    end_date timestamp NOT NULL,
    goal double NOT NULL,
    current double default 0
);

CREATE TABLE calendardetail (
    num INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    start_time TIMEstamp,
    end_time TIMEstamp DEFAULT CURRENT_TIMESTAMP,
    fit_part VARCHAR(50) NOT NULL
);



