package com.mclods.javadb;

import com.mclods.javadb.domain.Author;
import com.mclods.javadb.domain.Book;

public class TestDataUtils {
    private TestDataUtils() { }

    public static Author testAuthorA() {
        return Author.builder().id(1).name("Abigail Rose").age((short)80).build();
    }

    public static Author testAuthorB() {
        return Author.builder().id(2).name("Bob Dylan").age((short)45).build();
    }

    public static Author testAuthorC() {
        return Author.builder().id(3).name("Jeremy Reiner").age((short)56).build();
    }

    public static Book testBookA() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1)
                .build();
    }

    public static Book testBookB() {
        return Book.builder()
                .isbn("978-9-2022-6589-9")
                .title("The Whispering Shadows")
                .authorId(1)
                .build();
    }

    public static Book testBookC() {
        return Book.builder()
                .isbn("978-3-3921-5711-8")
                .title("Echoes of the Forgotten Realm")
                .authorId(1)
                .build();
    }
}
