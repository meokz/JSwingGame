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