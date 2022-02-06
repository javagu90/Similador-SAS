/**
 * 
 */

/**
 * @author Jav
 *
 */

import javax.swing.JFrame;  
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class DetectorMovimiento extends JFrame
{
 public Container contenedor;
 public JPanel panel;
 public JTextArea etiqueta;
 public StringBuilder mensaje;
 public Font fuente;
 
 public DetectorMovimiento()
 {
  super("Emisor");
  
  contenedor = getContentPane();
  contenedor.setLayout(new BorderLayout());
  
  panel=new JPanel(new FlowLayout());
  
  mensaje=new StringBuilder("No se ha detectado movimiento");
  
  fuente=new Font("Arial", 0, 20);
  
  etiqueta=new JTextArea(50,20);
  etiqueta.setText(mensaje.toString());
  etiqueta.setOpaque(false);
  etiqueta.setEditable(false);
  etiqueta.setLineWrap(true);
  etiqueta.setFont(fuente);
  
  panel.add(etiqueta);
  
  contenedor.add(panel, BorderLayout.CENTER);
  
  setSize(700, 250);
  
  Dimension dim_pantalla = Toolkit.getDefaultToolkit().getScreenSize();
      
  // Una cuenta para situar la ventana en el centro de la pantalla.
  setLocation( (dim_pantalla.width - this.getWidth())   / 2,
               (dim_pantalla.height - this.getHeight()) / 2);
  setVisible(true);
  setResizable(false);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 }
 
 public boolean movimientoActivado(boolean bandera)
 {
	 System.out.println(bandera);
	 return(bandera)?true:false;
 }
 
 public int definirEscala(boolean bandera)
 {
	 int escala=0;
	 if(bandera)
	 {
		 mensaje.replace(0, mensaje.length(), "Se ha detectado movimiento");
		 etiqueta.setText(mensaje.toString()); 
		 escala= (int) (Math.random() *(10-4)+4);
	 }
	 System.out.println(escala);
	 return escala;
 } 
}
