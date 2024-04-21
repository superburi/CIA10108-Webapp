package com.howard.rentalmytrack.vo;

import com.roger.member.vo.MemberVO;
import com.yu.rental.model.Rental;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

@Entity
@Table(name = "rentalmytrack")
@IdClass(RentalMyTrackVo_ORM.CompositeTrack.class)
public class RentalMyTrackVo_ORM implements Serializable {

	/*
	 * rNo -> 租借品編號
	 * memNo -> 會員編號
	 * rTrackTime -> 加入追蹤時間
	 * expRentalDate -> 期望租借時間
	 */
//    @Id
//    @Column(name = "rNo", updatable = false)
//    private Integer rNo;
    @Id
    @ManyToOne
    @JoinColumn(name = "rNo", referencedColumnName = "rNo")
    private Rental rental;

//    @Id
//    @Column(name = "memNo", updatable = false)
//    private Integer memNo;
    @Id
    @ManyToOne()
    @JoinColumn(name = "memNo", referencedColumnName = "memNo")
    private  MemberVO memberVO;

	@Column(name = "rTrackTime")
    private Timestamp rTrackTime;
	@Column(name = "expRentalDate")
    private Date expRentalDate;

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public MemberVO getMemberVO() {
        return memberVO;
    }

    public void setMemberVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    public Timestamp getrTrackTime() {
        return rTrackTime;
    }

    public void setrTrackTime(Timestamp rTrackTime) {
        this.rTrackTime = rTrackTime;
    }

    public Date getExpRentalDate() {
        return expRentalDate;
    }

    public void setExpRentalDate(Date expRentalDate) {
        this.expRentalDate = expRentalDate;
    }

    public void setCompositeTrack(CompositeTrack key) {
        key.setRental(this.rental);
        key.setMemberVO(this.memberVO);
    }

    public CompositeTrack getCompositeTrack() {
        return new CompositeTrack(this.rental, this.memberVO);
    }



    static class CompositeTrack implements Serializable {

        private Rental rental;
        private MemberVO memberVO;

        public CompositeTrack() {

        }

        public CompositeTrack(Rental rental, MemberVO memberVO) {
            this.rental = rental;
            this.memberVO = memberVO;
        }

        public Rental getRental() {
            return rental;
        }

        public void setRental(Rental rental) {
            this.rental = rental;
        }

        public MemberVO getMemberVO() {
            return memberVO;
        }

        public void setMemberVO(MemberVO memberVO) {
            this.memberVO = memberVO;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeTrack that)) return false;
            return Objects.equals(getRental(), that.getRental()) && Objects.equals(getMemberVO(), that.getMemberVO());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRental(), getMemberVO());
        }

    }

    @Override
    public String toString() {
        return "RentalMyTrackVo_ORM{" +
                "rental=" + rental +
                ", memberVO=" + memberVO +
                ", rTrackTime=" + rTrackTime +
                ", expRentalDate=" + expRentalDate +
                '}';
    }

    public RentalMyTrackVo_ORM() {

    }

}
