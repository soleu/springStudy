drop table if exists member CASCADE; //table 생성(이미 있을시엔 작성 X)
create table member
(
    id bigint generated by default as identity, //null값일때 db가 기본으로 id값을 채워줌
    name varchar(255),
    primary key (id)
    );