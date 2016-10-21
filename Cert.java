
import java.io.*;
import java.util.Scanner;
import java.security.*;

public class Cert {
    public static void main(String[] args) {
		BufferedReader br;
		String motLu;
		String ffinal = "";
		int compteur =0;
		byte[] buffer, resume;
		MessageDigest fonction_de_hachage;
		try {
			br = new BufferedReader(new FileReader("email1.txt"));
			while((motLu = br.readLine()) != null)
			{
				compteur ++;
				if(compteur >= 15)
				{
					ffinal += motLu;
					ffinal += "\r\n";
				}
			}
			System.out.println('"'+ffinal+'"');
		} catch (Exception e) { e.printStackTrace(); }
		try {
			buffer = ffinal.getBytes();          // On récupère les octets de la chaîne
			fonction_de_hachage = MessageDigest.getInstance("MD5");     // On charge MD5
			resume = fonction_de_hachage.digest(buffer);         // On calcule le résumé
			/*System.out.print("Le résumé MD5 de cette chaîne est: ");
			for(byte octet: resume)
			System.out.print(String.format("%02X", octet));*/
			// On affiche le résumé en hexadécimal
			System.out.println();
		} catch (Exception e) { e.printStackTrace(); }
    }
}

