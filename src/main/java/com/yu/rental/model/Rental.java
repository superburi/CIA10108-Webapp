package com.yu.rental.model;

import com.howard.rentalmytrack.vo.RentalMyTrackVo_ORM;
import com.howard.rentalorderdetails.vo.RentalOrderDetails_ORM;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "rental")
public class Rental implements java.io.Serializable{
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private Set<RentalOrderDetails_ORM> rentalOrderDetailsOrms;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private Set<RentalMyTrackVo_ORM> rentalMyTrackVoOrms;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rNo")
    private Integer rNo;
    @Column(name = "rCatNo")
    private Integer rCatNo;
    @Column(name = "rName")
    private String rName;
    @Column(name = "rPrice")
    private BigDecimal rPrice;
    @Column(name = "rSize")
    private Integer rSize;
    @Column(name = "rColor")
    private String rColor;
    @Column(name = "rInfo")
    private String rInfo;
    @Column(name = "rStat")
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

    public Set<RentalOrderDetails_ORM> getRentalOrderDetailsOrms() {
        return rentalOrderDetailsOrms;
    }

    public void setRentalOrderDetailsOrms(Set<RentalOrderDetails_ORM> rentalOrderDetailsOrms) {
        this.rentalOrderDetailsOrms = rentalOrderDetailsOrms;
    }

    public Set<RentalMyTrackVo_ORM> getRentalMyTrackVoOrms() {
        return rentalMyTrackVoOrms;
    }

    public void setRentalMyTrackVoOrms(Set<RentalMyTrackVo_ORM> rentalMyTrackVoOrms) {
        this.rentalMyTrackVoOrms = rentalMyTrackVoOrms;
    }

}
