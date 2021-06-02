package sample;

import sample.entities.*;

public class EmployeeFactory {

   public static Employee factory(String className) {
      switch (className) {
         case "Builder": return new Builder();
         case "Medic": return new Medic();
         case "Teacher": return new Teacher();
         case "Programmer": return new Programmer();
         case "SystemAdministrator": return new SystemAdministrator();
         default: return null;
      }
   }

}
