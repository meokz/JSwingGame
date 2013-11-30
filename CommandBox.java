import java.io.*;
import java.awt.*;
import javax.imageio.*;

class CommandBox extends GameObject {
	int width;
	int height;
	
	Image background;
	
	Font font;
	// 選択されている番号
	int index;
	boolean enabled = true;
	boolean visible = true;
	
	Command[] commands = new Command[4];
	
	public CommandBox(int x, int y, int width, int height, String[] text) {
		this(x, y, width, height, 0, text);
	}
	
	public CommandBox(int x, int y, int width, int height, int offset, String[] text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.index = 0;
		this.font = new Font("メイリオ", Font.PLAIN, 40);
		
		commands[0] = new Command(this, text[0], 40, 60);
		commands[1] = new Command(this, text[1], 190 + offset, 60);
		commands[2] = new Command(this, text[2], 40, 130);
		commands[3] = new Command(this, text[3], 190 + offset, 130);
		
		try {
			background = ImageIO.read(new File("Resorce\\Box.jpg"));
		} catch(Exception e) { }
	}
	
	@Override
	public void draw(DrawPanel panel, Graphics2D graphics) {
		if(!this.visible) return;
		
		graphics.setFont(this.font);
		
		graphics.drawImage(this.background, this.x, this.y, this.width, this.height, panel);
		
		// 現在のindexのコマンドを選択状態にする
		for(int i = 0; i < commands.length; i++) {
			if(i == this.index) commands[i].selected = true;
			else commands[i].selected = false;
		}
		
		// 各コマンドの文字列描画
		for(Command command : commands) command.draw(graphics);
	}
	
	public void Up() {
		if(!this.enabled) return;
		if(index <= 1) index += 2;
		else index -= 2;
	}
	
	public void Down() {
		if(!this.enabled) return;
		if(index <= 1) index += 2;
		else index -= 2;
	}
	
	public void Right() {
		if(!this.enabled) return;
		if(index == 0 || index == 2) index += 1;
		else index -= 1;
	}
	
	public void Left() {
		if(!this.enabled) return;
		if(index == 1 || index == 3) index -= 1;
		else index += 1;
	}
	
}