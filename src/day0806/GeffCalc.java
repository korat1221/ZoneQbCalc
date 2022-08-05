package day0806;

public class GeffCalc {
	double g;
	double Fs;
	double Fw = 0.9;
	double Fv= 0.9;
	double gtot;
	double a;
	double Calc(double g, double Fs){
		double geff;		
		geff = Fs*Fw*Fv*g;		
		return geff;		
	}
	double Calc(double g, double Fs, double gtot, double a){
		double geff;		
		if(a*gtot+(1-a)*g>Fs*g) {	
			geff = Fs*Fw*Fv*g;				
		}else {
			geff = Fw*Fv*(a*gtot+(1-a)*g);	
		}		
		return geff;			
	}
}
