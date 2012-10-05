package se.lingonskogen.demo.comdemo;

import java.util.HashMap;

/**
 * Counter that counts number of references to a name. This class uses java
 * signaling to communicate with other threads that they may use the instance.
 * We could for example start a thread that waits for the counter to be updated
 * and processes the updated values some how. Note that the methods
 * <code>increaseCount</code> and <code>decreaseCount</code> are not
 * synchronized. Instead the <code>lock</code> and <code>unlock</code> methods
 * are synchronized. This will ensure threads that they alone manipulates the
 * counter between lock and unlock.
 */
public class ThreadSafeCounter
{
   private boolean locked = false;

   private Thread thread = null;

   private HashMap<String, Integer> counter = new HashMap<String, Integer>();

   /**
    * This method waits until this object is unlocked, and then locks it for
    * itself.
    */
   public synchronized void lock() throws InterruptedException
   {
      while (locked)
      {
         wait();
      }
      locked = true;
      thread = Thread.currentThread();
   }

   /**
    * This method unlocks this object and notifies other threads that the object
    * is free to use.
    */
   public synchronized void unlock()
   {
      if (thread != Thread.currentThread())
      {
         throw new IllegalMonitorStateException(
               "Unlocking thread must be the same as the locking thread.");
      }
      thread = null;
      locked = false;
      notify();
   }

   /**
    * Increases the count of name by 1.
    */
   public String increaseCount(String name) throws InterruptedException
   {
      String statistics = null;
      lock();
      if (!counter.containsKey(name))
      {
         counter.put(name, 0);
      }
      int count = counter.get(name) + 1;
      counter.put(name, count);
      statistics = counter.toString();
      unlock();
      return statistics;
   }

   /**
    * Decreases the count of name by 1.
    */
   public String decreaseCount(String name) throws InterruptedException
   {
      String statistics = null;
      lock();
      if (!counter.containsKey(name))
      {
         counter.put(name, 0);
      }
      int count = counter.get(name) - 1;
      counter.put(name, count);
      statistics = counter.toString();
      unlock();
      return statistics;
   }
}
