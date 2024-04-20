package com.howard.yu.rental.model;

import com.howard.rentalorderdetails.vo.RentalOrderDetails_ORM;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "rental")
public class Rental implements java.io.Serializable{
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private Set<RentalOrderDetails_ORM> rentalOrderDetailsOrms;
    @Id
    private Integer rNo;
    private Integer rCatNo;
    private String rName;
    private BigDecimal rPrice;
    private Integer rSize;
    private String rColor;
    private String rInfo;
    private Byte rStat;

    public Integer getrNo() {
        return rNo;
    }

    public void setrNo(Integer rNo) {
        this.rNo = rNo;
    }

    public Integer getrCatNo() {
        return rCatNo;
    }

    public void setrCatNo(Integer rCatNo) {
        this.rCatNo = rCatNo;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public BigDecimal getrPrice() {
        return rPrice;
    }

    public void setrPrice(BigDecimal rPrice) {
        this.rPrice = rPrice;
    }

    public Integer getrSize() {
        return rSize;
    }

    public void setrSize(Integer rSize) {
        this.rSize = rSize;
    }

    public String getrColor() {
        return rColor;
    }

    public void setrColor(String rColor) {
        this.rColor = rColor;
    }

    public String getrInfo() {
        return rInfo;
    }

    public void setrInfo(String rInfo) {
        this.rInfo = rInfo;
    }

    public Byte getrStat() {
        return rStat;
    }

    public void setrStat(Byte rStat) {
        this.rStat = rStat;
    }
}
