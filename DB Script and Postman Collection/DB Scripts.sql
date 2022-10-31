CREATE DATABASE teams_management_system;

CREATE TABLE `team_management_system`.`team` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(30) NOT NULL,
    game_name VARCHAR(30) NOT NULL,
    email_id VARCHAR(30)
);

insert into team_management_system.team(team_name,game_name,email_id) values('A','Game A','a@game.com'); 
insert into team_management_system.team(team_name,game_name,email_id) values('B','Game B','b@game.com'); 
insert into team_management_system.team(team_name,game_name,email_id) values('C','Game C','c@game.com'); 
