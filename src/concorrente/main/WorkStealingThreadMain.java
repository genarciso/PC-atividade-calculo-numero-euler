package concorrente.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import concorrente.threads.Fatores;

public class WorkStealingThreadMain {

	public static void main(String[] args) {
		double numeroEuler = 0.0;
		int numeroTermos = Integer.parseInt(args[0]);
	
		ExecutorService	executor = Executors.newWorkStealingPool();
		List<Future<Double>> resultados = new ArrayList<Future<Double>>();

		for (int i = 0; i < numeroTermos; i++) {
			Callable<Double> calculoNumero = new Fatores(i);
			Future<Double> termo = executor.submit(calculoNumero);
			resultados.add(termo);
		}

		try {
			for (Future<Double> future : resultados) {
				numeroEuler += future.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
		System.out.println("---- Work Stealing Thread Pool ----");
		System.out.printf("O resultado do número de Euler com %d termos foi: %f\n", numeroTermos, numeroEuler);

	}

}
