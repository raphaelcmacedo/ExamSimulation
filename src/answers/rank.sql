/* Question
 * 
 * Write a query to rank order the following table in MySQL by votes,
 * display the rank as one of the columns. 
 * CREATE TABLE votes ( name CHAR(10), votes INT ); INSERT INTO votes VALUES ('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);
 * 
 */

SELECT v.name, v.votes, 
	FIND_IN_SET( votes, (
		SELECT GROUP_CONCAT( votes
		ORDER BY votes DESC ) 
		FROM votes )
	) AS rank
FROM votes v
ORDER BY votes DESC;


