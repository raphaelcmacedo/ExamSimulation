/* Question
 * 
 * Write a procedure in MySQL to split a column into rows using a delimiter.
 * CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) ); INSERT INTO sometbl VALUES (1, 'Smith'), 
 * (2, 'Julio|Jones|Falcons'), (3, 'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag'); 
 * For (2), example rows would look like >> “3, white”, “3, Snow” ...
 * 
 */

DROP PROCEDURE IF EXISTS split_column;

DELIMITER $$


CREATE PROCEDURE split_column(delimeter VARCHAR(255))

BEGIN

    DECLARE id INT DEFAULT 0;
    DECLARE NAME VARCHAR(250);
    DECLARE occur INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE pipe INT DEFAULT 0;
    DECLARE splitted_name VARCHAR(50);
    DECLARE done INT DEFAULT 0;
    DECLARE sourceTable CURSOR FOR SELECT split.id, split.name FROM split WHERE split.name != '';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    -- Table that will handle the records for the splitted columns.
    DROP TABLE IF EXISTS new_split;
    CREATE TABLE new_split(id INT, NAME VARCHAR(250));

    OPEN sourceTable;

       -- Read all the records from split and split columns with pipes.
      read_loop: LOOP 

        FETCH sourceTable INTO id, NAME;

        IF done THEN
            LEAVE read_loop;
        END IF;

        SET pipe = LOCATE(delimeter, NAME);

        IF pipe > 0 THEN -- If the name contains pipe '|' then split and add as a new record.

            SET occur = (SELECT LENGTH(NAME) - LENGTH(REPLACE(NAME, delimeter, '')) + 1 );
            SET i = 1;

            WHILE i <= occur DO

                SET splitted_name = (SELECT REPLACE(SUBSTRING(SUBSTRING_INDEX(NAME, delimeter, i), LENGTH(SUBSTRING_INDEX(NAME, delimeter, i - 1)) + 1), delimeter, ''));
                INSERT INTO new_split(id, NAME) VALUES (id, splitted_name);
                SET i = i + 1;

            END WHILE;
        -- The name has no piple so add it directly to the new table.    
        ELSE 
            INSERT INTO new_split VALUES (id, NAME);
        END IF;

      END LOOP;

      SELECT * FROM new_split;

    CLOSE sourceTable;
END;
$$

DELIMITER ;

CALL split_column('|');