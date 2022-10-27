package com.antonio.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BookRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecView);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,"On the Edge","Alison Levine",321,
                "https://m.media-amazon.com/images/I/81yhdZV3zLL.jpg",
                "Leadership lessons from Mount Everest and other extreme environments",
                "long desc"));
        books.add(new Book(2,"On the Edge","Alison Levine",331,
                "https://m.media-amazon.com/images/I/81yhdZV3zLL.jpg",
                "Leadership lessons from Mount Everest and other extreme environments",
                "long desc"));
        books.add(new Book(2,"On the Edge","Alison Levine",331,
                "https://m.media-amazon.com/images/I/81yhdZV3zLL.jpg",
                "Leadership lessons from Mount Everest and other extreme environments",
                "long desc"));
        adapter.setBooks(books);

    }
}