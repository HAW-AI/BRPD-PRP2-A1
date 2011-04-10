package de.hawhamburg.informatik.ai.prp2.interfaces;

public interface Simulatable {
	public void simulate(Double time);
	public void paint();
	public Double getPosition(); 
	public String getTexture();
	public Double getSpeed();
}
