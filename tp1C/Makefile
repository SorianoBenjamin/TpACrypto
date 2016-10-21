CC = gcc

INCLUDE = -I/usr/local/include -I/usr/include
LIB = -L/usr/local/lib -L/usr/lib -lm -lssl -lcrypto -g -Wall

all: cert

cert: cert.c
	$(CC) -o cert $(INCLUDE) cert.c $(LIB)



clean:
	-rm *.o *~
	-rm cert
