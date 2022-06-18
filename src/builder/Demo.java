package builder;

import java.util.Arrays;
import java.util.StringJoiner;

class Demo {
    public static void main(String[] args) {
        String hello = "hello";
        System.out.println("<p>" + hello + "</p>");
        String[] words = {"hello", "world", "hi"};//<p>를 일일이 concat해야하는 문제 발생
        StringJoiner sj = new StringJoiner("-","<p>","</p>");
        Arrays.stream(words).forEach(w -> sj.add(w));
        System.out.println("-------------");
        System.out.println(sj);

        String[] words2 = {"hello", "world", "hi","java"};
        StringBuilder sb = new StringBuilder();
        for (String word: words2) {
            sb.append("<p>");
            sb.append(word);
            sb.append("</p>");
        }
        System.out.println(sb);
    }
}
