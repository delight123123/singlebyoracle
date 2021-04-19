-- 자료형 답변 게시판
ALTER TABLE tbl_reboard
	DROP CONSTRAINT FK_tbl_user_TO_tbl_reboard; -- 유저 -> 자료형 답변 게시판

-- 업로드파일목록록
ALTER TABLE upfile_list
	DROP CONSTRAINT FK_tbl_reboard_TO_upfile_list; -- 자료형 답변 게시판 -> 업로드파일목록록

-- 유저
ALTER TABLE tbl_user
	DROP CONSTRAINT PK_tbl_user; -- 유저 기본키

-- 자료형 답변 게시판
ALTER TABLE tbl_reboard
	DROP CONSTRAINT PK_tbl_reboard; -- 자료형 답변 게시판 기본키

-- 업로드파일목록록
ALTER TABLE upfile_list
	DROP CONSTRAINT PK_upfile_list; -- 업로드파일목록록 기본키

-- 유저
DROP TABLE tbl_user;

-- 자료형 답변 게시판
DROP TABLE tbl_reboard;

-- 업로드파일목록록
DROP TABLE upfile_list;

-- 유저
CREATE TABLE tbl_user (
	userid    VARCHAR2(50)       NOT NULL, -- 아이디
	userpw    VARCHAR2(500byte)  NOT NULL, -- 비밀번호
	email1    VARCHAR2(900)      NULL,     -- 이메일1
	email2    VARCHAR2(900)      NULL,     -- 이메일2
	salt      VARCHAR2(100 char) NOT NULL, -- 암호화
	writeauth VARCHAR2(3)        DEFAULT 'N', -- 쓰기권한
	readauth  VARCHAR2(3)        DEFAULT 'Y', -- 읽기권한
	adminauth VARCHAR2(3)        DEFAULT 'N', -- 관리자권한
	path      VARCHAR2(2000)     NULL      -- 공유 폴더
);

-- 유저
ALTER TABLE tbl_user
	ADD
		CONSTRAINT PK_tbl_user -- 유저 기본키
		PRIMARY KEY (
			userid -- id
		);

-- 자료형 답변 게시판
CREATE TABLE tbl_reboard (
	reboard_no       NUMBER        NOT NULL, -- 글번호
	reboard_title    VARCHAR2(300) NULL,     -- 글제목
	reboard_content  CLOB          NULL,     -- 글내용
	reboard_reg      DATE          DEFAULT sysdate, -- 작성일
	readcount        NUMBER        DEFAULT 0, -- 조회수
	groupno          NUMBER        DEFAULT 0, -- 그룹번호
	step             NUMBER        DEFAULT 0, -- 글의 단계
	sortno           NUMBER        DEFAULT 0, -- 글의 정렬순서
	delflag          CHAR          DEFAULT 'N', -- 삭제
	userid               VARCHAR2(50)  NULL      -- id
);

-- 자료형 답변 게시판
ALTER TABLE tbl_reboard
	ADD
		CONSTRAINT PK_tbl_reboard -- 자료형 답변 게시판 기본키
		PRIMARY KEY (
			reboard_no -- 글번호
		);

-- 업로드파일목록록
CREATE TABLE upfile_list (
	fileno           NUMBER        NOT NULL, -- 파일번호
	filename         VARCHAR2(150) NULL,     -- 업로드파일명
	filesize         NUMBER        NULL,     -- 파일사이즈
	downcount        NUMBER        DEFAULT 0,     -- 다운수
	originalfilename VARCHAR2(150) NULL,     -- 이름변경전 업로드파일명
	reboard_no       NUMBER        NULL      -- 글번호
);

-- 업로드파일목록록
ALTER TABLE upfile_list
	ADD
		CONSTRAINT PK_upfile_list -- 업로드파일목록록 기본키
		PRIMARY KEY (
			fileno -- 파일번호
		);

-- 자료형 답변 게시판
ALTER TABLE tbl_reboard
	ADD
		CONSTRAINT FK_tbl_user_TO_tbl_reboard -- 유저 -> 자료형 답변 게시판
		FOREIGN KEY (
			userid -- id
		)
		REFERENCES tbl_user ( -- 유저
			userid -- id
		);

-- 업로드파일목록록
ALTER TABLE upfile_list
	ADD
		CONSTRAINT FK_tbl_reboard_TO_upfile_list -- 자료형 답변 게시판 -> 업로드파일목록록
		FOREIGN KEY (
			reboard_no -- 글번호
		)
		REFERENCES tbl_reboard ( -- 자료형 답변 게시판
			reboard_no -- 글번호
		);
		
		
-- 결제테이블
ALTER TABLE tbl_payment
	DROP CONSTRAINT FK_tbl_user_TO_tbl_payment; -- 유저 -> 결제테이블

-- 환불테이블
ALTER TABLE tbl_refund
	DROP CONSTRAINT FK_tbl_payment_TO_tbl_refund; -- 결제테이블 -> 환불테이블		

-- 결제테이블
ALTER TABLE tbl_payment
	DROP CONSTRAINT PK_tbl_payment; -- 결제테이블 기본키

-- 환불테이블
ALTER TABLE tbl_refund
	DROP CONSTRAINT PK_tbl_refund; -- 환불테이블 기본키

-- 결제테이블
DROP TABLE tbl_payment;

-- 환불테이블
DROP TABLE tbl_refund;

-- 결제테이블
CREATE TABLE tbl_payment (
	payment_no  NUMBER        NOT NULL, -- 결제번호
	ordername   CLOB          NOT NULL, -- 주문명
	imp_uid     VARCHAR2(100) NOT NULL, -- 임포트아이디
	price       NUMBER        NOT NULL, -- 금액
	payment_reg DATE          DEFAULT sysdate, -- 결제일
	id          VARCHAR2(50)  NOT NULL  -- id
);

-- 결제테이블
ALTER TABLE tbl_payment
	ADD
		CONSTRAINT PK_tbl_payment -- 결제테이블 기본키
		PRIMARY KEY (
			payment_no -- 결제번호
		);

-- 환불테이블
CREATE TABLE tbl_refund (
	refund_no      NUMBER      NOT NULL, -- 환불번호
	refund_type    VARCHAR2(2) NOT NULL, -- 환불종류
	reporting_date DATE        DEFAULT sysdate, -- 환불신청일
	refund_state   VARCHAR2(2) DEFAULT 'N', -- 환불상태
	refund_date    DATE        NOT NULL, -- 환불완료일
	payment_no     NUMBER      NOT NULL  -- 결제번호
);

-- 환불테이블
ALTER TABLE tbl_refund
	ADD
		CONSTRAINT PK_tbl_refund -- 환불테이블 기본키
		PRIMARY KEY (
			refund_no -- 환불번호
		);

-- 결제테이블
ALTER TABLE tbl_payment
	ADD
		CONSTRAINT FK_tbl_user_TO_tbl_payment -- 유저 -> 결제테이블
		FOREIGN KEY (
			id -- id
		)
		REFERENCES tbl_user ( -- 유저
			id -- id
		);

-- 환불테이블
ALTER TABLE tbl_refund
	ADD
		CONSTRAINT FK_tbl_payment_TO_tbl_refund -- 결제테이블 -> 환불테이블
		FOREIGN KEY (
			payment_no -- 결제번호
		)
		REFERENCES tbl_payment ( -- 결제테이블
			payment_no -- 결제번호
		);

-- 결제테이블 시퀀스
drop sequence payment_seq;
create sequence payment_seq
start with 1
increment by 1
nocache;

-- 환불테이블 시퀀스
drop sequence refund_seq;
create sequence refund_seq
start with 1
increment by 1
nocache;


-- 게시판 시퀀스
drop sequence reboard_seq;
create sequence reboard_seq
start with 1
increment by 1
nocache;

-- 업로드파일리스트 시퀀스
drop sequence file_seq;
create sequence file_seq
start with 1
increment by 1
nocache;

/*
exec delete_reboard(13,13,0);
exec delete_reboard(1,1,1);
*/


create or replace procedure delete_reboard --프로시저 이름 
(
--매개변수
    p_no  number,
    p_groupno number,
    p_step    number
)
is
--변수선언부
    cnt number;
begin
--처리할 내용
    --답변있는 원본글인 경우 delflag를 Y로 update, 나머지는 delete
    if p_step=0 then --원본글
        --답변이 있는지 체크
        select count(*) into cnt
        from tbl_reboard
        where groupno=p_groupno;
        
        if cnt>1 then  --답변이 있는 경우
            update tbl_reboard
            set delflag='Y'
            where reboard_no=p_no;
        else    --답변이 없는 경우
            delete from tbl_reboard
            where reboard_no=p_no;
        end if;
        
    else --
        delete from tbl_reboard
        where reboard_no=p_no;
    end if;
    
    commit;

EXCEPTION
    WHEN OTHERS THEN
	raise_application_error(-20001, '글 삭제 실패!');
        ROLLBACK;
end;
