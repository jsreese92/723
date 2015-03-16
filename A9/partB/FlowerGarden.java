public class FlowerGarden extends Garden {
  public Plant getShade() { return new Plant("Impatiens"); }
  public Plant getCenter() { return new Plant("Zinnia"); }
  public Plant getBorder() { return new Plant("Phlox"); }
}
