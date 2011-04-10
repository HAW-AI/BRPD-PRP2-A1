package de.hawhamburg.informatik.ai.prp2;

import de.hawhamburg.informatik.ai.prp2.interfaces.Simulatable;
import jgame.JGObject;

public class JGObjectAdapter extends JGObject {
	private Simulatable object;
	private Simulator engine;
	
	public JGObjectAdapter(Simulator engine, Simulatable object) {
		// JGObject (String name,boolean unique_id,double x,double y,int collisionid,String gfxname
		super(object.getClass().toString(), false, 0,0, 1, object.getTexture());
		this.engine = engine;
		this.object = object;
	}
	
	@Override
	public void move() {
		object.simulate(gamespeed);
		this.setPos(object.getPosition() % engine.getDimension().x, 0);
	}
	
	public void paint() {
		object.paint();
	}
	
	public Double getSpeed() {
		return object.getSpeed();
	}
}
