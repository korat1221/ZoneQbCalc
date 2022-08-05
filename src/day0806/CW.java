package day0806;

public class CW {
	double Area_g;
	double Uvalue_g;
	double Ff_g;
	double g_g;
	double gtot_g;
	double τ_g;
	double τtot_g;
	double Area_p;
	double Uvalue_p;
	double α_p;
	double Area_d;
	double Uvalue_d;
	double Ff_d;
	double g_d;
	double τ_d;
	double Area_tot;
	double Uinst;
	
	CW(double Area_g, double Uvalue_g, double Ff_g,	double g_g,	double gtot_g, double τ_g, double τtot_g, double Area_p, double Uvalue_p, double α_p, double Area_d, double Uvalue_d, double Ff_d, double g_d, double τ_d, double Area_tot, double Uinst){
		this.Area_g = Area_g;
		this.Uvalue_g = Uvalue_g;
		this.Ff_g = Ff_g;
		this.g_g = g_g;
		this.gtot_g = gtot_g;
		this.τ_g = τ_g;
		this.τtot_g = τtot_g;
		this.Area_p = Area_p;
		this.Uvalue_p = Uvalue_p;
		this.α_p = α_p;
		this.Area_d = Area_d;
		this.Uvalue_d = Uvalue_d;
		this.Ff_d = Ff_d;
		this.g_d = g_d;
		this.τ_d = τ_d;
		this.Area_tot = Area_tot;
		this.Uinst = Uinst;
	}
	
	public double Area_g() {
		return Area_g;
	}
	public double Uvalue_g() {
		return Uvalue_g;
	}
	public double Ff_g() {
		return Ff_g;
	}
	public double g_g() {
		return g_g;
	}
	public double gtot_g() {
		return gtot_g;
	}
	public double τ_g() {
		return τ_g;
	}
	public double τtot_g() {
		return τtot_g;
	}
	public double Area_p() {
		return Area_p;
	}
	public double Uvalue_p() {
		return Uvalue_p;
	}
	public double α_p() {
		return α_p;
	}
	public double Area_d() {
		return Area_d;
	}
	public double Uvalue_d() {
		return Uvalue_d;
	}
	public double Ff_d() {
		return Ff_d;
	}
	public double g_d() {
		return g_d;
	}
	public double τ_d() {
		return τ_d;
	}
	public double Area_tot() {
		return Area_tot;
	}
	public double Uinst() {
		return Uinst;
	}
}
