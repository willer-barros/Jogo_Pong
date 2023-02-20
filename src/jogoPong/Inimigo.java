package jogoPong;

import java.awt.Color;
import java.awt.Graphics;

public class Inimigo {
	public int x, y, width, heigth;

	Inimigo(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.heigth = 5;
	}

	public void logica() {
		x += (Jogo.bola.x - x - 6) * 0.07;
		if (x + width > Jogo.WIDTH) {
			x = Jogo.WIDTH - width;
		} else if (x < 0) {
			x = 0;
		}
	}

	public void renderizar(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, heigth);
	}
}
