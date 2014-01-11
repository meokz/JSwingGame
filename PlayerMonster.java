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
		skills[0] = new Skill("Status\\Skill1.txt");
		skills[1] = new Skill("Status\\Skill1.txt");
		skills[2] = new Skill("Status\\Skill1.txt");
		skills[3] = new Skill("Status\\Skill1.txt");
		/*
		skills[0] = new MagicSkill();
		skills[1] = new MagicSkill();
		skills[2] = new MagicSkill();
		skills[3] = new MagicSkill();*/
	}

	public String[] getSkillsName(){
		String[] str = new String[4];
		for(int i = 0; i < str.length; i++) {
			str[i] = skills[i].name;
		}
		return str;
	}
		
}