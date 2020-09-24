package _02_File_Encrypt_Decrypt;

import java.awt.AlphaComposite;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class FileEncryptor {
	/*
	 * Encryption is the process of encoding a message or information in such a way
	 * that only authorized parties can access it and those who are not authorized
	 * cannot.
	 *
	 * A simple shift cipher works by shifting the letters of a message down based
	 * on a key. If our key is 4 then:
	 * 
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 * 
	 * becomes:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 * 
	 * "Hello World" changes to "Lipps Asvph" AA Create a program that takes a
	 * messager. Use any key you want (1 - 25) to shift each letter in the users
	 * input and save the final result to a file.
	 */
	public static char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) {
		
		String inputMessage = JOptionPane.showInputDialog("Type your secret message!");
		int key = Integer.parseInt(JOptionPane.showInputDialog("Type your secret number key!"));

		char[] outputAlphabet = new char[alphabet.length];
		int[] messageInt = new int[inputMessage.length()];
		String outputMessage = "";

		// This takes the original alphabet and shifts it over by the given key
		for (int i = alphabet.length - 1; i >= 0; i--) {
			outputAlphabet[i] = alphabet[(i + key) % alphabet.length];
		}

		// This compares every char in the message to the original alphabet and places
		// the correct indexes in a new int[]
		for (int i = 0; i < inputMessage.length(); i++) {
			if (inputMessage.charAt(i) == ' ') {
				messageInt[i] = -1;
			} else {
				for (int j = 0; j < alphabet.length; j++) {
					if (inputMessage.charAt(i) == alphabet[j]) {
						messageInt[i] = j;
					}
				}
			}
		}

		// This takes the int[] of indexes and applies them to the new shifted alpahabet
		// to get
		// the final shifted message
		for (int i = 0; i < messageInt.length; i++) {
			if (messageInt[i] == -1) {
				outputMessage += " ";
			} else {
				outputMessage += outputAlphabet[messageInt[i]];
			}
		}

		try {
			FileWriter fw = new FileWriter("src/_02_File_Encrypt_Decrypt/EncodedMessage.txt");
			fw.write(outputMessage);
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

}
//Copyright © 2020 Adrian Olguin