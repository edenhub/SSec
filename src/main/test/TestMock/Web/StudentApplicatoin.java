package TestMock.Web;

/**
 * Created by adam on 2014/11/27.
 */
public class StudentApplicatoin {
    IStudent student;
    AbsStudent absStudent;

    public StudentApplicatoin(){}

    public String testIStudent(){
        return student.doMethod1()+" : "+student.doMethod2();
    }

    public String testAbsStudent(){
        return absStudent.doMethod1()+" : "+absStudent.doMethod2()+" : "+absStudent.doMethod3();
    }

    public IStudent getStudent() {
        return student;
    }

    public void setStudent(IStudent student) {
        this.student = student;
    }

    public AbsStudent getAbsStudent() {
        return absStudent;
    }

    public void setAbsStudent(AbsStudent absStudent) {
        this.absStudent = absStudent;
    }
}
