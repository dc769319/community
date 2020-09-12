package cc.charles.community.leetCode;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 网易社招算法题：
 * 输出大文件末尾的n行，程序如何实现
 *
 * 思路：利用seek从文件末尾开始逐个字符读取文件内容，读取到换行符就获取当前行内容。读取指定行数后结束读取
 *
 * @author charlesdong
 * @version 1.0
 * @since 1.8
 */
public class ReadLastNLine {

    public static void main(String[] args) {
        File file = new File("/Users/charlesdong/Projects/Java/community/src/test/java/cc/charles/community/leetCode/test.log");
        List<String> list = readFile(file, 10);
        if (list == null) {
            System.out.println("list is empty");
        } else {
            for (String line : list) {
                System.out.println(line);
            }
        }
    }

    /**
     * 读取文件
     * @param file 文件
     * @param num 末尾行数
     * @return 列表
     */
    private static List<String> readFile(File file, int num) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        List<String> list = new ArrayList<>();
        long len = file.length();
        RandomAccessFile fd = null;
        try {
            fd = new RandomAccessFile(file, "r");
            long pos = len - 1;
            while (num > 0) {
                fd.seek(pos);
                byte ch = fd.readByte();
                if (ch == '\n') {
                    String line = fd.readLine();
                    list.add(line.trim());
                    num--;
                } else if (pos == 0) {
                    String line = fd.readLine();
                    list.add(line.trim());
                    num--;
                }
                pos--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fd != null) {
                    fd.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
