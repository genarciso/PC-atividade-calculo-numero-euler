package concorrente.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import concorrente.threads.Fatores;

public class FixedThreadMain {

	public static void main(String[] args) {
		
		// Calcular tempo inicial
		long tempoInicial = System.currentTimeMillis();
		
		// Criar variáveis
		double numeroEuler = 0.0;
		int numeroTermos = -1;
		int numeroThreads = -1;
		
		// Ler Argumentos
		try {
			 numeroTermos = Integer.parseInt(args[0]);
			 numeroThreads = Integer.parseInt(args[1]);
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possivel ler os argumentos. Digite dois valores positivos.");
		}
		
		if (numeroTermos <= 0 || numeroThreads <= 0 ) {
			throw new IllegalArgumentException("Não foi possivel ler os argumentos. Digite dois valores maiores que 0.");
		}			
		
		// Criar Classes 
		ExecutorService	executor = Executors.newFixedThreadPool(numeroThreads);
		List<Future<Double>> resultados = new ArrayList<Future<Double>>();

		// Executar tarefa do cálculo do fatorial
		for (int i = 0; i < numeroTermos; i++) {
			Callable<Double> calculoNumero = new Fatores(i);
			Future<Double> termo = executor.submit(calculoNumero);
			resultados.add(termo);
		}
		
		// Computar Total de Threads
		int activeThread = Thread.activeCount();

		// Somatório dos valores calculados
		try {
			for (Future<Double> future : resultados) {
				numeroEuler += future.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
		
		// Calcular tempo de execução
		long tempoFinal = System.currentTimeMillis() - tempoInicial;
		
		// Imprimir resultado
		System.out.println("\n---- Fixed Thread Pool ----");
		System.out.printf("Foram utilizadas %d threads nessa execução \n", activeThread);
		System.out.printf("O resultado do número de Euler com n = %d foi: %s \n", numeroTermos, String.valueOf(numeroEuler));
		System.out.println("Tempo de Execução: " + tempoFinal + " ms \n");
		

	}

}
