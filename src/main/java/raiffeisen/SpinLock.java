package raiffeisen;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Попросили реализовать спин лок
 * Я сделал обычный TaS (Test and Set spin lock) с одним атомиком:
 */
public class SpinLock {

    private final AtomicBoolean locked = new AtomicBoolean(false);

    public void lock() {
        /**
         * Меня спросили, а в чем тут проблема.
         * Я сказал, что не соблюдается гарантия прогресса для каждого отдельного потока.
         * Чтобы ее достичь, можно использовать очередь -  с использованием TicketLock (из лекций Липовского)
         *
         * Но он сказал, что он спрашивает про другу проблему.
         * Про эту проблему рассказывал автор книги The Art Of Multiprocessor Programming, когда рассказывал,
         * как написать хороший spin lock
         *
         * Дальше интевьюер рассказал, что проблема в кэшах процессора и в протоколе когерентности.
         * В таком подходе, когда постоянно перезаписывается один и тот же атомик, постоянно приходится
         * сбрасывать и перечитывать целую кэш линию. (Надо об этом подробнее еще у Липовского посмотреть).
         *
         * Исправить проблему можно используя, например, реализацию неблокирующей очереди:
         * ConcurrentLinkedDeque
         */
        while (locked.compareAndExchange(false, true)) {
            Thread.onSpinWait();
        }

        //Acquired
    }

    public void unlock() {
        locked.set(false);
    }

    static class TicketLock {
        private final AtomicLong owner = new AtomicLong(Long.MIN_VALUE);
        private final AtomicLong nextTicket = new AtomicLong(Long.MIN_VALUE);

        public void lock() {
            long ticket = nextTicket.getAndIncrement();
            while (owner.get() != ticket) {
                Thread.onSpinWait();
            }
        }

        public void unlock() {
            nextTicket.incrementAndGet();
        }

    }
}
