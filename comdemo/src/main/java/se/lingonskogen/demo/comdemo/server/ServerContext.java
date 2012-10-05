package se.lingonskogen.demo.comdemo.server;

import java.net.DatagramPacket;
import java.util.HashSet;
import java.util.Set;

import se.lingonskogen.demo.comdemo.LoggingContext;

/**
 * Context used by the server threads. The methods <code>registerClient</code>
 * and <code>getClientCount</code> both have synchronized blocks to ensure
 * thread safety.
 */
public class ServerContext extends LoggingContext
{
   /**
    * Set of clients (ids) that has been in touch with the server.
    */
   private Set<String> clients = new HashSet<String>();

   public ServerContext(String logFile)
   {
      super(logFile);
   }

   public int getClientCount()
   {
      int count = 0;
      synchronized (this)
      {
         count = clients.size();
      }
      return count;
   }

   public void registerClient(DatagramPacket packet)
   {
      // extract the client id
      String msg = new String(packet.getData());
      String[] split = msg.split(":");
      String id = split[0];
      synchronized (this)
      {
         // add the client id if this is its first package
         if (!clients.contains(id))
         {
            clients.add(split[0]);
         }
      }
   }

}
