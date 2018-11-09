package com.company.Books;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private List<book> bookList;
    public BookList(){
        bookList = new ArrayList<>();
    }

    public List<book> getBookList() {
        return this.bookList;
    }

    public void addBookList(book b){
        this.bookList.add(b);
    }

    public void cleanList(){
        this.bookList.clear();
    }

}
