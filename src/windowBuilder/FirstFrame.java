package windowBuilder;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

public class FirstFrame extends JFrame {

	private FirstPanel contentPane;

	/**
	 * Launch the application.
	 * oyun baþlamadan önce gelen form þeklindeki açýlýþ ekraný
	 * 
	 */
	public static void main(String[] args) {
		FirstFrame frame = new FirstFrame();
		frame.pack();
		
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public FirstFrame() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new FirstPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		setTitle("LKD 2017 Java");

	}

}
