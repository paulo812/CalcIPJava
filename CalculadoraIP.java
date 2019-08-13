package calculadoraIp;

import java.util.Arrays;
import java.util.Scanner;

public class CalculadoraIP {

	public static void menu() {
		System.out.println("\tCalculadora IP");
		System.out.println("Selecione uma op��o: ");
		System.out.println("1. Compara��o de IPs");
		System.out.println("2. Identifica��o dos Endere�os de Rede, Broadcast e IPs");
		System.out.println("3. C�lculo de Subrede");
		System.out.println("4. Sair do programa.");
		System.out.println("\nOpcao:");
	}

	public static String decimalParaBinario(int numero) {
		int d = numero;
		StringBuffer binario = new StringBuffer();
		while (d > 0) {
			int b = d % 2;
			binario.append(b);
			d = d >> 1;
		}
		return binario.reverse().toString();
	}

	public static String verificaClasse(int[] primeiroOcteto) {
		//
		if (primeiroOcteto[0] >= 0 && primeiroOcteto[0] <= 127) {
			String classeA = new String();
			classeA = "Classe A";
			return classeA;
		} else {
			if (primeiroOcteto[0] >= 128 && primeiroOcteto[0] <= 191) {
				String classeB = new String();
				classeB = "Classe B";
				return classeB;
			}
			if (primeiroOcteto[0] >= 192 && primeiroOcteto[0] <= 223) {
				String classeC = new String();
				classeC = "Classe C";
				return classeC;
			}
			if (primeiroOcteto[0] >= 224 && primeiroOcteto[0] <= 239) {
				String classeD = new String();
				classeD = "Classe D";
				return classeD;
			} else {
				String classeE = new String();
				classeE = "Classe E";
				return classeE;
			}
		}
	}

	public static boolean validacaoIP(String ip) {
		try {
			if (ip == null || ip.isEmpty()) {
				return false;
			}

			String[] octetos = ip.split("\\.");
			if (octetos.length != 4) {
				return false;
			}

			for (String s : octetos) {
				int i = Integer.parseInt(s);
				if ((i < 0) || (i > 255)) {
					return false;
				}
			}
			if (ip.endsWith(".")) {
				return false;
			}

			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static void compara() {
		Scanner input = new Scanner(System.in);
		System.out.println("Voc� entrou no m�todo de compara��o de IPs\n");
		System.out.println("\n");

		System.out.println("Primeiro IP: (xxx.xxx.xxx.xxx)");
		String primeiroIp = input.nextLine();
		if (validacaoIP(primeiroIp) == true) {
			System.out.println("Primeiro IP v�lido!");
		} else {
			System.out.println("Primeiro IP inv�lido!");
		}
		int[] arrayPrimeiroIp = Arrays.stream(primeiroIp.split("\\.")).mapToInt(Integer::parseInt).toArray();

		System.out.println("Segundo IP: (xxx.xxx.xxx.xxx)");
		String segundoIp = input.nextLine();
		if (validacaoIP(segundoIp) == true) {
			System.out.println("Segundo IP v�lido!");
		} else {
			System.out.println("Segundo IP inv�lido!");
		}
		int[] arraySegundoIp = Arrays.stream(segundoIp.split("\\.")).mapToInt(Integer::parseInt).toArray();

		if (((arrayPrimeiroIp[0] <= 127) && (arraySegundoIp[0] == arrayPrimeiroIp[0]))
				|| ((arrayPrimeiroIp[0] >= 128 && arrayPrimeiroIp[0] <= 191)
						&& (arraySegundoIp[0] == arrayPrimeiroIp[0]) && (arraySegundoIp[1] == arrayPrimeiroIp[1]))
				|| (arrayPrimeiroIp[0] >= 192 && arrayPrimeiroIp[0] <= 223) && (arraySegundoIp[0] == arrayPrimeiroIp[0])
						&& (arraySegundoIp[1] == arrayPrimeiroIp[1]) && (arraySegundoIp[2] == arrayPrimeiroIp[2])) {
			System.out.println("Os IPs fazem parte da mesma rede!\n");
		} else {
			System.out.println("Os IPs n�o fazem parte da mesma rede!\n");
		}
		System.out.println("O primeiro endere�o IP pertence � " + verificaClasse(arrayPrimeiroIp) + "\n");
		System.out.println("O segundo endere�o IP pertence � " + verificaClasse(arraySegundoIp) + "\n");
	}

	public static void identifica() {
		System.out.println("Voc� entrou no m�todo de identifica��o dos Endere�os de Rede, Broadcase e IPs.\n");

		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite um endere�o IP v�lido.\n");
		System.out.print("IP: ");
		String numeroIp = entrada.nextLine();
		if (validacaoIP(numeroIp) == true) {
			System.out.println("\n");
		} else {
			System.out.println("IP inv�lido!");
		}
		int[] arrayNumeroIp = Arrays.stream(numeroIp.split("\\.")).mapToInt(Integer::parseInt).toArray();
		if (validacaoIP(numeroIp) == true) {
			if (verificaClasse(arrayNumeroIp) == "Classe A") {
				System.out.println("=========================================");
				System.out.println("Endere�o de rede: " + arrayNumeroIp[0] + ".0.0.0");
				System.out.println("M�scara de Rede: 255.0.0.0");
				System.out.println("Endere�o de Broadcast: " + arrayNumeroIp[0] + ".255.255.255");
				System.out.println("Endere�o inicial: " + arrayNumeroIp[0] + ".0.0.1");
				System.out.println("Endere�o final: " + arrayNumeroIp[0] + ".255.255.254");
				System.out.println("=========================================\n");
			} else {
				if (verificaClasse(arrayNumeroIp) == "Classe B") {
					System.out.println("=========================================");
					System.out.println("Endere�o de rede: " + arrayNumeroIp[0] + "." + arrayNumeroIp[1] + ".0.0");
					System.out.println("M�scara de Rede: 255.255.0.0");
					System.out.println(
							"Endere�o de Broadcast: " + arrayNumeroIp[0] + "." + arrayNumeroIp[1] + ".255.255");
					System.out.println("Endere�o inicial: " + arrayNumeroIp[0] + "." + arrayNumeroIp[1] + ".0.1");
					System.out.println("Endere�o final: " + arrayNumeroIp[0] + "." + arrayNumeroIp[1] + ".255.254");
					System.out.println("=========================================\n");
				}
				if (verificaClasse(arrayNumeroIp) == "Classe C") {
					System.out.println("=========================================");
					System.out.println("Endere�o de rede: " + arrayNumeroIp[0] + "." + arrayNumeroIp[1] + "."
							+ arrayNumeroIp[2] + ".0");
					System.out.println("M�scara de Rede: 255.255.255.0");
					System.out.println("Endere�o de Broadcast: " + arrayNumeroIp[0] + "." + arrayNumeroIp[1] + "."
							+ arrayNumeroIp[2] + ".255");
					System.out.println("Endere�o inicial: " + arrayNumeroIp[0] + "." + arrayNumeroIp[1] + "."
							+ arrayNumeroIp[2] + ".1");
					System.out.println("Endere�o final: " + arrayNumeroIp[0] + "." + arrayNumeroIp[1] + "."
							+ arrayNumeroIp[2] + ".254");
					System.out.println("=========================================\n");
				}
			}
		}

	}

	public static void calculo() {
		System.out.println("Voc� entrou no m�todo de C�lculo de Subrede.\n");
	}

	public static void main(String[] args) {
		int opcao;
		Scanner entrada = new Scanner(System.in);

		do {
			menu();
			opcao = entrada.nextInt();

			switch (opcao) {
			case 1:
				compara();
				break;

			case 2:
				identifica();
				break;

			case 3:
				calculo();
				break;

			case 4:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Op��o inv�lida.");
			}
		} while (opcao != 4);
	}
}