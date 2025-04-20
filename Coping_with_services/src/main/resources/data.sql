-- Insert into crisis_descriptions
INSERT INTO crisis_descriptions (id, description, page, title) VALUES
                                                                   (1, 'Understanding how to manage crisis situations.', 1, 'Coping with Crisis'),
                                                                   (2, 'A psychological crisis is a state of emotional turmoil.', 2, 'What is a Psychological Crisis?'),
                                                                   (3, 'Crisis states can be overwhelming, but they can be managed.', 3, 'Understanding Crisis States'),
                                                                   (4, 'Steps to handle crisis effectively.', 4, 'Crisis Response Plan'),
                                                                   (5, 'How to maintain and use your crisis plan.', 5, 'Finalizing Your Crisis Plan');

-- Insert into crisis_response_steps
INSERT INTO crisis_response_steps (id, pdf_link, question, step) VALUES
                                                                     (1, 'https://example.com/calming_techniques.pdf', 'What can I do to feel calmer?', 1),
                                                                     (2, 'https://example.com/avoid_unhealthy_coping.pdf', 'What should I avoid for quick relief?', 2),
                                                                     (3, 'https://example.com/self_talk.pdf', 'What positive things can I tell myself?', 3),
                                                                     (4, 'https://example.com/seeking_support.pdf', 'Who can I talk to for help?', 4);

-- Insert into crisis_response_step_options
INSERT INTO crisis_response_step_options (crisis_response_step_id, options) VALUES
                                                                                (1, 'Breathing exercises'),
                                                                                (1, 'Go for a walk'),
                                                                                (1, 'Listen to music'),
                                                                                (2, 'Avoid reckless driving'),
                                                                                (2, 'Avoid alcohol or drugs'),
                                                                                (2, 'Do not self-harm'),
                                                                                (3, 'This too shall pass'),
                                                                                (3, 'I am stronger than this moment'),
                                                                                (3, 'I have overcome difficulties before'),
                                                                                (4, 'Call a friend'),
                                                                                (4, 'Talk to a counselor'),
                                                                                (4, 'Reach out to family');

-- Insert into helplines
INSERT INTO helplines (id, name, number, region, timings) VALUES
                                                              (1, 'Tele-Manas', '14416', 'All-India', '24*7'),
                                                              (2, 'Vandrwala Foundation', '18002333330', 'All-India', '24*7'),
                                                              (3, 'Aasra', '9820466726', 'Mumbai', '24*7'),
                                                              (4, 'Samaritans', '9999999999', 'Mumbai', '4pm - 10pm'),
                                                              (5, 'Sahai', '08025497777', 'Bangalore', '10am - 8pm');
