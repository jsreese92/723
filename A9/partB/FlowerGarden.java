public class FlowerGarden extends Garden {

  private static boolean made = false;
  private FlowerGarden() {}

  static public FlowerGarden FloInstance(){
    if (!made) {
      made = true;
      return new FlowerGarden();
    }
    else
      return null;
  }

  public Plant getShade() { return new Plant("Impatiens"); }
  public Plant getCenter() { return new Plant("Zinnia"); }
  public Plant getBorder() { return new Plant("Phlox"); }
}
