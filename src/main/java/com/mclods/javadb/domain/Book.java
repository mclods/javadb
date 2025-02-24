package com.mclods.javadb.domain;

public class Book {
    private String isbn, title;
    private Integer authorId;

    public Book(String isbn, String title, Integer authorId) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return String.format("Book(Title: %s, ISBN: %s, AuthorId: %d)", title, isbn, authorId);
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {
        private String isbn, title;
        private Integer authorId;

        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder authorId(Integer authorId) {
            this.authorId = authorId;
            return this;
        }

        public Book build() {
            return new Book(isbn, title, authorId);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Book bookObj)) {
            return false;
        }

        return this.title.equals(bookObj.getTitle()) &&
                this.isbn.equals(bookObj.getIsbn()) &&
                this.authorId.equals(bookObj.getAuthorId());
    }
}
