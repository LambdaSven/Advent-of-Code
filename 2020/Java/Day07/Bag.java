package Day07;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bag {
  String id;
  HashMap<String, Integer> contents = new HashMap<String, Integer>();

  public Bag(String in){
    Pattern p = Pattern.compile("(\\d{1})? ?(\\w* \\w* bag)");
    Matcher m = p.matcher(in);
    m.find();
    id = m.group(0);
    in = in.replace(m.group(0), "");
    m = p.matcher(in);

    m.results()
      .filter(e -> e.group(1) != null)
      .forEach(e -> contents.put(e.group(2), Integer.parseInt(e.group(1))));
  }

  public String getId(){
    return id;
  }

  public HashMap<String, Integer> getContents(){
    return contents;
  }
}
