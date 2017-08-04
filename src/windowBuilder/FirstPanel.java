package windowBuilder;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import logic.GameLogic;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class FirstPanel extends JPanel {
	private JTextField txtNickname;
	private JPasswordField pwdPassword;

	private ButtonGroup buttonGroup;
	
	/**
	 * Create the panel.
	 */
	public FirstPanel() {
		setLayout(new MigLayout("", "[][][grow]", "[][][][][][][][][][][]"));
		
		JLabel lblUserName = new JLabel("User Name: ");
		add(lblUserName, "cell 0 0");
		
		txtNickname = new JTextField();
		txtNickname.setText("Nickname");
		add(txtNickname, "cell 2 0,growx");
		txtNickname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		add(lblPassword, "cell 0 2");
		
		pwdPassword = new JPasswordField();
		pwdPassword.setToolTipText("enter password");
		pwdPassword.setText("Password");
		add(pwdPassword, "cell 2 2,growx");
		
		JLabel lblSelectColor = new JLabel("Select Color: ");
		add(lblSelectColor, "cell 0 4");
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.addItem("BLACK");
		comboBox.addItem("BLUE");
		comboBox.addItem("CYAN");
		comboBox.addItem("ORANGE");
		comboBox.addItem("GRAY");
		comboBox.setToolTipText("Color\r\n");
		add(comboBox, "cell 2 4,growx");
		
		
		JLabel lblDifficulty = new JLabel("Difficulty: ");
		add(lblDifficulty, "cell 0 6");
		
		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		add(rdbtnEasy, "cell 2 6");
		rdbtnEasy.setSelected(true);
		
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		add(rdbtnNormal, "cell 2 7");
		
		JRadioButton rdbtnHard = new JRadioButton("Hard");
		add(rdbtnHard, "cell 2 8");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnEasy);
		buttonGroup.add(rdbtnNormal);
		buttonGroup.add(rdbtnHard);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Color selectedColor = Color.BLACK;
				switch (comboBox.getSelectedItem().toString()) {
				
				case "BLUE":
					selectedColor = Color.BLUE;
					break;
				case "GREEN":
					selectedColor = Color.GREEN;
					break;
				case "ORANGE":
					selectedColor = Color.ORANGE;
					break;
				case "CYAN":
					selectedColor = Color.CYAN;
					break;
				case "GRAY":
					selectedColor= Color.GRAY;
					break;
					
				default:
					break;
				}
				
				if(rdbtnEasy.isSelected()) { 
					//ez 
					}
				else if(rdbtnNormal.isSelected()) {
					//medium
					}
				else if(rdbtnHard.isSelected()) {
					//hard
					}
				
				
				
				
				GameLogic gameLogic = new GameLogic(txtNickname.getText(), selectedColor);
				gameLogic.startApplication();
				
			}
		});
		add(btnStart, "cell 2 9");
		
		JButton btnAbout = new JButton("About");
		add(btnAbout, "cell 2 10");

	}

}
