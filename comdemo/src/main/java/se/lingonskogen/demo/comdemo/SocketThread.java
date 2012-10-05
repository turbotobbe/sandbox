package se.lingonskogen.demo.comdemo;

/**
 * Abstract class for both the server and client thread classes. The abstract
 * method <code>shutdown</code> is a hook that will be called when the
 * application is interrupted so that all sockets and threads can close down in
 * a controlled matter.
 */
public abstract class SocketThread extends Thread
{
   /**
    * Hook that is called when application is interrupted.
    */
   public abstract void shutdown();
}
