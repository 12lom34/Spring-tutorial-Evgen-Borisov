package borisov.spring.internals.screensaver;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.springframework.stereotype.Component;

@Component
public abstract class ColorFrame extends JFrame {
	
	public ColorFrame() {
		setSize(200, 200);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void showOnRandomPlace() {
		Random random = new Random();
		setLocation(random.nextInt(2000), random.nextInt(700));
		getContentPane().setBackground(getColor());
		repaint();
	}

	abstract Color getColor();
}
