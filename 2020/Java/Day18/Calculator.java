package Day18;

import java.util.Stack;

public class Calculator {
  Stack<Long> stack = new Stack<Long>();
  Stack<Character> opStack = new Stack<Character>();
  long result = -1L;
  String eqn;

  public Calculator(String s){
    eqn = s;
  }


  public long calculate(){
    for(char c : eqn.toCharArray()){
      switch(c){
        case ' ':
          break;
        case '+':
          opStack.push(c);
          break;
        case '*':
          opStack.push(c);
          break;
        case '(':
          stack.push(result);
          opStack.push(c);
          break;
        case ')':
          if(opStack.isEmpty()){
            break;
          }
          switch(opStack.pop()){
            case '+' -> result += stack.pop();
            case '*' -> result *= stack.pop();
          }
          break;
        default:
          if(opStack.isEmpty()){
            result = Character.getNumericValue(c);
          } else if(opStack.peek() == ')'){
            result = Character.getNumericValue(c);
            opStack.pop();
          } else {
            switch(opStack.pop()){
              case '+' -> result += Character.getNumericValue(c);
              case '*' -> result *= Character.getNumericValue(c);
              case '(' -> result = Character.getNumericValue(c);
            }
          }
          break;
      }
    }
    return result;
  }
}
