package info.salzsee.haw.prp2;

import java.util.ArrayList;

import jgame.JGObject;
/*
 * Partikel
 * - bewegen sich kontinuierlich
 * - bewegen sich, weil Kräfte auf sie einwirken
 * - können (aber müssen nicht !) eigene Antriebskräfte entwickeln (z.B. bei angetriebenen Fahrzeugen)
 */
public class Particle
	implements MassAfflicted, ForceAfflicted, SpeedAfflicted, Acceleratable, Simulatable {
	
	public Particle() {
		this("ball");
	}
	
	public Particle(String texture) {
		forces = new ArrayList<Double>();
		speed = 0.0;
		setMass(1000.0);
		setPosition(0.0);
		setTexture(texture);
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

	private Double position;
	
	@Override
	public Double getPosition() {
		return position;
	}
	
	public void setPosition(Double position) {
		this.position = position;
	}
	
	private String texture;
	
	public void setTexture(String texture) {
		this.texture = texture;
	}
	
	@Override
	public String getTexture() {
		return texture;
	}

	@Override
	public void simulate(Double time) {
		setPosition(getPosition() + getSpeed(time) * time);
	}
}
