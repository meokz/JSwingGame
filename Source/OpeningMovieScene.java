import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

// オープニングムービー
class OpeningMovieScene extends GameScene {
	// Imageの配列の要素数
	int index = 0;
	// 次の画像の読み込み(1フレームにつき100回)
	int flame = 1;

	Image[] images = new Image[100];

	// 動画のコマの数
	final int image_file = 993;

	Sound sound;

	public OpeningMovieScene(DrawPanel panel) {
		super(panel);
		sound = new Sound("Resorce\\Opening\\Sound.wav");

		try {
			for(int i = 0; i < images.length; i++) {
				String str = "Resorce\\Opening\\Opening_00";
				if(i < 10) str += "00" + Integer.toString(i);
				else if(i < 100) str += "0" + Integer.toString(i);

				str += ".jpg";

				images[i] = ImageIO.read(new File(str));
				System.out.println(str);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	// 更新
	public void update() {
		try {
			if(flame * 100 + index < image_file) {
				String str = 
					"Resorce\\Opening\\Opening_00" + 
					Integer.toString(flame * 100 + index) + ".jpg";
				images[index] = ImageIO.read(new File(str));
				System.out.println(str);
			}
		} catch(Exception e) {
			System.out.println(e);
		}

		if(index < 99) index++;
		else { index = 0; flame += 1; }

		if((flame - 1) * 100 + index >= image_file)
			panel.setScene(new TitleScene(panel));
	}
	
	// 描画
	public void draw(Graphics2D graphics) {
		if(!sound.isStart) sound.start();

		graphics.drawImage(this.images[index], 0, 0, panel);
	}
	
	// 入力
	public void input(KeyEvent key) { }
}