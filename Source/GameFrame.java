import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// ゲームのウィンドウクラス
class GameFrame extends JFrame {
	// ウィンドウの高さ
	static int Height = 1280;
	// ウィンドウの横幅
	static int Width = 720;
	
	DrawPanel panel;
	
	public GameFrame() {
		// 親クラスのコンストラクタ呼び出し
		super();
		
		// ウィンドウの設定
		this.setTitle("PokeDora");
		this.setSize(GameFrame.Height, GameFrame.Width);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawPanel();
		this.getContentPane().add(panel);
		this.addKeyListener(panel);

		// フォントの生成
		new FontInstance();
	}
	
	// ゲームの実行
	public void run() {
		this.setVisible(true);
		panel.start();
	}
}

// 描画用パネル
class DrawPanel extends JPanel implements KeyListener {
	// 現在のシーン
	GameScene scene;
	
	// タイマー
	Timer timer;
	
	public DrawPanel() {
		try {
			java.util.Scanner sc = new java.util.Scanner(new File("Resorce\\Setting.inf"));

			if(sc.nextInt() == 0) {
				// タイトル画面を生成
				this.setScene(new TitleScene(this));
			} else {
				// 発表用
				this.setScene(new OpeningMovieScene(this));
			}

			// Timerを設定
			timer = new Timer(sc.nextInt(), new TimerListener());
		} catch(Exception e) { }
	}
	
	// タイマーをスタート
	public void start() {
		timer.start();
	}
	
	// 画面遷移。各シーンから呼び出す
	public void setScene(GameScene scene) {
		this.scene = scene;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// GraphicsをGraphics2Dにキャスト
		Graphics2D graphics = (Graphics2D)g;
		// アンチエイジングの設定
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// シーンの描画
		scene.draw(graphics);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// シーンの入力
		scene.input(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) { }
	
	@Override
	public void keyTyped(KeyEvent e) { }
	
	class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// シーンの更新
			scene.update();
			// 描画
			repaint();
		}
	}
}