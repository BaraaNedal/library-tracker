package com.example.library_tracker;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class LibraryManager {
    private static final String FILE_NAME = "books.json";
    private final Context context;

    public LibraryManager(Context context) {
        this.context = context;
    }

    public static String print_banner() {
        return "=== Library Tracker System v1.0 ===";
    }

    private JSONArray loadBooks() {
        try {
            File file = new File(context.getFilesDir(), FILE_NAME);
            if (!file.exists()) return new JSONArray();
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            return new JSONArray(new String(data, StandardCharsets.UTF_8));
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private void saveBooks(JSONArray books) {
        try {
            File file = new File(context.getFilesDir(), FILE_NAME);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(books.toString(4).getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBook(String title, String author) {
        try {
            JSONArray books = loadBooks();
            JSONObject book = new JSONObject();
            book.put("title", title);
            book.put("author", author);
            books.put(book);
            saveBooks(books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String listBooks() {
        JSONArray books = loadBooks();
        if (books.length() == 0) {
            return "No books found in library.";
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < books.length(); i++) {
                JSONObject book = books.getJSONObject(i);
                sb.append(i + 1).append(". ")
                        .append(book.getString("title"))
                        .append(" - ")
                        .append(book.getString("author"))
                        .append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}