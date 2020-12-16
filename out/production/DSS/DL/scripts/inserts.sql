INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0001', 'AAA');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0002', 'BBB');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0003', 'CCC');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0004', 'DDD');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0005', 'EEE');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0006', 'FFF');


INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0001', 'AAA', 0, 0, 2);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0002', 'BBB', 0, 0, 3);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0003', 'CCC', 0,0, 4);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0004', 'DDD', 1, 5, 3);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0005', 'EEE', 1, 5, 2);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0006', 'FFF', 1, 0, 1);

INSERT INTO `Armazem`.`Entrega` (`codID`, `Palete_codID`, `Palete_conteudo`) VALUES ('E0001', 'p0001', 'AAA');
INSERT INTO `Armazem`.`Entrega` (`codID`, `Palete_codID`, `Palete_conteudo`) VALUES ('E0002', 'p0002', 'BBB');
INSERT INTO `Armazem`.`Entrega` (`codID`, `Palete_codID`, `Palete_conteudo`) VALUES ('E0003', 'p0003', 'CCC');
INSERT INTO `Armazem`.`Entrega` (`codID`, `Palete_codID`, `Palete_conteudo`) VALUES ('E0004', 'p0004', 'DDD');
INSERT INTO `Armazem`.`Entrega` (`codID`, `Palete_codID`, `Palete_conteudo`) VALUES ('E0005', 'p0005', 'EEE');
INSERT INTO `Armazem`.`Entrega` (`codID`, `Palete_codID`, `Palete_conteudo`) VALUES ('E0006', 'p0006', 'FFF');

INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0001', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0002', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0003', 9);

INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0001', 'Francisco Peixoto', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0002', 'David Carvalho', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0003', 'Renato Gomes', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0004', 'Sebastião Freitas', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0005', 'Nestor', 'g0d');

