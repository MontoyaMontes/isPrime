import java.lang.Math;

public class isPrime implements Runnable{

	private int threads;
	private static int numPrimo;
	public static boolean result;
	public static int longitudSubInter;
	public static boolean[] answerArray;

	public isPrime() {
		this.threads = 1;
	}

	/**Método isPrime que, dado un número threads, crealo dicha cantidad de hilos.*/
	public isPrime(int threads) {
		this.threads = threads > 1 ? threads : 1;
	}

	/** Método isPrime, que dado un entero n, decide si n es primo o no.
	  * @return True si n es primo, False en otro caso.
	  */
	public boolean isPrime(int n) throws InterruptedException{
		result = true;                          //Por defecto el valor es 'true' (si se llega a ser divisible entre algún i, se cambia).
		int sqrtPrime = (int)Math.ceil(Math.sqrt(n));//Sacamos la raíz del valor, le aplicamos la función techo, y "casting" a tipo int.

		numPrimo = n;					//Guardamos el valor del número para posterior uso, funcion abs para evitar casos donde n es negativo.
	    longitudSubInter = Math.abs((Integer) sqrtPrime/threads);//Los sub-intervalos donde se movera nuestros hilos.
		answerArray = new boolean[threads];

		if(numPrimo == 0 || numPrimo == 1 )
			return false; 
		
		Runnable process = () -> {
			//System.out.println("HM");
			int idThread = Integer.valueOf(Thread.currentThread().getName()); 

			if(longitudSubInter*(idThread+1) <= numPrimo){//Para evitar salirnos del rango y hacer calculos inecesarios.

				/** FOR: inicia en el primer valor de nuestro subIntervalor; acaba al final del mismo;aumenta i */
				for(int i = (longitudSubInter * idThread); i <(longitudSubInter * idThread)+ longitudSubInter ; i++){  

					if(i<2) 				//Quitamos el caso donde i es igual a cero, uno o dos.
						i=3;
					answerArray[idThread] = (numPrimo % i == 0) ? (false): true;
				}
			}
		};

		//Cada sub-intervalo se procesará en los diferentes hilos.
		
		for(int i = 0; i < threads; i++){ ///Aquí se puede cambiar a una clase anonima con Runnable.
			Thread t = new Thread (process);
			t.setName(String.valueOf(i));
			t.start();
			t.join();
		}
	
		for (boolean b : answerArray) {
			result = result&&b;
			System.out.println("FOR RES:"+result);
		}
		return result;
	}
	public void run(){}
}
