package com.disney.alm.test;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.support.SoapUIException;

public class GetUserInput {
	
	
	public static String userName = null;
	public static String passWord = null;
	public static String excelPath = null;
	public static String domain = null;
	public static String project = null;
	public static String testSetID = null;
	public static String almProjectPath = null;
	public static String dProject = null;
	public static String[] domainProject = new String[2];

	public static void main(String[] args) {

		final JFrame outFrame = new JFrame("ALM connection");

		outFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		outFrame.setBounds(400, 270, 600, 300);

		Container container = outFrame.getContentPane();
		container.setLayout(null);

		JLabel heading = new JLabel("Enter all required details");
		heading.setBounds(140, 0, 250, 30);

		JLabel label1 = new JLabel("Username");
		label1.setBounds(20, 30, 250, 30);
		JLabel label2 = new JLabel("Password");
		label2.setBounds(20, 60, 250, 30);
		JLabel label3 = new JLabel("Excel Report Path");
		label3.setBounds(20, 90, 250, 30);
		JLabel label4 = new JLabel("Domain/Project");
		label4.setBounds(20, 120, 250, 30);
		JLabel label5 = new JLabel("TestsetID");
		label5.setBounds(20, 150, 250, 30);
//		JLabel label6 = new JLabel("SoapUi Project Path");
//		label6.setBounds(20, 180, 250, 30);

		JTextField field1 = new JTextField();
		field1.setBounds(140, 30, 400, 30);
		JPasswordField field2 = new JPasswordField();
		field2.setBounds(140, 60, 400, 30);
		JTextField field3 = new JTextField();
		field3.setBounds(140, 90, 400, 30);
		JTextField field4 = new JTextField();
		field4.setBounds(140, 120,400, 30);
		JTextField field5 = new JTextField();
		field5.setBounds(140, 150, 400, 30);
//		JTextField field6 = new JTextField();
//		field6.setBounds(140, 180, 400, 30);
		

		JButton button = new JButton("Update ALM");
		button.setBounds(230, 210, 200, 30);

		container.add(heading);

		container.add(label1);
		container.add(label2);
		container.add(label3);
		container.add(label4);
		container.add(label5);
//		container.add(label6);

		container.add(field1);
		container.add(field2);
		container.add(field3);
		container.add(field4);
		container.add(field5);
//		container.add(field6);

		container.add(button);

		outFrame.setVisible(true);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				userName = field1.getText().trim();
				passWord = String.valueOf(field2.getPassword()).trim();
				excelPath = field3.getText().trim();
				dProject = field4.getText().trim();
				domainProject  = dProject.split("/");
				domain = domainProject[0];
				project = domainProject[1];
				testSetID = field5.getText().trim();
//				almProjectPath = field6.getText().trim();

				outFrame.dispose();
				
				// Create a new WSDL project
				WsdlProject project = null;
				try {
					project = new WsdlProject(args[0]);
				} catch (XmlException | IOException | SoapUIException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.exit(0);
				}

				// Send variable to Soap Project
				project.setPropertyValue("Username", userName);
				project.setPropertyValue("Password",passWord);
				project.setPropertyValue("ExcelPath",excelPath);
				project.setPropertyValue("Domain", domain);
				project.setPropertyValue("Project", GetUserInput.project);
				project.setPropertyValue("TestSetID", testSetID);

				// create empty properties and run synchronously
				TestRunner runner = project.run(new PropertiesMap(), false);

				System.out.println("Test Status = " + runner.getStatus());
				
			}
		});

	}
}
