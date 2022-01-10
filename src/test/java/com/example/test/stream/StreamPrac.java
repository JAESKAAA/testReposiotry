package com.example.test.stream;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
public class StreamPrac {

    /**
     * Stream 만드는 부분 연습
     */

    @Test
    public void streamTest(){
        List<Integer> list = Arrays.asList(1,2,3,4,5); // int를 리스트로 변환
        Stream<Integer> intStream = list.stream(); //list를 stream으로 만들어줌
        intStream.forEach(System.out::println);

        Stream<String> strStream = Stream.of("a", "b", "c");
        strStream.forEach(System.out::println);

        Stream<String> stringStream = Stream.of(new String[]{"a", "b", "c", "d"});
        stringStream.forEach(System.out::println);

        int[] intArr = {1, 2, 3, 4, 5, 6};
        // 기본형타입일때만 IntStream으로 받음
        // 참조형으로 들어오면 Stream<Integer>로 받을 수 있음 - IntStream은 기본적인 합계, 평균 등의 메소드를 제공함
        IntStream intStream1 = Arrays.stream(intArr);
       // intStream1.forEach(System.out::println);
        //System.out.println("카운트는?? ==> "+intStream1.count());
        System.out.println("합계는?? ==> "+intStream1.sum());
    }

    @Test
    public void randomStream(){
        //무한 난수 스트림 (리미트로 잘라주지 않으면 계속 출력됨
        //아니면 ints에 숫자로 범위를 지정해줘야함
        IntStream intStream = new Random().ints();
        intStream.limit(5).forEach(System.out::println);
    }

    @Test
    public void specificRangeStream(){
        IntStream range = IntStream.range(5, 10);
        range.forEach(System.out::println);
        IntStream range2 = IntStream.rangeClosed(5, 10);
        range2.forEach(System.out::println);
    }

    @Test
    public void usingLambda(){
       // 1. iterate는 초기값을 필요로함 (무한스트림) iterate(T seed, UnaryOperator f) 와 같이 시드와, 단항연산자를 받음
        Stream<Integer> intStream = Stream.iterate(0, n -> n + 2);
        intStream.limit(10).forEach(System.out::println);

        // 2. generate는 초기값을 필요로 하지 않음 (무한스트림) generate(Supplier s) : 입력x, 출력O
        Stream<Integer> generate = Stream.generate(() -> 1);
        generate.limit(10).forEach(System.out::println);
    }
}
