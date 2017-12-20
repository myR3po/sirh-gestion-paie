CREATE TABLE IF NOT EXISTS cotisation (
  ID int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  CODE varchar(10) NOT NULL,
  libelle varchar(50) NOT NULL,
  tauxSalarial decimal(7,2) NOT NULL,
  tauxPatronal decimal(7,2) NOT NULL,
  PRIMARY KEY (ID)
);



CREATE TABLE IF NOT EXISTS grade (
  ID int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  CODE varchar(10) NOT NULL,
  nbHeuresBase decimal(7,2) NOT NULL,
  tauxBase decimal(7,2) NOT NULL,
  PRIMARY KEY (ID) 
);

