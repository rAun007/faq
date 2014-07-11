package com.raunak.trie;

import java.util.HashMap;

class TrieNode {

    private char value;

    private HashMap<Character, TrieNode> children;

    private boolean isEnd;

    private Object actualLeafObject;

    public TrieNode(char ch) {

        value = ch;
        children = new HashMap<Character, TrieNode>();
        isEnd = false;
    }

    public HashMap<Character, TrieNode> getChildren() {

        return children;
    }

    public char getValue() {

        return value;
    }

    public void setIsEnd(boolean val) {

        isEnd = val;
    }

    public boolean isEnd() {

        return isEnd;
    }

    public Object getActualLeafObject() {

        return actualLeafObject;
    }

    public void setActualLeafObject(Object actualLeafObject) {

        this.actualLeafObject = actualLeafObject;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "TrieNode [value=" + value + ", children=" + children + ", isEnd=" + isEnd + ", actualLeafObject=" + actualLeafObject + "]";
    }
}