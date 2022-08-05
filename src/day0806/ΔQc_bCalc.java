package day0806;

public class ΔQc_bCalc {
	double Calc(double Cwirk, double θi_h_set , double θi_h, double awe, double Δθi_NA, double Qsink, double η, double Qsource){
		double ΔQc_b;
		ΔQc_b = Math.min(Math.min((Cwirk*2*(θi_h_set - θi_h) / awe),(Cwirk*Δθi_NA / awe)), (Qsink - η*Qsource));
		return ΔQc_b;
	}	

}
