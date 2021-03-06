CREATE DEFINER=`root`@`%` FUNCTION `getDepartmentChilds`(GivenID  VARCHAR(1024),needChild BOOLEAN) RETURNS TEXT CHARSET latin1
    DETERMINISTIC
BEGIN
    DECLARE q,queue,queue_children,front_id VARCHAR(1024);
    DECLARE rv TEXT;
    DECLARE queue_length,pos INT;
    SET rv = '';
    SET queue = GivenID;
    SET queue_length = 1;
    WHILE queue_length > 0 DO
        IF queue_length = 1 THEN
            SET front_id = queue;
            SET queue = '';
        ELSE
            SET pos = LOCATE(',',queue) - 1;
            SET q = SUBSTR(queue,1,pos);
            SET front_id = q;
            SET queue = SUBSTR(queue,pos+2);
        END IF;
        SET queue_length = queue_length - 1;
        IF needChild = TRUE THEN
          SELECT IFNULL(qc,'') INTO queue_children
          FROM (SELECT GROUP_CONCAT(id) qc
          FROM ETEC_DEPARTMENT WHERE super_department = front_id) A;
        ELSE
          SELECT IFNULL(qc,'') INTO queue_children
          FROM (SELECT GROUP_CONCAT(id) qc
          FROM ETEC_DEPARTMENT WHERE super_department = front_id) A;
        END IF;
        IF LENGTH(queue_children) = 0 THEN
            IF LENGTH(queue) = 0 THEN
                SET queue_length = 0;
            END IF;
        ELSE
            IF LENGTH(rv) = 0 THEN
                SET rv = queue_children;
            ELSE
                SET rv = CONCAT(rv,',',queue_children);
            END IF;
            IF LENGTH(queue) = 0 THEN
                SET queue = queue_children;
            ELSE
                SET queue = CONCAT(queue,',',queue_children);
            END IF;
            SET queue_length = LENGTH(queue) - LENGTH(REPLACE(queue,',','')) + 1;
        END IF;
    END WHILE;
    RETURN rv;
END

