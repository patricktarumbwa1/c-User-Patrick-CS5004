/**
 * Main program. Uses System.in and System.out to create the controller/view.
 * Because TextController accepts Readable and TextView accepts Appendable,
 * this main still works for manual runs and the code is testable automatically.
 */
public class MVCExampleTextUI {
  public static void main(String[] args) {
    IModel model = new Model();
    IView view = new TextView(System.out);
    IController controller = new TextController(model, new java.io.InputStreamReader(System.in), view);
    controller.go();
  }
}
