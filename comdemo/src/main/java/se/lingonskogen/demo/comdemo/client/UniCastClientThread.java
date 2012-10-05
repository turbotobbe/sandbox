package se.lingonskogen.demo.comdemo.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import se.lingonskogen.demo.comdemo.SocketThread;

/**
 * Client Thread for sending messages to the server. If <code>ack</code> is
 * <code>true</code>, the thread expects to get an acknowledgment message for
 * every sent message. This thread will try to send all data in /etc/msg.txt
 * line by line as long as the socket is open. This can be interrupted by a call
 * to <code>shutdown</code> from outside this thread.
 */
public class UniCastClientThread extends SocketThread
{
   private final ClientContext context;

   private final InetAddress address;
   private final int port;

   private final String id;
   private final boolean ack;

   private DatagramSocket socket;

   public UniCastClientThread(ClientContext context, String host, int port, String id, boolean ack)
         throws UnknownHostException
   {
      this.context = context;
      this.address = InetAddress.getByName(host);
      this.port = port;
      this.id = id;
      this.ack = ack;
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
         added = context.addReference(UniCastClientThread.class.getSimpleName());
         socket = new DatagramSocket();
         for (int i = 0; i < context.getMessageCount() && !socket.isClosed(); i++)
         {
            // create a message
            String msg = String.format("%s:%s", id, context.getMessage(i));
            byte[] data = msg.getBytes();

            // send request
            System.err.println(UniCastClientThread.class.getSimpleName() + " sending...");
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet); // Do not halt here, just send.
            context.logSend("UNI", packet);

            if (ack && !socket.isClosed())
            {
               // allocate message
               data = new byte[128];

               // receive response
               System.err.println(UniCastClientThread.class.getSimpleName() + " receiving...");
               packet = new DatagramPacket(data, data.length);
               socket.receive(packet); // Halt here until a new packet has been received.
               context.logReceive("UNI", packet);
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
            context.removeReference(UniCastClientThread.class.getSimpleName());
         }
         shutdown();
      }
   }
}
