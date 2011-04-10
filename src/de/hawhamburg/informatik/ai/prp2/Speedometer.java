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
		engine.drawString(object.getSpeed().toString(), engine.getDimension().x - 20, engine.getDimension().y -20, 1);
	}
}
