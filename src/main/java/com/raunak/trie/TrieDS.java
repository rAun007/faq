package com.raunak.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieDS {

    private TrieNode root;

    public TrieDS() {

        root = new TrieNode((char) 0);
    }

    public void insert(String word, Object o) {

        int length = word.length();

        word = word.toLowerCase();

        TrieNode crawl = root;

        // Traverse through all characters of given word
        for (int level = 0; level < length; level++) {
            HashMap<Character, TrieNode> child = crawl.getChildren();
            char ch = word.charAt(level);

            // If there is already a child for current character of given word
            if (child.containsKey(ch)) {
                crawl = child.get(ch);
            } else { // Else create a child
                TrieNode temp = new TrieNode(ch);
                child.put(ch, temp);
                crawl = temp;
            }
        }

        // Set isEnd true for last character
        crawl.setIsEnd(true);
        crawl.setActualLeafObject(o);
    }

    // The main method that finds out the longest string 'input'
    public List<Object> getMatchingPrefix(String input) {

        List<Object> returnList = new ArrayList<Object>();

        int length = input.length();
        input = input.toLowerCase();

        // Initialize reference to traverse through Trie
        TrieNode crawl = root;

        // Iterate through all characters of input string and traverse
        // down the Trie
        for (int level = 0; level < length; level++) {

            char ch = input.charAt(level);

            Map<Character, TrieNode> chilren = crawl.getChildren();

            TrieNode child = chilren.get(ch);

            if (child == null) {
                return null;
            }

            crawl = child;
        }

        // once the loop is complete now do exhaustive search in all the tree of the "crawl".
        returnList.addAll(doExhaustiveSearch(crawl));
        return returnList;
    }

    private List<Object> doExhaustiveSearch(TrieNode root) {

        List<Object> returnList = new ArrayList<Object>();

        if (root != null) {

            if (root.isEnd()) {
                returnList.add(root.getActualLeafObject());
            }

            Map<Character, TrieNode> children = root.getChildren();

            if (children != null && !children.isEmpty()) {

                for (Map.Entry<Character, TrieNode> entry : children.entrySet()) {

                    returnList.addAll(doExhaustiveSearch(entry.getValue()));
                }
            }
        }

        return returnList;
    }

    public static void main(String[] args) {

        TrieDS trieDS = new TrieDS();

        Book book = new Book("JA", "Raunak", 2014);
        Book book2 = new Book("JAT", "Kavita", 2014);
        Book book3 = new Book("JAV", "Auth3", 2014);
        Book book4 = new Book("JSU", "Raunak", 2014);
        Book book5 = new Book("JS", "Kavita", 2014);
        Book book6 = new Book("JSW", "Auth3", 2014);
        Book book7 = new Book("JSWA", "Auth3", 2014);
        Book book8 = new Book("JSWB", "Auth3", 2014);

        trieDS.insert(book.getName(), book);
        trieDS.insert(book2.getName(), book2);
        trieDS.insert(book3.getName(), book3);
        trieDS.insert(book4.getName(), book4);
        trieDS.insert(book5.getName(), book5);
        trieDS.insert(book6.getName(), book6);
        trieDS.insert(book7.getName(), book7);
        trieDS.insert(book8.getName(), book8);

        List<Object> books = trieDS.getMatchingPrefix("JSWB");

        System.out.println(books.size());
        for (Object object : books) {
            System.out.println("Book --> " + object);
        }
    }

}
