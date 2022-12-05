package com.example.naga0818;

import java.util.List;


// 상위 리사이클러뷰 아이템클래스를 정의
public class PostscriptContent {
    private String pName;
    private String pContent;
    // 하위 리사이클러뷰 아이템으로 정의한 multiImage2List를 전역변수로 선언한다.
    private List<MultiImage2> multiImage2List;

    public PostscriptContent(String pName, String pContent, List<MultiImage2> multiImage2List) {
        this.pName = pName;
        this.pContent = pContent;
        this.multiImage2List = multiImage2List;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpContent() {
        return pContent;
    }

    public void setpContent(String pContent) {
        this.pContent = pContent;
    }

    public List<MultiImage2> getMultiImage2List() {
        return multiImage2List;
    }

    public void setMultiImage2List(List<MultiImage2> multiImage2List) {
        this.multiImage2List = multiImage2List;
    }
}
