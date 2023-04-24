### SQL script
``
CREATE TABLE Posts (
id int NOT NULL AUTO_INCREMENT,
title varchar(255) NOT NULL,
metatitle varchar(255),
category varchar(255) NOT NULL,
content varchar(65535) NOT NULL,
Author varchar(255) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
PRIMARY KEY (id)
);``