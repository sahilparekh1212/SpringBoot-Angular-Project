INSERT INTO teams(team_name,game_name,email_id) VALUES('A','Game A','a@game.com'); 
INSERT INTO teams(team_name,game_name,email_id) VALUES('B','Game B','b@game.com'); 
INSERT INTO teams(team_name,game_name,email_id) VALUES('C','Game C','c@game.com'); 

-- username: admin , password: admin
INSERT INTO users(username,password,email_id,roles) VALUES('admin','$2a$05$rKf9nb1lVDmt6/wSDoyWGeYorGR.BdEbJGwqjwEwQDDuKuZDbmtr2','admin@admin.com','ROLE_admin');
-- -- username: user , password: user
INSERT INTO users(username,password,email_id,roles) VALUES('user','$2a$05$Ju9gnRftvNtBeoHLXH3qj.DDGgrjKI7Fq9QKUVtz12UZdB6HBePXe','user@user.com','ROLE_user');
-- username: combo , password: combo
INSERT INTO users(username,password,email_id,roles) VALUES('combo','$2a$05$ct.ubVUfTjIdnERx76W1IezNtYK8SObeeZRnfOFQ6csPAZqh7tqEW','combo@user.com','ROLE_admin,ROLE_user');
