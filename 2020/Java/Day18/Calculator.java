package Day18;

import java.util.Stack;
import java.util.Collections;

public class Calculator {
  Stack<Long> stack = new Stack<Long>();
  Stack<Character> opStack = new Stack<Character>();

  Stack<Character> rpn = new Stack<Character>();

  long result = -1L;
  String eqn;

  public Calculator(String s){
    eqn = s;
  }

  public void setAdvanced(){
    for(char c : eqn.toCharArray()){
      switch(c){
        case '+':
            opStack.push('+');
          break;
        case '*':
          while(!opStack.isEmpty() && opStack.peek() == '+'){
            rpn.push(opStack.pop());
          }
          opStack.push('*');
          break;
        case ')':
          while(!opStack.empty() && opStack.peek() != '('){
            rpn.push(opStack.pop());
          }
          opStack.pop();
          break;
        case '(':
          opStack.push('(');
          break;
        case ' ':
          break;
        default:
          rpn.push(c);
      }

    }
  while(!opStack.isEmpty()){
      if(opStack.peek() != '('){
        rpn.push(opStack.pop());
      } else {
        opStack.pop();
      }
    }
  }

  public long calcRPN(){
    Stack<Long> nums = new Stack<Long>();
    Stack<Character> temp = new Stack<Character>();
    while(!rpn.isEmpty())
      temp.push(rpn.pop());


    // System.out.println(temp);
      do{
        switch(temp.peek()){
          case '+' -> nums.push(nums.pop() + nums.pop());
          case '*' -> nums.push(nums.pop() * nums.pop());
          default -> nums.push((long) Character.getNumericValue(temp.peek()));
        }
        temp.pop();
      } while (!temp.isEmpty());
      return nums.pop();
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
