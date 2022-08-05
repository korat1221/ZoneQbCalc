package day0806;

public class QSopCalc{
	double Rse =0.04;
	double Uvalue; 
	double Area; 
	double Ff =0.5;
	double hr = 4.5;
    double Δθer = 10;
    double α;
    double IS;
    
    double Calc(double Uvalue, double Area, double α, double IS){
    	double QSop_sink, QSop_source;
    	if(Ff * hr * Δθer >= α * IS) {
    		QSop_sink = Rse * Uvalue * Area * (Ff * hr * Δθer - α * IS) * 24;  
    		QSop_source = 0;  
    		return QSop_sink;
    	}else {
    		QSop_sink =0;
    		QSop_source = Rse * Uvalue * Area * (α * IS - Ff * hr * Δθer) * 24;    
    		return QSop_source;
    	}
    	
    	
	}	

}
