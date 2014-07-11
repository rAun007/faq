package com.raunak.trie;

public class Book {

    private String name;

    private String authorName;

    private int yearOfPublication;

    public Book(String name, String authorName, int yearOfPublication) {

        this.name = name;
        this.authorName = authorName;
        this.yearOfPublication = yearOfPublication;
    }

    /**
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return the authorName
     */
    public String getAuthorName() {

        return authorName;
    }

    /**
     * @param authorName
     *            the authorName to set
     */
    public void setAuthorName(String authorName) {

        this.authorName = authorName;
    }

    /**
     * @return the yearOfPublication
     */
    public int getYearOfPublication() {

        return yearOfPublication;
    }

    /**
     * @param yearOfPublication
     *            the yearOfPublication to set
     */
    public void setYearOfPublication(int yearOfPublication) {

        this.yearOfPublication = yearOfPublication;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "Book [name=" + name + ", authorName=" + authorName + ", yearOfPublication=" + yearOfPublication + "]";
    }
}
