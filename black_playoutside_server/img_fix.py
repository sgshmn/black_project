# 사진을 알맞은 크기와 모양으로 바꾸기


from PIL import Image


def profile_fix(user_id):

    fixed_len = 400
    img = Image.open(f'static/profile/{user_id}/original.png')

    width = img.width
    height = img.height

    is_vertical = width < height

    # 짧은 쪽을 fixed_len에 맞추기
    if is_vertical:
        img_resize = img.resize((fixed_len, int(height * fixed_len / width)), Image.LANCZOS)
    else:
        img_resize = img.resize((int(width * fixed_len / height), fixed_len), Image.LANCZOS)

    print("줄인 사진 크기 :", img_resize.size)  # 확인용
    width = img_resize.width
    height = img_resize.height

    if is_vertical:
        # crop(left, up, right, down)
        img_cut = img_resize.crop((0, (height - fixed_len)//2, fixed_len, (height + fixed_len)//2))
    else:
        img_cut = img_resize.crop(((width - fixed_len)//2, 0, (width + fixed_len)//2, fixed_len))

    print("자른 사진 크기 :", img_cut.size)

    img_cut.save(f'static/profile/{user_id}/fix.png')




