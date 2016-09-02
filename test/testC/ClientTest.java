/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testC;

import echoclient.Client;
import echoclient.EchoListener;
import echoserver.EchoServer;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author josephawwal
 */
public class ClientTest {
    
    public ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        new Thread (new Runnable(){
            
            @Override
            
            
            public void run(){
                
                EchoServer.main(null);
                
                
            }
        })
                .start();
  
        
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        EchoServer.stopServer();
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    
    public void send() throws IOException {
        
        
        Client client = new Client();
        client.connect("localhost", 8009);
        client.registerEchoListener(new EchoListener(){
            
            @Override
            
            public void messageArrived(String message){
                
                assertEquals("Hello", message);
                
            }
        });
        
        client.send("Hello");
        
    }
    
    
}
