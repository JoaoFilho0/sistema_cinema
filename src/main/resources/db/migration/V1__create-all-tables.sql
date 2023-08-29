CREATE TABLE endereco(
    end_id int PRIMARY KEY AUTO_INCREMENT,
    end_cidade varchar(100) not null,
    end_bairro varchar(100) not null,
    end_rua varchar(100) not null,
    end_numero int
);

CREATE TABLE filmes(
    fil_id int PRIMARY KEY AUTO_INCREMENT,
    fil_nome varchar(100) not null,
    fil_duracao int not null
);

CREATE TABLE cinema(
    cin_id int PRIMARY KEY AUTO_INCREMENT,
    cin_nome varchar(100) not null,
    fk_endereco_id int not null,
    FOREIGN KEY (fk_endereco_id) REFERENCES endereco(end_id)
);

CREATE TABLE horario(
    hor_id int PRIMARY KEY AUTO_INCREMENT,
    hor_dia varchar(100) not null,
    hor_horario varchar(100) not null
);

CREATE TABLE cinema_horario(
    cin_hor_id int PRIMARY KEY AUTO_INCREMENT,
    fk_cinema_id int not null,
    fk_horario_id int not null,
    FOREIGN KEY (fk_cinema_id) REFERENCES cinema(cin_id),
    FOREIGN KEY (fk_horario_id) REFERENCES horario(hor_id)
);

CREATE TABLE usuario(
    usu_id int PRIMARY KEY AUTO_INCREMENT,
    usu_nome varchar(100) not null,
    usu_email varchar(100) not null unique,
    usu_senha varchar(100) not null,
    fk_cinema_id int,
    FOREIGN KEY (fk_cinema_id) REFERENCES cinema(cin_id)
);

CREATE TABLE salas(
    sala_id int PRIMARY KEY AUTO_INCREMENT,
    sala_numero int not null,
    sala_assentos int not null
);

CREATE TABLE cinema_salas(
    cin_sala_id int PRIMARY KEY AUTO_INCREMENT,
    fk_cinema_id int not null,
    fk_salas_id int not null,
    FOREIGN KEY (fk_cinema_id) REFERENCES cinema(cin_id),
    FOREIGN KEY (fk_salas_id) REFERENCES salas(sala_id)
);

CREATE TABLE sessao(
    ses_id int PRIMARY KEY AUTO_INCREMENT,
    ses_horario varchar(100) not null,
    ses_quantidade_ingresso int not null,
    ses_preco_ingresso numeric(7,2) not null
);

CREATE TABLE sessao_salas(
    ses_salas_id int PRIMARY KEY AUTO_INCREMENT,
    fk_sessao_id int not null,
    fk_salas_id int not null,
    FOREIGN KEY (fk_sessao_id) REFERENCES sessao(ses_id),
    FOREIGN KEY (fk_salas_id) REFERENCES salas(sala_id)
);

CREATE TABLE sessao_filmes(
    ses_fil_id int PRIMARY KEY AUTO_INCREMENT,
    fk_filmes_id int not null,
    fk_salas_id int not null,
    FOREIGN KEY (fk_filmes_id) REFERENCES filmes(fil_id),
    FOREIGN KEY (fk_salas_id) REFERENCES salas(sala_id)
);

CREATE TABLE usuario_sessao(
    usu_ses_id int PRIMARY KEY AUTO_INCREMENT,
    usu_ses_check bit not null,
    fk_usuario_id int not null,
    fk_sessao_id int not null,
    FOREIGN KEY (fk_usuario_id) REFERENCES usuario(usu_id),
    FOREIGN KEY (fk_sessao_id) REFERENCES sessao(ses_id)
);