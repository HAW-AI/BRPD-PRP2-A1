package de.hawhamburg.informatik.ai.prp2;

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
		setLevel(1.0);
		setMass(1445.0);
		setPowerMax(456.0 * 1000);
		setSpeedMax(330.0 / 3.6);
		speed = 0.0;
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
	
	private Double level;
	
	public Double getLevel() {
		return level;
	}
	
	public void setLevel(Double level) {
		this.level = level;
	}
	
	public Double powerMax;
	
	public Double getPowerMax() {
		return powerMax;
	}
	
	public void setPowerMax(Double powerMax) {
		this.powerMax = powerMax;
	}
	
	public Double getPower() {
		return getLevel() * getPowerMax();
	}
	
	public Double getPropulsionMax() {
		return getMass() * getEarthAcceleration(); 
	}

	private Double getEarthAcceleration() {
		return 9.81;
	}
	
	public Double getPorpulsionAbsolute() {
		return Math.min(getPropulsionMax(), getPower() / getSpeed());
	}
	
	public Double getPorpulsion() {
		return getPorpulsionAbsolute() * getLevel();
	}
	
	private Double speedMax;

	public Double getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(Double speedMax) {
		this.speedMax = speedMax;
	}
	
	public Double getDragConstant() {
		return Math.abs(getPropulsionMax() / Math.pow(getSpeedMax(),3));
	}
	
	public Double getDragForce() {
		return getDragConstant() * Math.pow(getSpeed(),2) * -getSpeed();
	}
	
	public Double getForce() {
		return getPorpulsion() + getDragForce();
	}
}
