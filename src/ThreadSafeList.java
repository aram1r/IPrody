import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeList <E> {
    private final List<E> list;
    private final Lock lock;

    public ThreadSafeList(Class<E> clazz) {
        list = new ArrayList<E>();
        lock = new ReentrantLock();
    }

    public void add(E e) {
        try  {
            lock.lock();
            list.add(e);
        } finally {
            lock.unlock();
        }
    }

    public E get(int index) {
        try  {
            lock.lock();
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }

    public void remove(int index) {
        try {
            lock.lock();
            list.remove(index);
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        try {
            lock.lock();
            return list.size();
        } finally {
            lock.unlock();
        }
    }
}
