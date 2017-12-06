package comedor;

import javax.swing.JTextArea;

public class VentanaPrincipal extends javax.swing.JFrame {
    
    public VentanaPrincipal() {
        
        Comedor cmdComedor;
        Alumno aluAlumno;
        int i;           

        initComponents();
        cmdComedor = new Comedor();

    }
    
    public static synchronized void Mensaje( String sMensaje ) {
        
        txaTexto.append(sMensaje);
        txaTexto.setCaretPosition(txaTexto.getDocument().getLength());
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txaComedor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaTexto = new javax.swing.JTextArea();
        lblComedor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txaComedor.setEditable(false);
        txaComedor.setColumns(20);
        txaComedor.setRows(5);
        txaComedor.setName("txaComedor"); // NOI18N
        jScrollPane1.setViewportView(txaComedor);

        txaTexto.setEditable(false);
        txaTexto.setColumns(20);
        txaTexto.setRows(5);
        txaTexto.setName("txaTexto"); // NOI18N
        jScrollPane2.setViewportView(txaTexto);

        lblComedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblComedor.setText("Comedor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblComedor)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblComedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblComedor;
    public static javax.swing.JTextArea txaComedor;
    public static javax.swing.JTextArea txaTexto;
    // End of variables declaration//GEN-END:variables
}
