package pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="record")
public class Record {

	private int accountId;
	private String accountType;
	private String isIncome;
	private Date createDate;
	private BigDecimal price;
	
	public Record(int accountId, String accountType, String isIncome, Date createDate, BigDecimal price) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.isIncome = isIncome;
		this.createDate = createDate;
		this.price = price;
	}
	
	public Record() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getIsIncome() {
		return isIncome;
	}
	public void setIsIncome(String isIncome) {
		this.isIncome = isIncome;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "id is"+this.accountId+","+this.price+",date:"+this.getCreateDate();
	}
	
	
	
	
	
}
