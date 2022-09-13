CREATE DATABASE IF NOT EXISTS cnusam;

USE cnusam;

/*OSS_LICENSE_TYPE*/
CREATE TABLE IF NOT EXISTS oss_license_type
(
    license_type_name varchar(20) not null
        primary key
)
    engine = MyISAM;

INSERT IGNORE INTO oss_license_type (license_type_name) VALUES ('Copyleft'),('Permissive'),('Proprietary'),('Proprietary Free'),('Weak Copyleft');

/*PROJECT_CATEGORY*/
create table if not exists project_category
(
    project_category_name varchar(10) not null
        primary key
)
    engine = MyISAM;

INSERT IGNORE INTO PROJECT_CATEGORY (PROJECT_CATEGORY_NAME) VALUES ('개인'),('기타'),('대회'),('수업'),('연구'),('졸업'),('창업');

/*RESTRICTION*/
create table if not exists restriction
(
    restriction_name varchar(50) not null
        primary key
)
    engine = MyISAM;

INSERT IGNORE INTO RESTRICTION (RESTRICTION_NAME) VALUES ('배포시 라이선스사본첨부'),('조합저작물 작성 및 타 라이선스 배포허용'),('저작권 고지사항 또는 Attribution 고지사항 유지'),('수정내용 고지'),('명시적 특허라이선스의 허용'),('이름, 상표, 상호에 대한 사용제한'),('보증의 부인'),('책임의 제한');

/*LECTURE_TYPE*/
create table if not exists lecture_type
(
    lecture_type varchar(10) not null
        primary key
)
    engine = MyISAM;

INSERT IGNORE INTO LECTURE_TYPE (LECTURE_TYPE) VALUES ('교양'),('교양필수'),('교양선택'),('교양일반'),('교양공통기초'),('교양핵심'),('교양전문기초'),('전공'),('전공필수'),('전공선택'),('전공기초'),('전공핵심'),('전공심화'),('교직'),('기초'),('기초필수'),('기초선택'),('기초공통'),('일반선택'),('공통'),('선수'),('미기재');

/*DEPARTMENT*/
create table if not exists department
(
    department varchar(20) not null
        primary key
)
    engine = MyISAM;

INSERT IGNORE INTO DEPARTMENT (DEPARTMENT) VALUES ('학부'),('일반대학원'),('법학전문대학원'),('의학전분대학원'),('분석과학기술대학원'),('에너지과학기술대학원'),('신약전문대학원'),('녹색에너지기술전문대학원'),('경영대학원'),('교육대학원'),('행정대학원'),('보건대학원'),('산업대학원'),('특허법무대학원'),('평화안보대학원'),('보건·바이오산업기술대학원'),('국가정책대학원'),('스마트농업대학원');
