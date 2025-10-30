/**
 * testing the class
 */
public class Main {

  public static void main(String[] args) {

    // making the folders
    Folder root = new Folder("root");
    Folder home = new Folder("home");
    Folder ptarumbwa = new Folder("ptarumbwa");

    // putting them together
    root.addNode(home);
    home.addNode(ptarumbwa);

    // making file and adding it
    File patrickFile = new File("patrickFile.txt");
    ptarumbwa.addNode(patrickFile);

    // print the whole thing
    root.print("");
  }
}
