import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PetrolStation{
    Double stock;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public PetrolStation(Double stock) {
        this.stock = stock;
    }

    public void doTank(Double amount, int car) throws InterruptedException {
        if (getStock() > amount) {
            System.out.println("Машина " + car + " пытается заправиться");
            if (lock.writeLock().tryLock()) {
                try {
                    Thread.sleep((long) ((3 + (Math.random() + 1) * 7) * 10));
                    this.stock -= amount;
                    System.out.println("Машина " + car + " заправлена " + amount + " литрами топлива");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.writeLock().unlock();
                }
            } else {
                System.out.println("Машине " + car + " Не удалось заправиться");
            }
        } else {
            System.out.println("Не хватает топлива");
        }
    }

    public Double getStock() {
        try {
            lock.readLock().lock();
            return stock;
        } finally {
            lock.readLock().unlock();
        }
    }
}
