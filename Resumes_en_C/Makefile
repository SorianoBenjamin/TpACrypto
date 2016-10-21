CC = gcc

INCLUDE = -I/usr/local/include -I/usr/include
LIB = -L/usr/local/lib -L/usr/lib -lm -lssl -lcrypto -g -Wall

all: resumes resumes_chaine cert

cert: cert.c
	$(CC) -o cert $(INCLUDE) cert.c $(LIB)

resumes: resumes.c
	$(CC) -o resumes $(INCLUDE) resumes.c $(LIB)

resumes_chaine: resumes_chaine.c
	$(CC) -o resumes_chaine $(INCLUDE) resumes_chaine.c $(LIB)

clean:
	-rm *.o *~
	-rm resumes
	-rm resumes_chaine
	-rm cert
