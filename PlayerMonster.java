import java.io.*;
import java.awt.*;
import javax.imageio.*;

class PlayerMonster extends Monster {
	
	public PlayerMonster() {
		statusBox = new StatusBox(930, 365);

		try {
			image = ImageIO.read(new File("Resorce\\Monster1.png"));
			image_x = 200;
			image_y = 280;
		} catch(Exception e) { }

	}
		
}