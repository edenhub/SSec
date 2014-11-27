package TestMock.Web;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by adam on 2014/11/27.
 */

@RunWith(EasyMockRunner.class)
public class TestStudent {
    @Mock
    public IStudent iStudent;
    public AbsStudent absStudent;

    @TestSubject
    public StudentApplicatoin studentApplicatoin = new StudentApplicatoin();

    @Test
    public void testIStudent(){
        iStudent = EasyMock.createMock(IStudent.class);

        EasyMock.expect(iStudent.doMethod1()).andReturn("methodA").times(1);
        EasyMock.expect(iStudent.doMethod2()).andReturn("methodB").times(1);
        EasyMock.replay(iStudent);

        studentApplicatoin.setStudent(iStudent);
        System.out.println(studentApplicatoin.testIStudent());

        EasyMock.verify(iStudent);
    }

    @Test
    public void testAbs(){
        absStudent = EasyMock.createMock(AbsStudent.class);

        EasyMock.expect(absStudent.doMethod1()).andReturn("methodA").times(1);
        EasyMock.expect(absStudent.doMethod2()).andReturn("methodB").times(1);
        EasyMock.expect(absStudent.doMethod3()).andReturn("methodC").times(1 );
        EasyMock.replay(absStudent);

        studentApplicatoin.setAbsStudent(absStudent);

        System.out.println(studentApplicatoin.testAbsStudent());

        EasyMock.verify(absStudent);
    }

    @Test
    public void testAnno(){
//        iStudent = EasyMock.createMock(IStudent.class);
        EasyMock.expect(iStudent.doMethod1()).andReturn("methodA").times(1);
        EasyMock.expect(iStudent.doMethod2()).andReturn("methodB").times(1);
        EasyMock.replay(iStudent);

//        studentApplicatoin.setStudent(iStudent);
        System.out.println(studentApplicatoin.testIStudent());

        EasyMock.verify(iStudent);
    }

    @Test
    public void testPartial(){
        IStudent student = EasyMock.createMockBuilder(IStudent.class)
                .addMockedMethod("doMethod1").createMock();

        EasyMock.replay(student);

        studentApplicatoin.setStudent(student);



    }
}
