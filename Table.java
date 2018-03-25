package chang_jong;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Table {

	private boolean cursor_entered = false;
	private int x, y;
	private static final int width = 60, height = 60;
	private BufferedImage table_img, out_table_img;
	
	public Table(int x, int y) {
		this.x = x;
		this.y = y;
		
		try {
			table_img = ImageIO.read(new File("res/table.png"));
			out_table_img = ImageIO.read(new File("res/out_table.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void render(Graphics g) {
		if (this.cursor_entered)
			g.drawImage(table_img, this.x, this.y, this.width, this.height, null);
		else	
			g.drawImage(table_img, this.x, this.y, this.width, this.height, null);
	}
	
	public void tick() {
	
	}
	private void enter_table() {
		this.cursor_entered = true;		
	}
	
	private void exit_table() {
		this.cursor_entered = false;
	}
	
	private void check_table() {
		if ()
	}
	
}
