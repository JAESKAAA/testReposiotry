package com.example.test.stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.el.CompositeELResolver;
import java.io.File;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
public class StreamMiddleOperate {
/**
 * 중간연산 -> 연산결과가 스트림인 연산이며, 반복적으로 적용 가능
 *
 */

    @Test
    public void skipAndLimit(){
        IntStream.rangeClosed(1,10).skip(3).limit(5).forEach(System.out::println);
    }

    @Test
    public void filterAndDistinct(){
        IntStream.of(1, 2, 3, 4, 5, 5, 6, 7, 7, 7, 7, 8, 9, 10).distinct().forEach(System.out::println);
        //IntStream.rangeClosed(11,21).filter(i->i%2==0).forEach(System.out::println);
        IntStream.rangeClosed(11,21).filter(i->i%2!=0).filter(i->i%3!=0).forEach(System.out::println);
    }

    @Test
    public void sort() {
        //기본 역순 정렬
        Stream.of("a", "b", "c", "d", "e").sorted(Comparator.reverseOrder()).forEach(System.out::println);
        //대소문자 구분없이 역순 정렬
        Stream.of("a", "b", "c", "D", "E").sorted(String.CASE_INSENSITIVE_ORDER.reversed()).forEach(System.out::println);
        //문자열 길이 기준 정렬 길이 작은것  -> 큰것
        Stream.of("aaa", "bb", "c", "dddddd").sorted(Comparator.comparing(String::length)).forEach(System.out::println);
        //상기 메소드의 Comparator  : comparing()으로 정렬 기준 제공해줌

    }

    @Test
    public void map(){
        File[] fileArr = {new File("Ex1.java"), new File("Ex1.bak"), new File("Ex2.java"),
                new File("Ex2"), new File("Ex1.txt")};

        Stream<File> fileStream = Stream.of(fileArr);

        //map()을 통해 Stream<File>을 Stream<String>으로 변환
        Stream<String> fileNameStream = fileStream.map(File::getName); //모든 파일의 이름 출력
        fileNameStream.forEach(System.out::println);

        fileStream = Stream.of(fileArr);
        fileStream.map(File::getName)
                .filter(s->s.indexOf('.')!=-1) //확장자가 없는것 걸러줌
                .map(s->s.substring(s.indexOf('.')+1)) //확장자만 추출
                .map(String::toUpperCase) //전부 대문자로 변환
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void peekTestWithmap(){

        // peer() 은 스트림을 소모하지 않고 중간에 스트림 연산이 잘되고 있는지 디버깅의 역할을 해줌

        File[] fileArr = {new File("Ex1.java"), new File("Ex1.bak"), new File("Ex2.java"),
                new File("Ex2"), new File("Ex1.txt")};

        Stream<File> fileStream = Stream.of(fileArr);

        Stream<String> fileNameStream = fileStream.map(File::getName); //모든 파일의 이름 출력
        fileNameStream.forEach(System.out::println);

        fileStream = Stream.of(fileArr);
        fileStream.map(File::getName)
                .filter(s->s.indexOf('.')!=-1)
                .peek(s-> System.out.printf("filename=%s%n", s)) // 중간점검의 역할을 해줌 (스트림을 소모하지 않음)
                .map(s->s.substring(s.indexOf('.')+1))
                .peek(s-> System.out.printf("extenstion=%s%n", s))
                .map(String::toUpperCase)
                .distinct()
                .forEach(System.out::println);
    }

}
