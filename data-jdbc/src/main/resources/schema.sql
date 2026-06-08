CREATE TABLE customer_order (
    id SERIAL PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE order_item (
    id SERIAL PRIMARY KEY,
    customer_order_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (customer_order_id) REFERENCES customer_order(id)
);