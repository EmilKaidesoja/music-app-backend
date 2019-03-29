CREATE TABLE IF NOT EXISTS user (
    id 			 bigint 			NOT NULL,
    firstName 	 varchar(50)  	NOT NULL,
    lastName 	 varchar(50)  	NOT NULL,
    email		 varchar(50) 		NOT NULL,
    username	 varchar(50) 		NOT NULL,
    password	  varchar(255) 	NOT NULL,
    role		 varchar(10) 		NOT NULL,
    PRIMARY KEY(id)
    );
    
 CREATE TABLE IF NOT EXISTS previous_search (
    id 			  bigint		 	NOT NULL,
    searchWord  	varchar(50)  NOT NULL	,
    userId 		 bigint	 NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES user(id)
    );
    
 CREATE TABLE IF NOT EXISTS favorite_song (
    id 			bigint	 		NOT NULL,
    artistName 	varchar(50) NOT NULL,
    songName 	varchar(50) 	NOT NULL,
    userId 		bigint 	NOT NULL,
    PRIMARY KEY(id),
     FOREIGN KEY(userId) REFERENCES user(id)
    );