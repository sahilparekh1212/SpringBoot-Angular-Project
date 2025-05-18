CREATE SCHEMA teams_management_system;
CREATE TABLE teams_management_system.teams (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(30) NOT NULL,
    game_name VARCHAR(30) NOT NULL,
    email_id VARCHAR(30)
);

CREATE TABLE teams_management_system.users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(15) NOT NULL,
    password VARCHAR(80) NOT NULL,
    email_id VARCHAR(30) NOT NULL,
    roles VARCHAR(30) NOT NULL
);

-- username: admin , password: admin
INSERT INTO teams_management_system.users(username,password,email_id,roles) VALUES('admin','$2a$05$rKf9nb1lVDmt6/wSDoyWGeYorGR.BdEbJGwqjwEwQDDuKuZDbmtr2','admin@admin.com','ROLE_admin');
-- username: user , password: user
INSERT INTO teams_management_system.users(username,password,email_id,roles) VALUES('user','$2a$05$Ju9gnRftvNtBeoHLXH3qj.DDGgrjKI7Fq9QKUVtz12UZdB6HBePXe','user@user.com','ROLE_user');
-- username: combo , password: combo
INSERT INTO teams_management_system.users(username,password,email_id,roles) VALUES('combo','$2a$05$ct.ubVUfTjIdnERx76W1IezNtYK8SObeeZRnfOFQ6csPAZqh7tqEW','combo@user.com','ROLE_admin,ROLE_user');
