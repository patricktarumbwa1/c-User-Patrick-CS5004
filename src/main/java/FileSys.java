/**
 * Base class for file and folder.
 * has a name and a print function
 */
public abstract class FileSys {

  // name for file or folder
  String name;

  // constructor
  public FileSys(String name){
    this.name = name;
  }

  // getter
  public String getName(){
    return name;
  }

  // every subclass must print itself
  public abstract void print(String indent);
}
