package cc.charles.community;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName StreamTester
 * @description
 * @date 2020/1/31 上午9:41
 * @since 1.8
 */
public class StreamTester {
    public static void main(String[] args) {
        StreamTester streamTester = new StreamTester();
        streamTester.stringStream();

        streamTester.mapStream();

        streamTester.countStream();

        streamTester.limitStream();

        streamTester.statStream();
    }

    private void stringStream() {
        List<String> strings = Arrays.asList("abc", "", "rill", "hill", "milk");
        List<String> stringList = strings.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        System.out.println(stringList.toString());

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }

    private void mapStream() {
        List<Integer> numbers = Arrays.asList(3, 2, 6, 8, 9, 23);
        List<Integer> numList = numbers.stream().map(num -> num * num).distinct().collect(Collectors.toList());
        numList.forEach(System.out::println);
    }

    private void countStream() {
        List<String> strings = Arrays.asList("abc", "", "rill", "hill", "milk");
        Long sizeNum = strings.stream().filter(str -> !str.isEmpty()).count();
        System.out.println(sizeNum);
    }

    private void limitStream() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    private void statStream() {
        List<Integer> numbers = Arrays.asList(4, 5, 6, 8, 1, 2, 9);
        IntSummaryStatistics intStat = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(intStat.getMax());
        System.out.println(intStat.getAverage());
        System.out.println(intStat.getMin());
        System.out.println(intStat.getSum());
    }
}
