# DAO data access object

import cx_Oracle as db
import main

my_ip = main.my_ip

db_id = 'play'
db_pw = 'outside'
url = my_ip + '/xe'
# 내(이영웅)pc ip를 '192.168.21.62로 고정 했다. 1521은 오라클 DB의 고정포트 이고 xe는 Express Edition을 뜻한다


def join_db(join_id, join_pw, join_nick, join_profile):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select user_id
        from t_users
        where user_id = '{join_id}'
    """
    curs.execute(sql)
    respond = curs.fetchall()

    if respond:
        curs.close()
        conn.close()
        return '1'  # id중복

    sql = f"""
        select user_nick
        from t_users
        where user_nick = '{join_nick}'
    """
    curs.execute(sql)
    respond = curs.fetchall()

    if respond:
        curs.close()
        conn.close()
        return '2'  # nick중복

    sql = f"""
        insert into t_users
        values(
            '{join_id}',
            '{join_pw}',
            '{join_nick}',
            '{join_profile}',
            (select sysdate from dual),
            'U')
    """  # 'A'는 administrator, 'U'는 user
    curs.execute(sql)
    conn.commit()

    curs.close()
    conn.close()

    return '0'  # 회원가입 성공


def login_db(login_id, login_pw):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select user_id
        from t_users
        where
            user_id = '{login_id}' and
            user_pw = '{login_pw}'
    """
    curs.execute(sql)
    result = curs.fetchall()

    curs.close()
    conn.close()

    if not result:
        return '1'  # 입력한 id, pw에 맞는 사용자가 없다

    return '0'  # 로그인 성공


def search_db(search_id, search_word):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select prog_seq
        from t_programs
        where prog_name
        like '%{search_word}%'
    """
    curs.execute(sql)
    result = curs.fetchall()

    sql = f"""
        insert into t_searchs(
            user_id,
            search_word,
            search_time
            )
        values(
            '{search_id}',
            '{search_word}',
            (select sysdate from dual))
    """
    curs.execute(sql)
    conn.commit()

    curs.close()
    conn.close()

    if result:
        res = ""
        for r in result:
            res += str(r[0]) + ","
        res = res[:-1]  # 마지막 구분자 , 는 뺀다

        return res
    else:
        return "-1"  # 전처럼 오류가 있을 때 1을 보내면 안 된다. 1은 정상적인 결과가 될 수 있기 때문이다.


def top_search_db():
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select *
        from (
            select search_word
            from t_searchs
            where search_time + (interval '24' hour) > (select sysdate from dual)
            group by search_word
            order by count(search_seq) desc)
        where rownum <= 10
    """  # 최근 24시간 안에 검색한 내용 중에서 가장 많이 검색한 10개를 불러오는 sql
    curs.execute(sql)
    result = curs.fetchall()

    curs.close()
    conn.close()

    if result:
        res = ""
        for r in result:
            res += r[0] + ","
        res = res[:-1]  # 마지막 구분자 , 는 뺀다
        return res
    return '-1'


def theme_prog_db(theme):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""select * 
        from t_programs
        where theme_seq = {theme}
    """

    curs.execute(sql)
    result = curs.fetchall()

    curs.close()
    conn.close()

    res = ""
    for i in result:
        for j in i:
            res += str(j)
            res += ";"
        res = res[:-1]
        res += "`"
    res = res[:-1]
    return res


def prog_search_db(prog_seq):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select *
        from t_programs
        where prog_seq = '{prog_seq}'
    """

    curs.execute(sql)
    result = curs.fetchall()

    curs.close()
    conn.close()

    res = ""
    for i in result[0]:
        res += str(i)
        res += ";"

    res = res[:-1]
    return res


def review_search_db(prog_seq):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select *
        from t_reviews_program
        where prog_seq = '{prog_seq}'
    """

    curs.execute(sql)
    result = curs.fetchall()

    curs.close()
    conn.close()

    res = ""
    for i in result:
        for j in i:
            res += str(j)
            res += ";"
        res = res[:-1]
        res += "`"
    res = res[:-1]
    return res


def is_like_db(user_id, prog_seq):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select likes_seq
        from t_likes_program
        where
            user_id = '{user_id}' and
            prog_seq = {prog_seq}
    """

    curs.execute(sql)
    result = curs.fetchall()

    curs.close()
    conn.close()

    if result:
        return True
    else:
        return False


def like_db(user_id, prog_seq):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    liked = is_like_db(user_id, prog_seq)

    if liked:
        sql = f"""
            delete from t_likes_program
            where
                user_id = '{user_id}' and
                prog_seq = {prog_seq}
        """
    else:
        sql = f"""
            insert into t_likes_program (
                user_id,
                prog_seq)
            values (
                '{user_id}', 
                {prog_seq})
        """
    curs.execute(sql)
    conn.commit()

    curs.close()
    conn.close()


def insert_review_db(user_id, prog_seq, plan_seq, content):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        insert into t_reviews_program (
            user_id,
            prog_seq,
            plan_seq,
            review_content,
            review_date)
        values (
            '{user_id}',
            {prog_seq},
            {plan_seq},
            '{content}',
            (select sysdate from dual))
    """

    curs.execute(sql)
    conn.commit()

    curs.close()
    conn.close()


def now_plan_search_db(prog_seq):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select *
        from t_plans
        where
            prog_seq = '{prog_seq}' and
            plan_date > (select sysdate from dual)
    """

    curs.execute(sql)
    result = curs.fetchall()

    curs.close()
    conn.close()

    res = ""
    for i in result:
        for j in i:
            res += str(j)
            res += ';'
        res = res[:-1]
        res += '`'
    res = res[:-1]

    return res

