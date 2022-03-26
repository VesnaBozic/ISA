# INTERNET SOFTVERSKE ARHITEKTURE

## Java Sockets

***Socket programming*** refers to writing programs that execute across multiple computers in which the devices are all connected to each other using a network.

There are two communication protocols that we can use for socket programming: ***User Datagram Protocol (UDP)*** and ***Transfer Control Protocol (TCP).***

***UDP*** is connection-less, meaning there's no session between the client and the server.

***TCP*** is connection-oriented, meaning an exclusive connection must first be established between the client and server for communication to take place.

Java provides a collection of classes and interfaces that take care of low-level communication details between the client and server.

#### CLIENT CODE

```
public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
            if ("hello server".equals(greeting)) {
                out.println("hello client");
            }
            else {
                out.println("unrecognised greeting");
            }
    }

    public void stop() {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) {
        GreetServer server=new GreetServer();
        server.start(6666);
    }
}
```

#### SERVER CODE

```
public class GreetClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() {
        in.close();
        out.close();
        clientSocket.close();
    }
}
```

### How this work?

By definition, a socket is one endpoint of a two-way communication link between two programs running on different computers on a network. A socket is bound to a port number so that the transport layer can identify the application that data is destined to be sent to.

### THE SERVER

A server runs on a specific computer on the network and has a socket that's bound to a specific port number. In our case, we'll use the same computer as the client, and start the server on port 6666:

```
ServerSocket serverSocket = new ServerSocket(6666);
```

The server just waits, listening to the socket for a client to make a connection request. This happens in the next step:

```
Socket clientSocket = serverSocket.accept();
```

When the server code encounters the accept method, it blocks until a client makes a connection request to it.

If everything goes well, the server accepts the connection. Upon acceptance, the server gets a new socket, clientSocket, bound to the same local port, 6666, and also has its remote endpoint set to the address and port of the client.

At this point, the new Socket object puts the server in direct connection with the client. We can then access the output and input streams to write and receive messages to and from the client respectively:

```
PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
```

Now the server is capable of exchanging messages with the client endlessly until the socket is closed with its streams.

However, in our example, the server can only send a greeting response before it closes the connection. This means that if we ran our test again, the server would refuse the connection.

To allow continuity in communication, we'll have to read from the input stream inside a while loop, and only exit when the client sends a termination request. We'll see this in action in the following section.

For every new client, the server needs a new socket returned by the accept call. We use the serverSocket to continue to listen for connection requests, while tending to the needs of the connected clients. We haven't yet allowed for this in our first example.

### THE CLIENT

The client must know the hostname or IP of the machine on which the server is running, and the port number on which the server is listening.

To make a connection request, the client tries to rendezvous with the server on the server's machine and port:

```
Socket clientSocket = new Socket("127.0.0.1", 6666);
```

The client also needs to identify itself to the server, so it binds to a local port number assigned by the system that it'll use during this connection. We don't deal with this ourselves.

The above constructor only creates a new socket when the server has accepted the connection; otherwise, we'll get a connection refused exception. When successfully created, we can then obtain input and output streams from it to communicate with the server:

```
PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
```

The input stream of the client is connected to the output stream of the server, just like the input stream of the server is connected to the output stream of the client.

### Continuous Communication

```
public class EchoServer {
    public void start(int port) {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
        if (".".equals(inputLine)) {
            out.println("good bye");
            break;
         }
         out.println(inputLine);
    }
}
```

Notice that we added a termination condition, where the while loop exits when we receive a period character.

We'll start EchoServer using the main method, just as we did for the GreetServer. This time, we start it on another port, such as 4444, to avoid confusion.

The EchoClient is similar to GreetClient, so we can duplicate the code. We're separating them for clarity.

### Server with Multiple Clients

As much as the previous example was an improvement over the first one, it's still not a great solution. A server must have the capacity to service many clients and many requests simultaneously.

Another feature we'll see here is that the same client could disconnect and reconnect again, without getting a connection refused exception or a connection reset on the server. We weren't previously able to do this.

This means that our server is going to be more robust and resilient across multiple requests from multiple clients.

We'll do this by creating a new socket for every new client and service that client's request on a different thread. The number of clients being served simultaneously will equal the number of threads running.

The main thread will be running a while loop as it listens for new connections.

Now let's see this in action. We'll create another server called EchoMultiServer.java. Inside it, we'll create a handler thread class to manage each client's communications on its socket:

```

public class EchoMultiServer {
    private ServerSocket serverSocket;

    public void start(int port) {
        serverSocket = new ServerSocket(port);
        while (true)
            new EchoClientHandler(serverSocket.accept()).start();
    }

    public void stop() {
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
              new InputStreamReader(clientSocket.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    out.println("bye");
                    break;
                }
                out.println(inputLine);
            }

            in.close();
            out.close();
            clientSocket.close();
    }
}
```

Notice that we now call accept inside a while loop. Every time the while loop is executed, it blocks on the accept call until a new client connects. Then the handler thread, EchoClientHandler, is created for this client.

What happens inside the thread is the same as the EchoServer, where we handled only a single client. The EchoMultiServer delegates this work to EchoClientHandler so that it can keep listening for more clients in the while loop.

We'll still use EchoClient to test the server. This time, we'll create multiple clients each sending and receiving multiple messages from the server.



