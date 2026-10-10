INSERT INTO USERS(username, password, role) VALUES ('testUser1', '{bcrypt}$2a$12$7NaPX1GPSIuBrYT/HeswSeuzQAoo47ssiuuWI7vRRIkopnLynxIOm', 'USER');
INSERT INTO USERS(username, password, role) VALUES ('testUser2', '{bcrypt}$2a$12$B3y4nnsqfZ5eOx0VCSNhu.C8VK4k/miXvN3BrwhLtgUGqxnpwGboS', 'USER');
INSERT INTO USERS(username, password, role) VALUES ('testUser3', '{bcrypt}$2a$12$qcoae4OYhCoMQ4So9eW5xeiejx6ifugEq17Fnq/ZF40Sh3d8RSSK2', 'ADMIN');

INSERT INTO ITEMS(user_id, name, list_name) VALUES ('1', 'Test item 1', 'list 1');
INSERT INTO ITEMS(user_id, name, list_name) VALUES ('1', 'Test item 2', 'list 1');
INSERT INTO ITEMS(user_id, name, list_name) VALUES ('1', 'Test item 3', 'list 1');
INSERT INTO ITEMS(user_id, name, description, item_link, img_url, list_name) VALUES ('1', 'Test item 4', 'test Description', 'https://www.google.com/', 'https://via.placeholder.com/300', 'list 1');