import java.io.IOException;

public interface IView {
  void showString(String s) throws IOException;
  void showOptions() throws IOException;
  void showStringEntry() throws IOException;
  void showOptionError() throws IOException;
}
