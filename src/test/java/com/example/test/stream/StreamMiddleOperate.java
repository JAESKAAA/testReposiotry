package com.example.test.stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.el.CompositeELResolver;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    @Test
    public void flatMapTest(){
        //map으로 배열을 스트림화 할때 Stream<Stream<String>> 과 같이 스트림 안에 스트림화 되는 경우가 있음
        //이때 flatMap을 사용해주면 Stream<String> 형태로 적절히 변환 할 수 있음

        Stream<String[]> strArrStrm = Stream.of(new String[] {"abc","def","jkl"}, new String[] {"ABC","DEF","JKL"});

        //map사용시
        //Stream<Stream<String>> streamStream = strArrStrm.map(Arrays::stream);

        //flatMap사용시
        Stream<String> strStm = strArrStrm.flatMap(Arrays::stream);

        strStm.map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        //문장에서 각 단어를 String[]로 받아 스트림의 요소로 만들어 주기
        String[] lineArr = { "Believe or not It is true", "Do or not There is no try"};

        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" +")))
                .map(String::toLowerCase)
                .distinct()
                //.sorted()
                .forEach(System.out::println);

    }

    @Test
    public void optionalPra(){
        //Optional<String> opt = null -> 가능은 하지만 바람직하지 않음
        Optional<String> opt = Optional.empty();

        System.out.println("opt= "+opt);
        //System.out.println("opt == "+ opt.get()); // 들어있는것이 널이라 예외발생

        opt.orElse("널임");
        System.out.println("opt는 ? "+opt);

        opt.orElseGet(()->"널이야");
        System.out.println("람다식 쓴 opt는? "+opt);

    }

    @Test
    public void streamAndOptional(){
        Optional<String> optStr = Optional.of("abcde");
        Optional<Integer> optInt = optStr.map(String::length); // 상기배열을 정수타입의 5로 변경 / 람다: s->s.length();

        int result1 = Optional.of("123").filter(s -> s.length() > 0).map(s -> Integer.parseInt(s)).get();
        int result2 = Optional.of("").filter(x -> x.length() > 0).map(x -> Integer.parseInt(x)).orElse(-1);
        System.out.println(result1);
        System.out.println(result2);

        //문자를 숫자로바꿔서 문자열 길이가 3이상이면 출력
        Optional.of(1234).map(String::valueOf).filter(x->x.length() > 2).ifPresent(x -> System.out.printf("result => %s",x));
    }

    @Test
    public void forEachTest(){
        //직렬스트림일때는 값이 동일함
        IntStream.range(1,10).sequential().forEach(System.out::print);
        System.out.println();
        IntStream.range(1,10).sequential().forEachOrdered(System.out::print);
        System.out.println();
        //병렬일때는 forEachOrdered를 사용해야 순서를 보장해줌
        IntStream.range(1,11).parallel().forEach(System.out::print);
        System.out.println();
        IntStream.range(1,11).parallel().forEachOrdered(System.out::print);

        String[] strArr = {"치킨", "피자", "떡볶이", "순대", "만두", "짬뽕"};
        Stream.of(strArr).forEach(System.out::println); // 순서를 유지하지않음
        Stream.of(strArr).forEachOrdered(System.out::println); // 순서를 유지 대신, 성능이 저하될 수 있음

    }

    @Test
    public void strPrac(){
        Stream<String> stringStream = Stream.of("1", "2", "3", "4", "5", "6", "7");
        //문자 배열을 long배열로
        Long[] longArr = stringStream.map(Long::parseLong).toArray(Long[]::new);

        //배열을 스트림으로 바꿀땐 Arrays
        //다시 문자 배열로
        String[] stringArr = Arrays.stream(longArr).map(String::valueOf).toArray(String[]::new);

        List<String> stringList = Arrays.asList(stringArr);
        stringList.stream().map(Long::parseLong).distinct().forEach(System.out::println);



    }


    @Test
    public void matchesMethod(){
        String[] strArr = {"치킨", "피자", "떡볶이", "순대", "만두", "짬뽕"};

        boolean noEmpty = Stream.of(strArr).noneMatch(s -> s.length() == 2); //조건에 하나도 일치하지 않으면 true
        //Stream.of(strArr).filter(s->s.charAt(0)."치").findFirst(); //여기서부터 다시

    }

}
