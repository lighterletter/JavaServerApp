package com.c4q.lighterletter;
/**
 * Created by c4q-john on 10/18/15.
 *
 * import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.net.Socket;
 import java.net.UnknownHostException;

 /**
 * A Simple Socket client that connects to our socket server
 * @author faheem
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class Client
 {

     private String hostname;
     private int    port;
     Socket socketClient;

     public Client(String hostname, int port)
     {
         this.hostname = hostname;
         this.port = port;
     }

     public void connect() throws UnknownHostException, IOException
     {
         System.out.println("Attempting to connect to " + hostname + ":" + port);
         socketClient = new Socket(hostname, port);
         System.out.println("Connection Established");
     }

     public void readResponse() throws IOException
     {
         String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

        System.out.println("Response from server: ");
        if ((userInput = stdIn.readLine()) != null) {
            String scanner = userInput;

            System.out.println( scanner + "\n");

            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);

            String nowAsISO = df.format(new Date());
            System.out.println("Current time: " + String.valueOf(nowAsISO));

        }
    }

    public static void main(String arg[]){
        //Creating a SocketClient object
        Client client = new Client ("localhost",9990);
        try {
            //trying to establish connection to the server
            client.connect();
            //if successful, read response from server
            client.readResponse();

            Scanner scanner = new Scanner(System.in);
            System.out.println("type in time offset: ");
            scanner.next();

            // Calculate time..

        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
    }
}