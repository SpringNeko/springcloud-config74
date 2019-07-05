package com.jk.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class News implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String author;
    private String releaseDate;//发布时间
    private String context;//内容
    private String keywords;//关键字;
    private Integer type;//类别
}
