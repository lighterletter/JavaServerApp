package com.c4q.lighterletter;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * A simple socket server
 * @author faheem
 *
 */
/**
 * Created by c4q-john on 10/18/15.
 */
public class Server {

    private ServerSocket serverSocket;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(port);

        //Listen for clients. Block till one connects

        System.out.println("Waiting for clients...");
        Socket client = serverSocket.accept();

        //A client has connected to this server. Send welcome message
        sendWelcomeMessage(client);
    }

    private void sendWelcomeMessage(Socket client) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        writer.write("Hello. You are connected to a Simple Time-Offset Socket Server.\n Please enter desired offset in format HH:mm");
        writer.flush();
        writer.close();
    }
//    private void utcService(Socket client) throws IOException {
//        System.out.println("you entered: "  + "\n");
//
//        TimeZone tz = TimeZone.getTimeZone("UTC");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
//        df.setTimeZone(tz);
//
//        String nowAsISO = df.format(new Date());
//        System.out.println("Current: " + String.valueOf(nowAsISO));
//    }

    /**
     * Creates a SocketServer object and starts the server.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Setting a default port number.
        int portNumber = 9990;

        try {
            // initializing the Socket Server
           Server socketServer = new Server(portNumber);
            socketServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

