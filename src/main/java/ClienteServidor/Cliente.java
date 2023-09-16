package ClienteServidor;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    static Scanner sc = new Scanner(System.in);
    static int valor1,valor2,soma,factorial,multiplicacao;

    public static void main(String[] args) throws IOException {
        String hostName = "127.0.0.1";
        int portNumber = 8888;

        try (Socket socket = new Socket(hostName, portNumber);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ) {
            menu(dis, dos, socket);
            String userInput;
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro no cliente " + e.getMessage());
        }
    }
    public static void menu(DataInputStream dis, DataOutputStream dos, Socket socket) throws IOException, ClassNotFoundException {
        System.out.println("=============================================");
        System.out.println("                    MENU PRINCIPAL");
        System.out.println("1. Soma \n" +
                "2. Factorial \n " +
                "3. Multiplicar \n" +
                "4. Impressao \n" +
                "0. Terminar");
        int opcao = sc.nextInt();
        dos.writeInt(opcao);
        switch (opcao) {
            case 0:
                dos.writeInt(0);
                System.exit(0);
                break;
            case 1:
                somaValores(dis, dos, socket);
                break;
            case 2:
                factorialDeMaiorValor(dis, dos, socket);
                break;
            case 3:
                multiplicarValores(dis, dos, socket);
                break;
            case 4:
                impressaoDeValoresPares(dis, dos, socket);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }
    public static void somaValores(DataInputStream dis, DataOutputStream dos, Socket socket) throws IOException {
        //while (( != null){
            System.out.println("Insira o primeiro valor: ");
            valor1 = sc.nextInt();
            System.out.println("Insira o segundo valor: ");
            valor2 = sc.nextInt();
            soma = valor1 + valor2;
            System.out.println("A soma dos valores e: " + soma);
            dos.writeInt(soma);
        //}
    }
    public static void factorialDeMaiorValor(DataInputStream dis, DataOutputStream dos, Socket socket) throws IOException {
        System.out.println("Insira o primeiro valor: ");
        valor1 = sc.nextInt();
        System.out.println("Insira o segundo valor: ");
        valor2 = sc.nextInt();
        if (valor2 > valor1){
            if (valor2 == 0 || valor2 == 1){
                System.out.println("O factorial e igaual a: " + 1);
            }else{
                for (int i = 0; i > valor2; i++){
                    factorial *= i;
                }
                dos.writeInt(factorial);
            }
        }
    }
    public static void multiplicarValores(DataInputStream dis, DataOutputStream dos, Socket socket) throws IOException {
        System.out.println("Insira o primeiro valor: ");
        valor1 = sc.nextInt();
        System.out.println("Insira o segundo valor: ");
        valor2 = sc.nextInt();
        multiplicacao = valor1 * valor2;
        System.out.println("A multiplicacao dos valores e: " + multiplicacao);
        dos.writeInt(multiplicacao);
    }
    public static void impressaoDeValoresPares(DataInputStream dis, DataOutputStream dos, Socket socket) throws IOException {
        System.out.println("Insira o primeiro valor: ");
        valor1 = sc.nextInt();
        System.out.println("Insira o segundo valor: ");
        valor2 = sc.nextInt();

        if (valor2 % 2 == 0){
            if (valor2 > valor1){
                System.out.println("O maior valor  e " + valor2);
                dos.writeInt(valor2);
            }else {
                System.out.println("O maior valor  e " + valor1);
                dos.writeInt(valor1);
            }
        }
    }
}
