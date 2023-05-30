package com.example.glossary1;

import java.io.Serializable;
import java.util.TreeMap;

//the logical class for the glossary app
// implements Serializable, so we will be able to save and load files
public class Glossary implements Serializable {

    private TreeMap<String, String> glossary;

    //Constructor for the logical class
    public Glossary() {
        glossary = new TreeMap<String, String>();
    }

    public TreeMap<String, String> getGlossary() {
        return glossary;
    }

    public void setGlossary(Glossary glossary) {
        this.glossary = glossary.getGlossary();
    }

    //Add method, At first checks if the term are already exists in our glossary
    public void add(String term, String meaning) throws DuplicateTermException,EmptyTextException {
        if(term.equals("") || meaning.equals(""))
            throw new EmptyTextException("Empty text on one of the fields");
        else if (glossary.containsKey(term))
            throw new DuplicateTermException("The term " + term +
                    " are already exists in our glossary");
        else    //Add thew new term
            glossary.put(term, meaning);
    }

    //Update method, At first checks if the term are NOT exists in our glossary
    public void update(String term, String meaning) throws TermNotFoundException,EmptyTextException {
        if(term.equals("") || meaning.equals(""))
            throw new EmptyTextException("Empty text on one of the fields");
        else if (!glossary.containsKey(term))
            throw new TermNotFoundException("The term " + term +
                    " are not exist in our glossary");
        else    //Update the term
            glossary.put(term, meaning);
    }

    //Remove method,  At first checks if the term are NOT exists in our glossary
    public void remove(String term) throws TermNotFoundException,EmptyTextException {
        if(term.equals(""))
            throw new EmptyTextException("Empty text on one of the fields");
        else if (!glossary.containsKey(term))
            throw new TermNotFoundException("The term " + term +
                    "are not exist in our glossary");
        else    //Remove the term
            glossary.remove(term);
    }

    //Search method,searches the wanted term and present it if exists
    public TreeMap<String, String> search(String term) {
        if (!glossary.containsKey(term)) {
            return new TreeMap<String, String>();
        }
        return new TreeMap<String, String>() {
            {
                put(term, glossary.get(term));
            }
        };
    }


}
