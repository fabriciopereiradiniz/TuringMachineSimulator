package entities;

public class Estados {

	private char[] direcao = new char[100];
	private char[] letrasAux = new char[100];
	private int[] estadoFuturo = new int[100];
	private boolean isFinal = false;

	public Estados() {

	}

	public void setIsFinal() {
		isFinal = true;
	}

	public int getEstadoFuturo(int index) {

		return estadoFuturo[index];
	}

	int count = 0;

	public void setEstadoFuturo(int estadoFuturo) {

		this.estadoFuturo[count] = estadoFuturo;
		count = count + 1;
	}

	public char getLetrasAux(int index) {
		return letrasAux[index];
	}

	public int getLetrasAuxLength() {
		return letrasAux.length;
	}

	int count2 = 0;

	public void setLetrasAux(char letrasAux) {
		this.letrasAux[count2] = letrasAux;
		count2 = count2 + 1;
	}

	public char getDirecao(int index) {
		return direcao[index];
	}

	int count3 = 0;

	public void setDirecao(char direcao) {
		this.direcao[count3] = direcao;
		count3 = count3 + 1;
	}

	public boolean isFinal() {
		return isFinal;
	}

}
