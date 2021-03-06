/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UserTools.java
 *
 * Created on Dec 5, 2011, 7:41:48 AM
 */
package client;

/**
 *
 * @author Ram
 */
public class UserTools extends javax.swing.JFrame {

    private String name;
    private ClientGUI gui;

    /** Creates new form UserTools */
    public UserTools(String name, ClientGUI gui) {
        super("User Tools");
        this.name = name;
        this.gui = gui;
        initComponents();
        NAME.setText(name);
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        NAME = new javax.swing.JLabel();
        whisper = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nameLabel.setText("Nickname:");

        NAME.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NAME.setText("Nickname:");

        whisper.setText("Whisper");
        whisper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whisperActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NAME))
                    .addComponent(whisper))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(NAME))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(whisper)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void whisperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whisperActionPerformed
    gui.addTarget(name);
    this.dispose();
}//GEN-LAST:event_whisperActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NAME;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton whisper;
    // End of variables declaration//GEN-END:variables
}
