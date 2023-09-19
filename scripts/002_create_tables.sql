/*CRIA AS TABELAS*/
create table produtos(cod_produto int NOT NULL AUTO_INCREMENT,
				      nome_produto varchar(100),
                      primary key(cod_produto)
);

create table gerente(matricula int not null,
					 nome_gerente varchar(250),
                     primary key(matricula)
);

CREATE TABLE cliente(CPF varchar(11) NOT NULL,
                     nome varchar(250),
                     telefone varchar(11),
                     email varchar(30),
                     PRIMARY KEY (CPF)
);

CREATE TABLE conta(nro_conta int NOT NULL,
                   agencia int DEFAULT NULL,
                   tipo_conta varchar(30) NULL,
                   data_abertura datetime NULL,
                   saldo decimal(10,2) DEFAULT NULL,
                   CPF_cliente varchar(11) DEFAULT NULL,
                   PRIMARY KEY (nro_conta),
                   foreign key(CPF_cliente) references cliente(CPF)
);

create table transacao (cod_transacao int auto_increment,
						data_hora datetime,
                        valor_transacao decimal(10,2),
                        tipo_transacao varchar(20),
                        CPF_origem varchar(11),
                        nro_conta_origem int,
                        CPF_destino varchar(11),
                        nro_conta_destino int,
                        primary key(cod_transacao),
                        foreign key(CPF_origem) REFERENCES cliente(CPF),
                        foreign key(nro_conta_origem) references conta(nro_conta)
);
