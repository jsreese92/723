import java.util.ArrayList;

class File {
 protected String m_name;
 public File(String name) { m_name = name; }
 public String getName() { return m_name; }
 public static File createFile(String name)
 {
 return new File(name);
 }
 /*public static File createFileNoDisplayName(String number)
 {
 return new File("rendering: " + number);
 }*/
}

class FileWithoutDisplayName extends File {
public FileWithoutDisplayName(String name) {
super(name);
}

public String getName() { return "rendering:" + this.m_name;}
public static File createFileNoDisplayName(String name){
return new FileWithoutDisplayName(name);

}
}
class Directory {
 private String m_name;
 private int lev;
 private ArrayList m_files = new ArrayList();
 public Directory(String name) { m_name = name; }
 public static Directory createDirectory(String name) { return new Directory(name);}
 public void add(Object obj) { m_files.add(obj); }
 public void ls(int lev) {
   Ind.ent(lev); System.out.println(m_name);
   for (int i = 0; i < m_files.size(); ++i) {
     Object obj = m_files.get(i);
     // Recover the type of this object
     if (obj.getClass().getName().equals("Directory"))
        ((Directory)obj).ls(lev+2);
     else {
        Ind.ent(lev+2);
        System.out.println( ((File)obj).getName() );
     }
   }
 }
}

class Ind {
 // here just to indent some blank spaces on a line
 public static void ent(int lev) { 
   for (int i=0; i<lev; i++) { System.out.print(" "); }
 }
}

public class FilesDirs {
 public static void main(String[] args) {
   Directory one = Directory.createDirectory("dir111"), 
             two = Directory.createDirectory("dir222"),
             three = Directory.createDirectory("dir333");
   File a = File.createFile("aa"), b = File.createFile("bb"), c = File.createFile("cc"), 
        d = File.createFile("dd"), e = File.createFile("ee"), f = FileWithoutDisplayName.createFileNoDisplayName("12345");
   one.add(a);
   one.add(two);
   one.add(b);
   two.add(c);
   two.add(d);
   two.add(three);
   three.add(e);
   three.add(f);
   
   // print tree root
   one.ls(1);
   
   // print inner tree root
   System.out.println("\n-------\n");
   two.ls(1);   
   
   // print a leaf
   System.out.println("\n-------\n");
   // d.ls(1);
   System.out.println(d.getName());
   
 }
}
