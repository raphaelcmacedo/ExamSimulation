DROP FUNCTION IF EXISTS CAPITALIZE_FIRST;

DELIMITER $$

CREATE FUNCTION CAPITALIZE_FIRST(input VARCHAR(250))
  RETURNS VARCHAR(250) DETERMINISTIC

BEGIN

  -- Declare the variables to control the loop and captalize just the first letter in the word
  DECLARE output VARCHAR(250);  
  DECLARE prevChar VARCHAR(1);  
  DECLARE curChar VARCHAR(1);   
  DECLARE ctr INT;              

  SET output = UCASE(LEFT(input, 1));
  SET ctr=1;
  SET prevChar = SUBSTRING(input, ctr, 1);
  SET curChar = "";

  -- Iterates the input looking for the first letter based on the start of the string or the ' ' between the words
  elementLoop: LOOP
    SET ctr = ctr + 1;
    SET curChar = SUBSTRING(input, ctr, 1);

    IF prevChar = " " THEN
      SET output = CONCAT(output,UCASE(curChar));
    ELSE
      SET output = CONCAT(output,LCASE(curChar));
    END IF;

    SET prevChar = curChar;

    IF (ctr < LENGTH(input)) THEN
      ITERATE elementLoop;
    END IF;

    LEAVE elementLoop;

  END LOOP elementLoop;

  RETURN output;
END;
$$
DELIMITER ;

SELECT CAPITALIZE_FIRST(NAME) FROM countries;