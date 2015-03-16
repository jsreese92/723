public class VeggieGarden extends Garden {

  private static boolean made = false;
  private VeggieGarden() {}

  static public VeggieGarden VegInstance() {
    if (!made) {
      made = true;
      return new VeggieGarden();
    }
    else
      return null;
  }

  public Plant getShade() { return new Plant("Broccoli"); }
  public Plant getCenter() { return new Plant("Corn"); }
  public Plant getBorder() { return new Plant("Peas"); }
}
