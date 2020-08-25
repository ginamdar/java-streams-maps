package com.xcoder.collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainUsingCollectors {
    public static void main(String[] args) throws IOException {
        System.out.println("CWD: " + System.getProperty("user.dir"));
        Stream<String> shakespeare = Files.lines(Paths.get("maps-and-flatmap/files/words.shakespeare.txt"));
        Stream<String> opsd = Files.lines(Paths.get("maps-and-flatmap/files/ospd.txt"));

        Set<String> scrabbleWords = opsd.map(word -> word.toLowerCase())
                .collect(Collectors.toSet());

        Set<String> shakespeareWords = shakespeare.map(word -> word.toLowerCase())
                .collect(Collectors.toSet());

        System.out.println("# of words of Shakespeare:" + shakespeareWords.size());
        System.out.println("# of words of Scrabble:" + scrabbleWords.size());

        final int [] scrabbleENScore = {
                //  a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,  q, r, s, t, u, v, w, x, y,  z
                1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
        };

        Function<String, Integer> score = word -> word.chars()
                .map(letter -> scrabbleENScore[letter - 'a']).sum();

        Map<Integer, List<String>> histogramByWords = shakespeareWords.stream()
//                .filter(word -> scrabbleWords.contains(word))
                .filter(scrabbleWords::contains)
                .collect(
                        Collectors.groupingBy(
                                score
                        )
                );
        System.out.println("# histogramByWords = " + histogramByWords.size());

        histogramByWords.entrySet()     // Set<Map.Entry<Integer, List<String>>>
                .stream()
                .sorted(Comparator.comparing(entry -> - entry.getKey()))
                .limit(3)
                .forEach(entry -> System.out.println(entry.getKey() + " -- " + entry.getValue()));
    }
}

