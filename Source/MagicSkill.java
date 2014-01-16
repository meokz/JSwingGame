import java.io.*;
import java.awt.*;
import javax.imageio.*;

class MagicSkill extends Skill {
	
	public MagicSkill() {
		try {
			image = ImageIO.read(new File("Resorce\\Effect\\magic1.png"));
		} catch(Exception e) { }

		this.name = "せいっ！";
	}

	@Override
	public void draw(DrawPanel panel, Graphics2D graphics, int x, int y) {
		if(sorce_x <= 720) {
			sorce_x += 240;
		} else {
			sorce_x = 0;
			sorce_y += 240;
		}

		if(sorce_y > 480) {
			this.end = true;
			sorce_x = 0;
			sorce_y = 0;
		}

		graphics.drawImage(image, x, y, x + 240, y + 240,
			sorce_x, sorce_y, sorce_x + 240, sorce_y + 240, panel);
	}

}