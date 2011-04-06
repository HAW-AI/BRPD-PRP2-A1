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
		setX(0.0);
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

	@Override
	public Double getPosition() {
		return this.getX();
	}
	
	private String texture;
	
	public void setTexture(String texture) {
		this.texture = texture;
	}
	
	@Override
	public String getTexture() {
		return texture;
	}
	
	private Double x;
	
	public void setX(Double x) {
		this.x = x;
	}
	
	public Double getX() {
		return x;
	}

	@Override
	public void move(Double time) {
		x = x + getSpeed(time) * time;
	}
}
