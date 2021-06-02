package sample.pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.EmployeeFactory;
import sample.domain.TeacherChoiceboxAdapter;
import sample.entities.*;
import sample.plugins.FileSystemsZip;
import sample.plugins.ZipStream;
import sample.serialization.*;

import java.util.ArrayList;

public class Controller {

   @FXML
   private MenuItem openListMenuItem;

   @FXML
   private MenuItem menuItemSaveList;

   @FXML
   private TableView<Employee> tableView;

   @FXML
   private TableColumn<?, ?> firstnameColumn;

   @FXML
   private TableColumn<?, ?> lastnameColumn;

   @FXML
   private TableColumn<?, ?> ageColumn;

   @FXML
   private TableColumn<?, ?> typeOfWorkColumn;

   @FXML
   private ChoiceBox<String> choiceBox;

   @FXML
   private Button btnAdd;

   @FXML
   private Button btnEdit;

   @FXML
   private Button btnDelete;

   @FXML
   private RadioMenuItem binaryMethod;

   @FXML
   private RadioMenuItem xmlMethod;

   @FXML
   private RadioMenuItem textMethod;

   @FXML
   private TextField nameField;

   @FXML
   private TextField surnameField;

   @FXML
   private TextField ageField;

   @FXML
   private ChoiceBox<String> addBox;

   @FXML
   private RadioMenuItem filesystemsZip;

   @FXML
   private RadioMenuItem zipStream;

   private ObservableList<Employee> employees = FXCollections.observableArrayList();

   private ArrayList<Programmer> programmers = new ArrayList<>();

   @FXML
   void initialize() {

      choiceBox.getItems().add("Builder");
      choiceBox.getItems().add("Teacher");
      choiceBox.getItems().add("Medic");
      choiceBox.getItems().add("Programmer");
      choiceBox.getItems().add("SystemAdministrator");

      choiceBox.setValue("Builder");
      setBuilder();

      choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
         switch (t1) {
            case "Builder":
               setBuilder();
               break;
            case "Teacher":
               setTeacher();
               break;
            case "Medic":
               setMedic();
               break;
            case "Programmer":
               setProgrammer();
               break;
            case "SystemAdministrator":
               setSystemAdministrator();
               break;
         }
      });


      employees.clear();
      employees.add(new Teacher("11", "22", 23, Teacher.Subject.CHEMISTRY));
      employees.add(new Teacher("22", "33", 44, Teacher.Subject.MATH));
      tableView.setItems(employees);
      firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
      ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
      typeOfWorkColumn.setCellValueFactory(new PropertyValueFactory<>("workType"));
   }

   private void setMedic() {
      ArrayList<String> list = new ArrayList<>();
      for (Medic.MedicType value : Medic.MedicType.values()) {
         list.add(value.toString());
      }
      setChoiceBox(list, Medic.MedicType.DOCTOR.toString());
   }

   private void setBuilder() {
      ArrayList<String> list = new ArrayList<>();
      for (Builder.BuildingType value : Builder.BuildingType.values()) {
         list.add(value.toString());
      }
      setChoiceBox(list, Builder.BuildingType.BUSINESS.toString());
   }

   private void setTeacher() {
      ArrayList<String> list = new ArrayList<>();
      for (Teacher.Subject value : Teacher.Subject.values()) {
         list.add(value.toString());
      }
      setChoiceBox(list, Teacher.Subject.MATH.toString());
   }

   private void setProgrammer() {
      ArrayList<String> list = new ArrayList<>();
      for (Programmer.ProgrammingLanguage value : Programmer.ProgrammingLanguage.values()) {
         list.add(value.toString());
      }
      setChoiceBox(list, Programmer.ProgrammingLanguage.JAVA.toString());
   }

   private void setSystemAdministrator() {
      ArrayList<String> list = new ArrayList<>();
      programmers.clear();
      for (Employee emp : employees) {
         if (emp instanceof Programmer) {
            programmers.add((Programmer) emp);
            list.add(emp.getName() + " " + emp.getSurname());
         }
      }
      String init = list.isEmpty() ? "" : list.get(0);
      setChoiceBox(list, init);
   }

   private void setChoiceBox(ArrayList<String> values, String init) {
      addBox.getItems().clear();
      if (!values.isEmpty()) {
         addBox.getItems().addAll(values);
         addBox.setValue(init);
      }
   }

   private boolean isOnEdit = false;

   private Employee get() {
      if (!nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !ageField.getText().isEmpty()) {
         int age;
         try {
            age = Integer.parseInt(ageField.getText());
            Employee emp = EmployeeFactory.factory(choiceBox.getValue());
            emp.setName(nameField.getText());
            emp.setSurname(surnameField.getText());
            emp.setAge(age);

            switch (choiceBox.getValue()) {
               case "Builder":
                  Builder builder = (Builder) emp;
                  builder.setType(Builder.BuildingType.valueOf(addBox.getValue()));
                  return builder;
               case "Teacher":
                  Teacher teacher = (Teacher) emp;
                  teacher.setSubject(Teacher.Subject.valueOf(addBox.getValue()));
                  return teacher;
               case "Medic":
                  Medic medic = (Medic) emp;
                  medic.setType(Medic.MedicType.valueOf(addBox.getValue()));
                  return medic;
               case "Programmer":
                  Programmer programmer = (Programmer) emp;
                  programmer.setLang(Programmer.ProgrammingLanguage.valueOf(addBox.getValue()));
                  return programmer;
               case "SystemAdministrator":
                  if (!programmers.isEmpty()) {
                     int index = addBox.getSelectionModel().getSelectedIndex();
                     SystemAdministrator sys = (SystemAdministrator) emp;
                     sys.setProgrammer(programmers.get(index));
                     return sys;
                  }
            }
            clear();
         } catch (Exception ignored) {
         }
      }
      return null;
   }

   //and ok
   public void add() {
      if (isOnEdit) {
         if (index != -1) {
            Employee newEmp = get();
            if (newEmp != null ) tableView.getItems().set(index, newEmp);
         }
         edit();
      } else {
         Employee newEmp = get();
         if (newEmp != null ) employees.add(newEmp);
      }
   }

   private int index = -1;

   //and cancel
   public void edit() {
      if (isOnEdit) {
         isOnEdit = false;
         clear();
         btnAdd.setText("Add");
         btnEdit.setText("Edit");
         tableView.setDisable(false);
         btnDelete.setDisable(false);
      } else {
         index = tableView.getSelectionModel().getSelectedIndex();
         if (index != -1) {
            isOnEdit = true;
            btnDelete.setDisable(true);
            btnEdit.setText("Cancel");
            btnAdd.setText("Ok");
            Employee emp = tableView.getItems().get(index);
            nameField.setText(emp.getName());
            surnameField.setText(emp.getSurname());
            ageField.setText(Integer.toString(emp.getAge()));
            setChoice(tableView.getItems().get(index));
            tableView.setDisable(true);
         }
      }
   }

   private Serialization serializationInstance;

   void setUpSerialization() {
      if (textMethod.isSelected()) {
         serializationInstance = new StringSerialization();
      } else if (xmlMethod.isSelected()) {
         serializationInstance = new XMLSerialization();
      } else {
         serializationInstance = new BinarySerialization();
      }
   }

   private String getExtension() {
      if (textMethod.isSelected()) {
         return "txt";
      } else if (xmlMethod.isSelected()) {
         return "xml";
      } else {
         return "bin";
      }
   }

   @FXML
   void serialize() {
      setUpSerialization();
      serializationInstance.serialize(employees);
      if (filesystemsZip.isSelected())
         new FileSystemsZip().zip(getExtension());
      if (zipStream.isSelected())
         new ZipStream().zip(getExtension());
   }

   @FXML
   void deserialize() {
      setUpSerialization();
      employees.clear();
      employees.setAll(serializationInstance.deserialize());
   }

   private void setChoice(Employee init) {
      String name = init.getWorkType();
      choiceBox.setValue(name);
      switch (name) {
         case "Builder":
            addBox.setValue(((Builder) init).getType().toString());
            break;
         case "Teacher":
            addBox.setValue((new TeacherChoiceboxAdapter((Teacher) init)).toString());
            break;
         case "Medic":
            addBox.setValue(((Medic) init).getType().toString());
            break;
         case "Programmer":
            addBox.setValue(((Programmer) init).getLang().toString());
            break;
         case "SystemAdministrator":
            if (!programmers.isEmpty()) {
               Programmer programmer = ((SystemAdministrator) init).getProgrammer();
               addBox.setValue(programmer.getName()+" "+programmer.getSurname());
               break;
            }
      }
   }

   private void clear() {
      nameField.clear();
      surnameField.clear();
      ageField.clear();
   }

   public void delete() {
      tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
   }
}
