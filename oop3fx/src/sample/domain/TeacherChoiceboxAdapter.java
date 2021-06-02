package sample.domain;

import sample.entities.Teacher;

public class TeacherChoiceboxAdapter extends Teacher {

   private Teacher teacher;

   public TeacherChoiceboxAdapter(Teacher teacher) {
      this.teacher = teacher;
   }

   @Override
   public String toString() {
      return teacher.getSubject().toString();
   }
}
