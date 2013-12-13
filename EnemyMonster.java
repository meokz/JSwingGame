import java.io.*;
import java.awt.*;
import javax.imageio.*;

class EnemyMonster extends Monster {

	public EnemyMonster() {
		statusBox = new StatusBox(30, 30);

		try {
			image = ImageIO.read(new File("Resorce\\Monster1.png"));
			image_x = 700;
			image_y = 30;
		} catch(Exception e) { }
	}
}