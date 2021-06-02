package sample.serialization;

import javafx.collections.ObservableList;
import sample.entities.Employee;

public abstract class Serialization {

   public static final String FILEPATH = "serialization";

   public abstract void serialize(ObservableList<Employee> data);

   public abstract ObservableList<Employee> deserialize();

}
