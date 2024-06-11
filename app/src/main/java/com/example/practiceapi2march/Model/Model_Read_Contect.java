package com.example.practiceapi2march.Model;

public class Model_Read_Contect {
    private String name;
    private String no ;
    private String image;
//    private String call;
//    private String sms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Model_Read_Contect(String name, String no, String image) {
        this.name = name;
        this.no = no;
        this.image = image;
    }


}
