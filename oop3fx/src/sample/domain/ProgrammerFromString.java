package sample.domain;

import sample.entities.Programmer;

public class ProgrammerFromString extends Step<String, Programmer> {
   @Override
   public Programmer process(String in) {
      return (Programmer) (new Programmer()).fromString(in);
   }
}
