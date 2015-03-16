public class HerbGarden extends Garden {
  // this is a simple factory
  // meaning contains factory methods
  public Plant getShade() { return new Plant("Mint"); }
  public Plant getCenter() { return new Plant("Rosemary"); }
  public Plant getBorder() { return new Plant("Thyme"); }
}
