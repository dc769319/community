package cc.charles.community;

/**
 * @author Charles
 * @version 1.0
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.print("Bootstrap ClassLoader path: ");
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.print("Extension ClassLoader path: ");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.print("App ClassLoader path: ");
        System.out.println(System.getProperty("java.class.path"));
    }
}
