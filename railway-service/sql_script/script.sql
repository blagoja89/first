﻿/* start script 1 */

CREATE  TABLE IF NOT EXISTS rwby.`DB_VERSION` (
	`SEQ_NO` INT NULL ,
	`TIME_EXECUTE` TIMESTAMP NULL
	);

INSERT INTO `rwby`.`DB_VERSION` (SEQ_NO, TIME_EXECUTE) VALUE (1, CURRENT_TIMESTAMP);
COMMIT;
/* end script 1 */
	
	
/* EXAMPLE SCRIPT*/
/* start script XXX */
/*
... script body ... 
*/

/* INSERT INTO `rwby`.`DB_VERSION` (SEQ_NO, TIME_EXECUTE) VALUE (XXX, CURRENT_TIMESTAMP);
COMMIT;
 */

/* end script XXX */