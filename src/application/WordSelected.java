package application;

import java.util.ArrayList;

public class WordSelected {
	private ArrayList<Character> wordSelect = new ArrayList<Character>(); // Stores the list of words to find

    public void addLetter(char val) {
        wordSelect.add(val);
    }

    public int getSize() {
        return wordSelect.size();
    }

    public char getValue(int pos) {
        return wordSelect.get(pos);
    }

    public void clearArrayList() {
        wordSelect.clear();
    }

    public void removeValue(int pos) {
        wordSelect.remove(pos);
    }

    public void removeLastValue() {
        wordSelect.remove(wordSelect.size() - 1);
    }
}
