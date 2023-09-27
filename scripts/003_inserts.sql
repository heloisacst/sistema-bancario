INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (1234, 'João Silva', '12345678901');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (5678, 'Maria Oliveira', '23456789012');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (9876, 'Pedro Santos', '34567890123');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (4321, 'Ana Pereira', '45678901234');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (8765, 'Carlos Souza', '56789012345');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (3210, 'Fernanda Lima', '67890123456');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (6789, 'Ricardo Castro', '78901234567');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (1098, 'Camila Almeida', '89012345678');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (2109, 'Lucas Rocha', '90123456789');
INSERT INTO gerente (matricula, nome_gerente, cpf_gerente) VALUES (3456, 'Mariana Costa', '01234567890');

INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('48592738465', 'Paula Oliveira', '47123456789', 'paula.oliveira@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('23564870981', 'Rafael Santos', '48123456789', 'rafael.santos@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('34567890321', 'Amanda Lima', '47123456789', 'amanda.lima@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('98765432109', 'Gustavo Silva', '48123456789', 'gustavo.silva@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('56780987654', 'Juliana Rocha', '47123456789', 'juliana.rocha@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('12345678965', 'Thiago Oliveira', '48123456789', 'thiago.oliveira@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('77777788555', 'Patricia Costa', '47123456789', 'patricia.costa@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('88881111888', 'Henrique Castro', '48123456789', 'henrique.castro@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('99992949999', 'Isabela Almeida', '47123456789', 'isabela.almeida@email.com');
INSERT INTO cliente (CPF, nome, telefone, email) VALUES ('10000567890', 'Renato Lima', '48123456789', 'renato.lima@email.com');

INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (12345, '0001', 'CONTA_CORRENTE', '2023-09-27 12:00:00', 1500.50, '48592738465');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (23456, '0001', 'POUPANÇA', '2023-09-27 12:30:00', 5000.75, '23564870981');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (34567, '0001', 'CONTA_SALARIO', '2023-09-27 13:00:00', 1000.25, '34567890321');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (45678, '0001', 'CONTA_CORRENTE', '2023-09-27 13:30:00', 2000.00, '98765432109');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (56789, '0001', 'POUPANÇA', '2023-09-27 14:00:00', 3000.50, '56780987654');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (67890, '0001', 'CONTA_SALARIO', '2023-09-27 14:30:00', 1500.75, '12345678965');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (78901, '0001', 'CONTA_CORRENTE', '2023-09-27 15:00:00', 500.25, '77777788555');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (89012, '0001', 'POUPANÇA', '2023-09-27 15:30:00', 7500.00, '88881111888');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (90123, '0001', 'CONTA_SALARIO', '2023-09-27 16:00:00', 1200.50, '99992949999');
INSERT INTO conta (nro_conta, agencia, tipo_conta, data_abertura, saldo, CPF_cliente) VALUES (01234, '0001', 'CONTA_CORRENTE', '2023-09-27 16:30:00', 800.75, '10000567890');

INSERT INTO usuario VALUES ('admin', 'admin', 12345678901, 'GERENTE');
INSERT INTO usuario VALUES ('cliente', 'cliente', '48592738465', 'CLIENTE');

