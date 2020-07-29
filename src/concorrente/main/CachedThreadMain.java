package concorrente.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import concorrente.threads.Fatores;

public class CachedThreadMain {
	
	public static void main(String[] args) {
		
		// Calcular tempo inicial
		long tempoInicial = System.currentTimeMillis();
		
		// Criar variáveis
		double numeroEuler = 0.0;
		int numeroTermos = -1;
		
		// Ler Argumentos
		try {
			 numeroTermos = Integer.parseInt(args[0]);
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possivel ler esse argumento. Digite um número positivo.");
		}

		if (numeroTermos < 0) {
			throw new IllegalArgumentException("Não foi possivel ler esse argumento.  Digite um número maior que 0.");
		}	
		
		//if (numeroTermos >=35) {
		//	throw new IllegalArgumentException("Por favor, digite um número menor que 35.");
		//}	
		
		// Criar Classes 
		ExecutorService	executor = Executors.newCachedThreadPool();
		List<Future<Double>> resultados = new ArrayList<Future<Double>>();

		// Executar tarefa do calculo do fatorial
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
		System.out.println("\n---- Cached Thread Pool ----");
		System.out.printf("Foram utilizadas %d threads nessa execução \n", activeThread);
		System.out.printf("O resultado do número de Euler com n = %d foi: %s \n", numeroTermos, String.valueOf(numeroEuler));
		System.out.println("Tempo de Execução: " + tempoFinal + " ms \n");
	}

}
