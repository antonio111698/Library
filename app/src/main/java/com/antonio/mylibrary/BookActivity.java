package com.antonio.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages,txtDescription;
    private Button btnAddToCurrentlyReading, btnAddToWantToReadList,btnAddToAlreadyReadList,btnAddToFavorite;
    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        /*String longDescription = "Imagine yourself on one of the highest mountains in the world. You’re at 26,000 feet above sea level – a place known as the death zone; an elevation at which human beings cannot survive for long. Your brain and body are oxygen-starved."
                +"\n"+  " You have to deal with the physiological effects of extreme altitude along with bone-chilling temperatures, battering winds, and a climbing team that’s counting on all of its members to make smart decisions. There is simply no room for poor judgment."
                +"\n"+ "Why? Because one mistake can result in an “unrecoverable error.” You can figure out what that means. Of course you can’t control the environment; only the way you react to it. Be it a high mountain, a polar landscape, a military battlefield or any situation where lives on are the line or the stakes are exceptionally high—there’s no better training ground for leaders than settings where people are pushed beyond their perceived limits.";

        //TODO: Get the data from recycle view in here
        Book book = new Book(1,"On the Edge","Alison Levine",321,
                "https://m.media-amazon.com/images/I/81yhdZV3zLL.jpg",
                "Leadership lessons from Mount Everest and other extreme environments",
                longDescription);
*/
        Intent intent = getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleCurrentlyReadingBook(incomingBook);
                    handleAddToWantToReadList(incomingBook);
                    handleAddToFavorite(incomingBook);

                }
            }
        }


    }

    private void handleAddToFavorite(final Book book){
        ArrayList<Book> favoriteBooksList = Utils.getInstance(this).getFavoriteBooks();

        boolean existsInFavoriteList = false;

        for(Book b : favoriteBooksList) {
            if (b.getId() == book.getId()) {
                existsInFavoriteList = true;
            }
        }
            if(existsInFavoriteList){
                btnAddToFavorite.setEnabled(false);
            }
            else {
                btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Utils.getInstance(BookActivity.this).addToFavoriteBooks(book)){
                            Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BookActivity.this,FavoriteBooksActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(BookActivity.this, "Something wrong happened.Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        }


    private void handleAddToWantToReadList(final Book book){
        ArrayList<Book> wantToReadList = Utils.getInstance(this).getWantToReadBooks();

        boolean existsInWantToReadList = false;

        for(Book b : wantToReadList){
            if(b.getId() == book.getId()){
                existsInWantToReadList = true;
            }
        }

        if(existsInWantToReadList){
            btnAddToWantToReadList.setEnabled(false);
        }
        else{
            btnAddToWantToReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToWantToReadBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,WantToReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something wrong happened. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    /*
    Enable and Disable Button,
    Add the book to Currently Reading Book ArrayList
     */
    private void handleCurrentlyReadingBook(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for(Book b : currentlyReadingBooks){
            if(b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;

            }
        }

        if(existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }
        else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something wrong happened. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    /*
    Enable and Disable Button,
    Add the book to Already Read Book ArrayList
     */
    private void handleAlreadyRead(final Book book){
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for(Book b : alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;

            }
        }

        if(existInAlreadyReadBooks){
            btnAddToAlreadyReadList.setEnabled(false);
        }
        else{
            btnAddToAlreadyReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something wrong happened. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(imgBook);
    }


    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWantToReadList = findViewById(R.id.btnAddToWantToReadList);
        btnAddToAlreadyReadList = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);

        imgBook = findViewById(R.id.imgBook);
    }
}