import java.awt.*;

class FontInstance {
	public static Font meiryo10;
	public static Font meiryo20;
	public static Font meiryo30;
	public static Font meiryo40;
	
	public FontInstance() {
		meiryo10 = new Font("メイリオ", Font.PLAIN, 10);
		meiryo20 = new Font("メイリオ", Font.PLAIN, 20);
		meiryo30 = new Font("メイリオ", Font.PLAIN, 30);
		meiryo40 = new Font("メイリオ", Font.PLAIN, 40);
	}
}