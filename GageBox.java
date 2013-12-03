import java.awt.*;
import java.awt.event.*;

class GageBox extends GameObject {
	int width;
	int height;
	
	public GageBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(DrawPanel panel, Graphics2D graphics, int maxHP, int hp) {
		// fillRect 塗りつぶし
		// drawRect 線だけ
		graphics.setColor(Color.blue);
		int w = (int)((float)hp / maxHP * this.width);
		graphics.fillRect(this.x, this.y, w, this.height);
		
		graphics.setColor(Color.black);
		graphics.drawRect(this.x, this.y, this.width, this.height);
	}
}