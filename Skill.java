import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.*;

class Skill {
	// 技の名前
	String name;
	
	// エフェクトの画像
	Image image;

	//エフェクトの描画終了フラグ
	boolean end = true;

	// 技の攻撃力
	int attack;

	// 切り取りX座標
	int sorce_x = 0;
	// 切り取りY座標
	int sorce_y = 0;
	// 横
	int hori;
	// 縦
	int vertic;
	// 1フレーム分の幅
	int width;
	// 1フレーム分の高さ
	int height;

	public Skill(String fileName) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			this.name = sc.next();
			this.image = ImageIO.read(new File(sc.next()));
			this.hori = sc.nextInt();
			this.vertic = sc.nextInt();
			this.width = sc.nextInt();
			this.height = sc.nextInt();
		} catch(Exception e) { }
	}

	public void draw(DrawPanel panel, Graphics2D graphics, int x, int y) {
		if(sorce_x <= width * (vertic - 2)) {
			sorce_x += width;
		} else {
			sorce_x = 0;
			sorce_y += height;
		}

		if(sorce_y > (vertic - 1) * height) {
			this.end = true;
			sorce_x = 0;
			sorce_y = 0;
		}

		// x + 好きな値　でもいける。汎用性
		graphics.drawImage(image, x, y, x + width, y + height,
			sorce_x, sorce_y, sorce_x + width, sorce_y + height, panel);
	}

}