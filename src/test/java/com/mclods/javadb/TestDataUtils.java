package com.mclods.javadb;

import com.mclods.javadb.domain.Author;
import com.mclods.javadb.domain.Book;

public class TestDataUtils {
    private TestDataUtils() { }

    public static Author testAuthor() {
        return Author.builder().name("Abigail Rose").age((short)80).build();
    }

    public static Book testBook() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1)
                .build();
    }
}
