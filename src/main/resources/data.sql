INSERT INTO `artists` (`id`,`name`,`year`) VALUES
(10,'Ebony Green',2015),(20,'Melyssa Gaines',2015),(30,'Chloe Winters',2011),
(40,'Petra Velazquez',2019),(50,'Tiger Patton',2011),(60,'Harriet Hinton',2014),
(70,'Knox Whitaker',1992),(80,'Davis Shannon',2017),(90,'Grady Heath',2012),(100,'Hanae Bennett',2013);


INSERT INTO
 `styles` (`id`,`name`) VALUES 
(10,'Jazz'),
(20,'Classic'),
(30,'Reggae'),
(40,'Pop');

INSERT INTO `artists_artists` 
(`artist_id`,`artists_id`) VALUES
 (90,80), (90,70), (10,20),  (20,10), (30,70), (40,20);
 
INSERT INTO `artists_styles` 
(`artists_id`,`styles_id`) VALUES
(10,20), (90,10), (20,10),(20,40), (30,10),(40,10),
(50,20), (60,20), (70,30),(70,40),(80,10),(80,20),(90,30) ,(100,40);

INSERT INTO `people` (`id`,`name`,`years`,`artist_id`) VALUES
(10,'Porter Rodriguez',28,10),
(20,'Amber Chase',20,20),
(30,'Uriah Roberson',52,20),
(40,'Eagan Nash',38,40),
(50,'Bruno Dalton',45,50),
(60,'Eagan Burt',20,70),
(70,'Dalton Oliver',28,60),
(80,'Samson Coleman',55,80),
(90,'Tobias Barry',65,90),
(100,'Fallon Williams',56,100), 
(110,'Sydney Rutledge',30,10),
(120,'Glenna Walters',54,90),
(130,'Otto Caldwell',49,20),
(140,'Hamish Richardson',21,90)
,(150,'Porter Garza',50,10),(16,'Ulla Ryan',27,60);



