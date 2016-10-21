// -*- coding: utf-8 -*-

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/sha.h>
#include <openssl/md5.h>

int compare(const char* chaine1, const char* chaine2)
{
	int i =0;
	if(strlen(chaine1) != strlen(chaine2))
		return 0;
	for(i=0;i<strlen(chaine1);i++)
	{
		if(chaine1[i] != chaine2[i])
			return 0;
	}
	return 1;
}

int main()
{
  int i;
  unsigned char resume_md5[MD5_DIGEST_LENGTH];
  unsigned char resume_sha1[SHA_DIGEST_LENGTH];
  int vide = 0;
  char message[10000];
  static const char filename[] = "email1.txt";
  FILE *file = fopen ( filename, "r" );
  if ( file != NULL )
  {
     char line [ 256 ]; /* or other suitable maximum line size */

     while ( fgets ( line, sizeof line, file ) != NULL ) /* read a line */
     {
        if(vide == 1)
        {
			strcat(message, line);
		}
        if(compare(line,"\r\n"))
			vide = 1;
     }
     fclose ( file );
  }
  else
  {
     perror ( filename ); /* why didn't the file open? */
  }
  printf("\"%s\" \n", message);

  MD5_CTX contexte;
  MD5_Init (&contexte); // Initialisation de la fonction de hachage
  MD5_Update (&contexte, message, sizeof(message) -1 );
  MD5_Final (resume_md5, &contexte);
  
  printf("Le résumé MD5 de cette chaîne est: 0x");
  for(i = 0; i < MD5_DIGEST_LENGTH; i++) printf("%02x", resume_md5[i]);
  printf("\n");

  /*printf("Message à hacher: \"%s\" \n", message);
  SHA1((unsigned char*)message, sizeof(message) -1, resume_sha1);  // Il faut supprimer le '\0'
  printf("Le résumé SHA1 de cette chaîne est: 0x");
  for(i = 0; i < SHA_DIGEST_LENGTH; i++) printf("%02x", resume_sha1[i]);
  printf("\n");*/
return 0;
}

/*
> make
> ./resumes_chaine
Message à hacher: "Alain Turin" 
Le résumé MD5 de cette chaîne est: 0xc5dcb78732e1f3966647655229729843
Message à hacher: "Alain Turin" 
Le résumé SHA1 de cette chaîne est: 0x9b682f2ca6f44cb60493288a686de5d81eca6b6d
> echo -n "Alain Turin" | md5
c5dcb78732e1f3966647655229729843
> echo -n "Alain Turin" | shasum
9b682f2ca6f44cb60493288a686de5d81eca6b6d  -
>
*/
