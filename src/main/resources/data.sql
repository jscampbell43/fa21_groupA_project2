INSERT INTO USERS(username, password) VALUES ('testUser1', 'tUser1Password');
INSERT INTO USERS(username, password) VALUES ('testUser2', 'tUser2Password');

INSERT INTO ITEMS(user_id, name, list_name) VALUES ('1', 'Test item 1', 'list 1');
INSERT INTO ITEMS(user_id, name, list_name) VALUES ('1', 'Test item 2', 'list 1');
INSERT INTO ITEMS(user_id, name, list_name) VALUES ('1', 'Test item 3', 'list 1');
INSERT INTO ITEMS(user_id, name, description, item_link, img_url, list_name) VALUES ('1', 'Test item 4', 'test Description', 'https://www.google.com/', 'https://via.placeholder.com/300', 'list 1');