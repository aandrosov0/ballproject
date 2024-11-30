package ballspiel;

import javafx.scene.paint.Color;

/**
 * ein Farbtopf mit einer bestimmten Menge Farbe
 */
public class Farbtopf {
	private Color farbe;
	private int fuellstand = 1000;


	/**
	 * fuellstand
	 * @return fuellstand
	 */
	public int getFuellstand() {
		return fuellstand;
	}
	
	/**
	 * verringert den Füllstand um die angegebene Menge
	 * @param menge entnommene Menge
	 */
	public synchronized void fuellstandVerringern(int menge)
	{
		if(menge > 0)
			fuellstand -= menge;
	}
	
	/**
	 * erhöht den Füllstand um die angegebene Menge
	 * @param menge dazukommende Menge
	 */
	public synchronized void fuellstandErhoehen(int menge)
	{
		if(menge > 0)
			fuellstand += menge;
	}
	
	/**
	 * farbe
	 * @return farbe
	 */
	public Color getFarbe() {
		return farbe;
	}
	
	/**
	 * erzeugt einen Farbtopf mit 1000 l der angegebenen Farbe
	 * @param farbe Farbe im Topf
	 * @throws IllegalArgumentException wenn farbe null ist
	 */
	public Farbtopf(Color farbe) {
		if(farbe == null)
			throw new IllegalArgumentException();
		this.farbe = farbe;
	}
	
}
