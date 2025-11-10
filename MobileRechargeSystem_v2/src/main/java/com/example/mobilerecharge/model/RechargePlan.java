package com.example.mobilerecharge.model;
import jakarta.persistence.*;
@Entity @Table(name="recharge_plans")
public class RechargePlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String operator; private String planName; private Double amount; private String validity;
    @Column(length=1000) private String description;
    public RechargePlan() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getOperator(){return operator;} public void setOperator(String o){this.operator=o;}
    public String getPlanName(){return planName;} public void setPlanName(String p){this.planName=p;}
    public Double getAmount(){return amount;} public void setAmount(Double a){this.amount=a;}
    public String getValidity(){return validity;} public void setValidity(String v){this.validity=v;}
    public String getDescription(){return description;} public void setDescription(String d){this.description=d;}
}
