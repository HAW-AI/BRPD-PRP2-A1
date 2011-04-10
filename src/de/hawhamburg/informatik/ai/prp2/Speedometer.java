package de.hawhamburg.informatik.ai.prp2;

import de.hawhamburg.informatik.ai.prp2.interfaces.Simulatable;
import jgame.JGColor;

public class Speedometer implements Simulatable {
	private Simulator engine;
	private Simulatable object;

	public Speedometer(Simulator engine, Simulatable object) {
		this.engine = engine;
		this.object = object;
	}

	@Override
	public void simulate(Double time) {
		engine.setFGColor(new JGColor((int)engine.random(0,255), (int)engine.random(0,255), (int)engine.random(0,255)));
	}
	
	public void paint() {
		engine.drawString(object.getSpeed().toString(), engine.getDimension().x - 20, engine.getDimension().y -20, 1);
	}

	@Override
	public Double getPosition() {
		// Do nothing
		return null;
	}

	@Override
	public String getTexture() {
		// Do nothing
		return null;
	}

	@Override
	public Double getSpeed() {
		// Do nothing
		return null;
	}

}
