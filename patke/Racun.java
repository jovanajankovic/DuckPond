package patke;

public class Racun {
	
	public static double X(double ro, double fi) {		
		double x=ro*Math.cos(fi);
		return x;
	}
	
	public static double Y(double ro, double fi) {
		double y=ro*Math.sin(fi);	
		return y;
	}
	
	public static double[] sortiranje(double[] fi) {
		double pom;		
		for(int i=0; i<fi.length-1; i++) {
			for(int j=i+1; j<fi.length; j++ ) {
				if(fi[i]>fi[j]) {
					pom=fi[i];
					fi[i]=fi[j];
					fi[j]=pom;
				}
			}
		}	
		return fi;	
	}
	
	//f-ja O(n^2)
	public static boolean Pripada(double[] x, double[] y) {
		boolean pripada=false;
		int n=x.length;
		
		for(int i=0; i<n && pripada==false; i++) {
			int strana=0; //manje -1, vece 1
			boolean flag=true;
	
			double k=y[i]/x[i];
			
			for(int j=0; j<n; j++) {
				//da li je ispod (manje)
				if(y[j]<k*x[j]) {
					if (strana==0) strana=-1;
					if (strana==1) {
						flag=false;
						break;
					}
					//ako je strana==-1 onda nema promene 
					//-> sve tacke do sada su sa iste strane
				}
				
				//da li je iznad (vece)
				if(y[j]>k*x[j]) {
					if (strana==0) strana=1;
					if (strana==-1) {
						flag=false;
						break;
					}
					//ako je strana==1 onda nema promene 
					//-> sve tacke do sada su sa iste strane
				}
			}
			if(flag==true) pripada=true;
		}
				
		return pripada;
	}

	public static boolean Pripada(double[] fi) {
		double start=fi[0], end=fi[0];
		for (int i=1; i<fi.length; i++) {
			if (2*Math.PI-fi[i]+start<fi[i]-end) { 
				start=fi[i];
				break;
			}
			else end=fi[i];
		}
		
		if (start>fi[0]) 
			if (2*Math.PI-start+end<=Math.PI) return true;
			else return false;
		else
			if (end-start<=Math.PI) return true;
			else return false;	
	}

	public static double Kvantil(double nivo, int br) {
		double alfa, n=nivo/100;
		double[][] tabela= {
			{1.0,3.078,6.314,12.706,31.821,63.657},	//1
			{0.727,1.476,2.015,2.571,3.365,4.032}, 	//5
			{0.700,1.372,1.812,2.228,2.764,3.169},	//10
			{0.691,1.341,1.753,2.131,2.602,2.947},	//15
			{0.687,1.325,1.725,2.086,2.528,2.845},	//20
			{0.684,1.316,1.708,2.060,2.485,2.787},	//25
			{0.683,1.310,1.697,2.042,2.457,2.750},	//30
			{0.68,1.28,1.65,1.96,2.33,2.58},		//>30
		};
		
		alfa=1-n;
		n=1-alfa/2;
		int i=0;
		if (n<=0.75) i=0;
		else if (n<=0.90) i=1;
		else if (n<=0.95) i=2;
		else if (n<=0.975) i=3;
		else if (n<=0.99) i=4;
		else i=5;
		
		if (br==1) return tabela[0][i];
		else if (br<=5) return tabela[1][i];
		else if (br<=10) return tabela[2][i];
		else if (br<=15) return tabela[3][i];
		else if (br<=20) return tabela[4][i];
		else if (br<=25) return tabela[5][i];
		else if (br<=30) return tabela[6][i];
		else return tabela[7][i];
	}
	
}
