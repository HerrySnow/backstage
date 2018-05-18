package com.baojia.model;

import java.io.Serializable;

/**
 * [STRATO MyBatis Generator]
 * Table: users
@mbggenerated do_not_delete_during_merge 2018-05-15 15:43:09
 */
public class Users implements Serializable {


    /**
     * Column: users.id
    @mbggenerated 2018-05-15 15:43:09
     */
    private Integer id;

    /**
     *   ������
     * Column: users.name
    @mbggenerated 2018-05-15 15:43:09
     */
    private String name;

    /**
     * Column: users.km
    @mbggenerated 2018-05-15 15:43:09
     */
    private String km;

    /**
     * Column: users.score
    @mbggenerated 2018-05-15 15:43:09
     */
    private Integer score;

    /**
     * Table: users
    @mbggenerated 2018-05-15 15:43:09
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km == null ? null : km.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}