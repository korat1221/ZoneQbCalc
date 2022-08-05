package day0806;

public class Door {
	double Area;
	double Ueff;
	double α;
	String DiIndi;
	
	Door(double Area, double Ueff,	double α,String DiIndi){
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
