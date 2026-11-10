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
}