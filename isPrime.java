import java.lang.Math;

/**
 * Montoya Montes Pedro
 * 
 * Clase isPrime, que dado un número n calcula si es primo o no con ayuda de hilos y teorema de Teoria de números.
 *
 * los hilos pueden ser cambiados en su constructor y busca cualquier valor que divida a la raíz del número a veriticar
 *
 */
public class isPrime implements Runnable{

	private int threads;
	private static int numPrimo;
	public static boolean result;
	public static int intervalLength;
	public static boolean[] answerArray;

	/**
	 * Constructor isPrime que si no se reciben parámetros, inicia con un solo hilo.
	 */

	public isPrime() {
		this.threads = 1;
	}

	/**
	 * Constructor isPrime que, dado un número threads, crealo dicha cantidad de hilos.
	 */
	public isPrime(int threads) {
		this.threads = threads > 1 ? threads : 1;	//Si el valor es menor a 1, solo crea un solo hilo.
	}

	/** Método isPrime, que dado un entero n, decide si n es primo o no.
	  * @return true si n es primo.
	  * @return false si n es compuesto.
	  */
	public boolean isPrime(int n) throws InterruptedException{
		result = true;                          	//Por defecto el valor es 'true' (si se llega a ser divisible entre algún i, se cambia).
		numPrimo = n;
		int sqrtPrime = (int)Math.ceil(Math.sqrt(n));//Sacamos la raíz del valor, le aplicamos la función techo, y "casting" a tipo int.

		intervalLength = Math.abs((Integer) sqrtPrime/threads);//Los sub-intervalos donde se movera nuestros hilos.
		answerArray = new boolean[threads];			//Creamos un arreglo para evitar la sección critica.

		if(numPrimo < 2)							//Verificar si no es 0 o 1 el valor.
			return false; 
		
		Runnable process = () -> {//Lambda para evitar hacer uso de run()
			int idThread = Integer.valueOf(Thread.currentThread().getName()); //Obtenemos el número de hilo.

			if(intervalLength*(idThread+1) <= numPrimo){//Para evitar salirnos del rango y hacer calculos inecesarios.

				int interval = (intervalLength * idThread);
				/** FOR: inicia en el primer valor de nuestro subIntervalor; acaba al final del mismo;aumenta i */
				for(int i = interval; i < interval + intervalLength ; i++){  

					if(i<2) 				//Quitamos el caso donde i es igual a cero, uno o dos.
						i=3;

					answerArray[idThread] = (numPrimo % i == 0) ? (false): true;//Si numPrimo es divisible entre cualquier valor i,
																				//en la posición que corresponde al hilo lo hacemos false, true en otro caso.
				}
			}
		};

		/**
		 * Inicializamos los hilos y los procesamos.
		 */
		for(int i = 0; i < threads; i++){ //
			Thread t = new Thread (process);
			t.setName(String.valueOf(i));
			t.start();
			t.join();
		}
	
		for (boolean b : answerArray) {//Recorremos el arreglo de respuestas, si alguna es false, hará a la variable result 'false'.
			result = result&&b;
		}
		return result;
	}

	public void run(){}
}
