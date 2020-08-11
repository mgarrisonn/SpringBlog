USE spring_db;

INSERT INTO posts (id, title, post, user_id)
VALUES
(1, 'Title', 'Post Post Post Post Post', 1);

#
use comments;
INSERT INTO comments (content, post_id)
VALUES
('This is a test comment', 1);


INSERT INTO users (id, username, email, password) values (1, 'trey210', 'trey@email.com', 'password');
