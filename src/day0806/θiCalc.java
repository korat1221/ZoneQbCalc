package day0806;

public class θiCalc {
	

	double τ_Calc(double cwirk, double H){
		double τ;
		τ = cwirk / H;
		return τ;
	}	


	double θihwe_Calc(double τ, String Mode_we, double θe, double θi_h_set, double Δθi_NA){
		double θi_h_we;
		double f_we;
		
		if(Mode_we.equals("reduced operation")) {
			f_we = 0.2 * (1 - 0.4 * τ / 250);
		} else if(Mode_we.equals("stop operation")) {
			f_we = 0.3 * (1 - 0.2 * τ / 250);
		}else {
			f_we = 0;
			
		}
		θi_h_we= Math.max(θi_h_set-f_we*(θi_h_set-θe),θi_h_set - Δθi_NA);
		
		return θi_h_we;
	}	

	
	double θihwd_Calc(double τ, String Mode_wd, double tNA, double θe, double θi_h_set, double Δθi_NA){
		double θi_h_wd;
		double f_wd;
		
		if(Mode_wd.equals("reduced operation")) {
			f_wd = 0.13 * tNA / 24 * Math.exp((-τ / 250));
		} else if(Mode_wd.equals("stop operation")) {
			f_wd = 0.26 * tNA / 24 * Math.exp((-τ / 250));
		}else {
			f_wd = 0;
			
		}
		θi_h_wd= Math.max(θi_h_set-f_wd*(θi_h_set-θe),θi_h_set- Δθi_NA * tNA / 24);
		
		return θi_h_wd;
	}	

	double θic_Calc(double θi_c_set){
		double θi_c = θi_c_set -2;
		
		return θi_c;
	}	
	
	
}
