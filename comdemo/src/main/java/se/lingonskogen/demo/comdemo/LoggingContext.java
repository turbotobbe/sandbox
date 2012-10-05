package se.lingonskogen.demo.comdemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Common context class used both by the server and client threads. It keeps
 * track of how many threads are using the context instance. This is done by a
 * thread safe counter. It is also used for logging messages sent and received.
 */
public class LoggingContext
{
   private static final String NEWLINE = System.getProperty("line.separator");

   private File file;

   /**
    * A thread safe counter used to count thread types.
    */
   private ThreadSafeCounter referenceCounter = new ThreadSafeCounter();

   public LoggingContext(String logFile)
   {
      file = new File(logFile);
      file.getParentFile().mkdirs();
   }

   public boolean addReference(String name)
   {
      try
      {
         String statistics = referenceCounter.increaseCount(name);
         log(statistics);
         return true;
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      return false;
   }

   public boolean removeReference(String name)
   {
      try
      {
         String statistics = referenceCounter.decreaseCount(name);
         log(statistics);
         return true;
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      return false;
   }

   /**
    * Since this context is shared between multiple threads, it must be thread
    * safe. One way of doing that is to synchronize the method that uses the
    * actual shared resource. i.e. the log file. This will make it easy to
    * change the log functionality without the need to consider if it is thread
    * safe or not. Note that it is not needed if the actual shared resource is
    * thread safe by design.
    */
   public synchronized void log(String msg)
   {
      FileWriter writer = null;
      try
      {
         writer = new FileWriter(file, true);
         writer.write(String.format("[%d] %s%s", System.currentTimeMillis(), msg, NEWLINE));
         writer.close();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      finally
      {
         if (writer != null)
         {
            try
            {
               writer.close();
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
         }
      }
   }

   /**
    * No need to be synchronized since the method just delegates without
    * depending on the state of the class instance.
    */
   public void logReceive(String cast, DatagramPacket packet)
   {
      String entry = makeEntry(cast, "RECV", packet);
      log(entry);
   }

   /**
    * No need to be synchronized since the method just delegates without
    * depending on the state of the class instance.
    */
   public void logSend(String cast, DatagramPacket packet)
   {
      String entry = makeEntry(cast, "SEND", packet);
      log(entry);
   }

   /**
    * No need to be synchronized since the method just delegates without
    * depending on the state of the class instance.
    */
   private String makeEntry(String cast, String type, DatagramPacket packet)
   {
      StringBuilder sb = new StringBuilder();

      sb.append("[" + cast + "] [" + type + "]");

      sb.append(" [address] ");
      sb.append(packet.getAddress().getHostAddress());
      sb.append(":");
      sb.append(packet.getPort());

      sb.append(" [msg] ");
      sb.append(new String(packet.getData(), 0, packet.getLength()));
      return sb.toString();
   }

}
