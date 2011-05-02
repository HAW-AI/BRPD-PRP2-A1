package prp2;

import jgame.JGPoint;
import jgame.platform.JGEngine;

@SuppressWarnings("serial")
public class Simulator extends JGEngine {
	private JGPoint size = new JGPoint(960,480);
	
	public static void main(String[] args) {
		new Simulator();
	}
	
	public Simulator() {
		initEngine(size.x,size.y);
		setPFWrap(true, true, 0, 0);
	}

	@Override
	public void initCanvas() {
		setCanvasSettings(size.x/16,size.y/16,16,16,null,null, null);
	}

	@Override
	public void initGame() {
		defineMedia("sprite.tbl");
		setFrameRate(60,1);
		
		Particle particle = new Particle();
		System.out.println(particle);
		new JGObjectAdapter(particle);
	}
	
	@Override 
	public void doFrame() {
		moveObjects();
	}
}
