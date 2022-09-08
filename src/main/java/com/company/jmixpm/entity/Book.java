package com.company.jmixpm.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@JmixEntity
@Table(name = "BOOK")
@Entity
@PrimaryKeyJoinColumn(name = "BOOK_ID", referencedColumnName = "ID")
public class Book extends Publication {
    @Column(name = "PAGES")
    private Integer pages;

    @Column(name = "PICTURE_COUNT")
    private Integer pictureCount;

    public void setPictureCount(Integer pictureCount) {
        this.pictureCount = pictureCount;
    }

    public Integer getPictureCount() {
        return pictureCount;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}