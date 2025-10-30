/**
 * File class
 */
public class File extends FileSys {

  // constructor
  public File(String n){
    super(n);
  }

  // prints the file name
  public void print(String indent){
    System.out.println(indent + name);
  }
}
