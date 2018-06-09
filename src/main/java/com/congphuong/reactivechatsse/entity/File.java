package com.congphuong.reactivechatsse.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "files")
public class File {
    @Id
    private String id;

    @NotNull
    private String url;

    @NotNull
    private Date createAt = new Date();

    public File() {
    }

    public File(String id, @NotNull String url, @NotNull Date createAt) {
        this.id = id;
        this.url = url;
        this.createAt = createAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
