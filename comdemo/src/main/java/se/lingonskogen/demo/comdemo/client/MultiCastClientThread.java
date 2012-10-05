package se.lingonskogen.demo.comdemo.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import se.lingonskogen.demo.comdemo.LoggingContext;
import se.lingonskogen.demo.comdemo.SocketThread;

/**
 * Client Thread for receiving messages from the server. This thread will halt
 * and wait for messages from the server until the socket is closed. This
 * happens when <code>shutdown</code> is called from outside this thread.
 */
public class MultiCastClientThread extends SocketThread
{
   private final LoggingContext context;

   private final InetAddress address;
   private final int port;

   private MulticastSocket socket;

   public MultiCastClientThread(LoggingContext context, String group, int port)
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
         try
         {
            socket.leaveGroup(address);
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
         socket.close();
      }
   }

   @Override
   public void run()
   {
      boolean added = false;
      try
      {
         added = context.addReference(MultiCastClientThread.class.getSimpleName());
         socket = new MulticastSocket(port);
         socket.joinGroup(address);
         while (!socket.isClosed())
         {
            // allocate message
            byte[] data = new byte[128];

            // receive message
            System.err.println(MultiCastClientThread.class.getSimpleName() + " receiving...");
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet); // Halt here until a new packet has been received.
            context.logReceive("MUL", packet);
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
            context.removeReference(MultiCastClientThread.class.getSimpleName());
         }
         shutdown();
      }
   }

}
