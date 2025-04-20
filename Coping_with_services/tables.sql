CREATE TABLE helplines (
                           id SERIAL PRIMARY KEY,
                           region VARCHAR(255) NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           timings VARCHAR(50) NOT NULL,
                           number VARCHAR(20) NOT NULL
);

CREATE TABLE crisis_descriptions (
                                     id SERIAL PRIMARY KEY,
                                     page INT NOT NULL,
                                     title VARCHAR(255) NOT NULL,
                                     description TEXT NOT NULL
);

CREATE TABLE crisis_response_steps (
                                       id SERIAL PRIMARY KEY,
                                       step INT NOT NULL,
                                       question VARCHAR(255) NOT NULL,
                                       options TEXT NOT NULL,
                                       pdf_link VARCHAR(255)
);

CREATE TABLE user_crisis_plans (
                                   id SERIAL PRIMARY KEY,
                                   user_dummy_id UUID NOT NULL,
                                   response_details TEXT NOT NULL
);
INSERT INTO helplines (region, name, timings, number) VALUES
                                                          ('All-India', 'Tele-Manas', '24*7', '14416'),
                                                          ('All-India', 'Vandrwala Foundation', '24*7', '18002333330'),
                                                          ('Mumbai', 'Aasra', '24*7', '9820466726');

INSERT INTO crisis_descriptions (page, title, description) VALUES
                                                               (1, 'Coping with Crisis', 'Some description about crisis.'),
                                                               (2, 'What is a Psychological Crisis?', '8 key points explaining crisis.');

INSERT INTO crisis_response_steps (step, question, options, pdf_link) VALUES
                                                                          (1, 'What can I do to feel a bit calmer?', '{"Breathing exercise", "Go for a walk"}', 'link_to_pdf'),
                                                                          (2, 'What should I avoid doing for quick relief?', '{"Speed driving", "Alcohol"}', 'link_to_pdf');
