CREATE DATABASE jogo_da_velha;
USE jogo_da_velha;

CREATE TABLE jogadores (
	id INT PRIMARY KEY AUTO_INCREMENT,
  robo boolean NOT NULL DEFAULT false,
  marca varchar(3) NOT NULL,
  sigla varchar(3) NOT NULL
);

CREATE TABLE configuracoes (
	id INT PRIMARY KEY AUTO_INCREMENT,
  jogador1_id INT NOT NULL,
  jogador2_id INT NOT NULL,
	FOREIGN KEY(jogador1_id) REFERENCES jogadores(id),
  FOREIGN KEY(jogador2_id) REFERENCES jogadores(id)
);

INSERT INTO jogadores(robo, marca, sigla) VALUE (false, 'X', 'P01');
INSERT INTO jogadores(robo, marca, sigla) VALUE (true, 'O', 'P02');
INSERT INTO configuracoes(jogador1_id, jogador2_id) VALUE (1, 2);
