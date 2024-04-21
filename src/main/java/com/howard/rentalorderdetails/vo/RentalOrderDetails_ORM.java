package com.howard.rentalorderdetails.vo;

import com.howard.rentalorder.vo.RentalOrderVo_ORM;
import com.yu.rental.model.Rental;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "rentalorderdetails")
@IdClass(RentalOrderDetails_ORM.CompositeDetail.class)
public class RentalOrderDetails_ORM implements Serializable{

    public RentalOrderDetails_ORM() {

    }

    /*
     * rOrdNo -> 租借品訂單編號
     * rNo -> 租借品編號
     * rPrice -> 單價
     * rDesPrice -> 押金(單件)
     */

//    @Id
//    @Column(name = "rOrdNo")
//    private Integer rOrdNo;
    @Id
    @ManyToOne
    @JoinColumn(name = "rOrdNo", referencedColumnName = "rOrdNo")
    private RentalOrderVo_ORM rentalOrderVoOrm;

//    @Id
//    @Column(name = "rNo")
//    private Integer rNo;
    @Id
    @ManyToOne
    @JoinColumn(name = "rNo", referencedColumnName = "rNo")
    private Rental rental;

    @Column(name = "rPrice")
    private BigDecimal rPrice;
    @Column(name = "rDesPrice")
    private BigDecimal rDesPrice;

/*-------------------------------聯合映射用的的getter、setter--------------------------------------*/

    public RentalOrderVo_ORM getRentalOrderVoOrm() {
        return rentalOrderVoOrm;
    }

    public void setRentalOrderVoOrm(RentalOrderVo_ORM rentalOrderVoOrm) {
        this.rentalOrderVoOrm = rentalOrderVoOrm;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
/*-------------------------------getter、setter--------------------------------------*/
    public BigDecimal getrPrice() {
        return rPrice;
    }

    public void setrPrice(BigDecimal rPrice) {
        this.rPrice = rPrice;
    }

    public BigDecimal getrDesPrice() {
        return rDesPrice;
    }

    public void setrDesPrice(BigDecimal rDesPrice) {
        this.rDesPrice = rDesPrice;
    }

/*-------------------------------內部類別的 getter、setter--------------------------------------*/

    public CompositeDetail getCompositeDetail() {
        return new CompositeDetail(rentalOrderVoOrm, rental);
    }

    public void setCompositeDetail(CompositeDetail key) {
        key.setRentalOrderVoOrm(this.rentalOrderVoOrm);
        key.setRental(this.rental);
    }

/*-------------------------------因為複合主鍵所以加上的內部類別--------------------------------------*/

    static class CompositeDetail implements Serializable {

        private RentalOrderVo_ORM rentalOrderVoOrm;
        private Rental rental;

        public CompositeDetail() {

        }

        public CompositeDetail(RentalOrderVo_ORM rentalOrderVoOrm, Rental rental) {
            this.rentalOrderVoOrm = rentalOrderVoOrm;
            this.rental = rental;
        }

        public RentalOrderVo_ORM getRentalOrderVoOrm() {
            return rentalOrderVoOrm;
        }

        public void setRentalOrderVoOrm(RentalOrderVo_ORM rentalOrderVoOrm) {
            this.rentalOrderVoOrm = rentalOrderVoOrm;
        }

        public Rental getRental() {
            return rental;
        }

        public void setRental(Rental rental) {
            this.rental = rental;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeDetail that)) return false;
            return Objects.equals(getRentalOrderVoOrm(), that.getRentalOrderVoOrm()) && Objects.equals(getRental(), that.getRental());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRentalOrderVoOrm(), getRental());
        }

    } // 內部類別結束

    @Override
    public String toString() {
        return "RentalOrderDetails_ORM{" +
                "rentalOrderVoOrm=" + rentalOrderVoOrm +
                ", rental=" + rental +
                ", rPrice=" + rPrice +
                ", rDesPrice=" + rDesPrice +
                '}';
    }



}


