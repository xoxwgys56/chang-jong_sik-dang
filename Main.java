package chang_jong;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Main implements Runnable{

	private boolean running = false;
	
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private Table[] table;
	private Display display;
	private MouseManager mouseManager;
	
	public Main() {
	
	}
	
	public void init() {
		//entities, 테이블 변수들 정리해야 함. ---2018.03.19
		table = new Table[3];
		
		table[0] = new Table(1, 1);
		table[1] = new Table(120, 1);
		table[2] = new Table(1, 120);
		
		//system
		mouseManager = new MouseManager();
		display = new Display(1200, 1200, "창종을 하자");
		
		display.getCanvas().addMouseListener(mouseManager);
	}
	
	@Override
	public void run() {
	
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		this.stop();
	}

	public void tick() {
		//현재 필요없음.
	}
	
	public void render() {

		bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return ;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, display.getWidth(), display.getHeight());
		
		table[0].render(g);
		table[1].render(g);
		table[2].render(g);
		
		bs.show();
		g.dispose();
	}
	
	public synchronized void start() {
		if (running)
			return ;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running)
			return ;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//main
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
}
