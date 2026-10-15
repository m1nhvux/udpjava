

import UDP.Student;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udp5 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2209;
        try(DatagramSocket socket = new DatagramSocket()) {
            String sendR = ";B23DCCN569;pmtfpOs7";
            InetAddress sAddress = InetAddress.getByName(host);
            DatagramPacket dt = new DatagramPacket(sendR.getBytes(),sendR.getBytes().length, sAddress, port);
            socket.send(dt);
            byte[] buf = new byte[65507];
            DatagramPacket rec = new DatagramPacket(buf, buf.length);
            socket.receive(rec);
            byte[] data = rec.getData();
            int len = rec.getLength();
            int offs = rec.getOffset();
            String resq = new String(data, offs, 8);
            ByteArrayInputStream inp = new ByteArrayInputStream(data, offs + 8,len - 8 );
            ObjectInputStream in = new ObjectInputStream(inp);
            Student st = (Student) in.readObject();
            st.normalize();
            System.out.println(st);

            ByteArrayOutputStream outB = new ByteArrayOutputStream();
            ObjectOutputStream ob = new ObjectOutputStream(outB);
            ob.writeObject(st);
            ob.flush();
            ByteArrayOutputStream resB = new ByteArrayOutputStream();
            resB.write(resq.getBytes());
            resB.write(outB.toByteArray());
            byte[] x = resB.toByteArray();
            DatagramPacket sendB = new DatagramPacket(x,x.length,sAddress,port);
            socket.send(sendB);
        } catch (Exception e) {
        }
    }
}
