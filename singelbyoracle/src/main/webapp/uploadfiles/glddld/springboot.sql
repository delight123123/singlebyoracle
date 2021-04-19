-- �ڷ��� �亯 �Խ���
ALTER TABLE tbl_reboard
	DROP CONSTRAINT FK_tbl_user_TO_tbl_reboard; -- ���� -> �ڷ��� �亯 �Խ���

-- ���ε����ϸ�Ϸ�
ALTER TABLE upfile_list
	DROP CONSTRAINT FK_tbl_reboard_TO_upfile_list; -- �ڷ��� �亯 �Խ��� -> ���ε����ϸ�Ϸ�

-- ����
ALTER TABLE tbl_user
	DROP CONSTRAINT PK_tbl_user; -- ���� �⺻Ű

-- �ڷ��� �亯 �Խ���
ALTER TABLE tbl_reboard
	DROP CONSTRAINT PK_tbl_reboard; -- �ڷ��� �亯 �Խ��� �⺻Ű

-- ���ε����ϸ�Ϸ�
ALTER TABLE upfile_list
	DROP CONSTRAINT PK_upfile_list; -- ���ε����ϸ�Ϸ� �⺻Ű

-- ����
DROP TABLE tbl_user;

-- �ڷ��� �亯 �Խ���
DROP TABLE tbl_reboard;

-- ���ε����ϸ�Ϸ�
DROP TABLE upfile_list;

-- ����
CREATE TABLE tbl_user (
	userid    VARCHAR2(50)       NOT NULL, -- ���̵�
	userpw    VARCHAR2(500byte)  NOT NULL, -- ��й�ȣ
	email1    VARCHAR2(900)      NULL,     -- �̸���1
	email2    VARCHAR2(900)      NULL,     -- �̸���2
	salt      VARCHAR2(100 char) NOT NULL, -- ��ȣȭ
	writeauth VARCHAR2(3)        DEFAULT 'N', -- �������
	readauth  VARCHAR2(3)        DEFAULT 'Y', -- �б����
	adminauth VARCHAR2(3)        DEFAULT 'N', -- �����ڱ���
	path      VARCHAR2(2000)     NULL      -- ���� ����
);

-- ����
ALTER TABLE tbl_user
	ADD
		CONSTRAINT PK_tbl_user -- ���� �⺻Ű
		PRIMARY KEY (
			userid -- id
		);

-- �ڷ��� �亯 �Խ���
CREATE TABLE tbl_reboard (
	reboard_no       NUMBER        NOT NULL, -- �۹�ȣ
	reboard_title    VARCHAR2(300) NULL,     -- ������
	reboard_content  CLOB          NULL,     -- �۳���
	reboard_reg      DATE          DEFAULT sysdate, -- �ۼ���
	readcount        NUMBER        DEFAULT 0, -- ��ȸ��
	groupno          NUMBER        DEFAULT 0, -- �׷��ȣ
	step             NUMBER        DEFAULT 0, -- ���� �ܰ�
	sortno           NUMBER        DEFAULT 0, -- ���� ���ļ���
	delflag          CHAR          DEFAULT 'N', -- ����
	userid               VARCHAR2(50)  NULL      -- id
);

-- �ڷ��� �亯 �Խ���
ALTER TABLE tbl_reboard
	ADD
		CONSTRAINT PK_tbl_reboard -- �ڷ��� �亯 �Խ��� �⺻Ű
		PRIMARY KEY (
			reboard_no -- �۹�ȣ
		);

-- ���ε����ϸ�Ϸ�
CREATE TABLE upfile_list (
	fileno           NUMBER        NOT NULL, -- ���Ϲ�ȣ
	filename         VARCHAR2(150) NULL,     -- ���ε����ϸ�
	filesize         NUMBER        NULL,     -- ���ϻ�����
	downcount        NUMBER        DEFAULT 0,     -- �ٿ��
	originalfilename VARCHAR2(150) NULL,     -- �̸������� ���ε����ϸ�
	reboard_no       NUMBER        NULL      -- �۹�ȣ
);

-- ���ε����ϸ�Ϸ�
ALTER TABLE upfile_list
	ADD
		CONSTRAINT PK_upfile_list -- ���ε����ϸ�Ϸ� �⺻Ű
		PRIMARY KEY (
			fileno -- ���Ϲ�ȣ
		);

-- �ڷ��� �亯 �Խ���
ALTER TABLE tbl_reboard
	ADD
		CONSTRAINT FK_tbl_user_TO_tbl_reboard -- ���� -> �ڷ��� �亯 �Խ���
		FOREIGN KEY (
			userid -- id
		)
		REFERENCES tbl_user ( -- ����
			userid -- id
		);

-- ���ε����ϸ�Ϸ�
ALTER TABLE upfile_list
	ADD
		CONSTRAINT FK_tbl_reboard_TO_upfile_list -- �ڷ��� �亯 �Խ��� -> ���ε����ϸ�Ϸ�
		FOREIGN KEY (
			reboard_no -- �۹�ȣ
		)
		REFERENCES tbl_reboard ( -- �ڷ��� �亯 �Խ���
			reboard_no -- �۹�ȣ
		);
		
		
-- �������̺�
ALTER TABLE tbl_payment
	DROP CONSTRAINT FK_tbl_user_TO_tbl_payment; -- ���� -> �������̺�

-- ȯ�����̺�
ALTER TABLE tbl_refund
	DROP CONSTRAINT FK_tbl_payment_TO_tbl_refund; -- �������̺� -> ȯ�����̺�		

-- �������̺�
ALTER TABLE tbl_payment
	DROP CONSTRAINT PK_tbl_payment; -- �������̺� �⺻Ű

-- ȯ�����̺�
ALTER TABLE tbl_refund
	DROP CONSTRAINT PK_tbl_refund; -- ȯ�����̺� �⺻Ű

-- �������̺�
DROP TABLE tbl_payment;

-- ȯ�����̺�
DROP TABLE tbl_refund;

-- �������̺�
CREATE TABLE tbl_payment (
	payment_no  NUMBER        NOT NULL, -- ������ȣ
	ordername   CLOB          NOT NULL, -- �ֹ���
	imp_uid     VARCHAR2(100) NOT NULL, -- ����Ʈ���̵�
	price       NUMBER        NOT NULL, -- �ݾ�
	payment_reg DATE          DEFAULT sysdate, -- ������
	id          VARCHAR2(50)  NOT NULL  -- id
);

-- �������̺�
ALTER TABLE tbl_payment
	ADD
		CONSTRAINT PK_tbl_payment -- �������̺� �⺻Ű
		PRIMARY KEY (
			payment_no -- ������ȣ
		);

-- ȯ�����̺�
CREATE TABLE tbl_refund (
	refund_no      NUMBER      NOT NULL, -- ȯ�ҹ�ȣ
	refund_type    VARCHAR2(2) NOT NULL, -- ȯ������
	reporting_date DATE        DEFAULT sysdate, -- ȯ�ҽ�û��
	refund_state   VARCHAR2(2) DEFAULT 'N', -- ȯ�һ���
	refund_date    DATE        NOT NULL, -- ȯ�ҿϷ���
	payment_no     NUMBER      NOT NULL  -- ������ȣ
);

-- ȯ�����̺�
ALTER TABLE tbl_refund
	ADD
		CONSTRAINT PK_tbl_refund -- ȯ�����̺� �⺻Ű
		PRIMARY KEY (
			refund_no -- ȯ�ҹ�ȣ
		);

-- �������̺�
ALTER TABLE tbl_payment
	ADD
		CONSTRAINT FK_tbl_user_TO_tbl_payment -- ���� -> �������̺�
		FOREIGN KEY (
			id -- id
		)
		REFERENCES tbl_user ( -- ����
			id -- id
		);

-- ȯ�����̺�
ALTER TABLE tbl_refund
	ADD
		CONSTRAINT FK_tbl_payment_TO_tbl_refund -- �������̺� -> ȯ�����̺�
		FOREIGN KEY (
			payment_no -- ������ȣ
		)
		REFERENCES tbl_payment ( -- �������̺�
			payment_no -- ������ȣ
		);

-- �������̺� ������
drop sequence payment_seq;
create sequence payment_seq
start with 1
increment by 1
nocache;

-- ȯ�����̺� ������
drop sequence refund_seq;
create sequence refund_seq
start with 1
increment by 1
nocache;


-- �Խ��� ������
drop sequence reboard_seq;
create sequence reboard_seq
start with 1
increment by 1
nocache;

-- ���ε����ϸ���Ʈ ������
drop sequence file_seq;
create sequence file_seq
start with 1
increment by 1
nocache;

/*
exec delete_reboard(13,13,0);
exec delete_reboard(1,1,1);
*/


create or replace procedure delete_reboard --���ν��� �̸� 
(
--�Ű�����
    p_no  number,
    p_groupno number,
    p_step    number
)
is
--���������
    cnt number;
begin
--ó���� ����
    --�亯�ִ� �������� ��� delflag�� Y�� update, �������� delete
    if p_step=0 then --������
        --�亯�� �ִ��� üũ
        select count(*) into cnt
        from tbl_reboard
        where groupno=p_groupno;
        
        if cnt>1 then  --�亯�� �ִ� ���
            update tbl_reboard
            set delflag='Y'
            where reboard_no=p_no;
        else    --�亯�� ���� ���
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
	raise_application_error(-20001, '�� ���� ����!');
        ROLLBACK;
end;
