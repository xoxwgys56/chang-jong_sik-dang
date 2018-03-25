package chang_jong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends JFrame{

	private int width, height;
	private String title;
	
	private Canvas canvas;
	
	public Display(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		init_frame();
	}
	
	private void init_frame() {
		this.setTitle(this.title);
		this.setSize(this.width, this.height);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.canvas = new Canvas();
		this.canvas.setPreferredSize(new Dimension(this.width, this.height));
		this.canvas.setMaximumSize(new Dimension(this.width, this.height));
		this.canvas.setMinimumSize(new Dimension(this.width, this.height));
		//this.canvas.setBackground(new Color(255, 100, 255));
		
		this.add(this.canvas);
		this.setVisible(true);
	}
	

	public Canvas getCanvas() {
		if (canvas == null)
			System.out.println("canvas is null");
		return canvas;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
