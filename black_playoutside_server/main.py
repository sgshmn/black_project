# server main

from flask import Flask, request

import img_fix
import sqlq
import existence as ex
import dummy

app = Flask(__name__)  # Flask("__main__") ?? 전에 쓴거 갖다 쓴거라 왜 넣은 지는 잘 모른다.
#
my_ip = "192.168.43.120"


@app.route('/')
def show():
    return "왔냐?"


@app.route('/join', methods=['POST'])  # 회원가입 # 나중에 get은 지워야 한다.
def join():
    print('회원 가입 요청')

    join_id = request.form['id']
    join_pw = request.form['pw']
    join_nick = request.form['nick']
    join_profile = '/'  # 이제 필요 없다

    ex.profile(join_id)

    return sqlq.join_db(join_id, join_pw, join_nick, join_profile)


@app.route('/login', methods=['POST'])  # 로그인 # 나중에 get은 지워야 한다.
def login():
    print('로그인 요청')

    login_id = request.form['id']
    login_pw = request.form['pw']

    # login_id = 'test2'
    # login_pw = '2222'  # 실험용 데이터

    # if not (login_id and login_pw):
    #     return '1'  # id, pw를 다 써야 한다. id, pw를 모두 써 주세요
    # 이제 안드로이드에서 한다
    return sqlq.login_db(login_id, login_pw)


@app.route('/profileUpload', methods=['POST'])
def profile_upload():
    # print("제발")
    if request.method == 'POST':
        # print("살려줘")
        upload = request.files.getlist('image')
        temp = request.files.getlist('id')
        user_id = str(temp)[16:-10]  # [<FileStorage: 'id' (None)>] 이거를 문자열로 바꿔서 id를 찾았다
        # 16 ~ -10 인덱스가 id다
        print(user_id)
        if upload:  # 있긴 있음
            # print("한번만")
            print(type(upload))  # list
            print(type(upload[0]))  # werkzeug.datastructures.FileStorage

            upload[0].save(f'static/profile/{user_id}/original.png')
            img_fix.profile_fix(user_id)
            # print("소원이야")
    return '0'


# @app.route('/imageFixTest', methods=['GET'])
# def image_fix_test():
#     print('사진 고치기')
#
#     img_fix.profile_fix("test")
#
#     return'0'
# 위에는 사진 고치기 테스트 코드였음

# @app.route('/imgTest')
# def img_test():
#     print("img_test")
#     test = request.files.getlist('image')
#     print(type(test))
#     return '0'  # 언젠가 쓰겠지


@app.route('/search', methods=['POST'])
def search():
    search_id = request.form['id']
    search_word = request.form['search_word']

    # if not search_word:
    #     return "-2"  # search_word에 아무것도 오지 않음 안드로이드에서 해결함

    return sqlq.search_db(search_id, search_word)


@app.route('/topSearch', methods=['POST'])
def top_search():
    return sqlq.top_search_db()


@app.route('/progSieve', methods=['POST'])  #
def prog_sieve():
    theme = request.form['theme']
    # theme = '7'  # 테스트 데이터
    return sqlq.theme_prog_db(theme)


@app.route('/progSearch', methods=['POST'])
def prog_search():
    length = int(request.form['length'])
    prog_seqs = []

    # print(type(length))
    # print(length)

    # prog_seq0 = request.form['prog_seq0']
    # prog_seq1 = request.form['prog_seq1']
    #
    # print(prog_seq0)
    # print(prog_seq1)

    for i in range(length):
        prog_seqs.append(request.form[f'prog_seq{i}'])

    # for i in prog_seqs:
    #     print(i)

    # print(type(prog_seqs))
    # print(prog_seqs)

    result = ""
    for i in prog_seqs:
        result += sqlq.prog_search_db(i)
        result += "`"

    result = result[:-1]
    return result


@app.route('/randomPlanInsert')
def random_plan_insert():
    dummy.random_plan_insert_db()
    return '0'


@app.route('/randomReviewInsert')
def random_review_insert():
    dummy.random_review_insert_db()
    return '0'


@app.route('/reviewSearch', methods=['POST'])
def review_search():
    prog_seq = request.form['prog_seq']

    return sqlq.review_search_db(prog_seq)


@app.route('/like', methods=['POST'])
def like():
    user_id = request.form['user_id']
    prog_seq = request.form['prog_seq']

    sqlq.like_db(user_id, prog_seq)
    return '0'  # 암거나 넣음


@app.route('/isLike', methods=['POST'])
def is_like():
    user_id = request.form['user_id']
    prog_seq = request.form['prog_seq']

    if sqlq.is_like_db(user_id, prog_seq):
        return '0'  # 있다
    else:
        return '-1'  # 없다


@app.route('/nowPlanSearch', methods=['POST'])
def now_plan_search():
    prog_seq = request.form['prog_seq']

    return sqlq.now_plan_search_db(prog_seq)


@app.route('/insertReview', methods=['POST'])
def insert_review():
    user_id = request.form['user_id']
    prog_seq = request.form['prog_seq']
    plan_seq = request.form['plan_seq']
    content = request.form['content']

    # print(user_id)
    # print(prog_seq)
    # print(plan_seq)
    # print(content)

    sqlq.insert_review_db(user_id, prog_seq, plan_seq, content)
    return '0'


if __name__ == '__main__':
    app.run(host=my_ip, port=5000)
