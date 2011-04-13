package de.hawhamburg.informatik.ai.prp2;

import de.hawhamburg.informatik.ai.prp2.interfaces.Paintable;
import de.hawhamburg.informatik.ai.prp2.interfaces.Simulatable;

public class Speedometer implements Paintable {
	private Simulator engine;
	private Simulatable object;

	public Speedometer(Simulator engine, Simulatable object) {
		this.engine = engine;
		this.object = object;
	}

	public void paint() {
		Double speed = object.getSpeed() * 3.6;
		engine.drawString(speed.toString(), engine.getDimension().x - 20, engine.getDimension().y -20, 1);
	}
}
