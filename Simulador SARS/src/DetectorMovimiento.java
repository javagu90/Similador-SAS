/**
 * 
 */

/**
 * @author Jav
 *
 */
import java.awt.Graphics;  
import javax.swing.ImageIcon;  
import javax.swing.JFrame;  
import java.awt.*;
import java.awt.event.*;
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

@SuppressWarnings("serial")
class ReceptorAlarma extends JFrame
{
	public Container contenedor;
	public JPanel panel;
	public JTextArea etiqueta;
	public StringBuilder mensaje;
	public Font fuente;
	
	public ReceptorAlarma ()
	{
		  super("Receptor-Alarma");
		  
		  contenedor = getContentPane();
		  contenedor.setLayout(new BorderLayout());
		  
		  panel=new JPanel(new FlowLayout());
		  
		  fuente=new Font("Arial", 0, 20);
		  
		  etiqueta=new JTextArea(50,20);
		  //etiqueta.setText(mensaje.toString());
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
		  setVisible(false);
		  setResizable(false);
	}
	
	public void tipoAlarma(int escala)
	{
		mensaje=new StringBuilder();
		if(escala>=6)
		{
			etiqueta.setForeground(Color.RED);
			mensaje.append("ALARMA!!! EVACUE LA ZONA");
			etiqueta.setText(mensaje.toString());
			this.setVisible(true);
			mensaje.delete(0, mensaje.length());
		}
		else
		{
			etiqueta.setForeground(Color.BLACK);
			mensaje.append("Alarma preventiva");
			etiqueta.setText(mensaje.toString());
			this.setVisible(true);
			mensaje.delete(0, mensaje.length());
		}
	}
}

@SuppressWarnings("serial")
class JFrameBackground extends JFrame 
{    
 public ImageIcon imageIcon = new ImageIcon("./img/g2.jpg");
 public int xPos, yPos;
 public DetectorMovimiento d;
 public ReceptorAlarma ra;
 
 public JFrameBackground() 
 {  
   super( "Detector de Sismo (MAPA)" );
  getContentPane().addMouseListener( new ManejadorClicsRaton() );   
  setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
  setResizable(false);
  setVisible(true);
  d= new DetectorMovimiento();
  ra= new ReceptorAlarma();
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 }
 
 public void paint(Graphics g) 
 { 
   g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);  
   g.setColor(Color.red);
   g.fillOval(xPos,yPos, 9, 9);
   g.drawOval(xPos-4,yPos-3, 15, 15);
   g.drawOval(xPos-9,yPos-8, 25, 25);
   g.drawOval(xPos-14,yPos-13, 35, 35);
   g.drawOval(xPos-19,yPos-18, 45, 45);
 }
 
  private class ManejadorClicsRaton extends MouseAdapter {
 
      // manejar evento de clic del rat?n y determinar cu?l bot?n se oprimi?
      public void mouseClicked(MouseEvent evento)
      {
    	 repaint();
         xPos = evento.getPoint().x+5;
         yPos = evento.getPoint().y+28;
         int esc=d.definirEscala(d.movimientoActivado(true));
         if(esc>=6)
         {
        	 d.etiqueta.setForeground(Color.RED);
        	 d.mensaje.append("\nMOVIMIENTO TELURICO DE:\n\n"+ esc+" GRADOS RICHTER\n\n");
        	 d.mensaje.append("ENVIANDO SE?AL PARA ACTIVAR ALARMA");
        	 d.etiqueta.setText(d.mensaje.toString());
        	 System.out.println(d.mensaje.toString());
         }
         else
         {
        	 d.etiqueta.setForeground(Color.BLACK);
        	 d.mensaje.append("\nMOVIMIENTO TELURICO DE:\n\n"+ esc+" GRADOS RICHTER\n");
        	 d.etiqueta.setText(d.mensaje.toString());
         }
         ra.tipoAlarma(esc);
         
      } // fin del m?todo mouseClicked
 
   } // fin de la clase interna privada ManejadorClicsRaton
} 
