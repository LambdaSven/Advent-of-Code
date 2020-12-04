package Day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
  private String data;
  private Pattern birthYear = Pattern.compile("byr:(\\d{4})");
  private Pattern issueYear = Pattern.compile("iyr:(\\d{4})");
  private Pattern expYear = Pattern.compile("eyr:(\\d{4})");
  private Pattern height = Pattern.compile("hgt:(\\d{2,3})(in|cm)");
  private Pattern hairColour = Pattern.compile("hcl:#[0-9a-f]{6}");
  private Pattern eyeColour = Pattern.compile("ecl:(amb|blu|brn|gry|grn|hzl|oth)");
  private Pattern id = Pattern.compile("pid:\\d{9}\\b");
  private ArrayList<Pattern> patterns = new ArrayList<Pattern>();

  public Passport(String lines){
      this.data = lines;
      patterns.addAll(Arrays.asList(birthYear, issueYear, expYear, height, hairColour, eyeColour, id));
  }

  public boolean cheapValidation(){
    Pattern cheap = Pattern.compile("(\\w{3}:\\S+?)");
    int numMatches = data.contains("cid") ? 8 : 7;
    return numMatches == cheap.matcher(data)
                              .results()
                              .count();
  }

  public boolean richValidation(){
    for(Pattern p: patterns){
      Matcher matcher = p.matcher(data);
      if(matcher.find()){
          if(matcher.group().contains("byr")){
            int number = Integer.parseInt(matcher.group(1));
            if(number < 1920 || number > 2002)
              return false;
          }

          if(matcher.group().contains("iyr")){
            int number = Integer.parseInt(matcher.group(1));
            if(number < 2010 || number > 2020)
              return false;
          }

          if(matcher.group().contains("eyr")){
            int number = Integer.parseInt(matcher.group(1));
            if(number < 2020 || number > 2030)
              return false;
          }

          if(matcher.group().contains("hgt")){
            String unit = matcher.group(2);
            int number = Integer.parseInt(matcher.group(1));
            if(unit.equals("cm")){
              if(number < 150 || number > 193)
                return false;
            } else if(unit.equals("in")){
              if(number < 59 || number > 76)
                return false;
            }
          }
          //System.out.println("\t MATCHED → " + matcher.group());
      } else {
        return false;
      }
    }
    return true;
  }
}
