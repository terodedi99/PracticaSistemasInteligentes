package esi.uclm.main;

import esi.uclm.util.DibujaLaberinto;
import esi.uclm.util.JSONParser;
import esi.uclm.maze.Laberinto;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

 /*****************************************************************************
 * 
 * Class Name: MazeGenGUI
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: es la encargada de mostrar la interfaz 
 * con la que podremos realizar pruebas cómo (Generación,Exportación,Lectura..) 
 * 
 *****************************************************************************/
public class MazeGenGUI extends javax.swing.JFrame {

    private int txtFilasLeidas;
    private int txtColumnasLeidas;
    private DibujaLaberinto dibujo;
    private BufferedImage imagen;
    private Laberinto lab_1;
    
     /*****************************************************************************
     * 
     * Constructor Name: MazeGenGui
     * Author/s Name: Antonio, Luis y Teresa
     * Description of constructor: Inicializa los elementos de la GUI 
     * 
     *****************************************************************************/
    public MazeGenGUI() {
        initComponents();
    }
    
    /*****************************************************************************
    * 
    * Method Name: Imprimir
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: Escribe la imagen proporcionada en la ruta 
    * dada. (Formato JPG)
    * 
    *****************************************************************************/
    private void imprimir(BufferedImage imagen,String ruta){
        try {
            ImageIO.write(imagen, "jpg", new File(ruta + ".jpg"));
        }catch (IOException ex) {
            System.out.println("Se ha producido el siguiente error al generar la imagen "+ex.getMessage());
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFilas = new javax.swing.JTextField();
        txtColumnas = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        btnExportarImagen = new javax.swing.JButton();
        btnExportarJson = new javax.swing.JButton();
        btnCargarJson = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generación de laberintos");
        setResizable(false);

        jPanel1.setName(""); // NOI18N

        jLabel1.setText("Valor filas: ");

        jLabel2.setText("Valor columnas: ");

        btnGenerar.setText("Generar laberinto");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnExportarImagen.setText("Exportar imagen");
        btnExportarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarImagenActionPerformed(evt);
            }
        });

        btnExportarJson.setText("Exportar Json");
        btnExportarJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarJsonActionPerformed(evt);
            }
        });

        btnCargarJson.setText("Cargar Json");
        btnCargarJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarJsonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFilas, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(txtColumnas))
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCargarJson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportarJson, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnGenerar)
                    .addComponent(txtFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarJson))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExportarImagen)
                        .addComponent(btnExportarJson)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setLocationRelativeTo(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
     /*****************************************************************************
     * 
     * Method Name: btnGenerarActionPerformed
     * Author/s Name: Antonio, Luis y Teresa
     * Description of method: Este método corresponde al evento de pulsar el 
     * botón generar en la GUI
     * 
     *****************************************************************************/
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        try{
            //Obtenemos los datos
            txtFilasLeidas = Integer.parseInt(txtFilas.getText());
            txtColumnasLeidas = Integer.parseInt(txtColumnas.getText());

            //Se crea el laberinto y se realiza el algoritmo de Wilson
            lab_1 = new Laberinto (txtFilasLeidas, txtColumnasLeidas);
            lab_1.generarLaberinto();

            //Generar imagen
            JFrame frame = new JFrame("Laberinto generado ["+txtFilasLeidas+" x "+ txtColumnasLeidas+"]");
            frame.setLocationRelativeTo(null);

            dibujo = new DibujaLaberinto();
            dibujo.setLaberinto(lab_1);
            dibujo.setSize(dibujo.getX_final(),dibujo.getY_final());

            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(dibujo);
            frame.pack();

            //Maximizamos la ventana y la hacemos visible
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);

            //Generación previa de la imagen(para posteriormente imprimirla si se desea)
            //JComponent
            imagen = new BufferedImage(960,960,BufferedImage.TYPE_INT_RGB);

            //Background blanco
            Graphics2D drawer = imagen.createGraphics() ;
            drawer.setBackground(Color.WHITE);
            drawer.clearRect(0,0,960,960);

            //Super importante
            dibujo.printAll(imagen.getGraphics());
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Tienes que introducir los datos correctos","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtFilas.setText("");
            txtColumnas.setText("");
        }
        
    }//GEN-LAST:event_btnGenerarActionPerformed

     /*****************************************************************************
     * 
     * Method Name: btnExportarImagenActionPerformed
     * Author/s Name: Antonio, Luis y Teresa
     * Description of method: Este método corresponde al evento de pulsar el 
     * botón exportar la imagen del laberinto en la GUI
     * 
     *****************************************************************************/
    private void btnExportarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarImagenActionPerformed
        
        if (imagen != null) {
            //Para poder seleccionar la ruta donde lo vamos a guardar
            JFileChooser fileChooser = new JFileChooser();
            //Filtro para guardar en JPG
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");
            fileChooser.setFileFilter(filter);
        
            int seleccion = fileChooser.showSaveDialog(this);
            //Hemos decidido guardarlo
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                //Obtenemos la ruta y lo guardamos
                String ruta = fileChooser.getSelectedFile().toString();
                imprimir(imagen, ruta);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Es necesario generar el laberinto previamente","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExportarImagenActionPerformed
    
     /*****************************************************************************
     * 
     * Method Name: btnExportarJsonActionPerformed
     * Author/s Name: Antonio, Luis y Teresa
     * Description of method: Este método corresponde al evento de pulsar el 
     * botón exportar JSon en la GUI
     * 
     *****************************************************************************/
    private void btnExportarJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarJsonActionPerformed
        
        if (lab_1 != null) {
            //Para poder seleccionar la ruta donde lo vamos a guardar
            JFileChooser fileChooser = new JFileChooser();
            //Filtro para guardar en JSON
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
            fileChooser.setFileFilter(filter);

            int seleccion = fileChooser.showSaveDialog(this);
            //Hemos decidido guardarlo
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                //Obtenemos la ruta y lo guardamos
                String ruta = fileChooser.getSelectedFile().toString();
                //Exportar el laberinto a JSON
                JSONParser parser = new JSONParser();
                parser.parseToJSON(lab_1,ruta);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Es necesario cargar un laberinto previamente","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnExportarJsonActionPerformed
    
     /*****************************************************************************
     * 
     * Method Name: btnCargarJsonActionPerformed
     * Author/s Name: Antonio, Luis y Teresa
     * Description of method: Este método corresponde al evento de pulsar el 
     * botón cargar JSon en la GUI
     * 
     *****************************************************************************/
    private void btnCargarJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarJsonActionPerformed
        //Para poder seleccionar la ruta donde lo vamos a guardar
        JFileChooser fileChooser = new JFileChooser();
        //Filtro para leer archivo Json
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
        fileChooser.setFileFilter(filter);
        
        int seleccion = fileChooser.showOpenDialog(this);
        //Hemos proporcionado el fichero
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Obtenemos la ruta
            String ruta = fileChooser.getSelectedFile().toString();
            
            //LEER DE FICHERO JSON Y GENERAR IMAGEN
            JSONParser parser = new JSONParser();
            lab_1 = parser.parseToLaberinto(ruta);
            String mensaje = lab_1.comprobarLaberinto();
            if(!mensaje.equals("")){
                JOptionPane.showMessageDialog(null,mensaje,"Error",JOptionPane.ERROR_MESSAGE);
            }
            //Generar imagen
            JFrame frame2 = new JFrame("Laberinto leido de archivo Json");
            frame2.setLocationRelativeTo(null);

            dibujo = new DibujaLaberinto();
            dibujo.setLaberinto(lab_1);
            dibujo.setSize(dibujo.getX_final(),dibujo.getY_final());

            frame2.getContentPane().add(dibujo);
            frame2.pack();

            //Maximizamos la ventana y la hacemos visible
            frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame2.setVisible(true);
            
            //Generación previa de la imagen(para posteriormente imprimirla si se desea)
            //JComponent
            imagen = new BufferedImage(960,960,BufferedImage.TYPE_INT_RGB);

            //Background blanco
            Graphics2D drawer = imagen.createGraphics() ;
            drawer.setBackground(Color.WHITE);
            drawer.clearRect(0,0,960,960);

            //Super importante
            dibujo.printAll(imagen.getGraphics());
        }
    }//GEN-LAST:event_btnCargarJsonActionPerformed

     /*****************************************************************************
     * 
     * Method Name: Main
     * Author/s Name: Antonio, Luis y Teresa
     * Description of method: Main principal de la clase,Lanza la ventana y la
     * hace visible
     * 
     *****************************************************************************/

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MazeGenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MazeGenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MazeGenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MazeGenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MazeGenGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarJson;
    private javax.swing.JButton btnExportarImagen;
    private javax.swing.JButton btnExportarJson;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtColumnas;
    private javax.swing.JTextField txtFilas;
    // End of variables declaration//GEN-END:variables
}
