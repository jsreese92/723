/*
   Jordan Reese
   COMP 723: Assignment 9
   Due 3-15
*/


class GardenMaker {
  //Abstract Factory which returns one of three gardens
  private static boolean made = false;
  private static Garden gd;

  private GardenMaker() {}

  static public GardenMaker Instance() {
    if (!made) {
      made = true;
      return new GardenMaker();
    }
    else
      return null;
  }

  public Garden getGarden(String gtype) {
    gd = new VeggieGarden(); //default
    if(gtype.equals("Flower")) gd = new FlowerGarden();
    if(gtype.equals("Herb")) gd = new HerbGarden();
    return gd;
  }
}

