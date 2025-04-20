CREATE TABLE letters (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         date DATE NOT NULL,
                         category ENUM('pause to reflect', 'challenge yourself') NOT NULL,
                         heading VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL
);
