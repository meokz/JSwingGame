import java.util.*;
import java.awt.*;
import java.awt.event.*;

// 状態遷移
enum BattleState {
	Command, 	// コマンドフェーズ
	Player, 	// プレイヤーフェーズ
	PlayerAttack, // 自分の攻撃アニメーション
	Enemy, 		// エネミーフェーズ
	EnemyAttack, // 敵の攻撃アニメーション
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

	// プレイヤーが選んだスキル
	int index;

	BattleState state = BattleState.Command;
	
	Monster player, enemy;

	int turn = 1;

	public BattleScene(DrawPanel panel) {
		super(panel);
		
		gameObjects = new ArrayList<GameObject>();
		
		player = new PlayerMonster("Monster3");
		gameObjects.add(player);
		
		enemy = new EnemyMonster("Monster1");
		gameObjects.add(enemy);
		
		String[] commands = {"技", "交代", "防御", "降参" };
		command = new CommandBox(900, 500, 350, 170, commands);
		gameObjects.add(command);
		
		String[] skills = player.getSkillsName();
		skill = new CommandBox(30, 500, 850, 170, 230, skills);
		
		point = new MessageBox(900, 500, 350, 170, "");

		message = new MessageBox(30, 500, 850, 170, "どうする？");
		gameObjects.add(message);
		
		player.SP += player.status.SP / 5;
	}
	
	public void update() {
		switch(state) {
			// コマンドフェーズ
			case Command : {
				
			} break;
			
			// 自分の攻撃フェーズ
			case Player : {
				if(attack == 0 && player.skills[index].end) {
					// 自分の攻撃終了。敵の攻撃フェーズへ
					state = BattleState.Enemy;
					message.setText("てきの攻撃！！！");
					attack = enemy.status.attack;
				} else {
					// 攻撃中
					enemy.HP -= 5;
					this.attack -= 5;
					if(this.attack < 0) { enemy.HP += - attack; attack = 0; }
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

					player.SP += player.status.SP / 5;
				} else {
					// 攻撃中
					player.HP -= 5;
					this.attack -= 5;
					if(this.attack < 0){ player.HP += - attack; attack = 0; }
				}
			} break;
			
		}
	}
	
	public void draw(Graphics2D graphics) {
		for(GameObject gameObject : gameObjects) gameObject.draw(panel, graphics);

		if(state == BattleState.Player && !player.skills[index].end) {
			player.skills[index].draw(panel, graphics, 750, 80);
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
		// Escキーのとき
		if(key == 2) {
			gameObjects.remove(skill);
			gameObjects.remove(point);
			gameObjects.add(message);
			gameObjects.add(command);
			return;
		}

		if(player.SP < player.skills[index].skill) return;

		player.SP -= player.skills[index].skill;

		// ゲームの状況を更新
		state = BattleState.Player;
		// 選んだ技
		index = skill.index;
		// プレイヤーの攻撃力をアニメーションに反映
		attack = (int)(player.status.attack * player.skills[index].attack);
		// スキルの終了フラグを元に戻す
		player.skills[index].end = false;

		gameObjects.remove(skill);
		gameObjects.add(message);
		message.setText(player.status.name + " の " + player.skills[index].name + "！！");
	}
}