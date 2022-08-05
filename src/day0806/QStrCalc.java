package day0806;

public class QStrCalc {
	double Ff; 
	double Area; 
    double geff;
    double Is;
    
    double Calc(double Ff, double Area, double geff, double Is){
    	double QS;
    	QS = Ff * Area * geff * Is *24;    	
    		return QS;      	
	}	

   
}
