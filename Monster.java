import java.awt.*;
import java.awt.event.*;

class Monster extends GameObject {

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

	public Monster() {
		status = new Status();
		status.HP = 1000;
		this.HP = status.HP;
		status.SP = 100;
		this.SP = 100;
		status.attack = 100;
		status.name = "なまえ";
	}
	
	@Override
	public void draw(DrawPanel panel, Graphics2D graphics) {
		// ステータスボックス表示
		statusBox.draw(panel, graphics, this);

		graphics.drawImage(this.image, this.image_x, this.image_y, panel);
	}
}