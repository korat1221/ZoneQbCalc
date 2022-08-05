package day0806;

public class QTCalc {
	double Calc_sink(double Te, double Ti, double HT){
		double QT_sink;
		QT_sink = (Ti-Te)* HT*24;
		return QT_sink;
	}	

	double Calc_source(double Te, double Ti, double HT){
		double QT_source;
		QT_source = (Te-Ti)* HT*24;
		return QT_source;
	}	
}
