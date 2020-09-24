package _04_Directory_Iteration;

import java.io.File;
import java.io.FileWriter;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CopyrightAdder {

	public static void main(String[] args) {

		File srcFile = new File("src");
		idk(srcFile);

	}

	static void addCopyright(File f) {
		String copyright = "//Copyright © 2020 Adrian Olguin";
		try {
			FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
			fw.write(copyright);
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String getFileExtension(File f) {
		int dotIndex = f.getName().lastIndexOf('.');
		String fileName = f.getName();
		String extension = fileName.substring(dotIndex, fileName.length());
		System.out.println("file extension is: '" + extension + "'");
		return extension;
	}

	static void idk(File f) {
		System.out.println("called");
		if (f == null) {
			System.out.println("file doesnt exist");
			return;
		}

		System.out.println("checking file");
		if (f.isDirectory()) {
			System.out.println("file is directory... calling stored files");
			File[] files = f.listFiles();
			for (File file : files) {
				idk(file);
			}
		} else {
			if (getFileExtension(f).equals(".java")) {
				System.out.println("is java file");
				addCopyright(f);
				System.out.println("added copyright");
			}
		}
	}

}
//Copyright © 2020 Adrian Olguin