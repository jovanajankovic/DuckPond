package patke;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.awt.*;
import java.util.Scanner;

public class Prozor extends Frame {
	
	private double[] x, y, r, f;
	private Panel gore =new Panel(new GridLayout(2,4));
	private Panel pomocni=new Panel(new GridLayout(1,2));
	private TextField n,N,NIVO,p_kapa,interval;
	private Button dugme=new Button("Izracunaj");
	private Panel crtez=new Panel();
	private Panel desno=new Panel(new GridLayout(2,2));
	private Jezero jezero;
	private Scanner scanner=new Scanner(System.in);
	private static DecimalFormat df8=new DecimalFormat("#.########");
	private static DecimalFormat df3=new DecimalFormat("#.###");
	int nn,NN,brojac,brojac2;
	private int broj;
	double nivo;
	
	public Prozor() {
		super("Patke na jezeru");
		setBounds(300,300,800,700);
		popuniProzor1();
		popuniProzor2();   
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent w) {
				dispose();
			}
		});
		setVisible(true);
	}
	
	private void popuniProzor1() {
		gore.add(new Label("n:",Label.CENTER));
		gore.add(n=new TextField());
		gore.add(new Label("N:",Label.CENTER));
		gore.add(N=new TextField());
		gore.add(new Label("Nivo povereneja:",Label.CENTER));
		gore.add(NIVO=new TextField());
		gore.add(dugme);
        dugme.setEnabled(true);
        dugme.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent a) {
        		
        		Unif ro=new Unif(0,1);
        		Unif fi=new Unif(0,2*Math.PI);
        		
        		//cita iz polja u GUI-u
        		//int nn;  //br pataka	
                nn=Integer.parseInt(n.getText());
        		
        		//int NN;  //br simulacija
        		NN=Integer.parseInt(N.getText());
        		
        		//double nivo; //nivo poverenja na ulazu
        		nivo=Double.parseDouble(NIVO.getText());
        		
        		int brojac=0, brojac2=0; //broji koliko puta su sletele u jedan polukrug
        		//*kreiranje svih simulacija
        		for(int k=0; k<NN; k++) {
        
        			//*Odredjivanje koordinata na slucajan nacin      			
        			x=new double[nn];
        			y=new double[nn];
        			r=new double[nn];
        			f=new double[nn];
        		
        			for(int i=0; i<nn; i++) {
        				r[i]=ro.unif();
        				f[i]=fi.unif();        				
        			
        				x[i]=Racun.X(r[i], f[i]);
        				y[i]=Racun.Y(r[i], f[i]);   
        			}

        			f=Racun.sortiranje(f);       			
        			
        			//*Odredjivanje rasporeda pataka (da li su u istom polukrugu)
//        			if(Racun.Pripada(x, y)==true) brojac++;
        			if(Racun.Pripada(f)==true) brojac2++;
        			
        			if (broj==0) {
            			broj++;
            			pomocni.add(jezero=new Jezero(x,y));
            			validate();
            		}
            		else jezero.repaint();
            		validate();
        		}
//        		double exp_ver=(double)brojac/NN;
        		double exp_ver2=(double)brojac2/NN;  //P(A)=n(A)/n
        		double teo_ver;
        		
        		int imen=1<<(nn-1);
        		teo_ver=(double)nn/imen;	
        		
        		p_kapa.setText(""+df8.format(exp_ver2));
//        		System.out.print(exp_ver + "  " + exp_ver2);
        		
        		double intlevi, intdesni;
        		intlevi=exp_ver2-Racun.Kvantil(nivo,nn)*Math.sqrt(exp_ver2*(1-exp_ver2)/nn);
        		intdesni=exp_ver2+Racun.Kvantil(nivo,nn)*Math.sqrt(exp_ver2*(1-exp_ver2)/nn);
        		interval.setText("["+df3.format(intlevi)+","+df3.format(intdesni)+"]");
        	}
        });
        
        add(gore,"North");
	}
	
	private void popuniProzor2() {
		Panel pom=new Panel();
		add(pomocni);
		pomocni.add(pom);
		pom.add(desno);
		desno.add(new Label("p_kapa"));
		desno.add(p_kapa=new TextField());
		desno.add(new Label("interval poverenja"));
		desno.add(interval=new TextField());		
	}
}
