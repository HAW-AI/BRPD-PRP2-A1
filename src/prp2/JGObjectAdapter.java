package prp2;

import jgame.JGObject;

public class JGObjectAdapter extends JGObject {
	private Simulatable object;

	public JGObjectAdapter(Simulatable object) {
		super(object.getClass().toString(), false, 0,0, 1, "ball");
		this.object = object;
	}

	public void move() {
		System.out.println("Object: " + object.toString());
		object.step(gamespeed);
		this.setPos(object.getPosition(), 0);
	}
}
