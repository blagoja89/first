/* Account */

INSERT INTO `bank`.`ACCOUNT` (`ID_ACCOUNT`, `NUMBER`, `BILL`, `DELETED`) VALUES (1, '1001', '50000', NULL);
INSERT INTO `bank`.`ACCOUNT` (`ID_ACCOUNT`, `NUMBER`, `BILL`, `DELETED`) VALUES (2, '1002', '70000', NULL);

/* User_t */

INSERT INTO `bank`.`USER_T` (`ID_USER`, `LOGIN`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `EMAIL`, `DELETED`, `ROLE`, `ACCOUNT_ID`) VALUES (1, 'admin', 'dmn', 'Petr', 'Petrov', 'admin@rs.com', FALSE, 2, 2);
INSERT INTO `bank`.`USER_T` (`ID_USER`, `LOGIN`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `EMAIL`, `DELETED`, `ROLE`, `ACCOUNT_ID`) VALUES (2, 'mavs', 'mvs', 'Ivan', 'Ivanchenko', 'mavs@rs.com', FALSE, 2, 1);
INSERT INTO `bank`.`USER_T` (`ID_USER`, `LOGIN`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `EMAIL`, `DELETED`, `ROLE`, `ACCOUNT_ID`) VALUES (3, 'client', 'clnt', 'Jan', 'Janko', 'client@tut.by', FALSE, 1, 1);
INSERT INTO `bank`.`USER_T` (`ID_USER`, `LOGIN`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `EMAIL`, `DELETED`, `ROLE`, `ACCOUNT_ID`) VALUES (4, 'mark', 'mrk', 'Klim', 'Vanich', 'mark@mail.ru', FALSE, 1, 2);
