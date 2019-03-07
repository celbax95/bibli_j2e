-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 07 mars 2019 à 12:51
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `j2e`
--

-- --------------------------------------------------------

--
-- Structure de la table `cd`
--

DROP TABLE IF EXISTS `cd`;
CREATE TABLE IF NOT EXISTS `cd` (
  `idDoc` int(11) NOT NULL,
  `album` varchar(30) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`idDoc`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `cd`
--

INSERT INTO `cd` (`idDoc`, `album`) VALUES
(2, 'Single');

-- --------------------------------------------------------

--
-- Structure de la table `doctype`
--

DROP TABLE IF EXISTS `doctype`;
CREATE TABLE IF NOT EXISTS `doctype` (
  `idDoc` int(11) NOT NULL,
  `type` varchar(30) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`idDoc`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `doctype`
--

INSERT INTO `doctype` (`idDoc`, `type`) VALUES
(1, 'Livre'),
(2, 'CD'),
(13, 'DVD');

-- --------------------------------------------------------

--
-- Structure de la table `doctypes`
--

DROP TABLE IF EXISTS `doctypes`;
CREATE TABLE IF NOT EXISTS `doctypes` (
  `id` int(11) NOT NULL,
  `type` varchar(30) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `doctypes`
--

INSERT INTO `doctypes` (`id`, `type`) VALUES
(1, 'Livre'),
(2, 'CD'),
(3, 'DVD');

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `auteur` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `prix` int(11) NOT NULL,
  `emprunte` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_document_utilisateur` (`emprunte`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `document`
--

INSERT INTO `document` (`id`, `nom`, `auteur`, `prix`, `emprunte`) VALUES
(1, 'Le labyrinthe', 'James Dashner', 19, 0),
(2, 'I Do It', 'Riles', 2, 0),
(13, 'AVATAR', 'James Cameron', 12, 0);

-- --------------------------------------------------------

--
-- Structure de la table `dvd`
--

DROP TABLE IF EXISTS `dvd`;
CREATE TABLE IF NOT EXISTS `dvd` (
  `idDoc` int(11) NOT NULL,
  `resolution` varchar(10) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`idDoc`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `dvd`
--

INSERT INTO `dvd` (`idDoc`, `resolution`) VALUES
(13, '1920x1080');

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE IF NOT EXISTS `emprunt` (
  `idDoc` int(11) NOT NULL,
  `idUtilisateur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDoc`),
  KEY `idUtilisateur` (`idUtilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

DROP TABLE IF EXISTS `livre`;
CREATE TABLE IF NOT EXISTS `livre` (
  `idDoc` int(11) NOT NULL,
  `editeur` varchar(30) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`idDoc`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`idDoc`, `editeur`) VALUES
(1, 'Dell Publishing');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `pass` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `bibli` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `pass`, `bibli`) VALUES
(1, 'test', 'test', 0),
(2, 'testb', 'test', 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
