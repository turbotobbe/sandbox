package se.lingonskogen.demo.comdemo;

/**
 * Utility class for shutting down multiple socket threads. The socket threads
 * are closed down by closing their sockets. This will unhalt all halting
 * operation. i.e. socket.receive() and the threads cat exit nicely.
 */
public class SocketThreadsCloser extends Thread
{
   private final SocketThread[] threads;

   public SocketThreadsCloser(SocketThread... threads)
   {
      this.threads = threads;
   }

   @Override
   public void run()
   {
      for (int i = 0; i < threads.length; i++)
      {
         SocketThread thread = threads[i];
         System.err.println("Shutting down thread...");
         thread.shutdown();
         try
         {
            // wait 1 second for the thread to shut down. we don't have all day.
            thread.join(1000);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
   }
}
