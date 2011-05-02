package prp2;

/**
 * 
 * @var Position
 * @var Mass
 * @var PowerPropMax
 * @var SpeedMax
 */
public class Particle implements Simulatable {
	private final Double speed_max;
	private final Double drag_const;
	private final Double power_prob_max;
	private final Double mass;
	
	public Particle() {
		speed_max = 333.0 / 3.6;
		mass = 1445.0;
		power_prob_max = 456.0;
		drag_const = Math.abs(getPowerPropMax() / Math.pow(getSpeedMax(), 3));
		setLevel(1.0);
	}
	
	/*
	 * Beschleunigung
	 */
	public Double getAcceleration() {
		return getForce() / getMass();
	}
	
	/*
	 * Antriebskraft
	 */
	public Double getForceProp() {
		return getAbsoluteForceProp() * Util.sign(getLevel());
	}
	
	/* 
	 * Betrag der Antriebskraft
	 */
	public Double getAbsoluteForceProp() {
		// Math.abs(getPowerProp() / getAbsoluteSpeed()) or (getPowerProp() / getAbsoluteSpeed())
		return Math.min(getForcePropMax(), getPowerProp() / getAbsoluteSpeed());
	}
	
	/*
	 * Widerstandskraft
	 */
	public Double getForceDrag() {
		return getDragConst() * Math.pow(getSpeed(), 2) * Util.sign(-getSpeed());
	}
	
	/*
	 * Antriebskraft + Widerstrandskraft = Bewegungskraft(?)
	 */
	public Double getForce() {
		return getForceProp() + getForceDrag();
	}
	
	/* 
	 * Betrag der Widersteandskraft
	 */
	public Double getAbsoluteForceDrag() {
		return Math.abs(getDragConst() * Math.pow(getSpeed(), 2));
	}
	
	/* 
	 * Bewegungsleistung
	 */
	public Double getPowerProp() {
		return Math.abs(getLevel()) * getPowerPropMax();
	}
	
	/*
	 * Maximale Beschleunigungsleistung
	 */
	public Double getPowerPropMax() {
		return power_prob_max;
	}
	
	/* 
	 * Maximale Antriebskraft
	 */
	public Double getForcePropMax() {
		return getMass() * getAccelerationEarth();
	}
	
	/*
	 * Erdbeschleunigung
	 */
	private Double getAccelerationEarth() {
		return 9.81;
	}

	/*
	 * Schubkontrolle
	 */
	private Double level;
	public Double getLevel() {
		return level;
	}
	public void setLevel(Double level) {
		if (level > 1.0) {
			this.level = 1.0;
		}
		else if (level < -1.0) {
			this.level = -1.0;
		}
		else {
			this.level = level;
		}
	}
	
	/* 
	 * Maximal Geschwindigkeit
	 */
	public Double getSpeedMax() {
		return speed_max;
	}
	
	/* 
	 * Widerstandskonstrante
	 */
	public Double getDragConst() {
		return drag_const;
	}
	
	/*
	 * Widerstandskraft bei maximal Geschwindigkeit
	 */
	public Double getForceDragAtMaxSpeed() {
		return getDragConst() * Math.pow(getSpeedMax(), 2);
	}
	
	/* 
	 * Beschleunigungskraft bei maximal Geschwindigkeit
	 */
	public Double getForcePropAtMaxSpeed() {
		return getPowerPropMax() / getSpeedMax();
	}
	
	/*
	 * Masse 
	 */
	public Double getMass() {
		return mass;
	}
	
	/* 
	 * Geschwindigkeit
	 */
	private Double speed = 0.0;
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getAbsoluteSpeed() {
		return Math.abs(getSpeed());
	}
	
	/*
	 * Geschwindigkeitsberechnung fŸr den nŠchsten Schritt
	 */
	public void calculateSpeed(Double deltaTime) {
		setSpeed(getSpeed() + (getAcceleration() * deltaTime));
	}
	
	/* 
	 * Zeit
	 */
	private Double time = 0.0;
	public Double getTime() {
		return time;
	}
	public void addTime(Double deltaTime) {
		time += deltaTime;
	}
	
	/* 
	 * Schritt fŸr die Simulation
	 */
	public void step(Double deltaTime) {
		setPosition(getPosition() + getSpeed() * deltaTime);
		setSpeed(getSpeed() + (getAcceleration() * deltaTime));
	}
	
	/* 
	 * Position
	 */
	private Double position = 0.0;
	public Double getPosition() {
		return position;
	}
	public void setPosition(Double position) {
		this.position = position;
	}
	
	/* 
	 * toString()
	 */
	public String toString() {
		return "Particle("
		+ "Speed: " + getSpeed()
		+ ", Acc: "+ getAcceleration()
		+ ", Force: " + getForce()
		+ ")";
		
	}
}
