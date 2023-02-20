package jogoPong;

import java.awt.Color;
import java.awt.Graphics;

public class Jogador {

	public boolean right, left;
	public int x, y, width, heigth;

	Jogador(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.heigth = 5;
	}

	public void logica() {
		//eventos do teclado
		if (right) {
			x+=2;
		} else if (left) {
			x-=2;
		}
		// sistema de colisão
		if (x + width > Jogo.WIDTH) {
			x = Jogo.WIDTH - width;
		} else if (x < 0) {
			x = 0;
		}
	}

	public void renderizar(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, heigth);
	}
}
