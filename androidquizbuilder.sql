-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 09-Mar-2018 às 00:08
-- Versão do servidor: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `androidquizbuilder`
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
(52, 'Quem foi o primeiro rei de Portugal?', 1, 17),
(51, 'Quem foi o segundo rei de Portugal?', 1, 17),
(48, 'pergunta5', 1, 16),
(47, 'pergunta6', 1, 16),
(46, 'pergunta7', 1, 14),
(45, 'pergunta8', 1, 14),
(44, 'pergunta9', 1, 13),
(43, 'pergunta10', 1, 13),
(55, 'dia da apresentacao', 1, 23),
(56, 'dia de entrega', 1, 23);

-- --------------------------------------------------------

--
-- Estrutura da tabela `questionario`
--

CREATE TABLE `questionario` (
  `QuestionarioID` int(11) NOT NULL,
  `Titulo` varchar(50) NOT NULL,
  `Descricao` text NOT NULL,
  `DataDeCriacao` date NOT NULL,
  `Estado` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `questionario`
--

INSERT INTO `questionario` (`QuestionarioID`, `Titulo`, `Descricao`, `DataDeCriacao`, `Estado`) VALUES
(17, 'Reis de Portugal', 'Reis de Portugal', '2017-06-23', 'ativo'),
(23, 'que dia e hoje?', 'dia do mes', '2017-07-13', 'ativo'),
(16, 'Capitais de Paises', 'Capitais de Paises', '2017-06-23', 'ativo'),
(14, 'Perguntas de Matematica', 'Perguntas de Matematica', '2017-06-18', 'ativo'),
(13, 'Perguntas de Informatica', 'Perguntas de Informatica', '2017-06-17', 'ativo');

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
(104, 'D. Pedro I', 0, 52),
(103, 'D. Dinis I', 0, 52),
(102, 'D. Sancho I', 0, 52),
(101, 'D. Afonso Henriques', 1, 52),
(115, '11/7', 0, 56),
(92, 'D. Pedro I', 0, 51),
(91, 'D. Dinis I', 0, 51),
(90, 'D. Afonso Henriques', 0, 51),
(89, 'D. Sancho I', 1, 51),
(111, '13/7', 1, 55),
(112, '12/7', 0, 55),
(113, '5/7', 1, 56),
(114, '12/7', 0, 56),
(82, 'e', 0, 48),
(81, 'c', 1, 48),
(80, 'e4', 0, 47),
(79, 'e2', 0, 47),
(78, 'e1', 0, 47),
(77, 'c', 1, 47),
(76, 'e', 0, 46),
(75, 'c', 1, 46),
(74, 'c', 1, 45),
(73, 'e2', 1, 44),
(72, 'c2', 1, 44),
(71, 'e', 0, 43),
(70, 'c', 1, 43);

-- --------------------------------------------------------

--
-- Estrutura da tabela `resultados`
--

CREATE TABLE `resultados` (
  `ResultadoID` int(11) NOT NULL,
  `RespostasCertas` int(11) NOT NULL,
  `RespostasErradas` int(11) NOT NULL,
  `RespostasRespondidas` int(11) NOT NULL,
  `UtilizadorID` int(11) NOT NULL,
  `QuestionarioID` int(11) NOT NULL,
  `DataDePreenchimento` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `resultados`
--

INSERT INTO `resultados` (`ResultadoID`, `RespostasCertas`, `RespostasErradas`, `RespostasRespondidas`, `UtilizadorID`, `QuestionarioID`, `DataDePreenchimento`) VALUES
(24, 2, 0, 2, 1, 16, '2017-06-23'),
(23, 2, 0, 2, 1, 13, '2017-06-21'),
(26, 0, 2, 2, 4, 17, '2017-07-02'),
(27, 2, 0, 2, 1, 14, '2017-07-02'),
(20, 2, 0, 2, 2, 14, '2017-06-18'),
(34, 2, 0, 2, 54, 23, '2017-07-13'),
(25, 2, 0, 2, 1, 17, '2017-06-23');

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
(1, 'user1', 'user1@teste', '0cc175b9c0f1b6a831c399e269772661', 0),
(2, 'user2', 'ola@a', '2fe04e524ba40505a82e03a2819429cc', 0),
(3, 'admin', 'admin@a', '21232f297a57a5a743894a0e4a801fc3', 1),
(4, 'user3', 'user@a', 'ee11cbb19052e40b07aac0ca060c23ee', 0),
(5, 'user4', 'teste@a', '698dc19d489c4e4db73e28a713eab07b', 0),
(48, 'user5', 'gg@a', '73c18c59a39b18382081ec00bb456d43', 0),
(54, 'novoo', 'novo@novo', '42323e3211ed4478b2b8ba87d4185a03', 0),
(51, 'novoUser', 'user@novo', '42323e3211ed4478b2b8ba87d4185a03', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pergunta`
--
ALTER TABLE `pergunta`
  ADD PRIMARY KEY (`PerguntaID`);

--
-- Indexes for table `questionario`
--
ALTER TABLE `questionario`
  ADD PRIMARY KEY (`QuestionarioID`);

--
-- Indexes for table `resposta`
--
ALTER TABLE `resposta`
  ADD PRIMARY KEY (`RespostaID`);

--
-- Indexes for table `resultados`
--
ALTER TABLE `resultados`
  ADD PRIMARY KEY (`ResultadoID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pergunta`
--
ALTER TABLE `pergunta`
  MODIFY `PerguntaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT for table `questionario`
--
ALTER TABLE `questionario`
  MODIFY `QuestionarioID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `resposta`
--
ALTER TABLE `resposta`
  MODIFY `RespostaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;
--
-- AUTO_INCREMENT for table `resultados`
--
ALTER TABLE `resultados`
  MODIFY `ResultadoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
