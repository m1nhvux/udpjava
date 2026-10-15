
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udp3 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2208;
        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress sAddress = InetAddress.getByName(host);
            String se = ";B23DCCN569;GGSVgSqn";
            DatagramPacket sendR = new DatagramPacket(se.getBytes(),se.getBytes().length,sAddress, port);
            socket.send(sendR);
            byte[] buf = new byte[4096];
            DatagramPacket reci = new DatagramPacket(buf,buf.length);
            socket.receive(reci);
            String rec = new String(reci.getData(), reci.getOffset(),reci.getLength()).trim();
            System.out.println(rec);
            String[] part = rec.split(";");
            String[] data = part[1].split("\\s+");
            StringBuilder bd = new StringBuilder();
            for(String x : data){
                String tmp = x.substring(0,1).toUpperCase() + x.substring(1).toLowerCase();
                bd.append(tmp).append(" ");
            }
            String res = part[0] + ";" + bd.toString().trim();
            DatagramPacket sendRe = new DatagramPacket(res.getBytes(), res.getBytes().length, sAddress, port);
            socket.send(sendRe);
        } catch (Exception e) {
        }
    }
}
