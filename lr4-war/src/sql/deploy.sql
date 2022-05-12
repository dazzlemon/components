CREATE DATABASE Products

USE Products

CREATE TABLE Product( Id              INT IDENTITY(1, 1)
                    , [Name]          TEXT NOT NULL
                    , Quantity        INT NOT NULL
                    , PriceInUsdCents INT NOT NULL

                    , CONSTRAINT PK_Product PRIMARY KEY (Id)
                    )

INSERT INTO Product ([Name], Quantity, PriceInUsdCents)
             VALUES ('Tomato', 1000, 10)
                  , ('Apple', 2000, 5)
                  , ('Strawberry', 500, 10)
                  , ('Banana', 700, 7)
                  , ('Mango', 300, 24)