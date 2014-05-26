import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

class Background extends GameObject {
	int x;
	int y;

	Image background;
	Image[] anime = new Image[90];
	int index = 0;

	public Background() {
		try {
			this.background = ImageIO.read(new File("Resorce\\Background.png"));

			for(int i = 0; i < anime.length; i++) {
				String str = "Resorce\\Battle\\Background_000";
				if(i < 10) str += "0" + Integer.toString(i);
				else str += Integer.toString(i);
				str += ".png";
				System.out.println(str);
				anime[i] = ImageIO.read(new File(str));
			}
		} catch(Exception e) { System.out.println(e); }
	}

	@Override
	public void update() {
		if(index < 89) index++;
		else index = 0;
	}

	@Override
	public void draw(DrawPanel panel, Graphics2D graphics) {
		try {
			graphics.drawImage(this.background, 0, 0, panel);
			graphics.drawImage(anime[index], 0, 0, panel);
		}catch(Exception e) {System.out.println(e); }

	}

	public int input(KeyEvent key) { return 0; }
	
}