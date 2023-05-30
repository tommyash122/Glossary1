package com.example.glossary1;

//Duplicate Exception class , if we have duplicate term in our glossary
public class DuplicateTermException extends Exception {

    public DuplicateTermException(String str) {
        super(str);
    }
}

