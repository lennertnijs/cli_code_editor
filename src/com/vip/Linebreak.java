package com.vip;

public enum Linebreak {

    LF("\n"),
    CR("\r"),
    CRLF("\r\n");
    private final String value;

    Linebreak(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
