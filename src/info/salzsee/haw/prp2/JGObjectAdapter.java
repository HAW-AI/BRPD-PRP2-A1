package info.salzsee.haw.prp2;

import jgame.JGObject;

public class JGObjectAdapter extends JGObject {
	private Simulatable object;
	
	public JGObjectAdapter(Simulatable object) {
		// JGObject (String name,boolean unique_id,double x,double y,int collisionid,String gfxname
		super(object.getClass().toString(), false, 0,0, 1, object.getTexture());
		this.object = object;
	}
	
	@Override
	public void move() {
		object.simulate(gamespeed);
		this.setPos(object.getPosition(), 0);
	}
}
