package se.lingonskogen.demo.comdemo.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.lingonskogen.demo.comdemo.LoggingContext;

/**
 * This shared context does not need to be thread safe, since the only state
 * changing method is invoked only ones (by the ClientApp). However if multiple
 * threads would call <code>loadMessage</code> it would probably be a good idea
 * to use a lock on the messages array. Multiple client threads might use this
 * shared context since all they to in pulling messages from a non changing
 * list.
 */
public class ClientContext extends LoggingContext
{
   /**
    * Array of messages to send to the server.
    */
   private String[] messages;

   public ClientContext(String logFile)
   {
      super(logFile);
   }

   /**
    * Read the messages from a file and cache them in a list.
    */
   public void loadMessages(String msgFile) throws IOException
   {
      // use a method local list to build the list of messages. this way the
      // list is thread safe.
      List<String> list = new ArrayList<String>();

      BufferedReader reader = new BufferedReader(new FileReader(msgFile));
      String line = reader.readLine();
      while (line != null)
      {
         line = line.trim();
         if (line.length() > 0)
         {
            list.add(line);
         }
         line = reader.readLine();
      }
      reader.close();

      // Set the message array. If multiple threads run this method the
      // resulting array will be created from the thread that completed last.
      // this is NOT thread safe, but in this application only the ClientApp
      // calls this method. i.e. nemas problems.
      messages = list.toArray(new String[list.size()]);
   }

   public int getMessageCount()
   {
      return messages.length;
   }

   public String getMessage(int index)
   {
      return messages[index];
   }

}
