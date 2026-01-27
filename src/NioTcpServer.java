import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioTcpServer {

    private static final ExecutorService workerPool = Executors.newFixedThreadPool(4);
    // Список всех активных клиентов для рассылки
    private static final Set<SocketChannel> clients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(7));
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String msg = scanner.nextLine();
                    broadcast(msg);
                }
            }).start();

            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (!key.isValid()) continue;

                    if (key.isAcceptable()) {
                        acceptClient(serverSocketChannel, selector);
                    } else if (key.isReadable()) {
                        // Читаем данные и отдаем на обработку в пул потоков
                        processRead(key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processRead(SelectionKey key) {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            int bytesRead = clientChannel.read(buffer);
            if (bytesRead == -1) {
                closeConnection(clientChannel);
                return;
            }

            // Передаем задачу в ExecutorService
            workerPool.submit(() -> {
                try {
                    buffer.flip();
                    byte[] data = new byte[buffer.limit()];
                    buffer.get(data);
                    String message = new String(data).trim();

                    // Выводим сообщение от клиента
                    System.out.println("[Клиент " + clientChannel.getRemoteAddress() + "]: " + message);

                    // Отправляем эхо-ответ только если сообщение не начинается с Echo:
                    // (это чтобы избежать бесконечной пересылки эхо-ответов)
                    if (!message.startsWith("Echo:")) {
                        String echoMessage = "Echo: " + message;
                        ByteBuffer echoBuffer = ByteBuffer.wrap((echoMessage + "\n").getBytes());
                        while (echoBuffer.hasRemaining()) {
                            clientChannel.write(echoBuffer);
                        }
                    }
                } catch (IOException e) {
                    closeConnection(clientChannel);
                }
            });

        } catch (IOException e) {
            closeConnection(clientChannel);
        }
    }

    private static void acceptClient(ServerSocketChannel srvChannel, Selector selector) throws IOException {
        SocketChannel clientChannel = srvChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        clients.add(clientChannel); // Добавляем в список для рассылки
        System.out.println("Новое подключение: " + clientChannel.getRemoteAddress());
    }

    private static void broadcast(String message) {
        ByteBuffer msgBuffer = ByteBuffer.wrap((message + "\n").getBytes());
        synchronized (clients) {
            Iterator<SocketChannel> it = clients.iterator();
            while (it.hasNext()) {
                SocketChannel client = it.next();
                try {
                    // Проверяем, готов ли канал для записи
                    if (client.isConnected()) {
                        while (msgBuffer.hasRemaining()) {
                            client.write(msgBuffer);
                        }
                        msgBuffer.rewind(); // Сбрасываем позицию для следующего клиента
                    }
                } catch (IOException e) {
                    it.remove();
                    System.out.println("Ошибка при отправке клиенту, отключаем");
                }
            }
        }
    }

    private static void closeConnection(SocketChannel client) {
        try {
            System.out.println("Клиент отключился: " + client.getRemoteAddress());
            clients.remove(client);
            client.close();
        } catch (IOException ignored) {}
    }
}