package sample.plugins;

public abstract class Zip {

   static String srcName(String extension) { return"serialization."+extension;}

   public abstract void zip(String extension);

   public abstract void unzip(String extension);

}
