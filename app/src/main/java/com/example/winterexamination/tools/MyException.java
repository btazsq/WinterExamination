package com.example.winterexamination.tools;

public class MyException extends Exception{
    private String why;

    public MyException(String why){
        this.why = why;
    }

    @Override
    public String toString(){
        return why;
    }

}
