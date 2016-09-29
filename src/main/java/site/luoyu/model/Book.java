package site.luoyu.model;

import java.sql.Date;

/**
 * Created by xd on 2016/9/26.
 */
public class Book {
    private int bookId;
    private Integer stuId;
    private String isbn;
    private String pictures;
    private Integer level;
    //todo 推荐等级，这个可以让服务器在每晚12点求得最新值
    private Integer recommendStar;
    private Date publishDate;
    private Integer bookTypeId;
    private String typeCodeClass;
    private Float price;
    private String name;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRecommendStar() {
        return recommendStar;
    }

    public void setRecommendStar(Integer recommendStar) {
        this.recommendStar = recommendStar;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getTypeCodeClass() {
        return typeCodeClass;
    }

    public void setTypeCodeClass(String typeCodeClass) {
        this.typeCodeClass = typeCodeClass;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
}
