//package isPrime;

public class Main {

	public static void main(String[] a) throws InterruptedException {
		boolean respuesta1;
		boolean respuesta2;

        int prueba = 104543;//Número que queremos decidir si es o no un número primo.

		long iniSec = System.currentTimeMillis();
        isPrime secuencial = new isPrime(1);
        //respuesta1 = secuencial.isPrime(1045430); 
        respuesta1 = secuencial.isPrime(prueba);
        long finSec = System.currentTimeMillis();

        long tiempo01 = finSec - iniSec;

        long iniPar = System.currentTimeMillis();
        isPrime paralelo = new isPrime(8);
        respuesta2 = paralelo.isPrime(prueba);
        long finPar = System.currentTimeMillis();

        long tiempo02 = finPar -iniPar;
      
        System.out.println("Comparacion ejercicio 01");
        System.out.println("El primo a comparar es el numero: "+ prueba);
        System.out.println("El tiempo en el algoritmo secuencial tarda: " + tiempo01 + respuesta1);
        System.out.println("El tiempo en el algoritmo paralelo tarda: " + tiempo02 + respuesta2);
	}
}