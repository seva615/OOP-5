package sample.serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.EmployeeFactory;
import sample.entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class StringSerialization extends Serialization {

   @Override
   public void serialize(ObservableList<Employee> data) {
      try (FileWriter writer = new FileWriter(Serialization.FILEPATH+".txt")) {
         for (Employee unit : data) {
            writer.write(unit.toString()+"\n");
         }
      } catch (Exception ignored) {}
   }

   @Override
   public ObservableList<Employee> deserialize() {
      ObservableList<Employee> out = FXCollections.observableArrayList();
      String s;
      try (BufferedReader reader = new BufferedReader(new FileReader(Serialization.FILEPATH+".txt"))) {
         while((s = reader.readLine()) != null)
         out.add(EmployeeFactory.factory(s.substring(0, s.indexOf('_'))).fromString(s));
      } catch (Exception ignored) {
         System.out.println(ignored);
      }
      return out;
   }
}
