package sample.entities;

import java.io.Serializable;

public abstract class Employee implements Serializable {

   private String name;
   private String surname;
   private int age;

   Employee(String name, String surname, int age) {
      this.name = name;
      this.surname = surname;
      this.age= age;
   }

   Employee(){}

   public abstract Employee fromString(String s);

   @Override
   public String toString() {
      return name + "_" + surname + "_" + age;
   }

   void work() {
      System.out.println(name + " " + surname + "is working.");
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public String getWorkType() {
      return this.getClass().getSimpleName();
   }

}
