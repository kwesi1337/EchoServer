/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoclient;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josephawwal
 */
public class EGUI extends javax.swing.JFrame implements EchoListener {
    
      private javax.swing.JButton jButton1;
  private javax.swing.JLabel receiveText;
  private javax.swing.JTextField textToSend;
    
    
    Client client = new Client();
    private static int port = 8009;
    private static String ip = "localhost";
    
  public EGUI() {
    try {
      initComponents();
      
      addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent ev) {
        try {
          client.stopClient();
      
        } catch (IOException e){
            
            System.out.println("Error");
        }
      }
    });
      
      
      client.connect(ip, port);
      client.registerEchoListener(this);
    } catch (IOException ex) {
      Logger.getLogger(EGUI.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
            
            private void initComponents(){
                
                textToSend = new javax.swing.JTextField();
                receiveText = new javax.swing.JLabel();
                jButton1 = new javax.swing.JButton();
                
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                
                
                receiveText.setText("---");
                
                jButton1.setText("Sending");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        
                        jButton1ActionPerformed(evt);
                    }
                });
                
                 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(19, 19, 19)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(textToSend, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(31, 31, 31)
            .addComponent(receiveText, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jButton1)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(28, 28, 28)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(textToSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(receiveText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addComponent(jButton1)
        .addContainerGap(206, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    client.send(textToSend.getText());

  }//GEN-LAST:event_jButton1ActionPerformed
  
  @Override
  public void messageArrived(String message) {
    System.out.println("Received : " + message);
    receiveText.setText(message);
  }
  
    public static void main(String[] args) {
        
        if (args.length == 2) {
            
            port = Integer.parseInt(args[0]);
            ip = args[1];
            
    }
        
        try {
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                
                if ("Nimbus".equals(info.getName())){
                    
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
        } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(EGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(EGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(EGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(EGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new EGUI().setVisible(true);
      }
    });
        

                
            }
        }
    

