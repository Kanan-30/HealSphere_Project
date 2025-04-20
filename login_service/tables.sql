CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       dummy_id VARCHAR(255) UNIQUE NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE questions (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           step VARCHAR(50) NOT NULL,
                           question_text TEXT NOT NULL,
                           question_type VARCHAR(50) NOT NULL
);

CREATE TABLE options (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         question_id BIGINT NOT NULL,
                         option_text TEXT NOT NULL,
                         FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

CREATE TABLE user_responses (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                dummy_id VARCHAR(255) NOT NULL,
                                question_id BIGINT NOT NULL,
                                selected_option TEXT NOT NULL,
                                FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

CREATE TABLE stories (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         emotion VARCHAR(50) NOT NULL,
                         story_text TEXT NOT NULL
);

CREATE TABLE user_story_feedback (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     dummy_id VARCHAR(255) NOT NULL,
                                     story_id BIGINT NOT NULL,
                                     similarity_level VARCHAR(50) NOT NULL,
                                     FOREIGN KEY (story_id) REFERENCES stories(id) ON DELETE CASCADE
);

CREATE TABLE reports (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         question_id BIGINT NOT NULL,
                         selected_option TEXT NOT NULL,
                         report_text TEXT NOT NULL,
                         FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- Insert Questions for "The Weight of My Problem"
    INSERT INTO questions (step, question_text) VALUES
                                                    ('self_discovery_1', 'How long have you been carrying this burden?'),
                                                    ('self_discovery_1', 'Are you able to put this burden down sometimes?'),
                                                    ('self_discovery_1', 'Do you often feel overwhelmed?'),
                                                    ('self_discovery_1', 'Has this feeling affected your daily life?');

-- Insert Options for the Questions
INSERT INTO options (question_id, option_text) VALUES
                                                   (1, 'A week'), (1, 'Few weeks'), (1, 'Few months'), (1, 'A year'), (1, 'More than a year'),
                                                   (2, 'Rarely'), (2, 'Sometimes'), (2, 'Often'), (2, 'Very often'),
                                                   (3, 'Never'), (3, 'Sometimes'), (3, 'Frequently'), (3, 'Always'),
                                                   (4, 'No impact'), (4, 'Mild impact'), (4, 'Moderate impact'), (4, 'Severe impact');

-- Insert Reports (Mapping User Responses to Reports)
INSERT INTO reports (question_id, selected_option, report_text) VALUES
                                                                    (1, 'A week', 'It seems your burden is recent.'),
                                                                    (1, 'Few months', 'You have been struggling for a while.'),
                                                                    (2, 'Rarely', 'You seem to be managing your burden well.'),
                                                                    (2, 'Very often', 'It appears this is heavily affecting your life.');
