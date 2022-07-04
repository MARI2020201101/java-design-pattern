package interpreter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Demo2{
    public static void main(String[] args) {
        ExpressionProcessor ex = new ExpressionProcessor();
        ex.calculate("1+2+3");
    }
}
class ExpressionProcessor {
    public Map<Character, Integer> variables = new HashMap<>();
    public int result = 0;
    public int calculate(String expression) {
        for (Character c : expression.toCharArray()) {
            String digit = null;
            if(c == '+' || c == '-'){
                Integer.parseInt(digit);
                digit = null;
            }else if(Character.isDigit(c)){
                if(digit != null) digit += c;
                else digit = c+"";
            }else if(Character.isAlphabetic(c) && variables.containsKey(c)){

                digit = null;
            }else{

            }
        }


        return 0;
    }
    public void putVariable(Character key, Integer value){
        variables.put(key, value);
    }
}
enum OpType{
    PLUS, MINUS, INTEGER, VARIABLE
}