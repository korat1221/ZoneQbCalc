package day0806;

public class Window {
	double Area;
	double Uvalue;
	double Uinst;
	String DiIndi;
	double Ff;
	double g;
	double τ;
	double gtot;
	double τtot;
	
	Window(double Area, double Uvalue, double Uinst, String DiIndi, double Ff, double g, double τ,double gtot, double τtot){
	this.Area=Area;
	this.Uvalue=Uvalue;
	this.Uinst=Uinst;
	this.DiIndi=DiIndi;
	this.Ff=Ff;
	this.g=g;
	this.τ=τ;
	this.gtot=gtot;
	this.τtot=τtot;
	}
	
	public double Area() {
		return Area;
	}
	public double Uvalue() {
		return Uvalue;
	}
	public double Uinst() {
		return Uinst;
	}

	public String DiIndi() {
		return DiIndi;
	}
	public double Ff() {
		return Ff;
	}
	public double g() {
		return g;
	}
	public double τ() {
		return τ;
	}
	public double gtot() {
		return gtot;
	}
	public double τtot() {
		return τtot;
	}

}
