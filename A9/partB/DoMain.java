/*
   Jordan Reese
   COMP 723: Assignment 9
   Due 3-15

*/

class DoMain {

  public static void main (String[] args) {
     Garden plot; // a factory
    GardenMaker gfacA, gfacB; // a factory factory
    
    // gfac = new GardenMaker();
    gfacA = GardenMaker.Instance();
    if(gfacA != null) System.out.println("got 1st garden maker");

    gfacB = GardenMaker.Instance();
    if(gfacB == null) System.out.println("gardenmaker already created");

    
    plot = gfacA.getGarden("Flower");
    System.out.println("\nnew garden factory... flowers"); 
    System.out.println(" >> shade: " + plot.getShade().getName());
    
    plot = gfacA.getGarden("Herb");  
    System.out.println("\nnew garden factory... herbs");  
    System.out.println(" >> shade: " + plot.getShade().getName());
    System.out.println(" >> center: " + plot.getCenter().getName());
    
    plot = gfacA.getGarden("default"); // default veggie
    System.out.println("\nnew garden factory... vegetables"); 
    System.out.println(" >> center: " + plot.getCenter().getName());

    plot = gfacA.getGarden("default");
    if (plot == null) System.out.println("veggie garden already created");
    plot = gfacA.getGarden("Herb");
    if (plot == null) System.out.println("herb garden already created");
    plot = gfacA.getGarden("Flower");
    if (plot == null) System.out.println("flower garden already created");


  }
}
