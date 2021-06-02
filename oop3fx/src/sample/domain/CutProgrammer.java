package sample.domain;

public class CutProgrammer extends Step<String, String> {

   @Override
   public String process(String in) {
      for (int i = 0; i < 4; i++) {
         in = in.substring(in.indexOf('_')+1);
      }

      return in;
   }
}
