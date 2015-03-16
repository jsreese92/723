/* 
   Jordan Reese
   COMP 723: Assignment 9
   Due 3-15
*/

import java.io.*;

// factory method pattern

class NameFactory {
  private static boolean made = false;

  private NameFactory() { }

  static public NameFactory Instance() {
    if (!made) {
      made = true;
      return new NameFactory();
    }
    else
      return null;
  }

  //returns an instance of LastFirst or FirstFirst
  //depending on whether a comma is found
  public Name getName(String entry) {
    int cm = entry.indexOf(","); //comma determines name order
    if (cm >0)
      return new LastFirst(entry); //return one class
    else 
    return new FirstFirst(entry);
  }
}

//==============================================================

class Name {
  //a simple class to take a string apart into two names
  protected String last; //store last name here
  protected String first; //store first name here
  
  public String getFirst() {
    return first; //return first name
  }
  
  public String getLast() {
    return last; //return last name
  }
  
}

class LastFirst extends Name { //split last, first
  public LastFirst(String s) {
    int cm = s.indexOf(","); //find comma
    if (cm > 0) {
      //left is last name
      last = s.substring(0, cm).trim();
      //right is first name
      first = s.substring(cm + 1).trim();
   } else {
      last = s; // put all in last name
      first = ""; // if no comma
    }
  }
}

class FirstFirst extends Name { //split first last
  public FirstFirst(String s) {
    int i = s.lastIndexOf(" "); //find sep space
    if (i > 0) {
      //left is first name
      first = s.substring(0, i).trim();
      //right is last name
      last =s.substring(i+1).trim();
    } else {
      first = ""; // put all in last name
      last = s; // if no space
    }
  }
}

//================================================================


class DoMain {

  public static void main (String[] args) throws IOException {

    // NameFactory nfactory = new NameFactory();
    NameFactory nfactoryA, nfactoryB;

    nfactoryA = NameFactory.Instance();
    if(nfactoryA != null) System.out.println("got 1st nfactory");

    nfactoryB = NameFactory.Instance();
    if(nfactoryB == null) System.out.println("nfactory already created");

    Name n;
    String line;

    System.out.println("Enter first, last name. Enter / to quit");
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  

    while (true) {        
      line = in.readLine();
      if (line.equals("/")) break;

      //send the text to the factory and get a class back
      n = nfactoryA.getName(line);

      //compute the first and last names
      //using the returned class   
      System.out.println(
          "F:" + n.getFirst() + "  L:" + n.getLast());
    }
  }
}

