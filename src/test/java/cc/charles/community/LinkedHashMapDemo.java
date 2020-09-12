package cc.charles.community;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author Charles
 * @version 1.0
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new LinkedHashMap<>();
        map.put("name", "charles");
        map.put("age", "20");
        map.put("sex", "man");
        map.put("school", "GUANGXI UNIVERSITY");
        map.put("home", "JIANGXI");
        Iterator<HashMap.Entry<String, String>> mapIt = map.entrySet().iterator();
        while (mapIt.hasNext()) {
            HashMap.Entry<String, String> entry = mapIt.next();
            System.out.println("Key: " + entry.getKey() + ", Val: " + entry.getValue());
        }
    }
}
