package raiffeisen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Реализовать множество с операциями вставки и удаления, которые работают за O(1)
 * И с дополнительной операцией random() - которая возвращает случайный элемент множества так,
 * чтобы возврат элементов был равновероятным. Тоже работает за O(1)
 * @param <E>
 */
public class RandomSet<E> {

    /**
     * Я начал рассуждать с рандома:
     * - Можно использовать например массив (ArrayList) в качестве storage для элементов,
     * и для случайного выбора просто случайно выбрать индекс от 0 до arr.length - 1 с помощью встроенной функци
     * ThreadLocalRandom.nextInt(int bound, int origin)
     * <p>
     * Потом понял, что массив не подойдет для удаления за O(1)
     * Также не подойдет и LinkedList
     * А обычный Set не дает возможность по иднексу обращаться к элементу - то есть random с ним не реализовать
     * <p>
     * Подумал, что можно использовать две коллекции:
     * - ArrayList<E>
     * - Map<E, Integer> - отображение элементов из ArrayList в индексы
     * Это поможет при удалении - чтобы удаление было за O(1)
     * можно получить индекс удаляемого элемента из мапы, свапнуть этот элемент с последним,
     * Обновить мапу (потому что последний элемент теперь встал на место удаляемого),
     * и удалить из листа последний элемент.
     * Тогда не нужно будет сдвигать массив и операция будет за O(1)
     *
     *
     */

    private final ArrayList<E> list = new ArrayList<>();
    private final Map<E, Integer> eToIdx = new HashMap<>();

    /**
     * Работает за O(1)
     */
    public boolean add(E elem) {
        if (!eToIdx.containsKey(elem)) {
            eToIdx.put(elem, list.size());
            list.add(elem);
        }
        return false;
    }

    /**
     * Работает за O(1)
     */
    public boolean remove(E elem) {
        if (eToIdx.containsKey(elem)) {
            var idx = eToIdx.remove(elem);
            var tmp = list.get(list.size() - 1);
            list.set(idx, tmp);
            list.remove(list.size() - 1);
            eToIdx.put(tmp, idx);
        }
        return false;
    }

    /**
     *  Работает за O(1)
     */
    public E random() {
        var idx = ThreadLocalRandom.current().nextInt(0, list.size());
        return list.get(idx);
    }
}
