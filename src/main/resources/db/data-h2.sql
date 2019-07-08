DELETE FROM calendar;
INSERT INTO calendar (date, lunar, solarterms) VALUES
('2019-07-01', '廿九', '建党节'),
('2019-07-02', '三十', null),
('2019-07-03', '初一', null),
('2019-07-04', '初二', null),
('2019-07-05', '初三', null),
('2019-07-06', '初四', null),
('2019-07-07', '初五', '小暑'),
('2019-07-08', '初六', null),
('2019-07-09', '初七', null),
('2019-07-10', '初八', null),
('2019-07-11', '初九', null),
('2019-07-12', '初十', null),
('2019-07-13', '十一', null),
('2019-07-14', '十二', null),
('2019-07-15', '十三', null),
('2019-07-16', '十四', null),
('2019-07-17', '十五', null),
('2019-07-18', '十六', null),
('2019-07-19', '十七', null),
('2019-07-20', '十八', null),
('2019-07-21', '十九', null),
('2019-07-22', '二十', null),
('2019-07-23', '廿一', '大暑'),
('2019-07-24', '廿二', null),
('2019-07-25', '廿三', null),
('2019-07-26', '廿四', null),
('2019-07-27', '廿五', null),
('2019-07-28', '廿六', null),
('2019-07-29', '廿七', null),
('2019-07-30', '廿八', null),
('2019-07-31', '廿九', null),
('2019-08-01', '初一', '建军节'),
('2019-08-02', '初二', null),
('2019-08-03', '初三', null),
('2019-08-04', '初四', null),
('2019-08-05', '初五', null),
('2019-08-06', '初六', null),
('2019-08-07', '初七', '七夕'),
('2019-08-08', '初八', '立秋'),
('2019-08-09', '初九', null),
('2019-08-10', '初十', null),
('2019-08-11', '十一', null),
('2019-08-12', '十二', null),
('2019-08-13', '十三', null),
('2019-08-14', '十四', null),
('2019-08-15', '十五', '中元节'),
('2019-08-16', '十六', null),
('2019-08-17', '十七', null),
('2019-08-18', '十八', null),
('2019-08-19', '十九', null),
('2019-08-20', '二十', null),
('2019-08-21', '廿一', null),
('2019-08-22', '廿二', null),
('2019-08-23', '廿三', '处暑'),
('2019-08-24', '廿四', null),
('2019-08-25', '廿五', null),
('2019-08-26', '廿六', null),
('2019-08-27', '廿七', null),
('2019-08-28', '廿八', null),
('2019-08-29', '廿九', null),
('2019-08-30', '初一', null),
('2019-08-31', '初二', null),
('2019-09-01', '初三', null);


DELETE FROM user;
INSERT INTO user (id, name, pwd) VALUES
('20190626174900AAAAAA', 'admin', '123456'),
('20190626174901AAAAAA', 'gxl', 'gxl'),
('20190626174902AAAAAA', 'ggq', 'gxl');

DELETE FROM invite_code;
INSERT INTO invite_code (code, count) VALUES
('QINGLAOYE', 5);


DELETE FROM remind;
INSERT INTO remind (id, userid, createdate, reminddate, type, strategy, desc) VALUES
('20190626174900AAAAA1', '20190626174901AAAAAA', parsedatetime('17-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('18-07-2019 19:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),'1','0','a'),
('20190626174900AAAAA2', '20190626174901AAAAAA', parsedatetime('17-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('1-07-2019 20:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),'1','0','b'),
('20190626174900AAAAA4', '20190626174901AAAAAA', parsedatetime('17-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('1-07-2019 20:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),'1','0','b'),
('20190626174900AAAAA5', '20190626174901AAAAAA', parsedatetime('17-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('2-07-2019 20:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),'1','0','b'),
('20190626174900AAAAA6', '20190626174901AAAAAA', parsedatetime('17-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('1-07-2019 20:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),'1','0','b'),
('20190626174900AAAAA3', '20190626174901AAAAAA', parsedatetime('17-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),parsedatetime('7-07-2019 21:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),'1','0','c');

DELETE FROM remind_job;
INSERT INTO remind_job (id, remindid, date) VALUES
('20190626174900AAAAAb', '20190626174900AAAAA1', parsedatetime('1-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('20190626174900AAAAAc', '20190626174900AAAAA2', parsedatetime('1-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('20190626174900AAAAAd', '20190626174900AAAAA2', parsedatetime('1-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('20190626174900AAAAAe', '20190626174900AAAAA3', parsedatetime('1-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('20190626174900AAAAAr', '20190626174900AAAAA3', parsedatetime('1-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS')),
('20190626174900AAAAAg', '20190626174900AAAAA1', parsedatetime('1-07-2019 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'));