package Day06;
import aocutil.Utilities;
import java.util.ArrayList;
import java.io.File;

public class D6P2 {
  public static void main(String[] args){
    ArrayList<String> input = Utilities.parseInputFile(new File(args[0]));
    ArrayList<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
    
    String collector = "";
    int num = 0;
    for(String s : input){
      if(!s.isEmpty()){
        collector = collector.concat("" + s);
        num++;
      } else {
        questionnaires.add(new Questionnaire(collector, num));
        collector = "";
        num = 0;
      }
    }
    questionnaires.add(new Questionnaire(collector, num));

    int result = questionnaires.stream()
                               .map(Questionnaire::completeAnswers)
                               .reduce(0, Integer::sum);

    System.out.printf("Your group sum is %d", result);
  }
}
