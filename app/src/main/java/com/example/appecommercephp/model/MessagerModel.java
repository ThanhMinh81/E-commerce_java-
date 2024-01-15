package com.example.appecommercephp.model;

import com.google.gson.annotations.SerializedName;

public class MessagerModel {

    @SerializedName("id_messager")
    private String idMess ;
    @SerializedName("content_mess")
    private String contentMess;

    @SerializedName("time_mess")

    private String timeMess ;

    @SerializedName("id_user")

    private int idUser ;

    @SerializedName("id_group")

    private int idGroup ;

    public MessagerModel() {
    }

    public MessagerModel(String idMess, String contentMess, String timeMess, int idUser, int idGroup) {
        this.idMess = idMess;
        this.contentMess = contentMess;
        this.timeMess = timeMess;
        this.idUser = idUser;
        this.idGroup = idGroup;
    }

    public String getIdMess() {
        return idMess;
    }

    public void setIdMess(String idMess) {
        this.idMess = idMess;
    }

    public String getContentMess() {
        return contentMess;
    }

    public void setContentMess(String contentMess) {
        this.contentMess = contentMess;
    }

    public String getTimeMess() {
        return timeMess;
    }

    public void setTimeMess(String timeMess) {
        this.timeMess = timeMess;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}
