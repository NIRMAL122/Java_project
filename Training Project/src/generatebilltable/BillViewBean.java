package generatebilltable;

public class BillViewBean 
{
	String mobileno;
	int datestart;
	float totcowqty;
	float buffqty;
	float cowbill;
	float buffbill;
	float total;
	String month;
	String year;
	int recieve;
	
	BillViewBean()
	{}

	public BillViewBean(String mobileno, int datestart, float totcowqty, float buffqty, float cowbill, float buffbill,
			float total, String month, String year, int recieve) {
		super();
		this.mobileno = mobileno;
		this.datestart = datestart;
		this.totcowqty = totcowqty;
		this.buffqty = buffqty;
		this.cowbill = cowbill;
		this.buffbill = buffbill;
		this.total = total;
		this.month = month;
		this.year = year;
		this.recieve = recieve;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public int getDatestart() {
		return datestart;
	}

	public void setDatestart(int datestart) {
		this.datestart = datestart;
	}

	public float getTotcowqty() {
		return totcowqty;
	}

	public void setTotcowqty(float totcowqty) {
		this.totcowqty = totcowqty;
	}

	public float getBuffqty() {
		return buffqty;
	}

	public void setBuffqty(float buffqty) {
		this.buffqty = buffqty;
	}

	public float getCowbill() {
		return cowbill;
	}

	public void setCowbill(float cowbill) {
		this.cowbill = cowbill;
	}

	public float getBuffbill() {
		return buffbill;
	}

	public void setBuffbill(float buffbill) {
		this.buffbill = buffbill;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getRecieve() {
		return recieve;
	}

	public void setRecieve(int recieve) {
		this.recieve = recieve;
	}
		
	
	

}
