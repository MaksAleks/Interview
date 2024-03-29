# Задачи из интервью в Raiffeisen Bank


1) [MostCommonWords.java](./src/main/java/raiffeisen/MostCommonWords.java)

```java
class MostCommonWords {
    /**
     * Даны строки из файла.
     * В файле расположены слова через пробел.
     * Найти самые частые слова, которые встречаются в строках файла
     * @param lines - строки файла
     * @param topN - кол-во самых частых слов, которые нужно найти
     * @return
     */
    Map<String, Long> mostCommonWords(Collection<String> lines, int topN) {
        // Свое решение и вопросы интеврьюера привел в оставил в файле MostCommonWords.java
    }
}
```

2) [SpinLock.java](./src/main/java/raiffeisen/SpinLock.java)

```java
/**
 * Попросили реализовать спин лок
 * Я сделал обычный TaS (Test and Set spin lock) с одним атомиком
 * 
 * Свое решение и вопросы оставил в классе SpinLock.java
 */
public class SpinLock {
    
    public void lock() {
        
    }
    
    public void unlock() {
        
    }
}
```

3) [MostProfit.java](./src/main/java/raiffeisen/MostProfit.java)

```java
public class MostProfit {

    /**
     * Даны стоимости акций за какой-то период.
     * stockLevels[i] представляет стоимость акций на конец дня i.
     * Нужно вычислить максимально возможную прибыль.
     * 
     * Эту задачу мы просто обсудили, от меня не потребовали, чтобы я написал решение.
     * 
     * Обсуждение написал в MostProfit.java
     * 
     * @param stockLevels
     * @return
     */
    int mostProfit(int[] stockLevels) {
        
    }
}

```

4) [RandomSet](./src/main/java/raiffeisen/RandomSet.java)

```java
/**
 * Реализовать множество с операциями вставки и удаления, которые работают за O(1)
 * И с дополнительной операцией random() - которая возвращает случайный элемент множества так,
 * чтобы возврат элементов был равновероятным. Тоже работает за O(1)
 * 
 * Все расписал в RandomSet.java
 */
public class RandomSet<E> {
    
    /**
     * Работает за O(1)
     */
    public boolean add(E elem) {
    }

    /**
     * Работает за O(1)
     */
    public boolean remove(E elem) {
    }

    /**
     *  Работает за O(1)
     */
    public E random() {
    }
}
```

5) После он задал вопросы про структуру кучи.
И спросил: вот в куче есть обязательно два метода:
- добавить элемент в кучу
- доставить элемент из кучи

Спросил, почему обязательно хотя бы один метод должен работать за O(logN)
Почему нельзя построит реализацию, которая бы позволяля реализовать оба метода за O(1)

Сказал что-то про ATD кучи, и что бинарные кучи - это подмножество ATD куч, и что в книге по алгоритмам у кормена в разделе про кучи есть задача, решение которой связано с ответом на этот вопрос.

6) Потом спросил по быструю сортировку.
Спросил, почему она именно быстрая. За счет чего?
Я сделал предположение, что из-за того, что там нет этапа слияния, как в спортировке слиянием, например.

Он сказал, что дело в другом. Сказал, что в среднем ассимптотика алгоритма - O(N log N), но он может деградировать до O(N^2). Но вот вероятность этой деградации очень мала. И что даже если мы постоянно будет выбирать плохой pivot, мы все равно будет получать в ассимптотике O(N log N). И чтобы получить O(N^2). Сказал прочить у Кормена главу про quick sort и его рассуждения про скорость ее работы и как получается ассимптотика O(N log N)