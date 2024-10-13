-- Delete the travel table if it exists
DROP TABLE IF EXISTS travel;

-- create the travel table
CREATE TABLE  IF NOT EXISTS travel (
  id INT primary key,
  destination varchar(50),
  category INT
);