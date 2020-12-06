package Day06;

import java.util.regex.Pattern;
import java.util.Arrays;

public class Questionnaire {
  private String answers;
  private int numAnswers;
  
  public Questionnaire(String s){
    answers = s;
  }

  public Questionnaire(String s, int n){
    answers = s;
    numAnswers = n;
  }

  public int uniqueAnswers(){
    return (int) answers.chars()
                        .distinct()
                        .count();
  }

  public int completeAnswers(){
    Pattern p = Pattern.compile("(\\w)\\1{" + (numAnswers-1) + "}");
    char[] temp = answers.toCharArray().clone();
    Arrays.sort(temp);
    String sorted = String.copyValueOf(temp);
    System.out.println(sorted + ">> " + p.pattern() + " -> " + p.matcher(sorted).results().count());
    return (int) p.matcher(sorted)
                  .results()
                  .count();
  }
}
