DROP TABLE Document CASCADE CONSTRAINTS PURGE
/
DROP TABLE CD CASCADE CONSTRAINTS PURGE /*musique*/
/
DROP TABLE DVD CASCADE CONSTRAINTS PURGE
/
DROP TABLE Livre CASCADE CONSTRAINTS PURGE
/
DROP TABLE Utilisateur CASCADE CONSTRAINTS PURGE
/

REM -----------------------------------------------------------

CREATE TABLE Document (
	id INTEGER CONSTRAINT pk_document PRIMARY KEY,
	nom VARCHAR(30) not null,
	auteur VARCHAR(30) not null,
	prix INTEGER not null,
	emprunte INTEGER
)
/

CREATE TABLE CD (
	idDoc INTEGER CONSTRAINT pk_cd PRIMARY KEY,
	album VARCHAR(30) not null
)
/
CREATE TABLE DVD (
	idDoc INTEGER CONSTRAINT pk_dvd PRIMARY KEY,
	resolution VARCHAR(10) not null
)
/
CREATE TABLE Livre (
	idDoc INTEGER CONSTRAINT pk_livre PRIMARY KEY,
	editeur VARCHAR(30) not null
)
/

CREATE TABLE Utilisateur (
	id INTEGER CONSTRAINT pk_utilisateur PRIMARY KEY,
	login VARCHAR(50) not null unique,
	pass VARCHAR(50) not null
)
/

ALTER TABLE Document
	ADD CONSTRAINT fk_document_utilisateur emprunte REFERENCES Utilisateur(id)
ALTER TABLE CD
	ADD CONSTRAINT fk_cd_document idDoc REFERENCES Document(id)
ALTER TABLE DVD
	ADD CONSTRAINT fk_dvd_document idDoc REFERENCES Document(id)
ALTER TABLE Livre
	ADD CONSTRAINT fk_livre_document idDoc REFERENCES Document(id)

DROP SEQUENCE seq_document
/
DROP SEQUENCE seq_utilisateur
/

CREATE SEQUENCE seq_document start with 0 Minvalue 0
/
CREATE SEQUENCE seq_utilisateur start with 0 Minvalue 0
/

DROP VIEW V_CD;
CREATE VIEW V_CD (id, nom, auteur, prix, emprunte, album)
AS 
SELECT d.id, d.nom, d.auteur, d.prix, d.emprunte, c.album
FROM Document d, CD c WHERE d.id = c.idDoc
/
CREATE OR REPLACE TRIGGER T_V_CD
INSTEAD OF INSERT OR UPDATE OR DELETE ON V_CD
BEGIN
IF NOT DELETING THEN
	INSERT INTO Document VALUES (:NEW.id, :NEW.nom, :NEW.auteur, :NEW.prix, :NEW.emprunte);
	INSERT INTO CD VALUES (:NEW.id, :NEW.album);
ELSE
	DELETE FROM Document WHERE id = :OLD.id;
	DELETE FROM CD WHERE idDoc = :OLD.id;
END IF;
END;
/
show err

DROP VIEW V_DVD;
CREATE VIEW V_DVD (id, nom, auteur, prix, emprunte, resolution)
AS 
SELECT d.id, d.nom, d.auteur, d.prix, d.emprunte, c.resolution
FROM Document d, DVD c WHERE d.id = c.idDoc
/
CREATE OR REPLACE TRIGGER T_V_DVD
INSTEAD OF INSERT OR UPDATE OR DELETE ON V_DVD
BEGIN
IF NOT DELETING THEN
	INSERT INTO Document VALUES (:NEW.id, :NEW.nom, :NEW.auteur, :NEW.prix, :NEW.emprunte);
	INSERT INTO DVD VALUES (:NEW.id, :NEW.resolution);
ELSE
	DELETE FROM Document WHERE id = :OLD.id;
	DELETE FROM DVD WHERE idDoc = :OLD.id;
END IF;
END;
/

DROP VIEW V_Livre;
CREATE VIEW V_Livre (id, nom, auteur, prix, emprunte, editeur)
AS 
SELECT d.id, d.nom, d.auteur, d.prix, d.emprunte, c.editeur
FROM Document d, Livre c WHERE d.id = c.idDoc
/
CREATE OR REPLACE TRIGGER T_V_Livre
INSTEAD OF INSERT OR UPDATE OR DELETE ON V_Livre
BEGIN
IF NOT DELETING THEN
	INSERT INTO Document VALUES (:NEW.id, :NEW.nom, :NEW.auteur, :NEW.prix, :NEW.emprunte);
	INSERT INTO Livre VALUES (:NEW.id, :NEW.editeur);
ELSE
	DELETE FROM Document WHERE id = :OLD.id;
	DELETE FROM Livre WHERE idDoc = :OLD.id;
END IF;
END;
/

INSERT INTO Utilisateur VALUES (seq_utilisateur.nextval,'celbax','motdepasse');
INSERT INTO Utilisateur VALUES (seq_utilisateur.nextval,'celbax1','motdepasse');
INSERT INTO Utilisateur VALUES (seq_utilisateur.nextval,'celbax2','motdepasse');
INSERT INTO Utilisateur VALUES (seq_utilisateur.nextval,'celbax3','motdepasse');

INSERT INTO V_CD VALUES (seq_document.nextval, 'TitreRandom1', 'AuteurRandom1', 5, NULL, 'AlbumRandom1');
INSERT INTO V_CD VALUES (seq_document.nextval, 'TitreRandom2', 'AuteurRandom2', 5, NULL, 'AlbumRandom2');
INSERT INTO V_DVD VALUES (seq_document.nextval, 'TitreRandom1', 'AuteurRandom1', 5, NULL, '1920x1080');
INSERT INTO V_DVD VALUES (seq_document.nextval, 'TitreRandom2', 'AuteurRandom2', 5, NULL, '1920x1080');
INSERT INTO V_Livre VALUES (seq_document.nextval, 'TitreRandom1', 'AuteurRandom1', 5, NULL, 'EditeurRandom1');
INSERT INTO V_Livre VALUES (seq_document.nextval, 'TitreRandom2', 'AuteurRandom2', 5, NULL, 'EditeurRandom2');
