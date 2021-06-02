package sample.serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entities.Employee;

import java.io.*;

public class BinarySerialization extends Serialization {

   @Override
   public void serialize(ObservableList<Employee> data) {
      try (ObjectOutputStream encoder = new ObjectOutputStream(new BufferedOutputStream(
              new FileOutputStream(Serialization.FILEPATH+".bin")))
      ) {
         for (Employee emp: data) {
            encoder.writeObject(emp);
         }
      } catch (Exception ignored) {
         System.out.println(ignored);
      }
   }

   @Override
   public ObservableList<Employee> deserialize() {
      ObservableList<Employee> out = FXCollections.observableArrayList();
      try (ObjectInputStream decoder = new ObjectInputStream(new FileInputStream(Serialization.FILEPATH+".bin"));
      ) {
         Employee obj;
         while((obj =(Employee) decoder.readObject())!=null) out.add(obj);
      } catch (Exception ignored) {
         System.out.println(ignored);
      }
      return out;
   }
}
