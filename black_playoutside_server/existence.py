# 사진을 저장하기 전 디렉토리 생성 여부 알아보기
# 없으면 디렉토리 만들기
# y : 디렉토리 있다
# n : 디렉토리 없어서 만들고 초기 데이터 넣어줬다

import os.path
from PIL import Image
import img_fix


def profile(user_id):
    f_name = os.path.join('static/profile', user_id)

    if os.path.exists(f_name):
        return "y"
    else:
        os.mkdir(f_name)
        image = Image.open('static/profile/default.png')
        image.save(f'static/profile/{user_id}/original.png')  # 회원가입을 하면 디폴트 이미지로 프로필 사진을 바꿔준다
        img_fix.profile_fix(user_id)

        return "n"
