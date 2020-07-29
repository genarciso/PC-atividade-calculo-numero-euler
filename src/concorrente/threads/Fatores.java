package concorrente.threads;

import java.util.concurrent.Callable;

public class Fatores implements Callable<Double> {
	
	private int indice;
	
	public Fatores(int indice) {
		this.indice = indice;
	}
	
	@Override
	public Double call() throws Exception {
		Thread currentThread = Thread.currentThread();
		System.out.printf("Thread %s está calculando índice %d ... \n",currentThread.getId(), this.indice);
		return 1.00 / calcularFatorial(indice);
	}
	
	private int calcularFatorial(int numero) {
		if (numero <= 1) {
			return 1;
		}
		return numero * calcularFatorial(numero-1);
	}

}
