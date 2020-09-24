package _02_File_Encrypt_Decrypt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class FileDecryptor {
	/*
	 * Decryption is the process of taking encoded or encrypted text or other data
	 * and converting it back into text that you or the computer can read and
	 * understand.
	 *
	 * The shift cipher is decrypted by using using the key and shifting back up, at
	 * the end of our encryption example we had our alphabet as:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 *
	 * If we shift it back up by 4 based on the key we used the alphabet is
	 * decrypted:
	 *
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 *
	 * "Lipps Asvph" returns to "Hello World"
	 * 
	 * Create a program that opens the file created by FileEncryptor and decrypts
	 * the message, then display it to the user in a JOptionPane.
	 */
	public static char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] main) {
		String messageLine = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/_02_File_Encrypt_Decrypt/EncodedMessage.txt"));
			messageLine = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

//		String inputMessage = JOptionPane.showInputDialog("Type your secret message!");
		int key = Integer.parseInt(JOptionPane.showInputDialog("Type your secret number key!"));

		char[] outputAlphabet = new char[alphabet.length];
		int[] messageInt = new int[messageLine.length()];
		String outputMessage = "";

		// This takes the original alphabet and shifts it over by the given key
		for (int i = alphabet.length - 1; i >= 0; i--) {
			// outputAlphabet[i] = alphabet[(i - key) % alphabet.length];
			outputAlphabet[i] = alphabet[((i - key) % alphabet.length + alphabet.length) % alphabet.length];
		}

		// (a % b + b) % b

		// This compares every char in the message to the original alphabet and places
		// the correct indexes in a new int[]
		for (int i = 0; i < messageLine.length(); i++) {
			if (messageLine.charAt(i) == ' ') {
				messageInt[i] = -1;
			} else {
				for (int j = 0; j < alphabet.length; j++) {
					if (messageLine.charAt(i) == alphabet[j]) {
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
			FileWriter fw = new FileWriter("src/_02_File_Encrypt_Decrypt/DecryptedMessage.txt");
			fw.write(outputMessage);
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
	}

}
//Copyright © 2020 Adrian Olguin