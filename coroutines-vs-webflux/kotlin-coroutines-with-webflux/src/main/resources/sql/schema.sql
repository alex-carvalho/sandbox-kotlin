CREATE TABLE IF NOT EXISTS tasks
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    title               VARCHAR(255) NOT NULL,
    completed           BIT NOT NULL
);