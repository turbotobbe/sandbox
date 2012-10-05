package se.lingonskogen.demo.comdemo.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import se.lingonskogen.demo.comdemo.SocketThread;

/**
 * Server Thread that broadcasts the number of known clients (unique ids). It
 * only broadcast when the number of clients has changed. This can be
 * interrupted by a call to <code>shutdown</code> from outside this thread.
 */
public class MultiCastServerThread extends SocketThread
{
   private final ServerContext context;

   private final InetAddress address;
   private final int port;

   private DatagramSocket socket;

   public MultiCastServerThread(ServerContext context, String group, int port)
         throws UnknownHostException
   {
      this.context = context;
      this.address = InetAddress.getByName(group);
      this.port = port;
   }

   @Override
   public void shutdown()
   {
      if (socket != null && !socket.isClosed())
      {
         socket.close();
      }
   }

   @Override
   public void run()
   {
      boolean added = false;
      try
      {
         // update statistics about threads that uses the context
         added = context.addReference(MultiCastServerThread.class.getSimpleName());

         socket = new DatagramSocket();
         int knownClients = 0;
         while (!socket.isClosed())
         {
            int count = context.getClientCount();
            if (count != knownClients)
            {
               // update last known count
               knownClients = count;

               // create message
               String msg = String.format("%d", count);
               byte[] data = msg.getBytes();

               // send message
               System.err.println(MultiCastServerThread.class.getSimpleName() + " sending...");
               DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
               socket.send(packet); // do not halt here, just send.
               context.logSend("MUL", packet);
            }

            // This loop is very buzy, interval it to be nice.
            // Thread.sleep(...);
         }
      }
      catch (IOException e)
      {
         // this happens if we close the socket from outside.
         // i.e. by a shutdown hook
         System.err.println("Unable to use socket: " + e.getMessage());
      }
      finally
      {
         if (added)
         {
            // update statistics about threads that uses the context
            context.removeReference(MultiCastServerThread.class.getSimpleName());
         }
         shutdown();
      }
   }

}
