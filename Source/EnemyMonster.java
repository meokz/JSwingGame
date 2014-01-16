import java.io.*;
import java.awt.*;
import javax.imageio.*;

class EnemyMonster extends Monster {

	public EnemyMonster(String name) {
		super(name);

		statusBox = new StatusBox(30, 30);
		image_x = 700;
		image_y = 30;
	}
}