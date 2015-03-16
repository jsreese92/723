public class HerbGarden extends Garden {
  // this is a simple factory
  // meaning contains factory methods

  private static boolean made = false;
  private HerbGarden() {}

  static public HerbGarden HerInstance() {
    if (!made) {
      made = true;
      return new HerbGarden();
    }
    else
      return null;
  }

  public Plant getShade() { return new Plant("Mint"); }
  public Plant getCenter() { return new Plant("Rosemary"); }
  public Plant getBorder() { return new Plant("Thyme"); }
}
