package customertable;

public class CustomerBean
{
	String mobileno;
	String name;
	String address;
	String area;
	String city;
	float cowqty;
	float cowprice;
	float bufqty;
	float bufprice;
	String datestart;
	int status;
	
	public CustomerBean()
	{}
		
	
	
	
	
	
	
	public CustomerBean(String mobileno, String name, String address, String area, String city, float cowqty,
			float cowprice, float bufqty, float bufprice, String datestart, int status) {
		super();
		this.mobileno = mobileno;
		this.name = name;
		this.address = address;
		this.area = area;
		this.city = city;
		this.cowqty = cowqty;
		this.cowprice = cowprice;
		this.bufqty = bufqty;
		this.bufprice = bufprice;
		this.datestart = datestart;
		this.status = status;
	}







	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public float getCowqty() {
		return cowqty;
	}
	public void setCowqty(float cowqty) {
		this.cowqty = cowqty;
	}
	public float getCowprice() {
		return cowprice;
	}
	public void setCowprice(float cowprice) {
		this.cowprice = cowprice;
	}
	public float getBufqty() {
		return bufqty;
	}
	public void setBufqty(float bufqty) {
		this.bufqty = bufqty;
	}
	public float getBufprice() {
		return bufprice;
	}
	public void setBufprice(float bufprice) {
		this.bufprice = bufprice;
	}
	public String getDatestart() {
		return datestart;
	}
	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
