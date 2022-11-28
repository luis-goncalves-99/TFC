-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 21-Nov-2020 às 20:22
-- Versão do servidor: 10.4.6-MariaDB
-- versão do PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `androidquizbuilder`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `pergunta`
--

CREATE TABLE `pergunta` (
  `PerguntaID` int(11) NOT NULL,
  `Texto` text NOT NULL,
  `Resposta` int(11) NOT NULL,
  `QuestionarioID` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pergunta`
--

INSERT INTO `pergunta` (`PerguntaID`, `Texto`, `Resposta`, `QuestionarioID`) VALUES
(13, 'Pergunta1', 1, 5),
(14, 'Pergunta2', 1, 5),
(1, 'Qual Ã©, em geral, o algoritmo de ordenaÃ§Ã£o mais eficiente:', 1, 1),
(2, 'Qual Ã© a caracterÃ­stica essencial de um array para que se possa executar o algoritmo de pesquisa binÃ¡ria sobre o mesmo?', 1, 1),
(12, 'Pergunta2', 1, 4),
(3, 'HÃ¡ algum caso em que o Bubble Sort seja mais eficiente do que os outros algoritmos que estudou?', 1, 1),
(11, 'Pergunta1', 1, 4),
(10, 'testePergunta2', 1, 3),
(6, 'P1', 1, 2),
(7, 'P2', 1, 2),
(8, 'p3', 1, 2),
(9, 'testePergunta1', 1, 3),
(4, 'Quantos bits tem um int em Java?', 1, 1),
(5, 'Qual a instruÃ§Ã£o do Java para colocar informaÃ§Ã£o no ecrÃ£?', 1, 1),
(15, 'Quantos bits tem um Byte?', 1, 6),
(16, 'O que Ã© Hardware?', 1, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `questionario`
--

CREATE TABLE `questionario` (
  `QuestionarioID` int(11) NOT NULL,
  `UserCriacao` int(11) NOT NULL,
  `Modo` varchar(50) NOT NULL,
  `Titulo` varchar(50) NOT NULL,
  `Descricao` text NOT NULL,
  `DataDeCriacao` date DEFAULT NULL,
  `Acesso` varchar(50) NOT NULL,
  `Dificuldade` varchar(50) NOT NULL,
  `TimerMinutos` int(11) NOT NULL,
  `TimerSegundos` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `questionario`
--

INSERT INTO `questionario` (`QuestionarioID`, `UserCriacao`, `Modo`, `Titulo`, `Descricao`, `DataDeCriacao`, `Acesso`, `Dificuldade`, `TimerMinutos`, `TimerSegundos`) VALUES
(5, 1, 'classico', 'ClÃ¡ssico', 'Teste', '2020-06-26', 'publico', '1', 0, 45),
(4, 1, 'morte_subita', 'Morte SÃºbita', 'Teste', '2020-06-26', 'publico', '1', 0, 30),
(2, 10, 'questionario', 'teste123', 'teste123', '2020-06-24', 'publico', '1', 0, 0),
(3, 1, 'contra_relogio', 'Contra RelÃ³gio', 'QuestionÃ¡rio Contra RelÃ³gio', '2020-06-26', 'publico', '2', 1, 0),
(1, 2, 'classico', 'QuestionÃ¡rio Java', 'QuestionÃ¡rio Java', '2020-04-24', 'publico', '2', 0, 30),
(6, 27, 'classico', 'Questionario Teste', 'teste', '2020-06-30', 'publico', '1', 0, 45);

-- --------------------------------------------------------

--
-- Estrutura da tabela `resposta`
--

CREATE TABLE `resposta` (
  `RespostaID` int(11) NOT NULL,
  `Texto` text NOT NULL,
  `Correta` tinyint(1) NOT NULL,
  `PerguntaID` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `resposta`
--

INSERT INTO `resposta` (`RespostaID`, `Texto`, `Correta`, `PerguntaID`) VALUES
(59, 'a parte fÃ­sica do computador', 1, 16),
(55, '8', 1, 15),
(47, 'R5', 0, 12),
(48, 'R1', 1, 13),
(49, 'R2', 0, 13),
(50, 'R3', 0, 13),
(51, 'R1', 0, 14),
(52, 'R2', 1, 14),
(53, 'R3', 0, 14),
(16, '8', 0, 4),
(54, 'R4', 0, 14),
(56, '16', 0, 15),
(57, '32', 0, 15),
(58, 'a parte lÃ³gica do computador', 0, 16),
(60, 'nenhuma das anteriores', 0, 16),
(1, 'Bubble Sort', 0, 1),
(2, 'Merge Sort', 0, 1),
(3, 'Very Fast Sort', 0, 1),
(4, 'Quick Sort', 1, 1),
(5, 'Mega Sort', 0, 1),
(6, 'Tem de ter dimensÃ£o menor que 100.', 0, 2),
(7, 'Tem de estar ordenado', 1, 2),
(8, 'SÃ³ pode ter nÃºmeros >= 0', 0, 2),
(9, 'Todas as anteriores', 0, 2),
(10, 'Sim, se o array estiver ordenado ou quase ordenado.', 1, 3),
(11, 'NÃ£o.', 0, 3),
(12, 'Sim, mas nunca Ã© mais rÃ¡pido do que o Selection Sort.', 0, 3),
(13, '16', 0, 4),
(14, '32', 1, 4),
(15, '64', 0, 4),
(19, 'printf', 0, 5),
(17, 'System.out.println', 1, 5),
(18, 'O Java nÃ£o suporta colocar informaÃ§Ã£o no ecrÃ£.', 0, 5),
(46, 'R4', 0, 12),
(45, 'R3', 0, 12),
(43, 'R1', 0, 12),
(44, 'R2', 1, 12),
(42, 'Resposta4', 0, 11),
(41, 'Resposta3', 0, 11),
(40, 'Resposta2', 0, 11),
(39, 'Resposta1', 1, 11),
(38, 'R4', 0, 10),
(37, 'R3', 0, 10),
(36, 'R2', 1, 10),
(27, 'r2', 0, 8),
(28, 'r3', 1, 8),
(29, 'r4', 0, 8),
(30, 'r5', 0, 8),
(31, 'Resposta1', 1, 9),
(32, 'Resposta2', 0, 9),
(33, 'Resposta3', 0, 9),
(34, 'Resposta4', 0, 9),
(35, 'R1', 0, 10),
(20, 'Sim', 1, 6),
(21, 'Nao', 0, 6),
(22, 'Talvez', 0, 6),
(23, 'SIm', 0, 7),
(24, 'mais', 0, 7),
(25, 'menos', 1, 7),
(26, 'r1', 0, 8);

-- --------------------------------------------------------

--
-- Estrutura da tabela `resultados`
--

CREATE TABLE `resultados` (
  `ResultadoID` int(11) NOT NULL,
  `RespostasCertas` int(11) NOT NULL,
  `RespostasErradas` int(11) NOT NULL,
  `Score` int(11) NOT NULL,
  `RespostasRespondidas` int(11) NOT NULL,
  `UtilizadorID` int(11) NOT NULL,
  `QuestionarioID` int(11) NOT NULL,
  `Modo` varchar(50) NOT NULL,
  `DataDePreenchimento` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `resultados`
--

INSERT INTO `resultados` (`ResultadoID`, `RespostasCertas`, `RespostasErradas`, `Score`, `RespostasRespondidas`, `UtilizadorID`, `QuestionarioID`, `Modo`, `DataDePreenchimento`) VALUES
(12, 4, 1, 1045, 6, 2, 1, 'classico', '2020-06-26'),
(10, 5, 0, 1435, 6, 8, 1, 'classico', '2020-06-26'),
(11, 1, 1, 269, 3, 2, 4, 'morte_subita', '2020-06-26'),
(9, 2, 3, 548, 6, 7, 1, 'classico', '2020-06-26'),
(2, 2, 3, 568, 6, 2, 1, 'classico', '2020-06-24'),
(3, 5, 0, 1425, 6, 2, 1, 'classico', '2020-06-24'),
(4, 3, 0, 0, 4, 10, 2, 'questionario', '2020-06-24'),
(5, 3, 2, 777, 6, 2, 1, 'classico', '2020-06-25'),
(6, 3, 2, 827, 6, 4, 1, 'classico', '2020-06-26'),
(7, 5, 0, 1415, 6, 5, 1, 'classico', '2020-06-26'),
(8, 4, 1, 1146, 6, 6, 1, 'classico', '2020-06-26'),
(1, 4, 1, 1066, 6, 3, 1, 'classico', '2020-06-23');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `tipo` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `nome`, `email`, `password`, `tipo`) VALUES
(2, 'user2', 'user@a', '793f970c52ded1276b9264c742f19d1888cbaf73', 0),
(1, 'admin', 'admin@a', 'd033e22ae348aeb5660fc2140aec35850c4da997', 1),
(3, 'user3', 'user3@a', '0b7f849446d3383546d15a480966084442cd2193', 0),
(4, 'Joao', 'joao@a', 'c510cd8607f92e1e09fd0b0d0d035c16d2428fa4', 0),
(5, 'Miguel', 'miguel@a', '75004f149038473757da0be07ef76dd4a9bdbc8d', 0),
(6, 'Felipe', 'felipe@a', 'b41e9b8dd61267c8eb3db48acfda473f53d9964b', 0),
(7, 'Pedro', 'pedro@a', '4410d99cefe57ec2c2cdbd3f1d5cf862bb4fb6f8', 0),
(8, 'Tiago', 'tiago@a', '12fd5311017d4b8faf7abc6d7fa13d182f519a13', 0),
(10, 'gus', 'gus@gus', '22b4468ae6dcf46c36c9622e292c7a3506bb0db4', 0),
(27, 'user', 'user@gmail.com', '12dea96fec20593566ab75692c9949596833adc9', 0);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `pergunta`
--
ALTER TABLE `pergunta`
  ADD PRIMARY KEY (`PerguntaID`);

--
-- Índices para tabela `questionario`
--
ALTER TABLE `questionario`
  ADD PRIMARY KEY (`QuestionarioID`);

--
-- Índices para tabela `resposta`
--
ALTER TABLE `resposta`
  ADD PRIMARY KEY (`RespostaID`);

--
-- Índices para tabela `resultados`
--
ALTER TABLE `resultados`
  ADD PRIMARY KEY (`ResultadoID`);

--
-- Índices para tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `resposta`
--
ALTER TABLE `resposta`
  MODIFY `RespostaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8501;

--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
