import java.util.ArrayList;

/**
 * Folder class. With files within.
 */
public class Folder extends FileSys {

  // list for content inside folder
  ArrayList<FileSys> kids = new ArrayList<FileSys>();

  // constructor
  public Folder(String n){
    super(n);
  }

  // adds Node inside folder
  public void addNode(FileSys f){
    kids.add(f);
  }

  // print folder and its kids
  public void print(String indent){
    System.out.println(indent + "d " + name);
    for(int i=0; i<kids.size(); i++){
      kids.get(i).print(indent + "    ");
    }
  }
}
