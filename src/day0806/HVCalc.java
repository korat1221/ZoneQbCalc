package day0806;

public class HVCalc {
	double cpaρa =0.34;
	
	double HV_mech_Calc(double Vmech_SUP, double tV_mech, double V){		
		double nmech_SUP = Vmech_SUP / V;
		double nmech  = nmech_SUP * tV_mech / 24;		
		double HV_mech = nmech * V * cpaρa;
		return HV_mech;
	}	

	double HV_z_Calc(double Vmech_SUP, double Vmech_ETA, double tV_mech, double V){		
		double nmech_SUP = Vmech_SUP / V;
		double nmech_ETA = Vmech_ETA / V;
		double nz_SUP  =  nmech_ETA-nmech_SUP;	
		double nz_d  = nz_SUP * tV_mech / 24 ;		
		double HV_z = nz_d * V * cpaρa;
		return HV_z;
	}	
	double HV_inf_Calc(double Vmech_SUP, double Vmech_ETA, double Vmech_SUP_z, double Vmech_ETA_z, double tV_mech, double n50, double V, double e,double f){		
		double nmech_SUP = Vmech_SUP / V;
		double nmech_ETA = Vmech_ETA / V;
		double nz_SUP = nmech_ETA-nmech_SUP;	
		double nz_ETA = (Vmech_ETA_z-Vmech_SUP_z) / V;	
		double nSUP = nmech_SUP + nz_SUP;	
		double nETA = nmech_ETA + nz_ETA;	
		double ninf, fe=1;
		if(nSUP ==0){
			ninf = n50*e;			
		}else {
		fe = 1 / (1 + f/e * Math.pow(((nETA - nSUP) / n50),2));
		ninf = n50*e*(1+(fe-1)*tV_mech/24);
		}
		double HV_inf = ninf * V * cpaρa;
		return HV_inf;
	}		

	double HV_win_Calc(double Vmech_SUP, double Vmech_ETA, double Vmech_SUP_z, double Vmech_ETA_z, double tV_mech, double twd, double n50,  double nwd,  double V, double e,double f){		
		double nmech_SUP = Vmech_SUP / V;
		double nmech_ETA = Vmech_ETA / V;
		double nz_SUP = nmech_ETA-nmech_SUP;	
		double nz_ETA = (Vmech_ETA_z-Vmech_SUP_z) / V;	
		double nSUP = nmech_SUP + nz_SUP;	
		double nETA = nmech_ETA + nz_ETA;	
		double ninf, fe;
		double Δnwin_mech_0, Δnwin_mech, Δnwin, nwin;
		
		//ninf계산
		if(nSUP ==0){
			ninf = n50*e;	
			fe =1;
		}else {
		fe = 1 / (1 + f/e * Math.pow(((nETA - nSUP) / n50),2));
		ninf = n50*e*(1+(fe-1)*tV_mech/24);
		}
		
		//Δnwin_mech_0계산 
		if(nwd <1.2){
			Δnwin_mech_0 = Math.max(0, nwd - (nwd - 0.2) * ninf * fe - 0.1);		
		}else {
			Δnwin_mech_0 = Math.max(0, nwd - ninf * fe - 0.1);	
		}
		
		//Δnwin_mech 계산 
				if((Δnwin_mech_0 <= nSUP) && (nETA <= (nSUP+ninf))) {
					Δnwin_mech = 0;
				}else if((Δnwin_mech_0 <= nSUP) && (nETA > (nSUP+ninf))) {
					Δnwin_mech = nETA - nSUP - ninf;			
				}else if((Δnwin_mech_0 > nSUP) && (nETA <= (nSUP+ninf))) {
					Δnwin_mech = Δnwin_mech_0 - nSUP;			
				}else {
					Δnwin_mech = nETA - nSUP - ninf;		
				}
				

		//Δnwin 계산 
				if(nwd <1.2){
					Δnwin = Math.max(0, nwd - (nwd - 0.2) * ninf - 0.1);		
				}else {
					Δnwin = Math.max(0, nwd - ninf - 0.1);	
				}				
				
				
		//nwin 계산 
				if(nSUP ==0){
					nwin = 0.1 + Δnwin * twd /24;		
				}else {
					nwin = Math.max(0, 0.1 + Δnwin * Math.max((twd - tV_mech),0) / 24 + Δnwin_mech  * tV_mech / 24);	
				}

				double HV_win = nwin * V * cpaρa;
				return HV_win;		
	}		
	

}
