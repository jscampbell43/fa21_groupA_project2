CREATE TABLE IF NOT EXISTS USERS (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS ITEMS (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    name TEXT NOT NULL,
    description TEXT DEFAULT 'Description goes here' NOT NULL,
    item_link TEXT DEFAULT 'https://via.placeholder.com/150/' NOT NULL,
    img_url TEXT DEFAULT 'https://via.placeholder.com/150/' NOT NULL,
    list_name TEXT NOT NULL
);