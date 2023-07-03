package com.example.shopapplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static final int port = 1234;

    public ChatServer() throws IOException {
        ServerSocket server = new ServerSocket(port);

        Socket client;
    }
}
