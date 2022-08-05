package day0806;

public class QVCalc {
	double Calc_sink(double Te, double Ti, double HV){
		double QV_sink;
		QV_sink = (Ti-Te)* HV*24;
		return QV_sink;
	}	

	double Calc_source(double Te, double Ti, double HV){
		double QV_source;
		QV_source = (Te-Ti)* HV*24;
		return QV_source;
	}	
}
