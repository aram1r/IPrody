import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Создаем сокет и потоки ввода-вывода один раз в блоке try
        try (Socket socket = new Socket("127.0.0.1", 7);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Соединение установлено. Введите сообщение для отправки:");

            // Поток для чтения ответов от сервера
            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        if (!message.startsWith("Echo:")) {
                            System.out.println("Принято сообщение: " + message);
                            out.println("Echo: " + message);
                        } else {
                            System.out.println(message);
                        }
                    }
                } catch (IOException e) {
                    if (!socket.isClosed()) {
                        System.err.println("Ошибка чтения: " + e.getMessage());
                    }
                }
            });

            // Поток для отправки сообщений на сервер
            Thread writeThread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                try {
                    while (true) {
                        if (scanner.hasNextLine()) {
                            String userInput = scanner.nextLine();
                            out.println(userInput);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Ошибка записи: " + e.getMessage());
                }
            });

            readThread.start();
            writeThread.start();


            readThread.join();
            writeThread.join();
        }
    }
}