import java.util.*;
import java.awt.*;
import java.awt.event.*;

// 状態遷移
enum BattleState {
	Command, 	// コマンドフェーズ
	Player, 	// 自分の攻撃フェーズ
	Enemy, 		// 敵の攻撃フェーズ
}

// 戦闘画面
class BattleScene extends GameScene {

	ArrayList<GameObject> gameObjects;
	CommandBox command;
	CommandBox skill;
	MessageBox message;
	MessageBox point;

	// お互いに与える攻撃力。アニメーション用
	int attack;
	
	BattleState state = BattleState.Command;
	
	Monster player, enemy;

	int turn = 1;

	public BattleScene(DrawPanel panel) {
		super(panel);
		
		gameObjects = new ArrayList<GameObject>();
		
		player = new PlayerMonster();
		gameObjects.add(player);
		
		enemy = new EnemyMonster();
		gameObjects.add(enemy);
		
		String[] commands = {"技", "交代", "防御", "降参" };
		command = new CommandBox(900, 500, 350, 170, commands);
		gameObjects.add(command);
		
		String[] skills = player.getSkillsName();
		skill = new CommandBox(30, 500, 850, 170, 230, skills);
		
		point = new MessageBox(900, 500, 350, 170, "");

		message = new MessageBox(30, 500, 850, 170, "どうする？");
		gameObjects.add(message);
		
	}
	
	public void update() {
		switch(state) {
			// コマンドフェーズ
			case Command : {
				
				
			} break;
			
			// 自分の攻撃フェーズ
			case Player : {
				if(attack == 0 && player.skills[0].end) {
					// 自分の攻撃終了。敵の攻撃フェーズへ
					state = BattleState.Enemy;
					message.setText("てきの攻撃！！！");
					attack = enemy.status.attack;
				} else {
					enemy.HP -= 1;
					this.attack -= 1;
				}
			} break;
			
			// 敵の攻撃フェーズ
			case Enemy : {
				if(attack == 0) {
					// 敵の攻撃終了。コマンドフェーズへ
					state = BattleState.Command;
					turn++;
					message.setText("どうする？");
					gameObjects.remove(point);
					gameObjects.add(command);
				} else {
					// 攻撃中
					player.HP -= 1;
					this.attack -= 1;
				}
			} break;
			
		}
	}
	
	public void draw(Graphics2D graphics) {
		for(GameObject gameObject : gameObjects) gameObject.draw(panel, graphics);

		if(state == BattleState.Player && !player.skills[0].end) {
			player.skills[0].draw(panel, graphics, 500, 100);
		}
	}
	
	public void input(KeyEvent key) {
		LABEL : for(GameObject gameObject : gameObjects) {
			int result = gameObject.input(key);
			if(result != 0) {
				if(gameObject == command) commandEnter();
				else if(gameObject == skill) skillEnter(result);
				break LABEL;
			}
		}
	}
	
	// "技"が選択されたとき
	private void commandEnter() {
		switch(command.index) {
			case 0 : {
				gameObjects.remove(command);
				gameObjects.remove(message);
				gameObjects.add(skill);
				gameObjects.add(point);
			} break;
			
		}
	}
	
	// "技"の中の技が選択されたとき
	private void skillEnter(int key) {
		if(key == 2) {
			gameObjects.remove(skill);
			gameObjects.remove(point);
			gameObjects.add(message);
			gameObjects.add(command);
			return;
		}

		switch(skill.index) {
			case 0 : {
				state = BattleState.Player;
				attack = player.status.attack;
				player.skills[0].end = false;
				gameObjects.remove(skill);
				gameObjects.add(message);
				message.setText("おれの攻撃！！！");
			} break;

		}
	}
	
}