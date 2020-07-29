# PC-atividade-calculo-numero-euler

## Autores:
[Gabriel Estevam Narciso](https://github.com/genarciso)

[Júlia Ferreira de Souza Glória](https://github.com/juliafsg)
___
## Objetivos:
Implementar e avaliar mecanismos avançados providos pela linguagem de programação Java dentre as Java Concurrent Utilities.

___
## Descrição do problema:
* O programa deve ser capaz de realizar o cálculo do número de euler mediante uma série infinita utilizando threads independentes e concorrentes. 
* Devem ser utilizadas e comparadas thread pools de diferentes tipos disponibilizados pelo framework Executor e sua classe Executors.(fixed, cached e work stealing thread pools).
* Deverá ser verificado e informado na saída padrão quantas threads estão sendo utilizadas e o tempo de execução do programa.

___
## Metodologia:
Utilizamos o ExecutorService para implementar os 3 programas distintos e empregamos os tipos Callable e Future para facilitar a distribuição das tarefas de forma concorrente. Para computar o total de threads que foram usadas, foi aplicado o método Thread.activeCount(). E para avaliar o tempo de execução manipulamos o método System.currentTimeMillis().

___
## Execução:
O programa pode ser executado por meio de uma IDE, como o Eclipse, no qual deve ser importado o projeto e através da IDE utilizada rodar uma das classes que possuem o método main: CachedThreadMain.java, WorkStealingMain.java ou FixedThreadMain.java.

Caso deseje rodar por linha de comando, desloque-se dentro da pasta onde localiza-se o Main do projeto (src/main), la pode ser executado os comandos: 

``javac -d CachedThreadMain.java``
 
``javac -d FixedThreadMain.java ``

``javac -d WorkStealingMain.java``

Esse comando irá compilar o projeto java e o seguinte comando pode ser utilizado para executar o programa:

``java CachedThreadMain N``

``java FixedThreadMain N T``

``java WorkStealingMain N``

É importante ressaltar que N = número de termos para o cálculo do número de euler e T = número de threads a serem utilizadas. Ambos devem ser valores maiores que 0.


