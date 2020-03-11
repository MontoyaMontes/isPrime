/**
 * Montoya Montes Pedro
 * 
 * Clase principal, que prueba isPrime con 1 y 8 hilos con un valor.
 *
 */
public class Main {

	public static void main(String[] a) throws InterruptedException {
		boolean respuesta1;
		boolean respuesta2;

		int prueba = 104543;				//Número que queremos decidir si es o no un número primo.

		isPrime secuencial = new isPrime(1); 		//Iniciamos isPrime con un hilo.
		respuesta1 = secuencial.isPrime(prueba);

		isPrime paralelo = new isPrime(8); 		//Iniciamos isPrime con 8 hilos.
		respuesta2 = paralelo.isPrime(prueba);
	      
		System.out.println("El valor a comparar es el número: \n"+ prueba);
		System.out.println("El resultado secuencial es: \n"  + respuesta1);
		System.out.println("El resultado paralelo es: \n" + respuesta2);
	}
}