 import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.math.*;

import static java.lang.Math.abs;

public class BDserver {
    public static final String USAGE = "USAGE: java BDserver <PORT>";
    public static void main(String[] args)  {
        String[] operations = {"Add",
                "Sub",
                "Mult",
                "Shuffle"};

        ServerSocket serverSocket;
        Socket socket;
        String operation;
        String operand1;
        String operand2;
        String outputLine;
        int index;
        int port;
        boolean isBD1;
        boolean isBD2;

        try     {
            // use the port indicated on command-line
            port = Integer.parseInt(args[0]);
            // create a server socket
            serverSocket = new ServerSocket(port);
            // Listen for a connection request from a client

            // Keep answering questions until the client wants to quit
            while(true)
            {   // get a string from the client
                socket = serverSocket.accept();
                // Establish the input and output streams on the socket
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(new InputStreamReader(socket.getInputStream()));
                operation = in.nextLine();
                operand1 = in.nextLine();
                operand2 = in.nextLine();

                System.out.println(operation);
                System.out.println(operand1);
                System.out.println(operand2);

                index = find_operation(operation, operations);
                if(index == -1)
                    outputLine = "Error: unknown operation";

                else {
                    isBD1 = isBigDecimalNumber(operand1);
                    isBD2 = isBigDecimalNumber(operand2);
                    //error testing for whether input values are numbers
                    if(!isBD1 && !isBD2) {
                        outputLine = "Error: both values are not numbers";
                    }
                    else if(!isBD1) {
                        outputLine = "Error: the first value is not a number";
                    }
                    else if (!isBD2){
                        outputLine = "Error: the second value is not a number";
                    }
                    else if (index == 0){
                        //add
                        BigDecimal i1 = new BigDecimal(operand1);
                        BigDecimal i2 = new BigDecimal(operand2);
                        BigDecimal result;

                        result = i1.add(i2);
                        outputLine = "The sum is " + result.toPlainString();
                    }
                    else if (index == 1) {
                        //subtract
                        BigDecimal i1 = new BigDecimal(operand1);
                        BigDecimal i2 = new BigDecimal(operand2);
                        BigDecimal result;

                        result = i1.subtract(i2);
                        outputLine = "The difference is " + result.toPlainString();
                    }
                    else if (index == 2) {
                        //multiply
                        BigDecimal i1 = new BigDecimal(operand1);
                        BigDecimal i2 = new BigDecimal(operand2);
                        BigDecimal result;

                        result = i1.multiply(i2);
                        outputLine = "The product is " + result.toPlainString();
                    }
                    else {
                        //shuffle
                        String shuffleResult = shuffle(operand1, operand2);
                        outputLine = "These numbers shuffled together is " + shuffleResult;
                    }
                }

                out.println(outputLine);

                out.close();
                in.close();
                socket.close();

            }

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

    public static boolean isBigDecimalNumber(String num) {
        //method that checks if values entered are valid or not
        boolean isBD = false;
        BigDecimal BDtest;

        try{
            //if no error occurs assigning, then value is valid
            BDtest = new BigDecimal(num);
            isBD = true;
        }
        catch(NumberFormatException e){
            System.out.println("Error: Invalid value was inputted.");
        }

        return isBD;
    }

    public static int find_operation(String q, String[] the_qs){
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
    }

    public static String shuffle(String operand1, String operand2) {
        int operandLeft1 = operand1.indexOf(".");
        int operandLeft2 = operand2.indexOf(".");

        String shuffleResult="";

        if (operandLeft1 < operandLeft2) {
            //add padding 0s to the beginning of op1
            for(int i=0; i < (operandLeft2-operandLeft1); i++) {
                operand1 = "0" + operand1;
            }
            operandLeft1 = operandLeft2;
        }
        else if (operandLeft1 > operandLeft2) {
            //add padding 0s to the beginning of op2
            for(int i=0; i < (operandLeft1-operandLeft2); i++) {
                operand2 = "0" + operand2;
            }
        }

        for(int i=0; i < operandLeft1; i++) {
            //shuffle the values left of the decimal
            shuffleResult += operand1.charAt(i);
            shuffleResult += operand2.charAt(i);
        }

        shuffleResult += ".";
        //value assigned as the amount of digits differing on the right side of the decimal
        int operandDif = Math.abs(operand1.length() - operand2.length());

        if (operand1.length() < operand2.length()) {
            //add padding 0s to the end
            for(int i=0; i < operandDif; i++) {
                operand1 += "0";
            }

        }

        else if (operand1.length() > operand2.length()) {
            //check length of the overall operand since the left side is guaranteed now to be equal
            for(int i=0; i < operandDif; i++) {
                operand2 += "0";
            }
        }
        //allows server side to see that the operands are being padded correctly
        System.out.println(operand1);
        System.out.println(operand2);

        for(int i=operand1.indexOf(".")+1; i < operand1.length(); i++) {
            //shuffles values right of decimal
            shuffleResult += operand1.charAt(i);
            shuffleResult += operand2.charAt(i);
        }

        return shuffleResult;
    }
}
