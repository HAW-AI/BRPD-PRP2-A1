package de.hawhamburg.informatik.ai.prp2;


import java.util.ArrayList;

import de.hawhamburg.informatik.ai.prp2.interfaces.Controlable;
import de.hawhamburg.informatik.ai.prp2.interfaces.Paintable;
import de.hawhamburg.informatik.ai.prp2.interfaces.Simulatable;
import jgame.*;
import jgame.platform.*;

@SuppressWarnings("serial")
public class Simulator extends JGEngine {
	private JGPoint size;
	private ArrayList<Paintable> hud;
	private ArrayList<Controlable> controlable;

	public Simulator(JGPoint size) {
		this.size = size;
		initEngine(size.x,size.y);
		this.hud = new ArrayList<Paintable>();
		this.controlable = new ArrayList<Controlable>();
	}
	
	@Override
	public void initCanvas() {
		// setCanvasSettings(TilesX,TilesY,TileW,TileH,FGColor,BGColor,MsgFont)
		setCanvasSettings(size.x/16,size.y/16,16,16,null,null, new JGFont("Arial", JGFont.PLAIN, 12));
	}

	@Override
	public void initGame() {
		defineMedia("sprite.tbl");
		// setFrameRate(fps,maxFrameSkip)
		setFrameRate(60,1);
		
		Particle p = new Particle();
		addObject(p);
		hud.add(new Speedometer(this, p));
	}
	
	/*
	 * Do anything needed to be done before this frame gets rendered
	 * @see jgame.platform.JGEngine#doFrame()
	 */
	public void doFrame() {
		for(Controlable c : controlable) {
			if (getKey(KeyDown)) { c.onKeyDown(); }
			if (getKey(KeyUp)) { c.onKeyUp(); }
			if (getKey(KeyLeft)) { c.onKeyLeft(); }
			if (getKey(KeyRight)) { c.onKeyRight(); }
		}

		moveObjects();
	}
	
	/*
	 * Render all custom object
	 * @see jgame.platform.JGEngine#paintFrame()
	 */
	public void paintFrame() {
		for(Paintable paintable : hud) {
			paintable.paint();
		}
	}
	
	public void addObject(Simulatable object) {
		this.controlable.add(new JGObjectAdapter(this, object));
	}
	
	public JGPoint getDimension() {
		return size;
	}
}
