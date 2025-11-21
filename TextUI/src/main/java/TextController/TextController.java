import java.io.IOException;
import java.util.Scanner;

/**
 * Controller uses Readable and IView so tests can inject StringReader and StringBuffer.
 * The Scanner is constructed from a Readable, which allows StringReader input in tests
 * and System.in in the real program (Scanner accepts System.in as an InputStream wrapper).
 */
public class TextController implements IController {

  private final Scanner in;
  private final IView view;
  private final IModel model;

  /**
   * @param model the model
   * @param input any Readable (e.g., new StringReader("..."))
   * @param view  a view that writes to an Appendable
   */
  public TextController(IModel model, Readable input, IView view) {
    this.model = model;
    this.view = view;
    this.in = new Scanner(input);
  }

  @Override
  public void go() {
    boolean quit = false;

    try {
      while (!quit) {
        // show current string
        view.showString(model.getString());
        // show menu options
        view.showOptions();

        if (!in.hasNext()) {
          break; // no more input
        }

        String option = in.next();

        switch (option) {
          case "E":
            // request string input
            view.showStringEntry();
            in.nextLine(); // consume remainder of line if needed
            String inputString = in.nextLine();
            model.setString(inputString);
            break;
          case "Q":
            quit = true;
            break;
          default:
            view.showOptionError();
        }
      }
    } catch (IOException e) {
      // Convert checked IO exception to runtime to avoid changing interface
      throw new RuntimeException("I/O error in controller: " + e.getMessage(), e);
    }
  }
}
