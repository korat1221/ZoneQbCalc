package day0806;

public class ηCalc {
	double ηh_Calc(double γ, double a){
		double η1;
		double η2;
		double η;
		
		if(γ == 1) {
			η1 = a / (a + 1);
		}else {
			η1 = (1 - Math.pow(γ,a)) / (1 -Math.pow(γ,a+1));	
		}
		
		if((1 - η1 * γ) < 0.01) {
			η2 = 1 / γ;			
		}else {
			η2 = η1;			
		}
		η =Math.max(η1, η2);
       return η;		
	}	

	double ηc_Calc(double γ, double a){
		double η1;
		double η2;
		double η;
		
		if(γ == 1) {
			η1 = a / (a + 1);
		}else {
			η1 = (1 - Math.pow(γ,a)) / (1 -Math.pow(γ,a+1));	
		}
		
       if((1 - η1) * γ < 0.2) {
			η2 = 1 ;		
		} else {
			η2 = η1;			
		}
		η =Math.max(η1, η2);
       return η;		
	}	
	
}
