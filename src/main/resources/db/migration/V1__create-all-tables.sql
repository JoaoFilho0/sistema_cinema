CREATE TABLE endereco(
    end_id int not null auto_increment,
    end_cidade varchar(100) not null,
    end_bairro varchar(100) not null,
    end_rua varchar(100) not null,
    end_numero int not null,
    PRIMARY KEY(end_id)
);

CREATE TABLE filme(
    fil_id int not null auto_increment,
    fil_nome varchar(200) not null,
    fil_duracao int not null,
    PRIMARY KEY(fil_id)
);

CREATE TABLE cinema(
    cin_id int not null auto_increment,
    cin_nome varchar(200) not null,
    fk_endereco_id int not null,
    PRIMARY KEY(cin_id),
    FOREIGN KEY (fk_endereco_id) REFERENCES endereco(end_id)
);

CREATE TABLE cliente(
    cli_id int not null auto_increment,
    cli_nome varchar(200) not null,
    cli_email varchar(200) not null,
    cli_senha varchar(100) not null,
    fk_cinema_id int,
    PRIMARY KEY(cli_id),
    FOREIGN KEY (fk_cinema_id) REFERENCES cinema(cin_id)
);

CREATE TABLE horario(
    hor_id int not null auto_increment,
    hor_dia varchar(50) not null,
    hor_horario varchar(50) not null,
    fk_cinema_id int not null,
    PRIMARY KEY(hor_id),
    FOREIGN KEY (fk_cinema_id) REFERENCES cinema(cin_id)
);

CREATE TABLE sala_de_cinema(
    sala_id int not null auto_increment,
    sala_num int not null,
    sala_bancos int not null,
    fk_cinema_id int not null,
    PRIMARY KEY(sala_id),
    FOREIGN KEY (fk_cinema_id) REFERENCES cinema(cin_id)
);

CREATE TABLE sessao(
    ses_id int not null auto_increment,
    ses_horario varchar(100) not null,
    ses_quant_ingresso int not null,
    ses_ingresso_preco numeric(6,2) not null,
    fk_sala_de_cinema_id int not null,
    fk_filme_id int not null,
    PRIMARY KEY(ses_id),
    FOREIGN KEY (fk_sala_de_cinema_id) REFERENCES sala_de_cinema(sala_id),
    FOREIGN KEY (fk_filme_id) REFERENCES filme(fil_id)
);

CREATE TABLE cliente_sessao(
    cli_ses_id int not null auto_increment,
    cli_ses_check bool not null,
    fk_cliente_id int not null,
    fk_sessao_id int not null,
    PRIMARY KEY(cli_ses_id),
    FOREIGN KEY (fk_cliente_id) REFERENCES cliente(cli_id),
    FOREIGN KEY (fk_sessao_id) REFERENCES sessao(ses_id)
);