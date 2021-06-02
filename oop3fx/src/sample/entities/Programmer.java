package sample.entities;

public class Programmer extends ITWorker{

   private ProgrammingLanguage lang;

   public Programmer(){
      setSphere(ITSphere.SOFTWARE_ENGINEERING);
   }

   Programmer(String name, String surname, int age, ProgrammingLanguage lang) {
      super(name, surname, age, ITSphere.SOFTWARE_ENGINEERING);
      this.lang = lang;
   }

   public ProgrammingLanguage getLang() {
      return lang;
   }

   public void setLang(ProgrammingLanguage lang) {
      this.lang = lang;
   }

   @Override
   public Employee fromString(String s) {
      String[] args = s.split("_");
      return new Programmer(args[1], args[2], Integer.parseInt(args[3]), ProgrammingLanguage.valueOf(args[4]));
   }

   @Override
   public String toString() {
      return "Programmer_" + super.toString() + "_" + lang;
   }

   public enum ProgrammingLanguage {
      CPP, JAVA, PHP;
   }

}
