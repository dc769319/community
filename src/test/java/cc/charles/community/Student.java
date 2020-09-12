package cc.charles.community;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName Student
 * @description
 * @date 2020/2/5 下午6:00
 * @since 1.8
 */
public class Student {

    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" + "\"age\":" + this.age + ", \"name\":\"" + this.name + "\"}";
    }
}
