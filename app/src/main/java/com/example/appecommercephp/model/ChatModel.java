package com.example.appecommercephp.model;

import java.util.Comparator;

public class ChatModel implements Comparator<ChatModel> {

    private String idUser ;
    private String content ;
    private String time ;

    public ChatModel() {
    }

    public ChatModel(String idUser, String content, String time) {
        this.idUser = idUser;
        this.content = content;
        this.time = time;
    }


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public int compare(ChatModel o1, ChatModel o2) {
        return o1.getTime().compareTo(o2.getTime());
    }


    @Override
    public String toString() {
        return "ChatModel{" +
                "idUser='" + idUser + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
