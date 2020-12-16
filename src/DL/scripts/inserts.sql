INSERT INTO `Armazem`.`Entrega` (`codID`, `conteudo`) VALUES ('E0001', 'AAA');
INSERT INTO `Armazem`.`Entrega` (`codID`, `conteudo`) VALUES ('E0002', 'BBB');
INSERT INTO `Armazem`.`Entrega` (`codID`, `conteudo`) VALUES ('E0003', 'CCC');
INSERT INTO `Armazem`.`Entrega` (`codID`, `conteudo`) VALUES ('E0004', 'DDD');
INSERT INTO `Armazem`.`Entrega` (`codID`, `conteudo`) VALUES ('E0005', 'EEE');
INSERT INTO `Armazem`.`Entrega` (`codID`, `conteudo`) VALUES ('E0006', 'FFF');

INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0001', 'AAA');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0002', 'BBB');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0003', 'CCC');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0004', 'DDD');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0005', 'EEE');
INSERT INTO `Armazem`.`Requisicao` (`codID`, `conteudo`) VALUES ('P0006', 'FFF');


INSERT INTO `Armazem`.`Palete` (`codID`, `refrigerado`, `conteudo`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0001', 0, 'AAA', 0, 2);
INSERT INTO `Armazem`.`Palete` (`codID`, `refrigerado`, `conteudo`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0002', 0, 'BBB', 0, 3);
INSERT INTO `Armazem`.`Palete` (`codID`, `refrigerado`, `conteudo`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0003', 0, 'CCC', 0, 4);
INSERT INTO `Armazem`.`Palete` (`codID`, `refrigerado`, `conteudo`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0004', 1, 'DDD', 5, 3);
INSERT INTO `Armazem`.`Palete` (`codID`, `refrigerado`, `conteudo`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0005', 1, 'EEE', 5, 2);
INSERT INTO `Armazem`.`Palete` (`codID`, `refrigerado`, `conteudo`, `LocalizaçaoX`, `LocalizaçaoY`) VALUES ('p0006', 1, 'FFF', 0, 1);


INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0001', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0002', 0);
INSERT INTO `Armazem`.`Robots` (`codID`, `ordens_feitas`) VALUES ('R0003', 9);

INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0001', 'Francisco Peixoto', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0002', 'David Carvalho', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0003', 'Renato Gomes', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0004', 'Sebastião Freitas', 'dss2020');
INSERT INTO `Armazem`.`Gestor` (`codID`, `nome`, `password`) VALUES ('G0005', 'Nestor', 'g0d');

