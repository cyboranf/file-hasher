package com.filehasher.filehasher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//TODO:
/*

Przygotuj serwis WWW, który pozwala przesyład pliku na serwer metodą drag & drop,
i zwraca wartośd funkcji skrótu dla przesłanego pliku. Jako funkcję skrótu można wybrad np.
MD5, SHA1, SHA-256,SHA-512.

 */

@SpringBootApplication
public class FileHasherApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileHasherApplication.class, args);
	}

}
