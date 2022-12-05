import cx_Oracle as db
from random import randint, randrange
import time

db_id = 'play'
db_pw = 'outside'
url = '192.168.21.62:1521/xe'


def random_plan_insert_db():

    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    for i in range(randint(1, 500)):
        a = prog_seq = randint(1, 16)

        b1 = plan_date_month = randint(1, 12)
        if b1 in [1, 3, 5, 7, 8, 10, 12]:
            b2 = plan_date_date = randint(1, 31)
        elif b1 in [4, 6, 9, 11]:
            b2 = plan_date_date = randint(1, 30)
        elif b1 == 2:
            b2 = plan_date_date = randint(1, 28)
        b3 = plan_date_hour = randint(9, 16)
        b4 = plan_date_min = randrange(0, 31, 30)

        c1 = plan_s_date_month = randint(1, b1)
        if b1 == c1:
            c2 = plan_s_date_date = randint(1, b2)
        else:
            if c1 in [1, 3, 5, 7, 8, 10, 12]:
                c2 = plan_date_date = randint(1, 31)
            elif c1 in [4, 6, 9, 11]:
                c2 = plan_date_date = randint(1, 30)
            elif c1 == 2:
                c2 = plan_date_date = randint(1, 28)

        d1 = plan_e_date_month = randint(c1, b1)
        if d1 == c1:
            if d1 == b1:
                d2 = plan_e_date_date = randint(c2, b2)
            else:
                if d1 in [1, 3, 5, 7, 8, 10, 12]:
                    d2 = plan_e_date_date = randint(c2, 31)
                elif d1 in [4, 6, 9, 11]:
                    d2 = plan_e_date_date = randint(c2, 30)
                elif d1 == 2:
                    d2 = plan_e_date_date = randint(c2, 28)
        else:
            if d1 == b1:
                d2 = plan_e_date_date = randint(1, b2)
            else:
                if d1 in [1, 3, 5, 7, 8, 10, 12]:
                    d2 = plan_e_date_date = randint(1, 31)
                elif d1 in [4, 6, 9, 11]:
                    d2 = plan_e_date_date = randint(1, 30)
                elif d1 == 2:
                    d2 = plan_e_date_date = randint(1, 28)

        sql = f"""
            insert into t_plans(
                prog_seq,
                plan_date,
                plan_s_date,
                plan_e_date)
            values (
                {a},
                to_date('2022-{b1:0>2}-{b2:0>2} {b3:0>2}:{b4:0>2}', 'YYYY-MM-DD HH24:MI'),
                to_date('2022-{c1:0>2}-{c2:0>2}', 'YYYY-MM-DD'),
                to_date('2022-{d1:0>2}-{d2:0>2}', 'YYYY-MM-DD'))
        """
        curs.execute(sql)

    conn.commit()
    curs.close()
    conn.close()


def random_review_insert_db():
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    user_ids = search_all_user_id_db()
    prog_seqs = search_all_prog_seq_db()

    m = randint(1, 12)
    if m in [1, 3, 5, 7, 8, 10, 12]:
        d = randint(1, 31)
    elif m in [4, 6, 9, 11]:
        d = randint(1, 30)
    elif m == 2:
        d = randint(1, 28)

    for i in range(300):
        user_id = user_ids[randint(0, len(user_ids) - 1)]

        prog_seq = prog_seqs[randint(0, len(prog_seqs) - 1)]

        plan_seqs = search_all_plan_seq_match_prog_seq_db(prog_seq)
        plan_seq = plan_seqs[randint(0, len(plan_seqs) - 1)]

        # 97 ~ 122 알파벳 소문자 아스키코드

        content_len = randint(1, 100)
        content_list = []

        for j in range(content_len):
            content_list.append(randint(97, 122))

        content = ""
        for j in range(content_len):
            content += chr(content_list[j])

        m = randint(1, 12)
        if m in [1, 3, 5, 7, 8, 10, 12]:
            d = randint(1, 31)
        elif m in [4, 6, 9, 11]:
            d = randint(1, 30)
        elif m == 2:
            d = randint(1, 28)

        sql = f"""
            insert into t_reviews_program(
                user_id,
                prog_seq,
                plan_seq,
                review_content,
                review_date)
            values(
                '{user_id}',
                {prog_seq},
                {plan_seq},
                '{content}',
                to_date('2022-{m:0>2}-{d:0>2}', 'YYYY-MM-DD'))
        """
        curs.execute(sql)
        conn.commit()

    curs.close()
    conn.close()

    return '0'


def search_all_user_id_db():
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
            select user_id
            from t_users
    """
    curs.execute(sql)
    result = curs.fetchall()
    curs.close()
    conn.close()

    res = []

    for i in result:
        res.append(i[0])

    return res


def search_all_prog_seq_db():
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select prog_seq
        from t_programs
    """
    curs.execute(sql)
    result = curs.fetchall()
    curs.close()
    conn.close()

    res = []

    for i in result:
        res.append(i[0])

    return res


def search_all_plan_seq_match_prog_seq_db(prog_seq):
    conn = db.connect(db_id, db_pw, url)
    curs = conn.cursor()

    sql = f"""
        select plan_seq
        from t_plans
        where prog_seq = '{prog_seq}'
    """
    curs.execute(sql)
    result = curs.fetchall()
    curs.close()
    conn.close()

    res = []

    for i in result:
        res.append(i[0])

    return res

