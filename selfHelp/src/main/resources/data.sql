-- Insert into self_help_toolkit
INSERT INTO self_help_toolkit (id, description, name) VALUES
                                                          (1, 'Toolkit to manage self-critical thoughts', 'Self-Criticality'),
                                                          (2, 'Toolkit to master worry and overthinking', 'Mastering Worry'),
                                                          (3, 'Toolkit to calm and soothe yourself', 'Calming & Soothing');

-- Insert into self_help_levels
INSERT INTO self_help_levels (id, is_unlocked, level_name, level_order, toolkit_id) VALUES
                                                                                        (1, b'1', 'Understanding Self-Criticism', 1, 1),
                                                                                        (2, b'0', 'Challenging Negative Thoughts', 2, 1),
                                                                                        (3, b'1', 'Recognizing Worry Patterns', 1, 2),
                                                                                        (4, b'0', 'Practicing Mindfulness', 2, 2),
                                                                                        (5, b'1', 'Relaxation Techniques', 1, 3);

-- Insert into self_help_content
INSERT INTO self_help_content (id, content, content_type, level_id) VALUES
                                                                        (1, 'You are doing your best. Mistakes happen.', 'QUOTE', 1),
                                                                        (2, 'Imagine your friend made the same mistake. What would you say?', 'SITUATION', 1),
                                                                        (3, 'Let\'s talk about how negative thoughts affect emotions.', 'CONVERSATION', 2),
(4, 'Worrying won\'t change the outcome. Focus on what you control.', 'QUOTE', 3),
                                                                        (5, 'Try deep breathing: inhale 4 seconds, exhale 4 seconds.', 'CONVERSATION', 5);

-- Insert into self_help_assessments
INSERT INTO self_help_assessments (id, options, question, level_id) VALUES
                                                                        (1, '["Strongly Agree", "Agree", "Neutral", "Disagree", "Strongly Disagree"]', 'I often criticize myself when things go wrong.', 1),
                                                                        (2, '["Never", "Rarely", "Sometimes", "Often", "Always"]', 'How often do you find yourself worrying unnecessarily?', 3),
                                                                        (3, '["Yes", "No"]', 'Do you practice any relaxation techniques daily?', 5);
