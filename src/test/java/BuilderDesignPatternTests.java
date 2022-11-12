
import com.pojo.employee.Employee;
import com.pojo.employee.FavFood;
import com.pojo.employee.Student;
import com.pojo.employee.StudentBuilder;
import org.testng.annotations.Test;

import java.util.Arrays;

public class BuilderDesignPatternTests {

    @Test
    public void buildTest(){ //Immutability
        //1.No.of parameters increases readability decreases
        //2.If you want to ignore some fields,it becomes difficult to create multiple constructors

        //Builder Design Pattern --
        //  1.external builder

        //Student student = StudentBuilder.builder().setId(22).setEmail("mma@gmail.com").build();
        //System.out.println(student);

        // 2.Using static inner class
        Student student2 = Student.StudentBuilder1.builder().setId(123).setLname("mahesh").build();
        System.out.println(student2);
        //In-built @Builder from lombook
        //Student student1 = Student.builder().id(12).fname("mah").email("@gmail").build();
        //System.out.println(student1);

    }
}
