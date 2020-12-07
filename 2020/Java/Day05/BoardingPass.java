package Day05;

public class BoardingPass {
  private String pass;
  private int row, column, checksum;

  public BoardingPass(String input){
    this.pass = input;
    parsePass();
  }

  private void parsePass(){
    int minRow = 0, rowPart = 64;
    int minCol = 0, colPart = 4;
    char[] chars = pass.toCharArray();
    for(int i = 0; i < chars.length; i++){
      switch(chars[i]){
        case 'F':
          rowPart /= 2;
          break;
        case 'B':
          minRow += rowPart;
          rowPart /= 2;
          break;
        case 'L':
          colPart /= 2;
          break;
        case 'R':
          minCol += colPart;
          colPart /= 2;
      }
      this.column = minCol;
      this.row = minRow;
      this.checksum = row*8 + column;
    }
  }
  public int getChecksum(){
    return checksum;
  }
}
