package com.roger.columnarticle.vo;

import com.ren.administrator.model.AdministratorVO;
import com.roger.articlecollection.vo.ArticleCollectionVO;
import com.roger.clicklike.vo.ClickLikeVO;
import com.roger.columnreply.vo.ColumnReplyVO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "columnarticle")
public class ColumnArticleVO implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artNo")
    private Integer artNo;

    @ManyToOne
    @JoinColumn(name = "admNo", referencedColumnName = "admNo")
    private AdministratorVO administratorVO;

//    @Column(name = "admNo")
//    private Integer admNo;

    @Column(name = "artTitle")
    private String artTitle;

    @Column(name = "artContent")
    private String artContent;

    @Column(name = "artTime")
    private Timestamp artTime;

    @Column(name = "artCatNo")
    private Integer artCatNo;

    @Column(name = "artStat")
    private Byte artStat;

    @OneToMany(mappedBy = "columnArticleVO", cascade = CascadeType.ALL)
    private Set<ClickLikeVO> clickLikes;

    @OneToMany(mappedBy = "columnArticleVO", cascade = CascadeType.ALL)
    private Set<ArticleCollectionVO> articleCollections;

    @OneToMany(mappedBy = "columnArticleVO", cascade = CascadeType.ALL)
    private Set<ColumnReplyVO> columnReplys;


    public Integer getArtNo() {
        return artNo;
    }

    public void setArtNo(Integer artNo) {
        this.artNo = artNo;
    }

    public AdministratorVO getAdministratorVO() {
        return administratorVO;
    }

    public void setAdministratorVO(AdministratorVO administratorVO) {
        this.administratorVO = administratorVO;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getArtContent() {
        return artContent;
    }

    public void setArtContent(String artContent) {
        this.artContent = artContent;
    }

    public Timestamp getArtTime() {
        return artTime;
    }

    public void setArtTime(Timestamp artTime) {
        this.artTime = artTime;
    }

    public Integer getArtCatNo() {
        return artCatNo;
    }

    public void setArtCatNo(Integer artCatNo) {
        this.artCatNo = artCatNo;
    }

    public Byte getArtStat() {
        return artStat;
    }

    public void setArtStat(Byte artStat) {
        this.artStat = artStat;
    }

    public Set<ClickLikeVO> getClickLikes() {
        return clickLikes;
    }

    public void setClickLikes(Set<ClickLikeVO> clickLikes) {
        this.clickLikes = clickLikes;
    }

    public Set<ArticleCollectionVO> getArticleCollections() {
        return articleCollections;
    }

    public void setArticleCollections(Set<ArticleCollectionVO> articleCollections) {
        this.articleCollections = articleCollections;
    }

    public Set<ColumnReplyVO> getColumnReplys() {
        return columnReplys;
    }

    public void setColumnReplys(Set<ColumnReplyVO> columnReplys) {
        this.columnReplys = columnReplys;
    }
}
