package com.antonio.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY= "allBooks";
    private static final String ALREADY_READ_BOOKS= "already_read_books";
    private static final String WANT_TO_READ_BOOKS= "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS= "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

   /* private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;*/

     private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);

        if(null == getAllBooks()){
            initData();
        }


        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(null == getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getWantToReadBooks()){
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getFavoriteBooks()){
            editor.putString(FAVORITE_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,"On the Edge","Alison Levine",321,
                "https://m.media-amazon.com/images/I/81yhdZV3zLL.jpg",
                "Leadership lessons from Mount Everest and other extreme environments",
                "Extreme athlete Alison Levine describes the leadership skills that helped her reach the summit of Mt. Everest and explains how the same skills can help you navigate today’s tumultuous, fast-changing business environment. " +
                        "She shows why leadership fundamentals like teamwork, flexibility and agility are essential whether you’re contending with a blizzard in the Himalayas or handling a disruption in the technology market. This is an unusual business manual." +
                        " Instead of flow charts, Levine offers tableaux of subzero temperatures and perilous precipices in exotic locales like Kathmandu, Indonesia and Antarctica. You’ve heard her leadership concepts before: Learn from your failures; guard against complacency;" +
                        " break out of your comfort zone. But, these ideas take on new vibrancy against the backdrop of the world’s highest peaks. Levine’s friendly, chatty voice sparks her vivid storytelling, which is peppered with earthy wisecracks." +
                        " getAbstract recommends her business manual-slash-travelogue to adventurous managers and entrepreneurs who want to push their boundaries."));
        books.add(new Book(2,"Robinson Crusoe","Daniel Defoe",231,
                "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631057329l/2932._SY475_.jpg",
                "The Life and Strange Surprizing Adventures of Robinson Crusoe, of York, Mariner: Who lived Eight and Twenty Years, all alone in an un-inhabited Island on the Coast of America, near the Mouth of the Great River of Oroonoque;" +
                        " Having been cast on Shore by Shipwreck, wherein all the Men perished but himself." +
                        " With An Account how he was at last as strangely deliver'd by Pyrates. Written by Himself.",
                "Robinson Crusoe is an Englishman from the town of York in the seventeenth century, the youngest son of a merchant of German origin." +
                        " Encouraged by his father to study law, Crusoe expresses his wish to go to sea instead. His family is against Crusoe going out to sea, and his father " +
                        "explains that it is better to seek a modest, secure life for oneself. Initially, Robinson is committed to obeying his father, but he eventually succumbs to" +
                        " temptation and embarks on a ship bound for London with a friend. When a storm causes the near deaths of Crusoe and his friend, the friend is dissuaded from " +
                        "sea travel, but Crusoe still goes on to set himself up as merchant on a ship leaving London. This trip is financially successful, and Crusoe plans another, leaving" +
                        " his early profits in the care of a friendly widow. The second voyage does not prove as fortunate: the ship is seized by Moorish pirates, and Crusoe is enslaved to" +
                        " a potentate in the North African town of Sallee. While on a fishing expedition, he and a slave boy break free and sail down the African coast." +
                        " A kindly Portuguese captain picks them up, buys the slave boy from Crusoe, and takes Crusoe to Brazil. In Brazil, Crusoe establishes himself as a plantation owner and soon becomes successful." +
                        " Eager for slave labor and its economic advantages, he embarks on a slave-gathering expedition to West Africa but ends up shipwrecked off of the coast of Trinidad."));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();
    }

    public static Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }
    }



    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type= new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type= new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type= new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type= new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type= new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS,null),type);
        return books;
    }

    public Book getBookById(int id){
         ArrayList<Book> books = getAllBooks();
         if(books != null){
             for(Book b : books){
                 if(b.getId() == id){
                     return b;
                 }
             }
         }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadingBooks(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
     }

    public boolean addToWantToReadBooks(Book book){
         ArrayList<Book> books = getWantToReadBooks();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
     }

    public boolean addToFavoriteBooks(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
     }

    public boolean removeFromAlreadyRead(Book book){
         ArrayList<Book> books = getAlreadyReadBooks();
        if(null != books){
            for(Book b : books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if(null != books){
            for(Book b : books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }

            }
        }
        return false;
    }
    public boolean removeFromCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null != books){
            for(Book b : books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }

            }
        }
        return false;
    }
    public boolean removeFromFavorites(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if(null != books){
            for(Book b : books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }

            }
        }
        return false;
    }

}
