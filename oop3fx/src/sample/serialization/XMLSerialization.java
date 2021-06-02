package sample.serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entities.Employee;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XMLSerialization extends Serialization {

   @Override
   public void serialize(ObservableList<Employee> data) {
      try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
              new FileOutputStream(Serialization.FILEPATH+".xml")))
      ) {
         for (Employee emp: data) {
            encoder.writeObject(emp);
         }
      } catch (Exception ignored) {
      }
   }

   @Override
   public ObservableList<Employee> deserialize() {
      ObservableList<Employee> out = FXCollections.observableArrayList();
      try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(Serialization.FILEPATH+".xml"));
      ) {
         Employee obj;
         while((obj =(Employee) decoder.readObject())!=null) out.add(obj);
      } catch (Exception ignored) {
      }
      return out;
   }
}
