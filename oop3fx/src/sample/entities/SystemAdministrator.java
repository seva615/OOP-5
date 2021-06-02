package sample.entities;

import sample.domain.CutProgrammer;
import sample.domain.Pipeline;
import sample.domain.ProgrammerFromString;

public class SystemAdministrator extends ITWorker {

   private Programmer programmer = new Programmer();

   public SystemAdministrator(){
      setSphere(ITSphere.HARDWARE_ENGINEERING);
   }

   SystemAdministrator(String name, String surname, int age, Programmer programmer) {
      super(name, surname, age, ITSphere.HARDWARE_ENGINEERING);
      this.programmer = programmer;
   }

   public Programmer getProgrammer() {
      return programmer;
   }

   public void setProgrammer(Programmer programmer) {
      this.programmer = programmer;
   }

   @Override
   public Employee fromString(String s) {
      String[] args = s.split("_");
      Pipeline<String, Programmer> pipe = new Pipeline<>(new CutProgrammer()).pipe( new ProgrammerFromString());
      Programmer programmer = pipe.execute(s);
      return new SystemAdministrator(args[1], args[2], Integer.parseInt(args[3]), programmer);
   }

   @Override
   public String toString() {
      return "SystemAdministrator_" + super.toString() + "_" + programmer.toString();
   }

}
