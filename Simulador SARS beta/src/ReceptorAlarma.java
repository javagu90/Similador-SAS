import javax.swing.JOptionPane;

public class ReceptorAlarma
{
	public StringBuilder mensaje;
	
	public void tipoAlarma(int escala)
	{
		mensaje=new StringBuilder();
		if(escala>=6)
		{
			mensaje.append("ALARMA!!! EVACUE LA ZONA");
			new Alarma().play();
			JOptionPane.showMessageDialog(null,mensaje.toString(),"ALARMA", JOptionPane.WARNING_MESSAGE);
			mensaje.delete(0, mensaje.length());

		}
		else
		{
			mensaje.append("Alarma preventiva");
			JOptionPane.showMessageDialog(null,mensaje.toString(),"ALARMA", JOptionPane.INFORMATION_MESSAGE);
			mensaje.delete(0, mensaje.length());
		}
	}
}