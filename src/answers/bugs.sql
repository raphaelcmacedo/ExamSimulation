DROP PROCEDURE IF EXISTS search_bugs;
DELIMITER $$

CREATE PROCEDURE search_bugs(startDate DATE, endDate DATE)

BEGIN

  DECLARE output VARCHAR(750);
  DECLARE targetDate DATE;

  SET output = '';
  SET targetDate = startDate;

  iterLoop: LOOP

     IF targetDate = startDate THEN

       SET output = CONCAT(output, ' SELECT * FROM bugs WHERE open_date <= DATE(\'',targetDate,'\') AND close_date = DATE(\'',targetDate,'\') UNION ALL');

     ELSEIF targetDate = endDate THEN

       SET output = CONCAT(output, ' SELECT * FROM bugs WHERE open_date <= DATE(\'',targetDate,'\') AND close_date >= DATE(\'',targetDate,'\') UNION ALL');

     ELSE

       SET output = CONCAT(output, ' SELECT * FROM bugs WHERE open_date <= DATE(\'',targetDate,'\') AND close_date = DATE(\'',targetDate,'\') UNION ALL');

     END IF;

     SET targetDate = DATE_ADD(targetDate, INTERVAL 1 DAY);

     IF targetDate <= endDate THEN

        ITERATE iterLoop;

     END IF;

     LEAVE iterLoop;

   END LOOP iterLoop;

   SET output = LEFT(output, LENGTH(output)-LENGTH('UNION ALL'));

   PREPARE  statement FROM @output;
   EXECUTE statement;

END;
$$

DELIMITER ;

CALL search_bugs(STR_TO_DATE('2015-11-10', '%Y-%m-%d'), STR_TO_DATE('2015-11-03', '%Y-%m-%d'));