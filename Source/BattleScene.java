import java.util.*;
import java.awt.*;
import java.awt.event.*;

// 状態遷移
enum BattleState {
	Command, 	// コマンドフェーズ
	PlayerAttack, // 自分の攻撃アニメーション
	Player, 	// プレイヤーの自分への攻撃処理
	EnemyDead, // 敵を倒した
	EnemyAttack, // 敵の攻撃AI, アニメーション
	Enemy, 		// 敵の自分への攻撃処理
	PlayerDead, // プレイヤーが倒された
}

// 戦闘画面
class BattleScene extends GameScene {

	ArrayList<GameObject> gameObjects;

	// システムメッセージ
	MessageBox message;
	// 技,交代,防御, 降参のコマンドコマンド
	CommandBox command;
	// 技の中からモンスター固有のスキル選択
	CommandBox skill;
	// スキル選択時にSPを表示
	MessageBox point;
	// 降参選択時のダイアログ
	DialogBox dialog;

	// お互いが与える総攻撃力。アニメーション用
	int attack;
	// お互いが1フレームで与える攻撃力
	final int attack_anime = 19;

	// プレイヤーが選んだスキル
	int index;

	// 敵が選んだスキル
	int enemy_idx;

	BattleState state = BattleState.Command;
	
	Monster player, enemy;

	int turn = 1;

	// 勝敗がついた
	boolean end = false;

	public BattleScene(DrawPanel panel) {
		super(panel);
		
		gameObjects = new ArrayList<GameObject>();

		gameObjects.add(new Background());

		player = new PlayerMonster("Monster3");
		gameObjects.add(player);
		
		enemy = new EnemyMonster("Monster1");
		enemy.flag = false; // 自キャラかどうか
		gameObjects.add(enemy);
		
		String[] commands = {"技", "交代", "防御", "降参" };
		command = new CommandBox(900, 500, 350, 170, commands);
		gameObjects.add(command);
		
		String[] skills = player.getSkillsName();
		skill = new CommandBox(30, 500, 850, 170, 230, skills);
		
		point = new MessageBox(900, 500, 350, 170, "");

		message = new MessageBox(30, 500, 850, 170, "どうする？");
		gameObjects.add(message);

		dialog = new DialogBox("本当に降参しますか？");
		
		player.SP += player.status.SP / 5;
	}
	
	public void update() {
		for(GameObject gameObject : gameObjects) gameObject.update();

		switch(state) { 
			// コマンドフェーズ
			case Command : {
				
			} break;

			// 自分の攻撃アニメーション
			case PlayerAttack : {
				// エフェクトの再生が終わったらダメージ計算処理へ
				if(player.skills[index].end) state = BattleState.Player;
			} break;
			
			// 自分の敵への攻撃処理
			case Player : {
				// 攻撃中
				if(attack != 0) {
					// アニメーション分のHPを減らす
					enemy.HP -= attack_anime;
					// 残りアニメーション分を減らす
					this.attack -= attack_anime;

					// もし過剰が発生したら
					if(this.attack < 0) {
						// 残りのHP分を引く
						enemy.HP += -attack; 
						// アニメーション終わり
						attack = 0;
					}

					if(enemy.HP <= 0) {
						// アニメーション終わり
						attack = 0;
						// マイナスになってる可能性もある
						enemy.HP = 0;
						state = BattleState.EnemyDead;
					}

				} else {
					// 自分の攻撃終了。敵の攻撃AI & アニメーションへ
					state = BattleState.EnemyAttack;

					// 敵のAI(現在はワンパターン)
					enemy_idx = 0;
					// 敵の攻撃力をアニメーションに反映
					attack = (int)(enemy.status.attack *
						enemy.attack * enemy.skills[enemy_idx].attack);
					// スキルの終了フラグを元に戻す
					enemy.skills[enemy_idx].end = false;
					message.setText(enemy.status.name + " の " +
						enemy.skills[enemy_idx].name + "！！");
				} 
			} break;

			case EnemyDead : {
				message.setText("敵を倒した！");
				end = true;
			} break;

			// 敵の攻撃アニメーション
			case EnemyAttack : {
				// エフェクトの再生が終わったらダメージ計算処理へ
				if(enemy.skills[enemy_idx].end) state = BattleState.Enemy;
			} break;
			
			// 敵の自分への攻撃処理
			case Enemy : {
				// 攻撃中
				if(attack != 0) {
					// アニメーション分のHPを減らす
					player.HP -= attack_anime;
					// 残りアニメーション分を減らす
					this.attack -= attack_anime;

					// もし過剰が発生したら
					if(this.attack < 0) {
						// 残りのHP分を引く
						player.HP += -attack; 
						// アニメーション終わり
						attack = 0;
					}

					if(player.HP <= 0) {
						// アニメーション終わり
						attack = 0;
						// マイナスになってる可能性もある
						player.HP = 0;
						state = BattleState.PlayerDead;
					}

				} else {
					// 敵の攻撃終了。コマンドフェーズへ
					state = BattleState.Command;
					turn++;
					message.setText("どうする？");
					gameObjects.remove(point);
					gameObjects.add(command);

					// SP回復
					player.SP += player.status.SP / 5;
				}
			} break;

			case PlayerDead : {
				message.setText("負けてしまった。目の前が真っ暗になる....");
				end = true;
			} break;
		}
	}
	
	public void draw(Graphics2D graphics) {
		for(GameObject gameObject : gameObjects) gameObject.draw(panel, graphics);

		if(state == BattleState.PlayerAttack && !player.skills[index].end) {
			player.skills[index].draw(panel, graphics);
		} else if(state == BattleState.EnemyAttack && !enemy.skills[enemy_idx].end) {
			enemy.skills[enemy_idx].draw(panel, graphics);

		}

	}
	
	public void input(KeyEvent key) {
		LABEL : for(GameObject gameObject : gameObjects) {
			int result = gameObject.input(key);
			if(result != 0) {
				if(gameObject == command) commandEnter();
				else if(gameObject == skill) skillEnter(result);
				else if(gameObject == dialog) dialogEnter(result);
				break LABEL;
			}
		}

		if(end) {
			panel.setScene(new TitleScene(panel));
		}
	}
	
	// "技"が選択されたとき
	private void commandEnter() {
		switch(command.index) {
			case 0 : {
				// 技
				// Skill選択画面を追加
				gameObjects.remove(command);
				gameObjects.remove(message);
				gameObjects.add(skill);
				gameObjects.add(point);
			} break;

			case 3 : {
				// 降参
				gameObjects.remove(command);
				gameObjects.remove(message);
				gameObjects.add(dialog);
			}
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

		// SPが足りなかったらキャンセル
		if(player.SP < player.skills[index].skill) return;

		// SPを技分減らす
		player.SP -= player.skills[index].skill;

		// ゲームの状況を更新
		state = BattleState.PlayerAttack;
		// 選んだ技
		index = skill.index;
		// プレイヤーの攻撃力をアニメーションに反映
		attack = (int)(player.status.attack * 
			player.attack * player.skills[index].attack);

		// スキルの終了フラグを元に戻す
		player.skills[index].end = false;

		gameObjects.remove(skill);
		gameObjects.add(message);
		message.setText(player.status.name + " の " + player.skills[index].name + "！！");
	}

	// 降参の選択がされたとき
	private void dialogEnter(int key) {

	}
	
}