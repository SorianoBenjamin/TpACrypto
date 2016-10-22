import java.io.*;
import java.util.Scanner;
import java.security.*;
import java.io.FileWriter;

public class Check {

    private static String trouverMD5()
    {
      BufferedReader br;
  		String motLu;
  		String ffinal = "";
  		byte[] buffer, resume;
      String md5 = "";
  		MessageDigest fonction_de_hachage;
  		try {
  			br = new BufferedReader(new FileReader("email1-secure.txt"));
  			while((motLu = br.readLine()) != null)
  			{
  				if(motLu.contains("X-UdC_authentique:"))
  				{
            br.readLine();
            while((motLu = br.readLine()) != null)
            {
    					ffinal += motLu;
    					ffinal += "\r\n";
            }
  				}
  			}
        ffinal +="c5dcb78732e1f3966647655229729843\r\n";
  		} catch (Exception e) { e.printStackTrace(); }
  		try {
  			buffer = ffinal.getBytes();          // On récupère les octets de la chaîne
  			fonction_de_hachage = MessageDigest.getInstance("MD5");     // On charge MD5
  			resume = fonction_de_hachage.digest(buffer);         // On calcule le résumé
  			System.out.print("Le résumé MD5 du corps est: ");
  			for(byte octet: resume)
  			md5+=String.format("%02X", octet);
  			// On affiche le résumé en hexadécimal
  			System.out.println();
      } catch (Exception e) { e.printStackTrace();}
      return md5;
    }

    public static void main(String[] args) {
		BufferedReader br;
    String motLu;
		String appendice = "";
    char tmp;
    String md5;
    boolean cond1 = false;
		try {
			br = new BufferedReader(new FileReader("email1-secure.txt"));
			while((motLu = br.readLine()) != null)
			{
				if(motLu.contains("X-UdC_authentique:"))
				{
          int i;
          for(i=0;i<motLu.length();i++)
          {
            if(motLu.charAt(i) == ':')
            {
              i++;
              while(motLu.charAt(i) == ' ')
                i++;
              while(i<motLu.length())
              {
                appendice+=motLu.charAt(i);
                i++;
              }
            }
          }
          cond1 = true;
				}
			}
      if(cond1 == false)
        System.out.println("Ne contient pas le champ X-UdC_authentique");
      else if(appendice.length() == 0)
        System.out.println("Ne contient pas d'appendice");
      else
        System.out.println("Appendice : \""+appendice+"\"");
        md5 = trouverMD5();
      System.out.println(md5);
      if(md5.equals(appendice))
        System.out.println("Bravo ! l'email est authentique !!!");
      else
        System.out.println("Attention ! email frauduleux ");
    } catch(Exception e) {e.printStackTrace();}
  }


}
