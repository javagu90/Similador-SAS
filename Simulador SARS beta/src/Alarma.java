import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Alarma 
{
 private Player player; 
 	
 public void close()
 { 
  if (player != null) player.close();
 }

 public void play()
 {
	 System.out.println("hola");
  try 
  {
   FileInputStream fis     = new FileInputStream("./audio/alarma.mp3");
   BufferedInputStream bis = new BufferedInputStream(fis);
   player = new Player(bis);
  }
  catch (Exception e)
  {
   System.out.println("No se puede reproducir nada porque no se abrio un archivo mp3");
  }
  new Thread()
  {
   public void run()
   {
    try
    { 
     player.play();
    }
    catch (Exception e)
    { 
     System.out.println(); 
    }
   }
  }.start();
 }
}