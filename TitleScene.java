import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;

// タイトル画面
class TitleScene extends GameScene {
	Image image;
	
	public TitleScene(DrawPanel panel) {
		super(panel);
		try {
			image = ImageIO.read(new File("Resorce\\Title.jpg"));
		} catch(Exception e) { }
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawString("たいとる画面", 30, 30);
		graphics.drawImage(image, 0, 0, panel);
	}
	
	@Override
	public void input(KeyEvent key) {
		switch(key.getKeyCode()) {
		
			// Enterキーが入力されたときの処理
			case KeyEvent.VK_ENTER : {
				panel.setScene(new BattleScene(panel));
			} break;
			
			case KeyEvent.VK_UP : break;
			case KeyEvent.VK_DOWN : break;
			case KeyEvent.VK_RIGHT : break;
			case KeyEvent.VK_LEFT : break;
		}
	}
	
}