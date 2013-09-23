package net.ostis.confman.model.registrationform.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import net.ostis.confman.model.registrationform.RegistrationForm;
import net.ostis.confman.model.registrationform.wordparser.DocxRegistrationFormParser;

	public class UIRegistrationForm extends JFrame {
		
		private JButton openButton;
		private JTextArea textArea;
		private JFileChooser fileChooser;
		
		public UIRegistrationForm() {
			super();
			setWindowOptions();
			initOpenButton();
			initTextArea();
			initFileChooser();
		}

		public File[] openFiles() {
			File[] files = null;
			int stutus = fileChooser.showOpenDialog(null);
			if(stutus==JFileChooser.APPROVE_OPTION){
				files = fileChooser.getSelectedFiles();
			}	
			return files;
		}

		public void parseAllFiles(File[] files) {
			List<RegistrationForm> registrationForms = formingRegistrationForms(files);
			showInfonmation(registrationForms);
		}

		private void showInfonmation(List<RegistrationForm> registrationForms) {
			FormRenderer renderer = new FormRenderer();
			for(RegistrationForm form : registrationForms){
				renderer.render(form, textArea);
			}			
		}

		private List<RegistrationForm> formingRegistrationForms(File[] files) {
			List<RegistrationForm> registrationForms = new ArrayList<RegistrationForm>();
			RegistrationForm currentForm = null;
			for(File file : files){
				try {
					currentForm = new DocxRegistrationFormParser().parse(new FileInputStream(file));
				} 
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				registrationForms.add(currentForm);
			}
			return registrationForms;
		}

		private void initFileChooser() {
			fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(true);
			fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);			
		}

		private void initTextArea() {
			textArea = new JTextArea();
			textArea.setEditable(false);
			getContentPane().add(textArea, BorderLayout.CENTER);
		}

		private void initOpenButton() {
			openButton = new JButton("Open");
			openButton.addMouseListener(new OpenListener(this));
			getContentPane().add(openButton, BorderLayout.NORTH);
		}

		private void setWindowOptions() {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());
	        setSize(800, 500);
	        setResizable(false);        
	        setVisible(true);			
		}
		
		public static void main(String[] args) {
			new UIRegistrationForm();
		}
}
