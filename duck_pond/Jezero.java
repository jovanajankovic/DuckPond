package patke;
import java.awt.*;
import java.awt.event.*;

public class Jezero extends Canvas{

	protected int sirina;
	protected int visina;
	private double[] x;
	private double[] y;
	
	public Jezero(double[] xx, double[] yy) {
		x=xx;
		y=yy;
	}
	
	public void paint(Graphics g) {
		sirina=getWidth();
		visina=getWidth();
		g.setColor(Color.BLUE);
		g.fillOval(0, 0, sirina, sirina);
		g.setColor(Color.YELLOW);
		for(int i=0; i<x.length; i++) {
			int patkax,patkay;
			patkax=(int)(x[i]*sirina/2+sirina/2-5);
			patkay=(int)(sirina/2-y[i]*(sirina/2)-5);
			g.fillOval(patkax,patkay,5,5);
		}
	}	
}
