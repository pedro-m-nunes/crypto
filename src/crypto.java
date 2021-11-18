

// criptografia: cada caracter e acrescentado da quantidade de caracteres da palavra em que esta
// os numeros se mantem os mesmos
// as palavras sao separados por " "

// possibilidade: passar pela criptografia mais de uma vez (Pedro = Ujiwt = Zonby = ...)

/*
 * TO DO
 * fazer algoritmo "decodificador"
 * (talvez) colocar mais caracteres de pontuacao (talvez ASCII 33-47 | '#' '@' '%' '$')
 * (talvez) comando que mostre caracteres aceitos
 */

import java.util.Scanner;

public class crypto {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		/* CARACTERES ACEITOS
		 * letras a-z; A-Z;  
		 * algarismos numericos 0-9;
		 * pontuacoes/caracteres ','; '.'; '?'; '-'; '_';
		 */
		
		System.out.print("Mensagem: ");
		String msg = scan.nextLine();
		
		//String msgOriginal = msg; //salva a mensagem original, caso precise
		
		msg = _separaPontos(msg); //separa (alguns) caracteres especiais das letras
		
		msg = _separaNumeros(msg); //separa os numeros das letras
		
		//para evitar " " a mais
		msg = msg.replace("   ", " ");
		msg = msg.replace("  ", " ");
		
		String[] palavra = msg.split(" "); //separa as palavras
		String[] palavraCrp = new String[palavra.length]; //para armazenar as palavras criptografadas
		
		//System.out.println("Mensagem com separacoes: " + msg); //para testes
		
		for(int x = 0; x < palavra.length; x++) {
			char[] crp = new char[palavra[x].length()];
			
			for(int y = 0; y < palavra[x].length(); y++) {
				int _charASCII = palavra[x].charAt(y);
				
				if(_charASCII >= 97 && _charASCII <= 122) { //letras minusculas
					crp[y] = (char) ((int) palavra[x].charAt(y) + palavra[x].length());
					
					if((int) crp[y] > 122)
						crp[y] -= 26;
					
				} else if(_charASCII >= 65 && _charASCII <= 90) { //letras maiusculas // TENTAR JUNTAR COM CODIGO DAS MINUSCULAS
					crp[y] = (char) ((int) palavra[x].charAt(y) + palavra[x].length());
					
					if((int) crp[y] > 90)
						crp[y] -= 26;
					
				} else if(_charASCII >= 48 && _charASCII <= 57) { //numeros
					//crp[y] = (char) ((int) palavra[x].charAt(y) - palavra[x].length()); //substrai pelo numero de digitos que o numero tem (nao funciona ainda)
					
					crp[y] = (char) _charASCII;
					
					//if((int) crp[y] < 48)
					//	crp[y] += 10;
					
				} else if(palavra[x].charAt(y) == ',' || palavra[x].charAt(y) == '.' || palavra[x].charAt(y) == '?' || palavra[x].charAt(y) == '-' || palavra[x].charAt(y) == '_') { //pontuacoes
					crp[y] = palavra[x].charAt(y);
				} else { //caracteres nao aceitos
					System.out.println("A mensagem contem caracteres nao aceitos. (" + palavra[x].charAt(y) + ")");
					System.exit(_charASCII);
				}
				
			}
			
			palavraCrp[x] = String.valueOf(crp); //palavra criptografada
			
		}
		
		// escrita da mensagem criptografada
		System.out.print("\nMensagem criptografada: ");
		for(int i = 0; i < palavraCrp.length; i++) {
			if(!palavraCrp[i].isEmpty()) {
				if(i == palavraCrp.length - 1) //quebra de linha se for a ultima palavra
					System.out.println(palavraCrp[i]);
				else if(palavraCrp[i + 1].equals(",") || palavraCrp[i + 1].equals(".") || palavraCrp[i + 1].equals("?")) //nao imprimir espaco antes de pontuacoes
					System.out.print(palavraCrp[i]);
				else if(palavraCrp[i + 1].equals("-") || palavraCrp[i].equals("-") || palavraCrp[i + 1].equals("_") || palavraCrp[i].equals("_")) { // '-' e '_'
					if(palavraCrp[i].equals("_"))
						System.out.print(" ");
					else
						System.out.print(palavraCrp[i]);
				} else if(Character.isDigit(palavraCrp[i].charAt(0)) && Character.isDigit(palavraCrp[i + 1].charAt(0))) //nao imprimir espaco antes de numeros
					System.out.print(palavraCrp[i]);
				else
					System.out.print(palavraCrp[i] + " ");
			}
		}
		
		scan.close();
		
	}
	
	
	//FUNCOES
	static String _separaNumeros(String text) {
		for(int q = 0; q < 10; q++) {
			text = text.replace(String.valueOf(q), " " + String.valueOf(q) + " ");
		}
		
		return text;
	}
	
	static String _separaPontos(String text) {
		text = text.replace(",", " , ");
		text = text.replace(".", " . ");
		text = text.replace("?", " ? ");
		text = text.replace("-", " - ");
		text = text.replace("_", " _ "); //underline: imprime " "
		
		return text;
	}
	
}