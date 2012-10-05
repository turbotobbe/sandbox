package se.lingonskogen.demo.comdemo.server;

import java.io.File;
import java.net.UnknownHostException;

import se.lingonskogen.demo.comdemo.SocketThreadsCloser;

public class ServerApp
{
   public static void main(String[] args) throws UnknownHostException
   {
      // check arguments
      if (args.length != 4)
      {
         System.err.println("Usage: DEMO_HOME/bin/server <multi-group> <multi-port> <uni-port> <ack>");
         System.err.println("<multi-group> the multicast group (ip) used to send data");
         System.err.println("<multi-port> the multicast port used to send data");
         System.err.println("<uni-port> the unicast port used to receive data.");
         System.err.println("<ack> flag to indicate if the server should send ack to client on unicast.");
         return;
      }
      
      // parse arguments
      String multiGroup = args[0];
      int multiPort = Integer.parseInt(args[1]);
      int uniPort = Integer.parseInt(args[2]);
      boolean ack = Boolean.parseBoolean(args[3]);
      
      // print startup info
      System.out.println("Starting server...");
      System.out.println("Multicast on " + multiGroup + ":" + multiPort);
      System.out.println("Unicast on " + uniPort);
      System.out.println("-----");

      // make log file name
      String logDir = System.getProperty("log.dir");
      String logFile = logDir + File.separator + "server." + multiGroup + "." + multiPort + "." + uniPort + ".log";

      // create shared context
      ServerContext context = new ServerContext(logFile);
      
      // Create the server threads with shared context.
      final UniCastServerThread uniThread = new UniCastServerThread(context, uniPort, ack);
      final MultiCastServerThread multiThread = new MultiCastServerThread(context, multiGroup, multiPort);
      
      // start working threads
      multiThread.start();
      uniThread.start();
      
      // register socket thread closer for all started socket threads 
      SocketThreadsCloser closer = new SocketThreadsCloser(uniThread, multiThread);
      Runtime.getRuntime().addShutdownHook(closer);
   }
}
