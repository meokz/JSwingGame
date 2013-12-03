import java.io.*;
import java.awt.*;
import javax.imageio.*;

class MessageBox extends GameObject {
	int width;
	int height;
	
	String text;
	boolean visible = true;
	
	Image background;
	Font font;
	
	public MessageBox(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.font = new Font("メイリオ", Font.PLAIN, 40);
		
		try {
			background = ImageIO.read(new File("Resorce\\Box.jpg"));
		} catch(Exception e) { }
	}
	
	@Override
	public void draw(DrawPanel panel, Graphics2D graphics) {
		if(!visible) return;
		graphics.setFont(this.font);
		graphics.setColor(Color.white);
		graphics.drawImage(this.background, this.x, this.y, this.width, this.height, panel);
		graphics.drawString(this.text, this.x + 30, this.y + 60);
	}

}