import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;

class Monster extends GameObject {

	Skill[] skills = new Skill[4];

	// 名前, HPゲージ, SPゲージ
	StatusBox statusBox;
	
	// モンスターの画像(GUI)
	Image image;
	int image_x, image_y;

	Status status;

	// モンスターの現在の体力
	int HP;

	// モンスターの現在のスキルポイント
	int SP;


	public Monster(String name) {
		try {
			Scanner sc = new Scanner(new File("Status\\" + name + ".txt"));
			
			status = new Status();
			status.name = sc.next();
			this.image = ImageIO.read(new File("Resorce\\Character\\" + sc.next()));
			
			status.HP = sc.nextInt();
			this.HP = status.HP;
			status.attack = sc.nextInt();
			status.SP = sc.nextInt();
			this.SP = 0;


			skills[0] = new Skill("Status\\" + sc.next() + ".txt");
			skills[1] = new Skill("Status\\" + sc.next() + ".txt");
			skills[2] = new Skill("Status\\" + sc.next() + ".txt");
			skills[3] = new Skill("Status\\" + sc.next() + ".txt");

		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void draw(DrawPanel panel, Graphics2D graphics) {
		// ステータスボックス表示
		try {
			statusBox.draw(panel, graphics, this);
		}catch(Exception e) {
			if(panel == null)System.out.println("panel");
			if(graphics == null)System.out.println("grahpics");
			if(this == null)System.out.println("this");
		}

		graphics.drawImage(this.image, this.image_x, this.image_y, panel);
	}

	// 仮メソッド
	public String[] getSkillsName() {
		return null;
	}
}