package se.lingonskogen.demo.comdemo.client;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import se.lingonskogen.demo.comdemo.SocketThreadsCloser;

public class ClientApp
{
   public static void main(String[] args) throws UnknownHostException, IOException
   {
      // check arguments
      if (args.length != 6)
      {
         System.err.println("Usage: DEMO_HOME/bin/client <multi-group> <multi-port> <uni-port> <uni-host> <ack> <id>");
         System.err.println("<multi-group> the multicast group (ip) used to receive data");
         System.err.println("<multi-port> the multicast port used to receive data");
         System.err.println("<uni-port> the unicast port used to send (and receive) data.");
         System.err.println("<uni-host> the unicast host (ip) used to send (and receive) data.");
         System.err.println("<ack> flag to indicate if the client should receive ack from server on unicast.");
         System.err.println("<id> the identifier for this client. i.e. tobbe");
         return;
      }
      
      // parse arguments
      String multiGroup = args[0];
      int multiPort = Integer.parseInt(args[1]);
      int uniPort = Integer.parseInt(args[2]);
      String uniHost = args[3];
      boolean ack = Boolean.parseBoolean(args[4]);
      String id = args[5];
      
      // print startup info
      System.out.println("Starting client " + id + " ...");
      System.out.println("Multicast on " + multiGroup + ":" + multiPort);
      System.out.println("Unicast on " + uniHost + ":" + uniPort);
      System.out.println("-----");

      // make log file name
      String logDir = System.getProperty("log.dir");
      String logFile = logDir + File.separator + "client." + multiGroup + "." + multiPort + "." + uniHost + "." + uniPort + ".log";

      // create shared context
      ClientContext context = new ClientContext(logFile);

      // load messages
      String msgFile = System.getProperty("msg.file");
      context.loadMessages(msgFile);
      
      // Create the server threads with shared context.
      UniCastClientThread uniThread = new UniCastClientThread(context, uniHost, uniPort, id, ack);
      MultiCastClientThread multiThread = new MultiCastClientThread(context, multiGroup, multiPort);
      // We could create more multicast threads to stress the thread safe counter functionality
      
      // start working threads
      uniThread.start();
      multiThread.start();

      // register socket thread closer for all started socket threads 
      SocketThreadsCloser closer = new SocketThreadsCloser(uniThread, multiThread);
      Runtime.getRuntime().addShutdownHook(closer);
   }
}
