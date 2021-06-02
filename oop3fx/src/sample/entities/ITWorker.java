package sample.entities;

public abstract class ITWorker extends Employee{

   private ITSphere sphere;

   ITWorker(){}

   ITWorker(String name, String surname, int age, ITSphere sphere) {
      super(name,surname,age);
      this.sphere = sphere;
   }

   public ITSphere getSphere() {
      return sphere;
   }

   public void setSphere(ITSphere sphere) {
      this.sphere = sphere;
   }

   public enum ITSphere {
      SOFTWARE_ENGINEERING, HARDWARE_ENGINEERING
   }

}