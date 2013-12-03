import java.util.*;
import java.awt.*;
import java.awt.event.*;

// 戦闘画面
class BattleScene extends GameScene {

	ArrayList<GameObject> gameObjects;
	CommandBox command;
	CommandBox skill;
	MessageBox message;
	GageBox hp1;
	GageBox hp2;
	
	public BattleScene(DrawPanel panel) {
		super(panel);
		
		gameObjects = new ArrayList<GameObject>();
		
		String[] commands = {"技", "交代", "防御", "降参" };
		command = new CommandBox(900, 500, 350, 170, commands);
		gameObjects.add(command);
		
		String[] skills = {"すごいパンチ", "トンファーキック", "ああああ", "いいいい" };
		skill = new CommandBox(30, 500, 850, 170, 230, skills);
		skill.visible = false;
		gameObjects.add(skill);
		
		message = new MessageBox(30, 500, 850, 170, "どうする？");
		gameObjects.add(message);
		
		hp1 = new GageBox(950, 380, 230, 40);
	}
	
	public void update() {
		
	}
	
	int HP = 1000;
	public void draw(Graphics2D graphics) {
		graphics.drawString(HP + " / 1000" + "    "  + (int)((float)HP / 1000 *400), 100, 100);
		for(GameObject gameObject : gameObjects) gameObject.draw(panel, graphics);
		
		hp1.draw(panel, graphics, 1000, this.HP);
	}
	
	public void input(KeyEvent key) {
		switch(key.getKeyCode()) {
		
			// Enterキーが入力されたときの処理
			case KeyEvent.VK_ENTER : {
				if(command.enabled) {
					commandEnter();
				} else if(skill.enabled) {
					skillEnter();
				}
			} break;
			
			// Upキー
			case KeyEvent.VK_UP : {
				command.Up();
				skill.Up();
			} break;
			
			// Downキー
			case KeyEvent.VK_DOWN : {
				command.Down();
				skill.Down();
			} break;
			
			// Rihgtキー
			case KeyEvent.VK_RIGHT : {
				command.Right();
				skill.Right();
			} break;
			
			// Leftキー
			case KeyEvent.VK_LEFT : {
				command.Left();
				skill.Left();
			} break;
		}
	}
	
	// "技"が選択されたとき
	private void commandEnter() {
		switch(command.index) {
			case 0 : {
				command.enabled = false;
				message.visible = false;
				skill.visible = true;
				skill.index = 0;
			} break;
			
		}
	}
	
	// "技"の中の技が選択されたとき
	private void skillEnter() {
		switch(skill.index) {
			case 0 : {
				this.HP -= 100;
			} break;
			
			case 3 : {
				command.enabled = true;
				message.visible = true;
				skill.visible = false;
			}
		}
	}
	
}