package se.lingonskogen.demo.comdemo.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import se.lingonskogen.demo.comdemo.SocketThread;

/**
 * Server Thread that receives messages from the clients. An acknowledgment
 * message will be sent back to the client if <code>ack</code> is true. This can
 * be interrupted by a call to <code>shutdown</code> from outside this thread.
 */
public class UniCastServerThread extends SocketThread
{
   private final ServerContext context;

   private final int port;

   private final boolean ack;

   private DatagramSocket socket = null;

   public UniCastServerThread(ServerContext context, int port, boolean ack)
   {
      this.context = context;
      this.port = port;
      this.ack = ack;
   }

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
         added = context.addReference(UniCastServerThread.class.getSimpleName());
         socket = new DatagramSocket(port);
         while (!socket.isClosed())
         {
            // allocate message
            byte[] data = new byte[128];

            // receive request
            System.err.println(UniCastServerThread.class.getSimpleName() + " receiving...");
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet); // Halt here until a new packet has been received.
            context.logReceive("UNI", packet);
            context.registerClient(packet);

            if (ack && !socket.isClosed())
            {
               // create message
               String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
               data = String.format("%d", msg.hashCode()).getBytes();

               // send response
               System.err.println(UniCastServerThread.class.getSimpleName() + " sending...");
               packet.setData(data, 0, data.length);
               socket.send(packet); // Do not halt here, just send.
               context.logSend("UNI", packet);
            }
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
            context.removeReference(UniCastServerThread.class.getSimpleName());
         }
         shutdown();
      }
   }

}
