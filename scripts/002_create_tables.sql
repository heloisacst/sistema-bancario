/*CRIA AS TABELAS*/
create table produtos(cod_produto int NOT NULL AUTO_INCREMENT,
				      nome_produto varchar(100),
                      primary key(cod_produto)
);

create table gerente(matricula int not null,
					 nome_gerente varchar(250),
                     primary key(matricula)
);
					
