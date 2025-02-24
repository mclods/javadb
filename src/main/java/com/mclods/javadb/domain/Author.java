package com.mclods.javadb.domain;

public class Author {
    private Integer id;
    private Short age;
    private String name;

    public Author(Integer id, String name, Short age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Author(Id: %d, Name: %s, Age: %d)", id, name, age);
    }

    public static AuthorBuilder builder() {
        return new AuthorBuilder();
    }

    public static class AuthorBuilder {
        private Integer id;
        private Short age;
        private String name;

        public AuthorBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public AuthorBuilder age(Short age) {
            this.age = age;
            return this;
        }

        public AuthorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Author build() {
            return new Author(id, name, age);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Author authorObj)) {
            return false;
        }

        return this.id.equals(authorObj.getId()) &&
                this.name.equals(authorObj.getName()) &&
                this.age.equals(authorObj.getAge());
    }
}
