-- Topic
-- CREATE TABLE topic (
--     id BIGSERIAL PRIMARY KEY,
--     title VARCHAR(256) NOT NULL CHECK (LENGTH(title) >= 1 AND LENGTH(title) <= 256),
--     createdDatetime DATE
-- );

-- CREATE TABLE test_oylesine (
-- 	id bigserial primary key;
-- )

-- User
-- CREATE TABLE 'user' (
--     id SERIAL PRIMARY KEY,
--     username VARCHAR(255) NOT NULL,
--     is_naive BOOLEAN
-- );

-- Entry
-- CREATE TABLE entry (
--     id BIGSERIAL PRIMARY KEY,
--     content TEXT
--     written_by BIGINT,
--     written_datetime DATE,
--     topic BIGINT,
--     is_include_link BOOLEAN,
--     is_written_by_naive BOOLEAN,
--     upvoted INT,
--     downvoted INT,
--     fav_count INT
--     FOREIGN KEY (written_by) REFERENCES "user" (id),
--     FOREIGN KEY (topic) REFERENCES topic (id)
);

