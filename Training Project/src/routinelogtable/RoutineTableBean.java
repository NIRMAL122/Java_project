package routinelogtable;

public class RoutineTableBean 
{
	String mobileno;
	float cowqty;
	float bufqty;
	int year;
	int month;
	int day;
	
	RoutineTableBean()
	{}

	public RoutineTableBean(String mobileno, float cowqty, float bufqty, int year, int month, int day) {
		super();
		this.mobileno = mobileno;
		this.cowqty = cowqty;
		this.bufqty = bufqty;
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public float getCowqty() {
		return cowqty;
	}

	public void setCowqty(float cowqty) {
		this.cowqty = cowqty;
	}

	public float getBufqty() {
		return bufqty;
	}

	public void setBufqty(float bufqty) {
		this.bufqty = bufqty;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	

}
