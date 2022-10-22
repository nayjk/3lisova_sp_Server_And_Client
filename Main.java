import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Работа по протоколу TCP
 * Сервер получает байт (число)
 * и возвращает клиенту квадрат числа
 */
@SuppressWarnings("InfiniteLoopStatement")
class SqServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1020)) {
            while (true) {
                Socket socket = serverSocket.accept();
                serverClient(socket);
            }
        }
    }

    private static void serverClient(Socket socket) throws IOException {
        System.out.println("Serving client" + socket.getInetAddress());

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        while (true) {
            int request = inputStream.read();
            if (request == -1) {
                break;
            }
            if (request == 6) {
                break;
            }
            System.out.println("Request:" + request);
            outputStream.write(request + request);
            outputStream.flush();
        }
    }
}
