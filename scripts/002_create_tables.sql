/*CRIA AS TABELAS*/
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
                   agencia int NULL,
                   tipo_conta varchar(30) NULL,
                   data_abertura datetime NULL,
                   saldo decimal(10,2) NULL,
                   CPF_cliente varchar(11) NULL,
                   PRIMARY KEY (nro_conta),
                   foreign key(CPF_cliente) references cliente(CPF)
);

create table transacao (cod_transacao int auto_increment,
						data_hora datetime,
                        valor_transacao decimal(10,2),
                        tipo_transacao varchar(20),
                        nro_conta_origem int,
                        nro_conta_destino int,
                        primary key(cod_transacao),
                        foreign key(nro_conta_origem) references conta(nro_conta)
);
