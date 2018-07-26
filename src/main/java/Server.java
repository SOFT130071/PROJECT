import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.ServiceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 12345;

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);

        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        while (true) {
            Socket socket = server.accept();
            Runnable r = () -> {
                try {
                    InputStream is = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((len = is.read(bytes)) != -1) {
                        stringBuilder.append(new String(bytes, 0, len, "UTF-8"));

                        JsonObject jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
                        String type = jsonObject.get("type").getAsString();
                        JsonObject param = jsonObject.get("content").getAsJsonObject();

                        int ret = 0xFFFFFF;

                        switch (type) {
                            case "log":
                                ret = new ServiceFactory().getUserServiceInstance(param).log();
                                break;
                            case "reg":
                                ret = new ServiceFactory().getUserServiceInstance(param).reg();
                                break;
                            case "isLogged":
                                ret = new ServiceFactory().getUserServiceInstance(param).logged();


                        }

                        is.close();
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            threadPool.submit(r);
        }
    }
}