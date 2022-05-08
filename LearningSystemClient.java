import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class LearningSystemClient {

    public static void main(String[] args) throws IOException {

        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 7654);

            DataInputStream inc = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            while (true) {
                JPanel jPanel = new JPanel();
                JLabel outgoing = new JLabel("Message sent to server!");
                JTextField outer = new JTextField(10);
                jPanel.add(outgoing);
                jPanel.add(outer);
                String toSend = String.valueOf(outer.getText());

                out.writeUTF(toSend);

                if(toSend.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                String received = inc.readUTF();
                JOptionPane.showMessageDialog(null, received,
                        "idk", JOptionPane.PLAIN_MESSAGE);
            }
            inc.close();
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
