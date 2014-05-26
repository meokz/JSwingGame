import java.io.*;
import java.awt.*;
import javax.imageio.*;

class DialogBox extends GameObject {
	int height;
	int width;

	String text;

	Image background;
	Font font;
	
	public DialogBox(String text) {
		this.text = text;
		this.font = new Font("メイリオ", Font.PLAIN, 40);
		
		this.x = 300;
		this.y = 300;
		this.height = 200;
		this.width = 500;

		try {
			background = ImageIO.read(new File("Resorce\\Box.jpg"));
		} catch(Exception e) { }
	}
	
	@Override
	public void draw(DrawPanel panel, Graphics2D graphics) {
		graphics.setFont(this.font);
		graphics.setColor(Color.white);
		graphics.drawImage(this.background, this.x, this.y, this.width, this.height, panel);
		graphics.drawString(this.text, this.x + 30, this.y + 60);
	}
	
	public void setText(String text) {
		this.text = text;
	}
}