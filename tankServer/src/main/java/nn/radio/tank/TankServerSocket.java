package nn.radio.tank;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TankServerSocket extends Thread{
    ServerSocket serverSocket;

    public TankServerSocket () throws IOException {
        serverSocket = new ServerSocket(4444);
    }

    public void run() {
        boolean isConnected = false;
        Socket socket = null;
        while (!isConnected) {
            //метод accept ждет, пока кто-то не подключится.

            try {
                socket = serverSocket.accept();
                System.out.println("accept1");
                isConnected = socket.isConnected();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        while (true)
        {

            //читаем сообщение
            InputStream inputStream = null;
            try {
                inputStream = socket.getInputStream();
                System.out.println("inputStream1");
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String message = "asdfasd";
            try {
                message = in.readLine();
                System.out.println("message = " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //придумываем ответ – просто разворачиваем строку задом наперед
            String reverseMessage = new StringBuilder(message).reverse().toString();

            //отправляем ответ
            OutputStream outputStream = null;
            try {
                outputStream = socket.getOutputStream();
                //System.out.println("outputStream");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("0");
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println(reverseMessage);
            out.flush();

        }
    }
}
