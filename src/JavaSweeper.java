import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JavaSweeper extends JFrame {

	private final int COLS = 9;
	private final int ROWS = 9;
	private final int BOMBS = 10;
	private Game game;
	private final int IMAGE_SIZE = 50;
	private JPanel panel;

	private JLabel label;

	private JavaSweeper() {
		game = new Game(COLS, ROWS, BOMBS);
		game.start();
		setImages();
		initLabel();
		initPanel();
		iniFrame();
	}

	private void initLabel() {
		label = new JLabel("Welcome!");
		add(label, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new JavaSweeper().setVisible(true);

	}

	private void initPanel() {
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (Coord coord : Ranges.getAllCoords())
					g.drawImage((Image) game.getBox(coord).image,
							coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
			}
		};

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX() / IMAGE_SIZE;
				int y = e.getY() / IMAGE_SIZE;
				Coord coord = new Coord(x, y);
				if (e.getButton() == MouseEvent.BUTTON1)
					game.presLeftButton(coord);
				panel.repaint();
				if (e.getButton() == MouseEvent.BUTTON3)
					game.presRightButton(coord);
				panel.repaint();
				if (e.getButton() == MouseEvent.BUTTON2)
					game.start();
				label.setText(getMassage());
				panel.repaint();
			}
		});
		panel.setPreferredSize(new Dimension(
				Ranges.getSize().x * IMAGE_SIZE,
				Ranges.getSize().y * IMAGE_SIZE));
		add(panel);

	}

	private String getMassage() {
		switch (game.getState()) {
			case PLAYED:
				return "Think twice!";

			case BOMBED:
				return "YOU LOSE! BIG BA-DA-BOOM";

			case WINNER:
				return "CONGRATULATIONS!";
			default:
				return "Welcome!";
		}
	}

	private void iniFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java Sweeper");
		setResizable(false);
		setVisible(true);
		setIconImage(getImage("icon"));
		pack();
		setLocationRelativeTo(null);
	}

	private void setImages() {
		for (Box box : Box.values()) {
			box.image = getImage(box.name().toLowerCase());
		}

	}

	private Image getImage(String name) {
		String filename = "img/" + name.toLowerCase() + ".png";
		ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		return icon.getImage();
	}
}