package raiffeisen;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MostCommonWords {

    /**
     * Даны строки из файла.
     * В файле расположены слова через пробел.
     * Найти самые частые слова, которые встречаются в строках файла
     * @param lines - строки файла
     * @param topN - кол-во самых частых слов, которые нужно найти
     * @return
     */
    Map<String, Long> mostCommonWords(Collection<String> lines, int topN) {

        // Я все забыл - все операции группировок в стриме, операции compute и merge в мапе, и начал решать в тупую
        // вот такое было первое решение

        // Мне сказали, что в целом - правильно.
        Map<String, Long> commonWords = new HashMap<>();
        for (var line : lines) {
            Arrays.stream(line.split(" "))
                    .map(String::toLowerCase)
                    .forEach(word -> {
                        if (commonWords.containsKey(word)) {
                            // Спросили, а какая здесь проблема?
                            // Дали подсказку, что этой проблемы бы не было, если бы на стриме использовал
                            // операцию groupingBy
                            // (Я не ответил)

                            // Дело в выражении cnt + 1;
                            // cnt имеет тип Long
                            // чтобы сложить cnt + 1 сначала происходит unboxing: cnt.longValue()
                            // Затем складываются примитивы: long res = cnt.longValue() + 1
                            // Потом происходит boxing: Long.valueOf(res)
                            // И только потом вызывается метод put
                            var cnt = commonWords.get(word);
                            commonWords.put(word, cnt + 1);
                        } else {
                            commonWords.put(word, 1L);
                        }
                    });
        }

        // Дальше меня попросили переписать на groupingBy
        // Я немного потупил, так как забыл, что принимает groupingBy, но потом написал
        Map<String, Long> commonWords2 = lines.stream()
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        /**
         * Затем он спросил, а как можно улучшить эту часть.
         * Спросил сначала, за сколько она работает - O(n log n)
         * Затем спросил, а как можно улучшить.
         * Я сказал - наверное как-то можно использовать очередь с приоритетами (кучу- heap)
         * Про кучу и про стоимость ее операций я мало что помнил.
         * Неправильно дал оценку ее работы.
         *
         * Он сказал, что можно построить кучу за O(N) - а я сказал, она строится за N log N.
         * И вытащить topN элементов можно за topN log(N). А я сказал - за O(N)
         * Короче, все перепутал. Надо про кучи еще раз почитать.
         *
         * Потом он спросил, а как можно улучшить решение с кучей.
         * Ведь скорее всего topN << N (намного меньше общего кол-ва слов)
         *
         * Я сказал, что, наверное, использовать ограниченную очередь с приоритетами.
         * Что если ограничиенная очередь заполнена, и если мы добавляем элемент с более высоким приоритетом, то
         * элемент с самым низким приотитетом удаляется из кучи.
         *
         * Попросил оценить стоимость при таком подходе. Я снова все перепутал.
         */
        return commonWords2.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(topN)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
