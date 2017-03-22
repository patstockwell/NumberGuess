/*
 * Create a rocky concrete database without any integrity constraints. 
 */  

/* Set date format to Australian Standard Date Format DD/MM/YYYY */
-- alter session set nls_date_format = 'DD/MM/YYYY'; 
 
/* Remove any existing tables
 * Customers, Products, Orders, Order_details
 * This steps is important if you had stale tables from a previous session
 */
CREATE DATABASE NumberGuessDB;
USE NumberGuessDB;

drop table players;
drop table guesses;

/*  Data Definition */

CREATE TABLE PLAYERS
(
	USER_ID			INTEGER NOT NULL,
	POINTS			INTEGER			,
	PASSWORD		VARCHAR(40)		,
	PRIMARY KEY(USER_ID)
);

CREATE TABLE GUESSES
(
	GAME_ID			INTEGER NOT NULL,
	GUESS			INTEGER	NOT NULL,
	USER_ID			INTEGER	NOT NULL,
	PRIMARY KEY(USER_ID, GUESS, GAME_ID)
);

