import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Mapa extends JFrame
{    
	 public ImageIcon imageIcon = new ImageIcon("./img/g2.jpg");
	 public int xPos, yPos;
	 public DetectorMovimiento d;
	 public ReceptorAlarma ra;
	 
	 public Mapa() 
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
