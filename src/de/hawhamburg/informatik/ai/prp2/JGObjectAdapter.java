package de.hawhamburg.informatik.ai.prp2;

import de.hawhamburg.informatik.ai.prp2.interfaces.Controlable;
import de.hawhamburg.informatik.ai.prp2.interfaces.LevelAfflicted;
import de.hawhamburg.informatik.ai.prp2.interfaces.Simulatable;
import jgame.JGObject;

public class JGObjectAdapter extends JGObject implements Controlable {
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
		
		System.out.println(object.getSpeed() + " <-> " + ((LevelAfflicted) object).getLevel());
	}
	
	public Double getSpeed() {
		return object.getSpeed();
	}

	@Override
	public void onKeyDown() {
		if (object instanceof LevelAfflicted) {
			System.out.println("LevelAfflicted onKeyDown");
			((LevelAfflicted) object).decreaseLeveL();
		}
	}

	@Override
	public void onKeyUp() {
		if (object instanceof LevelAfflicted) {
			System.out.println("LevelAfflicted onKeyUp");
			((LevelAfflicted) object).increaseLevel();
		}
	}

	@Override
	public void onKeyLeft() {
		// NOTE not needed right now
	}

	@Override
	public void onKeyRight() {
		// NOTE not needed right now
		
	}
}
