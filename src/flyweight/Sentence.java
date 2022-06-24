package flyweight;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Collectors;

class Sentence {
    private List<WordToken> words;
    public Sentence(String plainText) {
        this.words = Arrays.stream(plainText.split(" "))
                .map(WordToken::new)
                .collect(Collectors.toList());
    }

    public WordToken getWord(int index) {
        return this.words.get(index);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ");
        for(WordToken word : words){
            if(word.capitalize){
                sj.add(word.word.toUpperCase(Locale.ROOT));
            }else{
                sj.add(word.word);
            }
        }
        return sj.toString();
    }

    class WordToken {
        public boolean capitalize;
        private String word;
        public WordToken(String word){
            this.word = word;
        }
        @Override
        public String toString() {
            return word;
        }
    }
}
class Demo3{
    public static void main(String[] args) {
        Sentence sentence = new Sentence("hello world");
        sentence.getWord(1).capitalize = true;
        System.out.println(sentence);
    }
}