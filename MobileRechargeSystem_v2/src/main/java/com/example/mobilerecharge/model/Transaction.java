package com.example.mobilerecharge.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="transactions")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long userId; private Long planId; private String operator; private String mobileNumber;
    private Double amount; private String paymentMethod; private LocalDateTime dateTime = LocalDateTime.now();
    public Transaction() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getUserId(){return userId;} public void setUserId(Long u){this.userId=u;}
    public Long getPlanId(){return planId;} public void setPlanId(Long p){this.planId=p;}
    public String getOperator(){return operator;} public void setOperator(String o){this.operator=o;}
    public String getMobileNumber(){return mobileNumber;} public void setMobileNumber(String m){this.mobileNumber=m;}
    public Double getAmount(){return amount;} public void setAmount(Double a){this.amount=a;}
    public String getPaymentMethod(){return paymentMethod;} public void setPaymentMethod(String pm){this.paymentMethod=pm;}
    public LocalDateTime getDateTime(){return dateTime;} public void setDateTime(LocalDateTime dt){this.dateTime=dt;}
}
