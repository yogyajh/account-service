DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS account ;
DROP TABLE IF EXISTS transaction;
DROP SEQUENCE IF EXISTS base_seq;

CREATE SEQUENCE base_seq
  START WITH 1
  INCREMENT BY 50;


CREATE TABLE user (  
id BIGINT NOT NULL,  
created_by BIGINT,
created_date TIMESTAMP,
updated_by BIGINT,
updated_date TIMESTAMP,
version INTEGER DEFAULT 0,
name VARCHAR(255) NOT NULL,
dob TIMESTAMP NOT NULL,
identity_number VARCHAR(255) NOT NULL,
passport_number VARCHAR(255),
mobile_number VARCHAR(255),
address VARCHAR(255)
);

CREATE TABLE account(  
id BIGINT NOT NULL,  
created_by BIGINT,
created_date TIMESTAMP,
updated_by BIGINT,
updated_date TIMESTAMP,
version INTEGER DEFAULT 0,
user_id BIGINT NOT NULL,
acct_number VARCHAR(255) NOT NULL,
acct_name VARCHAR(255) NOT NULL,
acct_type VARCHAR(255),
currency VARCHAR(255),
available_balance DECIMAL(19, 2),
total_balance DECIMAL(19, 2)
);


CREATE TABLE transaction(  
id BIGINT NOT NULL,  
created_by BIGINT,
created_date TIMESTAMP,
updated_by BIGINT,
updated_date TIMESTAMP,
version INTEGER DEFAULT 0,
from_acct VARCHAR(255) NOT NULL,
to_acct VARCHAR(255) NOT NULL,
amount DECIMAL(19, 2) NOT NULL
);
