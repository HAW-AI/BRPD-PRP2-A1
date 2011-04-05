package info.salzsee.haw.prp2;

import java.util.ArrayList;

import jgame.JGObject;
/*
 * Partikel
 * - bewegen sich kontinuierlich
 * - bewegen sich, weil Kr�fte auf sie einwirken
 * - k�nnen (aber m�ssen nicht !) eigene Antriebskr�fte entwickeln (z.B. bei angetriebenen Fahrzeugen)
 */
public class Particle extends JGObject 
	implements MassAfflicted, ForceAfflicted, SpeedAfflicted, Acceleratable, Simulatable {
	
	public Particle() {
		// JGObject (String name,boolean unique_id,double x,double y,int collisionid,String gfxname
		super("Particle", false, 0,0, 1, "ball");
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

	@Override
	public Double getPosition() {
		return this.getLastX();
	}
	
	@Override
	public void move() {
		x = x + getSpeed(gamespeed) * gamespeed;
	}
}
