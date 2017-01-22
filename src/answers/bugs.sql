DROP PROCEDURE IF EXISTS get_bugs;
DELIMITER $$

CREATE PROCEDURE get_bugs(startDate DATE, endDate DATE)

/*

The function iterates over each date in the interval creating a query to obtain all the bugs on that specific day
Concat the results of each query through UNION ALL

*/

BEGIN
	
	
  DECLARE QUERY VARCHAR(1000);
  DECLARE currentDate DATE;

  -- Variables used to concat the queries and iterate the dates
  SET QUERY = '';
  SET currentDate = startDate;
  	
  -- From the start date create 1 query per day	
  iterLoop: LOOP
	
	
     IF currentDate = startDate THEN

       SET QUERY = CONCAT(QUERY, ' SELECT * FROM bugs WHERE open_date <= DATE(\'',currentDate,'\') AND close_date = DATE(\'',currentDate,'\') UNION ALL');

     ELSEIF currentDate = endDate THEN

       SET QUERY = CONCAT(QUERY, ' SELECT * FROM bugs WHERE open_date <= DATE(\'',currentDate,'\') AND close_date >= DATE(\'',currentDate,'\') UNION ALL');

     ELSE

       SET QUERY = CONCAT(QUERY, ' SELECT * FROM bugs WHERE open_date <= DATE(\'',currentDate,'\') AND close_date = DATE(\'',currentDate,'\') UNION ALL');

     END IF;

     SET currentDate = DATE_ADD(currentDate, INTERVAL 1 DAY);

     IF currentDate <= endDate THEN

        ITERATE iterLoop;

     END IF;

     LEAVE iterLoop;

   END LOOP iterLoop;
   
   -- Format the prepared statement
   SET @query = LEFT(QUERY, LENGTH(QUERY)-LENGTH('UNION ALL'));
	
   PREPARE statement FROM @query;
   EXECUTE statement;
END;
$$

DELIMITER ;

-- Run a query test
CALL get_bugs('2017-01-01','2017-01-05');