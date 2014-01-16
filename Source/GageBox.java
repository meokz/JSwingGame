import java.awt.*;
import java.awt.event.*;

// HPゲージ, SPゲージの基クラス
class GageBox {
	int x, y;
	int width;
	int height;
	
	Color fill;
	Color stroke;

	public GageBox(int x, int y, int width, int height, Color fill, Color stroke) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fill = fill;
		this.stroke = stroke;
	}
	
	public void draw(DrawPanel panel, Graphics2D graphics, int maxHP, int hp) {
		graphics.setColor(fill);
		int w = (int)((float)hp / maxHP * this.width);
		graphics.fillRect(this.x + 1, this.y + 1, w, this.height);
		
		graphics.setColor(stroke);
		graphics.drawRect(this.x, this.y, this.width + 1, this.height + 1);
	}
}