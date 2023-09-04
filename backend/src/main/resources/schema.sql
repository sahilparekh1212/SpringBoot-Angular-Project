CREATE SCHEMA "teams_management_system";
CREATE TABLE teams (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(30) NOT NULL,
    game_name VARCHAR(30) NOT NULL,
    email_id VARCHAR(30)
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(15) NOT NULL,
    password VARCHAR(80) NOT NULL,
    email_id VARCHAR(30) NOT NULL,
    roles VARCHAR(30) NOT NULL
);
