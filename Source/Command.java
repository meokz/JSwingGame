import java.awt.*;

class Command {
	CommandBox box;
	String text;
	int offsetX, offsetY;
	boolean selected = false;
	
	public Command(CommandBox box, String text, int offsetX, int offsetY) {
		this.box = box;
		this.text = text;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public void draw(Graphics2D graphics) {
		String str = "";
		if(selected) str += "▶";
		str += text;
		graphics.drawString(str, box.x + offsetX, box.y + offsetY);
	}
}