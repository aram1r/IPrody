import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TcpServer{
    public static void main(String[] args) {
        List<Socket> sockets = new ArrayList<>();

        Thread receiveThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(7)) {
                System.out.println("Сервер запущен на порту " + serverSocket.getLocalPort());
                while(true) {
                    Socket socket = serverSocket.accept();
                    sockets.add(socket);
                    new Thread(()-> handleRequest(socket)).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread sendThread = getSendThread(sockets);
        receiveThread.start();
        try  {
            sendThread.join();
            receiveThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток приёма сообщений завершил работу" + e.getMessage());
        }
    }

    //Поток для отправки сообщений всем клиентам
    private static Thread getSendThread(List<Socket> sockets) {
        Thread sendThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите сообщения для отправки: ");
                String message = scanner.nextLine();
                for (Socket socket : sockets) {
                    try {
                        new PrintWriter(socket.getOutputStream(), true).println(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        sendThread.start();
        return sendThread;
    }

    //Поток для обработки клиентов
    private static void handleRequest(Socket socket) {
        try {
            Thread readThread = new Thread(() -> {
                while (true) {
                    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        String message;
                        while ((message = bufferedReader.readLine()) != null) {
                            if (!message.startsWith("Echo:")) {
                                System.out.println("Принятое сообщение: " + message);
                                new  PrintWriter(socket.getOutputStream(), true).println("Echo: " + message);
                            } else {
                                System.out.println(message);
                            }
                        }
                    }   catch (IOException e) {
                        System.out.println("Клиент " + socket.getInetAddress() + " отключился");
                    }
                }
            });


            readThread.start();
            readThread.join();
        } catch (InterruptedException e) {
            System.out.println("Исключение тут");
        }

    };



}
