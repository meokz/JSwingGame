import java.awt.*;
import java.awt.event.*;

// シーンの基クラス
class GameScene {
	// シーンが属するパネルのインスタンス
	DrawPanel panel;
	
	public GameScene(DrawPanel panel) {
		this.panel = panel;
	}
	
	// 更新処理
	public void update() { }
	
	// 描画
	public void draw(Graphics2D graphics) { }
	
	// 入力
	public void input(KeyEvent key) { }
}