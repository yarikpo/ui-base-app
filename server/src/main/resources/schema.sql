CREATE TABLE card (
    card_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    code VARCHAR(255) NOT NULL,
    cvv INT NOT NULL,
    creation_date DATE
);

CREATE TABLE operation (
    operation_id INT AUTO_INCREMENT PRIMARY KEY,
    card_id INT NOT NULL,
    amount INT NOT NULL,
    operation_datetime TIMESTAMP,
    FOREIGN KEY(card_id) REFERENCES card(card_id)
);