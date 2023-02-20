package jogoPong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Jogo extends Canvas implements Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 190;
	public static int HEIGTH = 160;
	private static int SCALE = 3;

	public BufferedImage camada = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);

	public static Jogador jogador;
	public static Inimigo inimigo;
	public static Bola bola;

	public Jogo() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGTH * SCALE));
		this.addKeyListener(this);
		jogador = new Jogador(60, HEIGTH -6);
		inimigo = new Inimigo(75, HEIGTH - 159);
		bola = new Bola(80, HEIGTH / 2);
	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(jogo);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		new Thread(jogo).start();
	}

	public void logica() {
		jogador.logica();
		inimigo.logica();
		bola.logica();
	}

	public void renderizar() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);

			return;
		}
		Graphics g = camada.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGTH);
		jogador.renderizar(g);
		inimigo.renderizar(g);
		bola.renderizar(g);
		g = bs.getDrawGraphics();
		g.drawImage(camada, 0, 0, WIDTH * SCALE, HEIGTH * SCALE, null);

		bs.show();
	}

	public void run() {
		requestFocus();
		while (true) {
			logica();
			renderizar();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jogador.right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			jogador.left = true;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jogador.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			jogador.left = false;

		}

	}

}