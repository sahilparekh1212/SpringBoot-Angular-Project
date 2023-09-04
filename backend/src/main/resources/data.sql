INSERT INTO teams(team_name,game_name,email_id) VALUES('A','Game A','a@game.com'); 
INSERT INTO teams(team_name,game_name,email_id) VALUES('B','Game B','b@game.com'); 
INSERT INTO teams(team_name,game_name,email_id) VALUES('C','Game C','c@game.com'); 

-- username: admin , password: admin
INSERT INTO users(username,password,email_id,roles) VALUES('admin','$2a$16$QeaGXv.0wS4XTQaSLB3BQO7xDw1F4h1IxdzCIMixb./O21By7KYQe','admin@admin.com','ROLE_admin');
-- username: user , password: user
INSERT INTO users(username,password,email_id,roles) VALUES('user','$2a$16$rni8rdrHO/dmXu8rCibCzukbOvHhbXau.c4KvgNER2nR9oM8flze6','user@user.com','ROLE_user');
-- username: combo , password: combo
INSERT INTO users(username,password,email_id,roles) VALUES('combo','$2a$16$zP2V3vDhdDm4tYxJuHfIR.doxm5dWxz6CklOjhr1m/TasSGMuEg5C','combo@user.com','ROLE_admin,ROLE_user');
