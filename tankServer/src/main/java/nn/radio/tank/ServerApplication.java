package nn.radio.tank;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        TankServerSocket s = new TankServerSocket();
        s.start();
        Socket clientSocket = new Socket("localhost", 4444);

//получаем OutputStream
        OutputStream outputStream = clientSocket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream, true);
        out.println("Kiss my shiny metal ass!");
        out.flush();

//читаем ответ
        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String answer = in.readLine();
        System.out.println(answer);

        System.out.println("close1 = " + clientSocket.isClosed());


        outputStream = clientSocket.getOutputStream();
        out = new PrintWriter(outputStream, true);
        out.println("Kiss my shiny metal ass!2");
        out.flush();
        System.out.println(in.readLine());

        out.println("Kiss my shiny metal ass!3");
        out.flush();
        System.out.println(in.readLine());
    }
}
