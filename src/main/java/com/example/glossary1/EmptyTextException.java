package com.example.glossary1;

//Empty Text Exception class, if the user haven't entered a text as required
public class EmptyTextException extends Exception{
    public EmptyTextException(String str)
    {
        super(str);
    }
}
