rollback;

select * from t_programs
/

DROP TRIGGER t_programs_AI_TRG
/

DROP SEQUENCE t_programs_SEQ
/

commit;

delete from t_programs
where prog_seq = 1
/

commit;

desc t_programs
/

desc t_plans
/


insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    theme_seq)
values(
    '전라북도 119안전체험관 어린이 안전마을',
    'static/prog_photo/',
    '전라북도 임실군 임실읍 호국로 1630(이도리 474)',
    1000,
    8000,
    5,
    7,
    'http://safe119.sobang.kr/index.sko?menuCd=IA02001003000',
    7)
/
    
select * from t_themes
/


select * from t_users
/

update t_users
set user_profile = 'static/profile/'
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '전라북도 119안전체험관 어린이 안전마을',
    'static/prog_photo/',
    '전라북도 임실군 임실읍 호국로 1630(이도리 474)',
    1000,
    8000,
    5,
    7,
    'http://safe119.sobang.kr/index.sko?menuCd=IA02001003000',
    7)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '담양 에코센터',
    'static/prog_photo/',
    '전라남도 담양군 담양읍 메타세콰이아로 45',
    2000,
    7000,
    0,
    99,
    'http://www.damyang.go.kr/gihoo/index.damyang?menuCd=DOM_000001202001000000',
    7)
/

SET DEFINE OFF -- &를 쓰면 그 뒤에 오는 문자를 변수로 취급한다. 그것을 방지하는 코드다.
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '농업과학관 관람',
    'static/prog_photo/',
    '전라북도 전주시 완산구 농생명로 30',
    0,
    0,
    0,
    99,
    'https://www.rda.go.kr:2360/aehBoard/ati_reservationCenter.do?prgId=ati_reservationCenter&tab=01&mode=&currPage=1&boardNo=',
    1)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '두근두근, 우리동네 숲 탐험',
    'static/prog_photo/',
    '광주광역시 남구 노대실로 9 상가 2층',
    0,
    0,
    8,
    13,
    'http://www.gjarte.or.kr/user/sub509010/detail?seq_no=202208-0008&page=1&area_tx=&target_tx=&genre_kb=',
    7)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '국립광주박물관 어린이박물관',
    'static/prog_photo/',
    '광주광역시 북구 하서로 110(매곡동 430번지)',
    0,
    0,
    0,
    99,
    'https://gwangju.museum.go.kr/prog/experience/child/sub01_03/list.do',
    1)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '국립광주과학관',
    'static/prog_photo/',
    '광주광역시 북구 첨단과기로 235',
    0,
    3000,
    0,
    99,
    'https://www.sciencecenter.or.kr/kor/menu/sub.do?menuId=17_21_22',
    7)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '국립아시아문화전당 어린이체험관',
    'static/prog_photo/',
    '광주광역시 동구 문화전당로 38',
    0,
    5000,
    0,
    99,
    'https://www.acc.go.kr/child/exhibition.do?PID=0204&action=Read&bnkey=EM_0000001800',
    7)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '동물생태해설사와 함께 동물원 한바퀴 신청',
    'static/prog_photo/',
    '광주광역시 서구 내방로 111',
    0,
    0,
    0,
    99,
    'https://www.gwangju.go.kr/uchipark/contentsView.do?pageId=uchipark24',
    7)
/

select * from t_programs where prog_seq = 8;

update t_programs
set theme_seq = 2
where prog_seq = 8
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '상하농원',
    'static/prog_photo/',
    '전라북도 고창군 상하면 상하농원길 11-23',
    0,
    36000,
    0,
    99,
    'https://www.sanghafarm.co.kr/brand/play/experience/view.jsp?seq=21',
    4)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '광주 김치타운',
    'static/prog_photo/',
    '광주광역시 남구 김치로 60',
    0,
    0,
    0,
    99,
    'https://www.gwangju.go.kr/kimchitown/contentsView.do?pageId=kimchitown118',
    4)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '천사히어로즈 복합놀이시설',
    'static/prog_photo/',
    '정읍시 내장산로 412',
    3000,
    9000,
    0,
    99,
    'https://www.jeongeup.go.kr/culture/index.jeongeup?menuCd=DOM_000000604005011000',
    5)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '여수유월드 단품권',
    'static/prog_photo/',
    '전라남도 여수시 소라면 안심산길 155',
    19500,
    29500,
    8,
    99,
    'https://home-ticket.co.kr/uworld/views/productDetail.jsp?hd_id=457',
    5)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '쥬키아이스파크',
    'static/prog_photo/',
    '광주광역시 남구 봉선중앙로 131번길 11 지하1층, 지하2층',
    16800,
    29000,
    0,
    99,
    'https://blog.naver.com/gongryong-world',
    8)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '고창 하전어촌체험마을 갯벌체험',
    'static/prog_photo/',
    '전라북도 고창군 심원면 서전길 30 하전어촌체험마을',
    6000,
    12000,
    0,
    99,
    'http://www.xn--hz2b11tl8a4vq5ocpar87bn3a.kr/expintroduce?jtdMode=viewUser&jtdId=JTD_0000000000000479',
    3)
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '섬진강기차마을',
    'static/prog_photo/',
    '전라남도 곡성군 오곡면 기차마을로 232',
    0,
    5000,
    0,
    99,
    'https://www.railtrip.co.kr/homepage/gokseong/0401.php',
    )
/

insert into t_programs(
    prog_name,
    prog_photo,
    prog_addr,
    prog_l_price,
    prog_h_price,
    prog_l_age,
    prog_h_age,
    prog_homepage,
    theme_seq)
values(
    '광양 목재문화체험장',
    'static/prog_photo/',
    '전라남도 광양시 옥룡면 백계로 337',
    1000,
    43000,
    0,
    99,
    'https://gwangyang.go.kr/bwmt/bmt/contents/wood/reserved.jsp',
    6)
/

commit;

select * from t_programs
/

SET DEFINE ON
/

select prog_seq from t_programs where prog_name like '%국립%'
/

commit;

select * from t_searchs;



select count(search_seq)
from t_searchs
/

select count(search_seq)
from t_searchs
where search_word = '김치'
/

select distinct(search_word)
from t_searchs
/

select search_word, count(search_seq)
from t_searchs
group by search_word
order by count(*) desc
/


select search_word, count(search_seq)
from t_searchs
group by search_word
order by count(search_seq) desc
/

select search_word
from t_searchs
/

select *
from (select search_word
    from t_searchs
    where to_char(search_time + (interval '5' hour),'YYYYMMDDHH24') > (select to_char(sysdate, 'YYYYMMDDHH24') from dual)
    group by search_word
    order by count(search_seq) desc)
where rownum <= 10
/

select *
from (select search_word
    from t_searchs
    where search_time + (interval '5' hour) > (select sysdate from dual)
    group by search_word
    order by count(search_seq) desc)
where rownum <= 10
/


select *
from (select search_word, count(search_seq)
    from t_searchs
    group by search_word
    order by count(search_seq) desc)
where rownum <= 10
/

select search_seq
from t_searchs
where search_time = (
    select max(search_time)
    from t_searchs)
/

select search_word, to_char(search_time, 'HH24:MI:SS')
from t_searchs
/

desc t_programs
/

select * from t_programs;


select to_char(search_time, 'HH:MI:SS') from t_searchs where search_word = '이영웅바보'
/

desc t_plans
/

--insert into t_plans(
--    prog_seq,
--    plan_date,
--    plan_s_date,
--    plan_e_date)
--values (
--    1,
--    to_date('2022-09-04 10:00', 'YYYY-MM-DD HH:MI'),
--    to_date('2022-01-01', 'YYYY-MM-DD'),
--    to_date('2099-12-31', 'YYYY-MM-DD'))
--/

select * from t_plans
/

delete from t_plans where plan_seq = 1
/

commit;

select count(plan_seq) from t_plans
/

select to_char(plan_date, 'HH24:MI')
from t_plans
where plan_seq > 300 and
    plan_seq < 400
/

desc t_reviews_program
/

select count(review_seq) from t_reviews_program
/

select * from t_plans
/

delete from t_reviews_program
where review_seq > 357
/

commit;

rollback;

select *
from t_reviews_program
where rownum < 130
/

desc t_reviews_program
/

select count(review_seq) from t_reviews_program
/


select plan_seq
from t_plans
/

delete from t_plans
where plan_seq > 500
/

delete from t_reviews_program
where plan_seq > 500
/

commit;

select count(*)
from t_reviews_program
/

select * from t_programs
/

update t_programs
set prog_name = '119안전체험관 어린이 안전마을'
where prog_seq = 1
/

commit;


select *
from t_reviews_program
/

select * from t_searchs
/

delete from t_searchs
where search_word = '감자'
/

commit;