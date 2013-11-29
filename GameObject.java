import java.awt.*;
import java.awt.event.*;

class GameObject {
	int x;
	int y;
	// http://codezine.jp/article/detail/2598?p=2
	// new Tiemr(33) 30fps
	// new Timer(16) 60fps
	
	public void update() { }
	public void draw(DrawPanel panel, Graphics2D graphics) { }
	public void input(KeyEvent key) { }
	
}


	// public abstract boolean drawImage( Image img, int x, int y, int width, int height, ImageObserver observer )
	
	// public abstract boolean drawImage( Image img, int dx1, int dy1,
	// int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer )
	
	// dx1,dy1は出力先の原点、dx2,dy2は出力先の長方形のサイズ
	// sy1,sy2は元画像の原点、sy1,sy2は元画像の長方形のサイズ
	
	//public abstract int getHeight( ImageObserver observer )
	// public abstract int getWidth( ImageObserver observer )