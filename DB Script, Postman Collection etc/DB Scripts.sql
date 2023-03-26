CREATE DATABASE teams_management_system;

CREATE TABLE `teams_management_system`.`team` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(30) NOT NULL,
    game_name VARCHAR(30) NOT NULL,
    email_id VARCHAR(30)
);

DESCRIBE teams_management_system.team;

INSERT INTO teams_management_system.team(team_name,game_name,email_id) VALUES('A','Game A','a@game.com'); 
INSERT INTO teams_management_system.team(team_name,game_name,email_id) VALUES('B','Game B','b@game.com'); 
INSERT INTO teams_management_system.team(team_name,game_name,email_id) VALUES('C','Game C','c@game.com'); 

SELECT * FROM teams_management_system.team;