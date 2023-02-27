package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Server is started");
        ServerSocket serverSocket = new ServerSocket(8081);
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        OutputStream outputStream = accept.getOutputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        String line;

        bufferedWriter.write("Привіт\n");
        bufferedWriter.flush();

        while (!(line = bufferedReader.readLine()).equals("exit")) {
            while (true) {
                line = bufferedReader.readLine();
                while (!isValid(line)) {
                    bufferedWriter.write("Що таке паляниця?\n");
                    bufferedWriter.flush();
                    if (bufferedReader.readLine().equals("хлеб")) {
                        Date date = new Date();
                        bufferedWriter.write("Дата:" + date + " Server is close, goodbye" + "\n");
                        bufferedWriter.flush();
                        System.out.println("Server is close");
                        accept.close();
                        bufferedWriter.close();
                        bufferedReader.close();
                        break;
                    } else {
                        bufferedWriter.write("Путин хуйло\n");
                        bufferedWriter.flush();
                        System.out.println("Server is close");
                        accept.close();
                        bufferedWriter.close();
                        bufferedReader.close();
                    }
                }
                System.out.println("Client sent: " + line);
                bufferedWriter.write("you sent to the server: " + line + "\n");
                bufferedWriter.flush();
            }
        }
    }
    public static boolean isValid(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.UnicodeBlock.of(s.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC) ||
                    s.charAt(i) == '-') {
                return false;
            }
        }
        return true;
    }
}