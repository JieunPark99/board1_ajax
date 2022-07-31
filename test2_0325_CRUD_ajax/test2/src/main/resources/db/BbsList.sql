select * from TCOM_BBS;

 select a.BBS_SEQ , a.TITLE , a.NAME, a.REG_DT, a.VIEW_COUNT,NOTICE_YN               from               (                            select rownum rnum, s.*                            from                            (                                   SELECT BBS_SEQ, TITLE, NAME, REG_DT, VIEW_COUNT,NOTICE_YN                                   FROM TCOM_BBS                                   WHERE 1=1                                                                                                                                            ORDER BY BBS_SEQ ASC                            ) s               ) a                   where                      rownum <= 20                      and rnum > 10                                  ORDER BY a.NOTICE_YN desc,a.BBS_SEQ asc        

SELECT BBS_SEQ, TITLE, NAME, REG_DT, VIEW_COUNT, NOTICE_YN
	FROM TCOM_BBS
	WHERE NOTICE_YN ='Y'

create table TCOM_ATCH_FILE(
	BBS_SEQ number not null
	,ATCH_SEQ number not null
	,FILE_PATH varchar2(100) not null
	,FILE_NM varchar2(100) not null
	,ORG_FILE_NM varchar2(100)
	,REG_DT date 
);

select * from TCOM_ATCH_FILE


