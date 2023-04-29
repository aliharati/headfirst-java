import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class DailyAdviceClient{
    public void go(){
        InetSocketAddress serverAdress = new InetSocketAddress("127.0.0.1", 5000);
        try (SocketChannel socketChannel = SocketChannel.open(serverAdress)){
            
            Reader channelReader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(channelReader);
            
            String advice = reader.readLine();
            System.out.println("Today you should: " + advice);

            reader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new DailyAdviceClient().go();
    }
}