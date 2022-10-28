package edu.eci.arep.entity;

public class Post {
    private String text;
    private String userName;
    private String date;

    public Post(String text, String userId, String date) {
        this.text = text;
        this.userName = userId;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                ", text='" + text + '\'' +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
