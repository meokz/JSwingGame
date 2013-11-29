
class TextBox extends GameObject {
	Font font;
	String text;
	// int offsetX, offsetY;
	
	public TextBox(int x, int y) {
		this.x = x;
		this.y = y;
		this.text = "";
		/*
		this.offsetX = offsetX;
		this.offsetY = offsetY;*/
		
		font = new ("ÉÅÉCÉäÉI", Font.PLAIN, 20);
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setFont(this.font);
		graphics.drawString(this.text, this.x, this.y);
	}
}