package com.example.glossary1;

//Term Not Found Exception class, if the term not exists
public class TermNotFoundException extends Exception {

    public TermNotFoundException(String str)
    {
        super(str);
    }
}
