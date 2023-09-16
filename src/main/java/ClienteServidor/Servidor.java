package ClienteServidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    //static int valor1,valor2,factorial,multiplicacao;
    public static void main (String [] args) {
        int portNumber = 8888;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Servidor Disponivel! ");
            Socket socket = serverSocket.accept();
            System.out.println("Servidor Conectado! ");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String inputLine;

            while (true) {

                int opcao = dis.readInt();
                switch (opcao) {
                    case 0:
                        System.out.println("Servidor encerrado.");
                        socket.close();
                        serverSocket.close();
                        System.exit(0);
                        break;
                    case 1:
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        int soma = dis.readInt();
                        break;
                    case 2:
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        int factorial = dis.readInt();
                        break;
                    case 3:
                        ObjectOutputStream oo1s = new ObjectOutputStream(socket.getOutputStream());
                        int multiplicaco = dis.readInt();
                        break;
                    case 4:
                        ObjectOutputStream oo2s = new ObjectOutputStream(socket.getOutputStream());
                        int impressao = dis.readInt();
                        break;
                    default:
                        dos.writeUTF("Opção inválida! Tente novamente.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
