// -*- coding: utf-8 -*-

import java.io.*;
import java.security.*;

public class ResumeChaine {
    public static void main(String[] args) {
	byte[] buffer, resume;
	MessageDigest fonction_de_hachage;
	// Calcul de l'empreinte MD5 d'une chaîne de caractères
	String message= "Alain Turin";
	try {
	    System.out.println("Message à hacher: \"" + message +"\"");
	    buffer = message.getBytes();          // On récupère les octets de la chaîne
	    fonction_de_hachage = MessageDigest.getInstance("MD5");     // On charge MD5
	    resume = fonction_de_hachage.digest(buffer);         // On calcule le résumé
	    System.out.print("Le résumé MD5 de cette chaîne est: 0x");
	    for(byte octet: resume)
		System.out.print(String.format("%02X", octet));
	    // On affiche le résumé en hexadécimal
	    System.out.println();
	} catch (Exception e) { e.printStackTrace(); }
	try {
	    System.out.println("Message à hacher: \"" + message +"\"");
	    buffer = message.getBytes();          // On récupère les octets de la chaîne
	    fonction_de_hachage = MessageDigest.getInstance("SHA1");   // On charge SHA1
	    resume = fonction_de_hachage.digest(buffer);         // On calcule le résumé
	    System.out.print("Le résumé SHA1 de cette chaîne est: 0x");
	    for(byte octet: resume)
		System.out.print(String.format("%02X", octet));
	    // On affiche le résumé en hexadécimal
	    System.out.println();
	} catch (Exception e) { e.printStackTrace(); }
    }
}

/* 
> javac ResumeChaine.java 
> java ResumeChaine
Message à hacher: "Alain Turin"
Le résumé MD5 de cette chaîne est: 0xC5DCB78732E1F3966647655229729843
Message à hacher: "Alain Turin"
Le résumé SHA1 de cette chaîne est: 0x9B682F2CA6F44CB60493288A686DE5D81ECA6B6D
>
*/

