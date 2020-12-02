package Day02;
/**
 * Class to represent our passwords. Additionally handles checking if the 
 * passwords are valid.
 */
public class Password {
  private int min, max;
  private char key;
  private String password;
  private boolean valid;


  public Password(String s, int part){
    String[] temp = s.split(":");
    password = temp[1].trim();

    temp = temp[0].split(" ");
    key = temp[1].charAt(0);
  
    temp = temp[0].split("-");
    min = Integer.parseInt(temp[0]);
    max = Integer.parseInt(temp[1]);
    // enables the same class for both parts of the problem
    if(part == 1)
      valid = checkValid();
    if(part == 2)
      valid = checkValidP2();
  }

  private boolean checkValidP2(){
    return (password.charAt(min-1) == key) ^ (password.charAt(max-1) == key);
  }

  private boolean checkValid(){
    long count = password.chars() // creates an IntStream from our string
                         .filter(e -> e == key)
                         .count();

    return count <= max && count >= min;
  }

  public boolean isValid(){
    return valid;
  }
}
