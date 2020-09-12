package cc.charles.community;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName InstanceRefTester
 * @description
 * @date 2020/2/5 下午5:58
 * @since 1.8
 */
public class InstanceRefTester {
    public static void main(String[] args) {
        InstanceRefTester instanceRefTester = new InstanceRefTester();
        Student student = new Student();
        student.setAge(18);
        student.setName("Lily");
        instanceRefTester.modifyData(student);

        System.out.println(student.toString());
    }

    private void modifyData(Student student) {
        student.setAge(20);
        student.setName("Maomao");
    }
}
