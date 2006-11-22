package HiQVisual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

public class Window extends javax.swing.JFrame {
  public Window() {
    initComponents();
  }
  
  private Vector<Point> list = new Vector();
  private Iterator i;
  
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    gamePanel = new javax.swing.JPanel();
    startButton = new javax.swing.JButton();
    stopButton = new javax.swing.JButton();
    stepButton = new javax.swing.JButton();
    clearButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Hi-Q!");
    addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        formMouseClicked(evt);
      }
    });

    gamePanel.setBackground(new java.awt.Color(0, 0, 0));
    gamePanel.setPreferredSize(new java.awt.Dimension(300, 300));
    gamePanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        gamePanelMouseClicked(evt);
      }
    });

    org.jdesktop.layout.GroupLayout gamePanelLayout = new org.jdesktop.layout.GroupLayout(gamePanel);
    gamePanel.setLayout(gamePanelLayout);
    gamePanelLayout.setHorizontalGroup(
      gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 300, Short.MAX_VALUE)
    );
    gamePanelLayout.setVerticalGroup(
      gamePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 300, Short.MAX_VALUE)
    );

    startButton.setText("Start");
    startButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        startButtonMouseClicked(evt);
      }
    });

    stopButton.setText("Stop");
    stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        stopButtonMouseClicked(evt);
      }
    });

    stepButton.setText("Step");
    stepButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        stepButtonMouseClicked(evt);
      }
    });

    clearButton.setText("Clear");
    clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        clearButtonMouseClicked(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(gamePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(layout.createSequentialGroup()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
              .add(stepButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .add(startButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 72, Short.MAX_VALUE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
              .add(clearButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .add(stopButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(gamePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .add(23, 23, 23)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(startButton)
          .add(stopButton))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(stepButton)
          .add(clearButton))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pack();
  }// </editor-fold>//GEN-END:initComponents
  
  private void stopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopButtonMouseClicked
    if(stopButton.isEnabled()){
      startButton.setEnabled(true);
      stopButton.setEnabled(false);
      stepButton.setEnabled(true);
      clearButton.setEnabled(true);
    }
  }//GEN-LAST:event_stopButtonMouseClicked
  
  private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked
    if(clearButton.isEnabled()){
      startButton.setEnabled(true);
      stopButton.setEnabled(false);
      stepButton.setEnabled(true);
      
      Graphics g = gamePanel.getGraphics();
      g.setColor(Color.BLACK);
      g.fillRect(0,0,gamePanel.getWidth(),gamePanel.getHeight());
      list.clear();
    }
  }//GEN-LAST:event_clearButtonMouseClicked
  
  private void stepButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stepButtonMouseClicked
    if(stepButton.isEnabled()){
      startButton.setEnabled(true);
      stopButton.setEnabled(false);
      stepButton.setEnabled(true);
      clearButton.setEnabled(true);
    }
  }//GEN-LAST:event_stepButtonMouseClicked
  
  private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    
  }//GEN-LAST:event_formMouseClicked
  
  private void startButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startButtonMouseClicked
    if(startButton.isEnabled()){
      startButton.setEnabled(false);
      stopButton.setEnabled(true);
      stepButton.setEnabled(false);
      clearButton.setEnabled(true);
      HiQ hiq = new HiQ();
      hiq.startGame();
    }
  }//GEN-LAST:event_startButtonMouseClicked
  
  private void drawBoard(){
    Graphics2D g = (Graphics2D) gamePanel.getGraphics();
    g.setColor(Color.RED);
    g.fillRect(0,0,30,30);
  }
  
  private void gamePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gamePanelMouseClicked
    Graphics2D g = (Graphics2D) gamePanel.getGraphics();
    
    list.add(evt.getPoint());
    
    for(i = list.iterator(); i.hasNext(); ) {
      Point nextPoint = (Point)i.next();
      Point lastPoint = list.get(list.size()-1);
      
      g.setColor(Color.RED);
      g.drawOval(nextPoint.x-3, nextPoint.y-3, 6, 6);
      
      g.setColor(Color.WHITE);
      g.drawLine(lastPoint.x, lastPoint.y, nextPoint.x, nextPoint.y);
    }
  }//GEN-LAST:event_gamePanelMouseClicked
  
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Window().setVisible(true);
      }
    });
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton clearButton;
  public static javax.swing.JPanel gamePanel;
  private javax.swing.JButton startButton;
  private javax.swing.JButton stepButton;
  private javax.swing.JButton stopButton;
  // End of variables declaration//GEN-END:variables
  
}
