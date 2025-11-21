public class Model implements IModel {
  private String input;

  public Model() {
    this.input = "";
  }

  @Override
  public void setString(String s) {
    this.input = s;
  }

  @Override
  public String getString() {
    return this.input;
  }
}
