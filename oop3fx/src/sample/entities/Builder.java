package sample.entities;

import java.util.ArrayList;

public class Builder extends Employee {

   private BuildingType type;

   public Builder(){}

   Builder(String name, String surname, int age, BuildingType type) {
      super(name, surname, age);
      this.type = type;
   }

   public BuildingType getType() {
      return type;
   }

   public void setType(BuildingType type) {
      this.type = type;
   }

   @Override
   public Employee fromString(String s) {
      String[] args = s.split("_");
      return new Builder(args[1], args[2], Integer.parseInt(args[3]), BuildingType.valueOf(args[4]));
   }

   @Override
   public String toString() {
      return "Builder_" + super.toString() + "_" + type;
   }

   public enum BuildingType {
      INDIVIDUAL, PUBLIC, BUSINESS
   }
}
