package patke;

public class Unif {
	private double a,b;
	public Unif(double aa, double bb) {
		a=aa;
		b=bb;
	}
	public double unif() {
		double t=a + Math.random()*(b-a);
//		System.out.println(t);
//		double k=(t-a)/(b-a);
		return t;
	}
}
