package _03_To_Do_List;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save
	 * list, and load list.
	 *
	 * When add task is clicked: Create a JOptionPane to ask the user for a task and
	 * add it to an ArrayList
	 * 
	 * When the view tasks button is clicked: show all the tasks in the list
	 * 
	 * When the remove task button is clicked: Create a JOptionPane to prompt the
	 * user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked: Save the list to a file
	 * 
	 * When the load list button is clicked: Create a JOptionPane to Prompt the user
	 * for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file
	 * into the list.
	 */

	static String locationFile = "ToDoList.txt";
	String latestFile;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	JButton add = new JButton("Add Task");
	JButton view = new JButton("View Tasks");
	JButton remove = new JButton("Remove Task");
	JButton save = new JButton("Save Task");
	JButton load = new JButton("Load Tasks");

	JTextArea taskTextArea = new JTextArea();
	JTextField inputField = new JTextField();
	ArrayList<String> taskList = new ArrayList<String>();

	public static void main(String[] args) {
		ToDoList main = new ToDoList();
		main.createGUI();

		// main.removeFileLine(locationFile, main.getFileLine(locationFile, 2));

	}

	void createGUI() {
		frame.setVisible(true);
		// frame.setResizable(false);
		frame.add(panel);

		add.addActionListener(this);
		view.addActionListener(this);
		remove.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);

		taskTextArea.setEditable(false);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(add, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(view, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		panel.add(remove, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		panel.add(save, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		panel.add(load, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(50, 5, 0, 5);
		c.ipady = 50;
		c.gridwidth = 5;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(taskTextArea, c);

		frame.pack();
		loadLatest();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(add)) {
			addTask();
		} else if (e.getSource().equals(view)) {
			viewTask();
			// taskTextArea.setText("hello world\nand bob");
		} else if (e.getSource().equals(remove)) {
			removeTask();
		} else if (e.getSource().equals(save)) {
			saveTask();
		} else if (e.getSource().equals(load)) {
			loadTask();
		}

	}

	public void addTask() {
		String task = JOptionPane.showInputDialog(null, "What is your task?");
		taskList.add(task);
	}

	public void viewTask() {
		taskTextArea.setText("");
		for (int i = 0; i < taskList.size(); i++) {
			taskTextArea.setText(taskTextArea.getText() + "\n" + taskList.get(i));
		}
		frame.pack();
	}

	public void removeTask() {
		String userInput = JOptionPane.showInputDialog("Which task would you like to remove");
		if (userInput.equals("")) {
			JOptionPane.showMessageDialog(null, "Enter a number.");
			removeTask();
		}
		int index = Integer.parseInt(userInput);
		taskList.remove(index - 1);
	}

	public void saveTask() {
		try {
			String fileName = JOptionPane.showInputDialog("Name your file");
			latestFile = fileName;
			BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
			for (int i = 0; i < taskList.size(); i++) {
				br.write(taskList.get(i));
				br.newLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadTask() {
		String fileName = null;
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileName = jfc.getSelectedFile().getAbsolutePath();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while (line != null) {
				taskTextArea.setText(taskTextArea.getText() + line + System.getProperty("line.separator"));
				line = br.readLine();
			}
			br.close();
			frame.pack();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	void loadLatest() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(latestFile));
			String line = br.readLine();
			while(line != null) {
				taskTextArea.setText(taskTextArea.getText() + line);
				line = br.readLine();
			}
			br.close();
			frame.pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
//Copyright © 2020 Adrian Olguin