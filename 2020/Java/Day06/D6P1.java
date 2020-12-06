package Day06;
import aocutil.Utilities;
import java.util.ArrayList;
import java.io.File;
public class D6P1 {
  public static void main(String[] args){
    ArrayList<String> input = Utilities.parseInputFile(new File(args[0]));
    ArrayList<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
    
    String collector = "";
    for(String s : input){
      if(!s.isEmpty()){
        collector = collector.concat("" + s);
      } else {
        questionnaires.add(new Questionnaire(collector));
        collector = "";
      }
    }
    questionnaires.add(new Questionnaire(collector));

    int result = questionnaires.stream()
                               .map(Questionnaire::uniqueAnswers)
                               .reduce(0, Integer::sum);

    System.out.printf("Your group sum is %d", result);
  }
}
