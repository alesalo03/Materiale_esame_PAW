-- Artista data (id, nome, bio)
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(1,'Metallica', 'California metal band whose aggressive yet melodic style made them one of the most popular bands of all time.');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(2,'Megadeth', 'One of the most popular and important thrash metal bands, led by Dave Mustaine, with strong social and political messages throughout their music.');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(3,'Anthrax', 'One of the most innovative bands in speed metal, and one of the first metal groups to collaborate with rap artists.');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(4,'Eric Clapton', 'A rock guitar legend who, in addition to a distinguished solo career, collaborated with countless artists and played in many classic bands.');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(5,'Van Halen', 'One of the best and most popular American hard rock/heavy metal bands, primarily distinguished by the fleet fingers of guitarist Eddie Van Halen.');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(6,'AC/DC', 'One of the defining acts of 70s hard rock, driven by the bazooka roar of the Young brothers twin guitars and Bon Scott''s snarling vocals.');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(7,'The Beatles', 'The most popular and influential rock act of all time, a band that blazed several new trails for popular music.');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(8, 'Lou Reed', 'One of rock'' most important singer/songwriters, the creator of a daring body of work who proved rock & roll could be art.');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(9, 'Linkin Park', 'Alternative metal quintet from Southern California that sold tens of millions during 2000s');
INSERT INTO artista (ID, NOME, BIOGRAFIA) VALUES(10, 'Jay Z', 'Embodiment of the rags-to-riches rap dream, largely due to his unsurpassed lyrical talent, incredible dedication, and industry smarts.');

-- album data (id, titolo, anno)
INSERT INTO album (ID, TITOLO, ANNO) VALUES (1, '...And Justice for All', 1988);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(2, 'Black Album',1991);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(3, 'Master of Puppets',1986);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(4, 'Endgame',2009);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(5, 'Peace Sells',1986);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(6, 'The Greater of 2 Evils',2004);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(7, 'Reptile',2001);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(8, 'Riding with the King',2000);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(9, 'Greatest Hits',2004);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(10, 'Greatest Hits',2003);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(11, 'Sgt. Pepper''s Lonely Hearts Club Band', 1967);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(12, 'Lulu', 2011); -- da qui in poi aggiungere brani
INSERT INTO album (ID, TITOLO, ANNO) VALUES(13, 'Collision Course', 2004); 
INSERT INTO album (ID, TITOLO, ANNO) VALUES(14, 'Meteora', 2003); 
INSERT INTO album (ID, TITOLO, ANNO) VALUES(15, 'Hybrid Theory', 2000);
INSERT INTO album (ID, TITOLO, ANNO) VALUES(16, 'Reasonable Doubt', 1996);

--album_artista (artista_id, album_id)
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (1, 1);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (1, 2);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (1, 3);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (2, 4);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (2, 5);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (3, 6);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (4, 7);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (4, 8);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (5, 9);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (6, 10);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (7, 11);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (1, 12);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (8, 12);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (9, 13);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (10, 13);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (9, 14);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (9, 15);
INSERT INTO album_artista (ARTISTA_ID, ALBUM_ID) VALUES (10, 16);
 
-- brano data (id, album_id, titolo, durata) 
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(1,1,'One', 6.42);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(2,1,'Blackned',6.42);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(3,2,'Enter Sandman',5.3);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(4,2,'Sad But True',5.29);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(5,3,'Master of Puppets',8.35);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(6,3,'Battery',5.13);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(7,4,'Dialectic Chaos',2.26);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(8,4,'Endgame',5.57);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(9,5,'Peace Sells',4.09);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(10,5,'The Conjuring',5.09);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(11,6,'Madhouse',4.26);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(12,6,'I am the Law',6.03);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(13,7,'Reptile',3.36);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(14,7,'Modern Girl',4.49);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(15,8,'Riding with the King',4.23);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(16,8,'Key to the Highway',3.39);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(17,9,'Eruption',1.43);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(18,9,'Hot For Teacher',4.43);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(19,10,'Thunderstruck',4.52);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(20,10,'T.N.T',3.35);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(21,11,'Sgt. Pepper''s Lonely Hearts Club Band', 2.03);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(22,11,'With a Little Help from My Friends', 2.73);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(23,11,'Lucy in the Sky with Diamonds', 3.46);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(24,11,'Getting Better', 2.80);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(25,11,'Fixing a Hole', 2.60);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(26,11,'She''s Leaving Home', 3.58);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(27,11,'Being for the Benefit of Mr. Kite!',2.61);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(28, 11,'Within You Without You',5.0);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(29,11,'When I''m Sixty-Four',2.61);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(30 ,11,'Lovely Rita', 2.7);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(31,11,'Good Morning Good Morning', 2.68);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(32,11,'Sgt. Pepper''s Lonely Hearts Club Band (Reprise)', 1.31);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(33,11,'A Day in the Life', 5.65);
------------
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(34,12,'Brandenburg Gate', 4.21);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(35,12,'The View', 5.20);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(36,12,'Pumping Blood', 7.24);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(37,12,'Mistress Dread', 6.52);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(38,12,'Iced Honey', 4.37);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(39,12,'	Cheat on Me', 11.26);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(40,13,'Dirt Off Your Shoulder/Lying from You', 4.04);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(41,13,'Big Pimpin''/Papercut', 2.36);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(42,13,'Numb/Encore', 3.25);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(43,13,'Points of Authority/99 Problems/One Step Closer', 7.27);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(44,13,'Jigga What/Faint', 3.31);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(45,13,'Izzo/In the End', 2.44);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(46,14,'Don''t Stay', 3.08);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(47,14,'Somewhere I Belong', 3.34);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(48,14,'Faint', 2.42);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(49,14,'Numb', 3.09);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(50,15,'Papercut', 3.05);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(51,15,'One Step Closer', 2.36);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(52,15,'Crawling', 3.29);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(53,15,'Runaway', 3.04);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(54,15,'In the End', 3.36);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(55,15,'Place for My Head', 3.05);

INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(56,16,'Can''t Knock the Hustle', 5.17);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(57,16,'Dead Presidents II', 4.27);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(58,16,'Feelin'' It', 3.48);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(59,16,'Friend or Foe', 1.49);
INSERT INTO brano (ID, ALBUM_ID, TITOLO, DURATA) VALUES(60,16,'Can I Live II', 3.57);

-- tipo_supporto(id, nome)
INSERT INTO tipo_supporto (ID, NOME) VALUES(1,'CD');
INSERT INTO tipo_supporto (ID, NOME) VALUES(2,'Vinile');
INSERT INTO tipo_supporto (ID, NOME) VALUES(3,'Audio Cassetta');
INSERT INTO tipo_supporto (ID, NOME) VALUES(4,'Digitale');

-- supporto (id, prezzo, album_id, tipo_supporto_id) 
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(1,1,1,8.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(2,1,2,28.35);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(3,1,3,20.50);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(4,1,4,4.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(5,2,1,6.49);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(6,2,2,26.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(7,2,4,3.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(8,3,1,10.31);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(9,3,2,25.91);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(10,3,4,5.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(11,4,1,16.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(12,4,2,34.25);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(13,4,4,8.99);


INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(14,5,1,8.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(15,5,2,21.22);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(16,5,3,15.25);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(17,5,4,3.99); --


INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(18,6,1,15.63);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(19,6,2,22.22);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(20,6,4,2.99);


INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(21,7,1,9.81);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(22,7,4,5.99);


INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(23,8,1,10.70);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(24,8,2,32.29);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(25,8,4,4.99);


INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(26,9,1,13.25);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(27,9,2,42.24);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(28,9,4,9.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(29,10,1,19.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(30,10,2,50.25);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(31,10,4,10.99);


INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(32,11,1,10.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(33,11,2,27.38);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(34,11,4,10.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(35,12,1,11.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(36,12,4,5.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(37,13,1,10.79);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(38,13,4,5.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(39,14,1,9.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(40,14,2,21.49);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(41,14,4,4.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(42,15,1,16.99);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(43,15,2,32.49);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(44,15,4,6.99);

INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(45,16,1,7.98);
INSERT INTO supporto (ID, ALBUM_ID, TIPO_SUPPORTO_ID, PREZZO) VALUES(46,16,4,3.99);

-- USER, MODULE, GROUP

INSERT INTO "MODULE" (ID, MODULEID, MODULENAME) VALUES
	(1, 'sv1', 'Public'),
	(2, 'sv2', 'Admin'),
	(3, 'area2', 'AreaOrders');

INSERT INTO "GROUP" (ID,GROUPNAME, MODULE_ID) VALUES
	 (1,'Customers', 1), -- customers by default can access to public view
	 (2,'Administrators', 2); -- administrators by default can access to admin view

INSERT INTO GROUP_MODULE (GROUP_ID, MODULE_ID) VALUES
	(2, 1), --administrators can also access to public view
	(1, 3); --customers can also access to orders area

INSERT INTO "USER" (ID,USERNAME,PASSWORD,EMAIL, GROUP_ID) VALUES
 (1,'admin','admin',NULL, 2),
 (2,'mario','rossi','mario.rossi@prova.it', 1);