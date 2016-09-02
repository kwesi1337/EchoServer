/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import shared.ProtocolStrings;

/**
 *
 * @author josephawwal
 */
public class ClientHandler extends Thread {
    
    
    private final Scanner in;
    private final PrintWriter write;
    private final Socket sock;
    
    
    ClientHandler(Socket sock) throws IOException {
        
        in = new Scanner(sock.getInputStream());
        write = new PrintWriter(sock.getOutputStream(), true);
        this.sock = sock;
    }
    
    @Override
    
    public void run(){
        
        String msg = in.nextLine();
        System.out.println("Received message");
        
        while (!msg.equals(ProtocolStrings.STOP)){
            
            write.println(msg.toUpperCase());
            
            
            msg = in.nextLine();
            
            
        }
        
        write.println(ProtocolStrings.STOP);
        
        
        try {
            
            sock.close();
            
        } catch (IOException ex){
            
            System.out.println("error");
        }
}
}

