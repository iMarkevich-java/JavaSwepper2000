import javax.swing.*;
import java.awt.*;

public class JavaSweeper extends JFrame {

	private final int COLS = 15;
	private final int ROWS = 1;
	private final int IMAGE_SIZE = 50;
	private JPanel panel;

	private JavaSweeper() {
		initPanel();
		iniFrame();
	}

	public static void main(String[] args) {
		new JavaSweeper().setVisible(true);

	}

	private void initPanel() {
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(getImage("bomb"), 0, 0, this);
				g.drawImage(getImage("num1"), IMAGE_SIZE, 0, this);
			}
		};
		panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
		add(panel);

	}

	private void iniFrame() {
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java Sweeper");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private Image getImage(String name) {
		String filename = "img/" + name.toLowerCase() + ".png";
		ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		return icon.getImage();
	}
}