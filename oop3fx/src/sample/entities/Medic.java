package sample.entities;

public class Medic extends Employee{

   private MedicType type;

   public Medic(){}

   Medic(String name, String surname, int age, MedicType type) {
      super(name, surname, age);
      this.type = type;
   }

   public MedicType getType() {
      return type;
   }

   public void setType(MedicType type) {
      this.type = type;
   }

   @Override
   public Employee fromString(String s) {
      String[] args = s.split("_");
      return new Medic(args[1], args[2], Integer.parseInt(args[3]), MedicType.valueOf(args[4]));
   }


   @Override
   public String toString() {
      return "Medic_" + super.toString() + "_" + type;
   }

   public enum  MedicType {
      DOCTOR, SURGEON, NURSE
   }
}


