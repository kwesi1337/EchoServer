/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoclient;
import shared.ProtocolStrings;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josephawwal
 */
public class Client extends Thread {

  Socket socket;
  private int port;
  private InetAddress serverAddress;
  private Scanner input;
  private PrintWriter output;
  private List<EchoListener> listeners = new ArrayList();
  
  
  public void connect(String address, int port) throws UnknownHostException, IOException
  {
    this.port = port;
    serverAddress = InetAddress.getByName(address);
    socket = new Socket(serverAddress, port);
    input = new Scanner(socket.getInputStream());
    output = new PrintWriter(socket.getOutputStream(), true);  //Set to true, to get auto flush behaviour
 start();
 
 
  }
  
  public void send(String msg)
  {
    output.println(msg);
  }
  
  public void stopClient() throws IOException{
    output.println(ProtocolStrings.STOP);
  }
  
  
  public void registerEchoListener(EchoListener l){
      
      listeners.add(l);
      
      
  }
  
  
  public void unRegisterEchoListener(EchoListener l){
      
      listeners.remove(l);
      
      
  }
  
  
  
  public String receive()
  {
    String msg = input.nextLine();
    if(msg.equals(ProtocolStrings.STOP)){
      try {
        socket.close();
      } catch (IOException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return msg;
  }
  
  public static void main(String[] args)
  {   
    int port = 8009;
    String ip = "localhost";
    if(args.length == 2){
      ip = args[0];
      port = Integer.parseInt(args[1]);
    }
    try {
      Client tester = new Client();      
      tester.connect(ip, port);
      System.out.println("Sending 'Hello world'");
      tester.send("Hello World");
      System.out.println("Waiting for a reply");
      System.out.println("Received: " + tester.receive()); //Important Blocking call         
      tester.stopClient();      
      //System.in.read();      
    } catch (UnknownHostException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
  }


}