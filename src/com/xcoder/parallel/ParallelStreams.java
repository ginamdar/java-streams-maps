package com.xcoder.parallel;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author José
 */
public class ParallelStreams {

    public static void main(String[] args) {

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
        
//        Stream.iterate("+", s -> s + "+")
//                .limit(6)
//                .forEach(System.out::println);

//        List<String> strings = new ArrayList<>();
//        List<String> strings = new CopyOnWriteArrayList<>();
          List<String> strings = Stream.iterate("+", s -> s + "+")
                .parallel()
                .limit(2000)
//                .peek(s -> System.out.println(s + " processed in the thread:" + Thread.currentThread().getName()))
//                .forEach(System.out::println);
//                .forEach(s -> strings.add(s));
                    .collect(Collectors.toList());
        System.out.println("# " + strings.size());
    }
}
