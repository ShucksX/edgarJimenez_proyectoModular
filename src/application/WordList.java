package application;

import java.util.ArrayList;

public class WordList {
	private ArrayList<String> wordList = new ArrayList<String>();

    public void addWord(String word) {
        wordList.add(word);
    }

    public int getSize() {
        return wordList.size();
    }

    public String getValue(int pos) {
        return wordList.get(pos);
    }

    public void clearArrayList() {
        wordList.clear();
    }

    public boolean checkContains(String word) {
        return wordList.contains(word);
    }

    public void removeWordListValue(int pos) {
        wordList.remove(pos);
    }
}
