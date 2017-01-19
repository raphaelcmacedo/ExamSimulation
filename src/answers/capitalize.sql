ROP FUNCTION IF EXISTS CAP_FIRST_CHAR;

DELIMITER $$

CREATE FUNCTION CAP_FIRST_CHAR(input VARCHAR(250))
  RETURNS VARCHAR(250) DETERMINISTIC

BEGIN

  DECLARE output VARCHAR(250);  -- Holds the final capitalised value.
  DECLARE prevChar VARCHAR(1);  -- Holds the previous character in the loop.
  DECLARE curChar VARCHAR(1);   -- Holds the current character in the loop.
  DECLARE ctr INT;              -- The index of the character in the loop.

  SET output = UCASE(LEFT(input, 1));
  SET ctr=1;
  SET prevChar = SUBSTRING(input, ctr, 1);
  SET curChar = "";

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

SELECT CAP_FIRST_CHAR(NAME) FROM countries;