import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioTcpClient {
    private static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 7));

        // Ждем завершения соединения
        while (!socketChannel.finishConnect()) {
            System.out.println("Подключение...");
        }
        System.out.println("Подключено к серверу!");

        // Поток для отправки сообщений
        Thread senderThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (running) {
                try {
                    if (scanner.hasNextLine()) {
                        String msg = scanner.nextLine();
                        sendMessage(msg, socketChannel);

                        // Для выхода из программы
                        if ("exit".equalsIgnoreCase(msg)) {
                            running = false;
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            scanner.close();
        });
        senderThread.start();

        // Основной поток для приема сообщений
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (running) {
                // Проверяем входящие сообщения от сервера
                buffer.clear();
                int bytesRead = socketChannel.read(buffer);

                if (bytesRead > 0) {
                    buffer.flip();
                    byte[] data = new byte[buffer.limit()];
                    buffer.get(data);
                    String response = new String(data).trim();
                    System.out.println("Ответ сервера: " + response);
                    if (!response.startsWith("Echo:")) {
                        sendMessage("Echo: " + response, socketChannel);
                    }
                } else if (bytesRead == -1) {
                    System.out.println("Сервер отключился");
                    break;
                }

                // Небольшая пауза, чтобы не грузить процессор
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            running = false;
            socketChannel.close();
            System.out.println("Клиент завершен");
        }
    }

    private static void sendMessage(String msg, SocketChannel socketChannel) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap((msg + "\n").getBytes());
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}