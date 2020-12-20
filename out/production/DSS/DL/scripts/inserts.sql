INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p1', 'AAA', 0, 0, 2);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p2', 'BBB', 0, 0, 3);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p3', 'CCC', 0, 0, 4);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p4', 'DDD', 1, 5, 3);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p5', 'EEE', 1, 5, 2);
INSERT INTO `Armazem`.`Palete` (`codID`, `conteudo`, `refrigerado`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p6', 'FFF', 1, 0, 4);

INSERT INTO `Armazem`.`Entrega` (`codID`, `palete`, `conteudo`) VALUES ('E1', 'p1', 'AAA');
INSERT INTO `Armazem`.`Entrega` (`codID`, `palete`, `conteudo`) VALUES ('E2', 'p2', 'BBB');
INSERT INTO `Armazem`.`Entrega` (`codID`, `palete`, `conteudo`) VALUES ('E3', 'p3', 'CCC');
INSERT INTO `Armazem`.`Entrega` (`codID`, `palete`, `conteudo`) VALUES ('E4', 'p4', 'DDD');
INSERT INTO `Armazem`.`Entrega` (`codID`, `palete`, `conteudo`) VALUES ('E5', 'p5', 'EEE');
INSERT INTO `Armazem`.`Entrega` (`codID`, `palete`, `conteudo`) VALUES ('E6', 'p6', 'FFF');


INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P1', 'AAA');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P2', 'BBB');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P3', 'CCC');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P4', 'DDD');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P5', 'EEE');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P6', 'FFF');


INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0001', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0002', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0003', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0004', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0005', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0006', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0007', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0008', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0009', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R9999', 9);

INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0001', 'Francisco Peixoto', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0002', 'David Carvalho', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0003', 'Renato Gomes', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0004', 'Sebastião Freitas', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0005', 'Nestor', 'g0d');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('a1', 'Mário', 'a1');
