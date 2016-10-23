
import java.io.*;
import java.util.Scanner;
import java.security.*;
import java.io.FileWriter;

public class Cert {

public static void main(String[] args)
{
        BufferedReader br;
        String buffe ="";
        String motLu;
        String ffinal = "";
        byte[] buffer, resume;
        MessageDigest fonction_de_hachage;
        try
        {
                br = new BufferedReader(new FileReader("email1.txt"));
                while((motLu = br.readLine()) != null) // Lit email1 Ligne par ligne
                {
                        if(motLu.contains("Message-Id")) // A partir de la derniere ligne de l'entete
                        {
                                br.readLine(); // saute une ligne ( la ligne vide)
                                while((motLu = br.readLine()) != null) // Lit le corps du message et l'ajoute a ffinal
                                {
                                        ffinal += motLu;
                                        ffinal += "\r\n";
                                }
                        }
                }
                ffinal +="c5dcb78732e1f3966647655229729843\r\n"; // ajout du secret
                System.out.println('"'+ffinal+'"');
        } catch (Exception e) { e.printStackTrace(); }

        try
        {
                buffer = ffinal.getBytes();  // On récupère les octets de la chaîne
                fonction_de_hachage = MessageDigest.getInstance("MD5"); // On charge MD5
                resume = fonction_de_hachage.digest(buffer); // On calcule le résumé
                System.out.print("Le résumé MD5 de cette chaîne est: ");
                for(byte octet: resume)
                        System.out.print(String.format("%02X", octet));
                // On affiche le résumé en hexadécimal
                System.out.println();
                try
                {
                        br = new BufferedReader(new FileReader("email1.txt"));
                        while((motLu = br.readLine()) != null) // On lit le fichier email1 ligne par ligne
                        {
                                buffe += motLu+"\r\n"; // On ajoute chaque ligne dans buffe
                                if(motLu.contains("Message-Id")) // A la fin de l'entete on ajoute la ligne X-UdC_authentique avec le résumé
                                {
                                        buffe +="X-UdC_authentique: ";
                                        for(byte octet: resume) // On ajoute le résumé
                                                buffe += String.format("%02X", octet);
                                        buffe += "\r\n";
                                }
                        }
                } catch (Exception e) { e.printStackTrace(); }
        } catch (Exception e) { e.printStackTrace(); }
        try
        {
                File f = new File("email1-secure.txt");
                f.createNewFile();
                FileWriter fw = new FileWriter(f); // On ecrit dans email1-secure
                fw.write(buffe);
                fw.close();
        } catch (Exception e) {e.printStackTrace();}
        System.out.println('"'+buffe+'"');
}

}
