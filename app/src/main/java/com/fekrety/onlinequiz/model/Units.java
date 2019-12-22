package com.fekrety.onlinequiz.model;

public class Units {
   private String unit_desc,book_Url,file_name;

    public String getUnit_desc() {
        return unit_desc;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setUnit_desc(String unit_desc) {
        this.unit_desc = unit_desc;
    }

    public String getBook_Url() {
        return book_Url;
    }

    public void setBook_Url(String book_Url) {
        this.book_Url = book_Url;
    }
}
