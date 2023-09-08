package application;

import java.util.Scanner;

import entities.Estados;

public class Program {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Informe a quantidade de letras do alfabeto.");
		char[] letrasAlfabeto = new char[sc.nextInt()];
		for (int i = 0; i < letrasAlfabeto.length; i++) {
			System.out.println("Informe uma letra do alfabeto:");
			letrasAlfabeto[i] = sc.next().charAt(0);
		}

		System.out.println("Informe a quantidade de letras do alfabeto auxiliar.");
		char[] letrasAlfabetoAux = new char[sc.nextInt()];
		for (int i = 0; i < letrasAlfabetoAux.length; i++) {
			System.out.println("Informe uma letra do alfabeto:");
			letrasAlfabetoAux[i] = sc.next().charAt(0);
		}

		System.out.println("Digite a quantidade de estados.");
		Estados[] arrayEstados = new Estados[sc.nextInt()];
		for (int i = 0; i < arrayEstados.length; i++) {
			arrayEstados[i] = new Estados(); //
		}

		System.out.println("Informe o estado inicial.");
		int estadoInicial = sc.nextInt();
		System.out.println("Informe a quantidade de estados finais.");
		int quantEstadosFinais = sc.nextInt();
		for (int i = 0; i < quantEstadosFinais; i++) {
			System.out.println("Digite um estado final.");
			arrayEstados[sc.nextInt()].setIsFinal();
		}
		System.out.println("Digite um marcador de inicio");
		char marcadorInicio = sc.next().charAt(0);
		System.out.println("Digite um simbolo para o branco");
		char simboloBranco = sc.next().charAt(0);
		System.out.println("Informe a palavra a ser testada");
		String auxPalavra = sc.next();
		char[] palavraSerTestada = new char[auxPalavra.length() + 2];
		palavraSerTestada[0] = marcadorInicio;
		palavraSerTestada[palavraSerTestada.length - 1] = simboloBranco;
		for (int i = 1; i <= auxPalavra.length(); i++) {
			palavraSerTestada[i] = auxPalavra.charAt(i - 1); // Inserindo cada letra da palavra
		}

		System.out.println("==== TABELA DE TRANSICAO =====");
		System.out.print("      ");
		for (int i = 0; i < letrasAlfabeto.length && i < letrasAlfabetoAux.length; i++) {
			System.out.print(letrasAlfabeto[i] + "        " + letrasAlfabetoAux[i] + "        ");
		}
		System.out.print(marcadorInicio);
		System.out.print("        " + simboloBranco);
		System.out.println();
		for (int i = 0; i < arrayEstados.length; i++) {
			System.out.print("S" + i);
			System.out.print("    ");
			for (int j = 0; j < letrasAlfabeto.length + letrasAlfabetoAux.length + 2; j++) {
				System.out.print("[" + i + "," + j + "]    ");
			}
			System.out.println();

		}
		System.out.println();
		System.out.println("Digite as transicoes:");
		System.out.println("Obs 1: caso nao haja transicoes, insira 51:");
		System.out.println("Obs 2: qualquer transicao invalida fara com que o campo seja anulado.");
		System.out.println();
		System.out.println("----------------------------------------------------------");
		int debugger = 0;
		for (int i = 0; i < arrayEstados.length; i++) {
			for (int j = 0; j < letrasAlfabeto.length + letrasAlfabetoAux.length + 2; j++) {
				System.out.print("Digite o estado futuro da transicao [" + i + "," + j + "]: ");
				int suporte = sc.nextInt();

				// Verifica se o estado futuro é 'X' e trata como anulação
				if (suporte == 51) {
					arrayEstados[i].setEstadoFuturo(51);
					System.out.println("O campo será anulado!");
					arrayEstados[i].setLetrasAux('X');
					arrayEstados[i].setDirecao('X');
				} else {
					arrayEstados[i].setEstadoFuturo(suporte);
					System.out.print("Digite o alfabeto futuro da transicao [" + i + "," + j + "]: ");
					arrayEstados[i].setLetrasAux(sc.next().charAt(0));
					System.out.println(
							"Digite a direcao da transicao: [" + i + "," + j + "] (D para direita ou E para esquerda)");
					arrayEstados[i].setDirecao(sc.next().charAt(0));
				}

				System.out.println("----------------------------------------------------------");
				debugger = debugger + 1;
			}
		}

		System.out.println();
		System.out.print("Fita inicio");
		for (char c : palavraSerTestada) {
			System.out.print(c);
		}

		// mudar a maneira de imprimir o header lá em cima
		int tamanhoTotal = (letrasAlfabeto.length + letrasAlfabetoAux.length);
		char[] header = new char[tamanhoTotal + 2];
		header[(header.length - 1)] = simboloBranco;
		header[(header.length - 2)] = marcadorInicio;
		int index = 0;
		int breaker = 0;

		for (int i = 0; i < letrasAlfabeto.length; i++) {
			header[index] = letrasAlfabeto[i];
			index += 2;
		}

		index = 1;

		for (int i = 0; i < letrasAlfabetoAux.length; i++) {
			header[index] = letrasAlfabetoAux[i];
			index += 2;
		}

		// header == parte de cima das transicoes

		// a string da palavra ser testada eh pro loop
		boolean aceito = false;
		int estadoAtual = estadoInicial;
		int posicaoNaPalavra = 1; // 1 pra n começar no > marcadorInicio

		while (aceito == false) {

			for (int i = 0; i < header.length; i++) { // mudei pra header pra teste

				if (palavraSerTestada[posicaoNaPalavra] == header[i]) {

					if (arrayEstados[estadoAtual].getEstadoFuturo(i) != 51) {
						palavraSerTestada[posicaoNaPalavra] = arrayEstados[estadoAtual].getLetrasAux(i);

						if (arrayEstados[estadoAtual].getDirecao(i) == 'D') {
							posicaoNaPalavra = posicaoNaPalavra + 1;

						}

						if (arrayEstados[estadoAtual].getDirecao(i) == 'E') {
							posicaoNaPalavra = posicaoNaPalavra - 1;

						}
						estadoAtual = arrayEstados[estadoAtual].getEstadoFuturo(i);

					}
				}

			}
			if (arrayEstados[estadoAtual].isFinal() == true && posicaoNaPalavra == 1) {
				aceito = true;
				System.out.println();
				System.out.println("A palavra foi aceita.");
			}
			breaker = +1;
			if (breaker > 1000) {
				System.out.println("A palavra nao foi aceita ou vai demorar demais para ser processada");
				break;
			}
		}

		System.out.println();
		System.out.print("Fita apos as transicoes: ");
		for (char d : palavraSerTestada) {
			System.out.print(d);
		}

		sc.close();
	}

}
