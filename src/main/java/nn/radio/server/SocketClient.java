package nn.radio.server;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    Socket clientSocket;

    public SocketClient() throws IOException {
        clientSocket = new Socket("localhost", 4444);
        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        PrintWriter outRint = new PrintWriter(outputStream, true);

    }

}
