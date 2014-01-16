import java.io.*;
import java.awt.*;
import javax.imageio.*;

class PlayerMonster extends Monster {

	public PlayerMonster(String name) {
		super(name);

		statusBox = new StatusBox(930, 365);
		image_x = 200;
		image_y = 280;
	}

	public String[] getSkillsName(){
		String[] str = new String[4];
		for(int i = 0; i < str.length; i++) {
			str[i] = skills[i].name;
		}
		return str;
	}
		
}