CREATE TABLE self_help_toolkit (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(255) NOT NULL UNIQUE,
                                   description TEXT
);

CREATE TABLE self_help_levels (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  toolkit_id BIGINT,
                                  level_name VARCHAR(255) NOT NULL,
                                  level_order INT NOT NULL,
                                  is_unlocked BOOLEAN DEFAULT FALSE,
                                  FOREIGN KEY (toolkit_id) REFERENCES self_help_toolkit(id)
);

CREATE TABLE self_help_content (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   level_id BIGINT,
                                   content_type ENUM('QUOTE', 'CONVERSATION', 'SITUATION'),
                                   content TEXT NOT NULL,
                                   FOREIGN KEY (level_id) REFERENCES self_help_levels(id)
);

CREATE TABLE self_help_assessments (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       level_id BIGINT,
                                       question TEXT NOT NULL,
                                       options JSON NOT NULL,
                                       FOREIGN KEY (level_id) REFERENCES self_help_levels(id)
);

CREATE TABLE self_help_user_progress (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         user_id BIGINT,
                                         toolkit_id BIGINT,
                                         level_id BIGINT,
                                         completed BOOLEAN DEFAULT FALSE,
                                         score INT,
                                         FOREIGN KEY (user_id) REFERENCES users(id),
                                         FOREIGN KEY (toolkit_id) REFERENCES self_help_toolkit(id),
                                         FOREIGN KEY (level_id) REFERENCES self_help_levels(id)
);
