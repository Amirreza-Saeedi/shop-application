package com.example.shopapplication;

//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class ChatServer implements Runnable {
//    public static final int port = 1234;
//
//    private final ArrayList<ConnectionHandler> connections;
//    private ServerSocket server;
//    private boolean done = false; // determines if the server is to close
//    private ExecutorService pool;
//
//
//
//    public ChatServer() {
//        connections = new ArrayList<>(2);
//
//    }
//
//    private void shoutDown() {
//        done = true; // the accept-client loop must be stopped
//        if (!server.isClosed()) {
//            try {
//                server.close();
//                for (ConnectionHandler conenction : connections) {
//                    conenction.shutDown();
//                }
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
//    @Override
//    public void run() {
//        try {
//            server = new ServerSocket(port);
//            System.out.println("server is ready.");
//            pool = Executors.newCachedThreadPool();
//
//            while (!done) {
//                Socket client = server.accept(); // wait for new client
//
//                // read obj
////                ChatController.FromAndTo fromAndTo;
////                inObj = new ObjectInputStream(client.getInputStream());
////                Object object = inObj.readObject();
////                if (object instanceof ChatController.FromAndTo) {
////                    fromAndTo = (ChatController.FromAndTo) object; // convert to familiar form
////                } else {
////                    throw new RuntimeException();
////                }
//
////                System.out.println("ChatServer.run");
////                System.out.println(fromAndTo);
//
//                ConnectionHandler handler = new ConnectionHandler(client);
//                connections.add(handler);
//                System.out.println("handler = " + handler);
//                pool.execute(handler);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    class ConnectionHandler implements Runnable {
//        private final Socket client;
//        private BufferedReader in;
//        private PrintWriter out;
//        private ChatController.FromAndTo fromAndTo;
//        private ObjectInputStream inObj;
//
//
//        ConnectionHandler(Socket client) {
//            this.client = client;
////            this.fromAndTo = fromAndTo;
//        }
//
//        @Override
//        public void run() {
//            try {
//                out = new PrintWriter(client.getOutputStream(), true);
//                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//                inObj = new ObjectInputStream(client.getInputStream());
//                Object object = inObj.readObject();
//                if (object instanceof ChatController.FromAndTo) {
//                    fromAndTo = (ChatController.FromAndTo) object; // convert to familiar form
//                } else {
//                    throw new RuntimeException();
//                }
//
//                System.out.println("fromAndTo = " + fromAndTo);
//
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        void shutDown() throws IOException {
//            in.close();
//            out.close();
//            if (!client.isClosed()) {
//                client.close();
//            }
//        }
//    }
//
//}

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable {
    ServerSocket server;
    int port = 1234;
    Socket client;
    BufferedReader in;
    PrintWriter out;

    @Override
    public void run() {

    }
}

