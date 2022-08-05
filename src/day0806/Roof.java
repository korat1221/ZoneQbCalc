package day0806;

public class Roof {
	double Area;
	double Ueff;
	double α;
	String DiIndi;
	
	Roof(double Area, double Ueff,	double α,String DiIndi){
	this.Area=Area;
	this.Ueff=Ueff;
	this.α=α;
	this.DiIndi=DiIndi;
	}
	
	public double Area() {
		return Area;
	}
	public double Ueff() {
		return Ueff;
	}

	public double α() {
		return α;
	}

	public String DiIndi() {
		return DiIndi;
	}

}
