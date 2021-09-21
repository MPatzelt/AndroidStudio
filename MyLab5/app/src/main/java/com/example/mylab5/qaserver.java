import java.util.*;
import java.net.*;
import java.io.*;

public class qaserver{
    public static final String USAGE = "USAGE: java qaserver <PORT>";
    public static void main(String[] args)  {
        // "database" of the questions (in abbreviated form)
        String[] questions = {"Color",   // Favorite color
         "Pet",            // Name of first pet
         "Birthday",       // Date of birth
         "Maiden name",    // Mother's maiden name
        "Best friend",  // Best friend's name
        "Video Game"};   // Favorite video game

        // "database" of answers to corresponding questions
        String[] answers = {"Purple",
                "Tigger",
                "July 12, 1999",
                "Lew",
                "Brian",
                "Borderlands 2"};

        ServerSocket serverSocket;
        Socket socket;
        String inputLine;
        String outputLine;
        boolean finished;
        int index;
        int port;

        try     {
            // use the port indicated on command-line
            port = Integer.parseInt(args[0]);
            // create a server socket
            serverSocket = new ServerSocket(port);
            // Listen for a connection request from a client
            socket = serverSocket.accept();
            // Establish the input and output streams on the socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(new InputStreamReader(socket.getInputStream()));
            // Keep answering questions until the client wants to quit
            finished = false;
            while(!finished)
            {   // get a string from the client
                inputLine = in.nextLine();
                // If it's "quit," we're done
                if(inputLine.equalsIgnoreCase("quit"))
                    finished = true;
                else {       // Produce the appropriate response to the client's question
                    index = find_question(inputLine, questions);
                    if(index == -1)
                        outputLine = "Error: unknown question";
                    else
                        outputLine = answers[index];
                    // send the response to the client
                    out.println(outputLine);
                }
            }
            out.close();
            in.close();
            socket.close();
            serverSocket.close();
        }
        catch(IOException e)  // socket problems
        {
            System.out.println(e);
        }
        catch(NumberFormatException e)  // port not a number (int)
        {
            System.out.println("First argument must be the port number.");
            System.out.println(USAGE);
        }
        catch(ArrayIndexOutOfBoundsException e)  // no port # given
        {
            System.out.println("Need to supply the port number.");
            System.out.println(USAGE);
        }
    } // end main
public static int find_question(String q, String[] the_qs){
        // Find the index of the given question q in the "database"
        // (array) of questions the_qs.  If it does not exist,
        // return -1
        int retVal;
        boolean found;
        retVal = 0;
        found = false;

        while(!found && retVal < the_qs.length) {
            if (the_qs[retVal].equalsIgnoreCase(q))
                found = true;
            else retVal++;
        }
        if(!found)retVal = -1;
        return retVal;
    } // end find_question
// ///////////////////////////////////////////////////////////////
}  //end qaserver