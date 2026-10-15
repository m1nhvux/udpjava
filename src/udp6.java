

import UDP.Customer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udp6 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2209;
        try(DatagramSocket socket = new DatagramSocket()) {
            String sen = ";B23DCCN569;Fgyag4qR";
            InetAddress sAddress = InetAddress.getByName(host);
            DatagramPacket dt = new DatagramPacket(sen.getBytes(),sen.getBytes().length, sAddress, port);
            socket.send(dt);
            byte[] buf = new byte[65507];
            DatagramPacket rec = new DatagramPacket(buf, buf.length);
            socket.receive(rec);
            String resq = new String(rec.getData(), rec.getOffset(), 8);
            ByteArrayInputStream inp = new ByteArrayInputStream(rec.getData(), rec.getOffset() + 8, rec.getLength() - 8);
            ObjectInputStream in = new ObjectInputStream(inp);
            Customer cs = (Customer) in.readObject();
            cs.chuanHoa();
            System.out.println(cs);
            ByteArrayOutputStream outB = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outB);
            out.writeObject(cs);
            out.flush();
            ByteArrayOutputStream resb = new ByteArrayOutputStream();
            resb.write(resq.getBytes());
            resb.write(outB.toByteArray());
            byte[] res = resb.toByteArray();
            DatagramPacket resD = new DatagramPacket(res,res.length,sAddress, port);
            socket.send(resD);

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
