package sample.entities;

public class Teacher extends Employee {

   private Subject subject;

   public Teacher(){}

   public Teacher(String name, String surname, int age, Subject subject) {
      super(name, surname, age);
      this.subject = subject;
   }

   public Subject getSubject() {
      return subject;
   }

   public void setSubject(Subject subject) {
      this.subject = subject;
   }

   @Override
   public Employee fromString(String s) {
      String[] args = s.split("_");
      return new Teacher(args[1], args[2], Integer.parseInt(args[3]), Subject.valueOf(args[4]));
   }

   @Override
   public String toString() {
      return "Teacher_" + super.toString() + "_" + subject;
   }

   public enum Subject {
      MATH, PE, CHEMISTRY
   }
}


