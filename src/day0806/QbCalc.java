package day0806;

public class QbCalc {
	double Qhb_Calc(double Qsink, double η, double Qsource){
		double Qhb;
		Qhb = Qsink - η * Qsource;
		return Qhb;
	}	

	double Qcb_Calc(double η, double Qsource){
		double Qcb;
		Qcb = (1 - η) * Qsource;
		return Qcb;
	}	
}
