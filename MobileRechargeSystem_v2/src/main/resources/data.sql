INSERT INTO users (name,email,password,wallet_balance,role) VALUES ('Admin','admin@example.com','admin123',0.0,'ADMIN') ON DUPLICATE KEY UPDATE email=email;
INSERT INTO users (name,email,password,wallet_balance,role) VALUES ('Test User','user@example.com','user123',200.0,'USER') ON DUPLICATE KEY UPDATE email=email;
INSERT INTO recharge_plans (operator,plan_name,amount,validity,description) VALUES ('Airtel','Airtel 199',199.0,'28 days','Unlimited calls + 1.5GB/day') ON DUPLICATE KEY UPDATE operator=operator;
