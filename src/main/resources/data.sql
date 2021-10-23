INSERT INTO user (id, created_by, created_date, updated_by, updated_date, version, name, dob, identity_number, passport_number, mobile_number, address) VALUES 
                  (1, 1, '2021-10-18', 1, '2021-10-18', 0, 'Test User','1990-10-18', '1235', '5678', '0112-67822186', 'Test Address');


INSERT INTO account (id, created_by, created_date, updated_by, updated_date, version, user_id, acct_number, acct_name, acct_type, currency, available_balance, total_balance) VALUES 
                    (6, 1, '2021-10-18', 1, '2021-10-18', 0, 1, '567459872', 'Savings', 'SAVINGS', 'AUD', 10000.00, 10000.00);
INSERT INTO account (id, created_by, created_date, updated_by, updated_date, version, user_id, acct_number, acct_name, acct_type, currency, available_balance, total_balance) VALUES 
                    (7, 1, '2021-10-18', 1, '2021-10-18', 0, 1, '123459874', 'Current', 'CURRENT', 'AUD', 70000.00, 70000.00);
INSERT INTO account (id, created_by, created_date, updated_by, updated_date, version, user_id, acct_number, acct_name, acct_type, currency, available_balance, total_balance) VALUES 
                    (8, 1, '2021-10-18', 1, '2021-10-18', 0, 1, '456459875', 'Savings', 'SAVINGS', 'SGD', 100000.00, 100000.00);
INSERT INTO account (id, created_by, created_date, updated_by, updated_date, version, user_id, acct_number, acct_name, acct_type, currency, available_balance, total_balance) VALUES 
                    (9, 1, '2021-10-18', 1, '2021-10-18', 0, 1, '789459876', 'Current', 'CURRENT', 'SGD', 120000.00, 120000.00);

				  
INSERT INTO transaction (id, created_by, created_date, updated_by, updated_date, version, from_acct, to_acct, amount) VALUES 
                        (10, 1, '2021-08-18', 1, '2021-08-18', 0, 7, 6, 100000.00);
INSERT INTO transaction (id, created_by, created_date, updated_by, updated_date, version, from_acct, to_acct, amount) VALUES 
                        (11, 1, '2021-09-08', 1, '2021-09-08', 0, 6, 8, 2000.00);
INSERT INTO transaction (id, created_by, created_date, updated_by, updated_date, version, from_acct, to_acct, amount) VALUES 
                        (12, 1, '2021-10-20', 1, '2021-10-20', 0, 8, 6, 7000.00);
INSERT INTO transaction (id, created_by, created_date, updated_by, updated_date, version, from_acct, to_acct, amount) VALUES 
                        (13, 1, '2021-10-21', 1, '2021-10-21', 0, 9, 6, 5000.00);
				  
				 