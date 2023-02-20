package jogoPong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bola {
	public int width, heigth;
	public double x, y;
	public double xx, yy;
	public double speed = 3.0;

	Bola(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.heigth = 4;

		int angulo = new Random().nextInt(120 - 45) + 46;
		xx = Math.cos(Math.toRadians(angulo));
		yy = Math.sin(Math.toRadians(angulo));

	}

	public void logica() {
		// colisão com a parede
		if (x + (xx * speed) + width >= Jogo.WIDTH) {
			xx *= -1;
		} else if (x + (xx * speed) < 0) {
			xx *= -1;
		}
		// Sistema de pontuação

		if (y >= Jogo.HEIGTH) {

			// ponto do inimigo
			System.out.println("Ponto do Will-B");
			new Jogo();
		} else if (y < 0) {
			// ponto do jogador
			System.out.println("Ponto do Jogador");
			new Jogo();

		}

		Rectangle bounds = new Rectangle((int) (x + (xx * speed)), (int) (y + (yy * speed)), width, heigth);
		Rectangle boundsJogador = new Rectangle(Jogo.jogador.x, Jogo.jogador.y, Jogo.jogador.width,
				Jogo.jogador.heigth);
		Rectangle boundsInimigo = new Rectangle(Jogo.inimigo.x, Jogo.inimigo.y, Jogo.inimigo.width,
				Jogo.inimigo.heigth);

		if (bounds.intersects(boundsJogador)) {
			int angulo = new Random().nextInt(120 - 45) + 46;
			xx = Math.cos(Math.toRadians(angulo));
			yy = Math.sin(Math.toRadians(angulo));
			if (yy > 0) {
				yy *= -1;
			}

		} else if (bounds.intersects(boundsInimigo)) {
			int angulo = new Random().nextInt(120 - 45) + 46;
			xx = Math.cos(Math.toRadians(angulo));
			yy = Math.sin(Math.toRadians(angulo));
			if (yy < 0) {
				yy *= -1;
			}

		}
			x += xx * speed;
			y += yy * speed;
	}

	public void renderizar(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, heigth);

	}
}
