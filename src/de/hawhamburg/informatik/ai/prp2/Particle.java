package de.hawhamburg.informatik.ai.prp2;


import java.util.ArrayList;

import de.hawhamburg.informatik.ai.prp2.interfaces.Acceleratable;
import de.hawhamburg.informatik.ai.prp2.interfaces.ForceAfflicted;
import de.hawhamburg.informatik.ai.prp2.interfaces.MassAfflicted;
import de.hawhamburg.informatik.ai.prp2.interfaces.Simulatable;
import de.hawhamburg.informatik.ai.prp2.interfaces.SpeedAfflicted;

/*
 * Partikel
 * - bewegen sich kontinuierlich
 * - bewegen sich, weil Kräfte auf sie einwirken
 * - können (aber müssen nicht !) eigene Antriebskräfte entwickeln (z.B. bei angetriebenen Fahrzeugen)
 */
public class Particle 
	implements MassAfflicted, ForceAfflicted, SpeedAfflicted, Acceleratable, Simulatable {
	
	public Particle() {
		forces = new ArrayList<Double>();
		speed = 0.0;
		setMass(500.0);
	}
	
	public Particle(Double force) {
		this();
		addForce(force);
	}
	
	private ArrayList<Double> forces;
	
	@Override
	public void addForce(Double force) {
		forces.add(force);
	}
	
	@Override
	public void clearForce() {
		forces.clear();
	}

	@Override
	public Double getForce() {
		Double accu = 0.0;
		for (Double force: forces) {
			accu += force;
		}
		return accu;
	}

	private Double mass;
	
	@Override
	public void setMass(Double mass) {
		this.mass = mass;
	}

	@Override
	public Double getMass() {
		return mass;
	}

	@Override
	public Double getAcceleration() {
		return getForce() / getMass();
	}

	private Double speed;
	@Override
	public Double getSpeed(Double time) {
		speed += (getAcceleration() * time);
		return speed;
	}
	
	public Double getSpeed() {
		return speed;
	}
	
	private Double position = 0.0;
	
	@Override
	public Double getPosition() {
		return position;
	}
	
	@Override
	public void simulate(Double time) {
		position = position + getSpeed(time) * time;
	}

	@Override
	public String getTexture() {
		return "ball";
	}
}
