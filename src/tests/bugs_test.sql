DROP TABLE IF EXISTS bugs;
CREATE TABLE bugs (id INT, severity INT, open_date DATE, close_date DATE);

INSERT INTO bugs VALUES(1, 1, STR_TO_DATE('2014-11-29', '%Y-%m-%d'), STR_TO_DATE('2014-11-30', '%Y-%m-%d'));
INSERT INTO bugs VALUES(2, 1, STR_TO_DATE('2015-11-10', '%Y-%m-%d'), STR_TO_DATE('2015-11-11', '%Y-%m-%d'));
INSERT INTO bugs VALUES(3, 1, STR_TO_DATE('2015-11-13', '%Y-%m-%d'), STR_TO_DATE('2015-11-13', '%Y-%m-%d'));
INSERT INTO bugs VALUES(4, 1, STR_TO_DATE('2015-11-14', '%Y-%m-%d'), STR_TO_DATE('2015-11-15', '%Y-%m-%d'));
INSERT INTO bugs VALUES(5, 1, STR_TO_DATE('2015-11-15', '%Y-%m-%d'), STR_TO_DATE('2015-11-16', '%Y-%m-%d'));
INSERT INTO bugs VALUES(5, 1, STR_TO_DATE('2015-11-16', '%Y-%m-%d'), STR_TO_DATE('2015-11-16', '%Y-%m-%d'));