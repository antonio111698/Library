package com.antonio.mylibrary;

import java.util.ArrayList;

public class Utils {


    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if(null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }

        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }

        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }

        if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }

        if(null == favoriteBooks){
            favoriteBooks = new ArrayList<>();
        }

    }

    private void initData() {
        //TODO: add initial data
        ArrayList<Book> books = new ArrayList<>();
        allBooks.add(new Book(1,"On the Edge","Alison Levine",321,
                "https://m.media-amazon.com/images/I/81yhdZV3zLL.jpg",
                "Leadership lessons from Mount Everest and other extreme environments",
                "long desc"));
        allBooks.add(new Book(2,"Robinson Crusoe","Daniel Defoe",231,
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631057329l/2932._SY475_.jpg",
                "Robinson Crusoe este un roman scris de Daniel Defoe, publicat pentru prima dată în 1719 și este adesea considerat primul roman scris vreodată în limba engleză.",
                "long desc"));
    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }



    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int id){
        for(Book b : allBooks){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }

    public boolean addToCurrentlyReadingBooks(Book book){return currentlyReadingBooks.add(book);}

    public boolean addToWantToReadBooks(Book book){return wantToReadBooks.add(book);}

    public boolean addToFavoriteBooks(Book book){return favoriteBooks.add(book);}

    public boolean removeFromAlreadyRead(Book book){
        return  alreadyReadBooks.remove(book);
    }

    public boolean removeFromWantToRead(Book book){
        return wantToReadBooks.remove(book);
    }
    public boolean removeFromCurrentlyReading(Book book){
        return currentlyReadingBooks.remove(book);
    }
    public boolean removeFromFavorites(Book book){
        return favoriteBooks.remove(book);
    }

}
