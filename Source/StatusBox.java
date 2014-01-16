import java.awt.*;

class StatusBox {
	// モンスターのHPゲージ(GUI)
	GageBox gageHP;
	
	// モンスターのSPゲージ(GUI)
	GageBox gageSP;
	
	int x, y;

	int margin_x = 10;

	int margin_y = 8;

	Font font10;
	Font font20;
	Font font30;

	public StatusBox(int x, int y) {
		this.x = x;
		this.y = y;
		
		gageHP = new GageBox(x + margin_x, y + margin_y + 30, 270, 45, Color.green, Color.black);
		gageSP = new GageBox(x + margin_x, gageHP.y + gageHP.height + margin_y,
			150, 25, Color.blue, Color.black);

		font10 = FontInstance.meiryo10;
		font20 = FontInstance.meiryo20;
		font30 = FontInstance.meiryo30;
	}

	public void draw(DrawPanel panel, Graphics2D graphics, Monster monster) {
		int width = gageHP.width + margin_x * 2;
		int height = gageHP.height + gageSP.height + 40 + margin_y * 2;

		graphics.setColor(Color.white);
		graphics.fillRect(this.x - 1, this.y - 1, width, height);
		graphics.setColor(Color.black);
		graphics.drawRect(this.x, this.y, width + 1, height + 1);

		// 名前表示
		graphics.setFont(font30);
		graphics.drawString(monster.status.name, x + 10, y + 30);
		graphics.setFont(font20);
		
		// HPゲージ表示
		gageHP.draw(panel, graphics, monster.status.HP, monster.HP);
		graphics.drawString(monster.HP + " / " + monster.status.HP, 
		gageHP.x + 10, gageHP.y + 27);

		// SPゲージ表示
		gageSP.draw(panel, graphics, monster.status.SP, monster.SP);
		graphics.drawString(monster.SP + " / " + monster.status.SP,
			gageSP.x + 10, gageSP.y + 20);
	}
}