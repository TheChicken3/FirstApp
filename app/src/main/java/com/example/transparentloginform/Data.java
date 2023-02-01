package com.example.transparentloginform;

public class Data {
    private String Username ;
    private String nickname ;
    private String number ;

    public Data(String username, String nickname, String number) {
        Username = username;
        this.nickname = nickname;
        this.number = number;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Data{" +
                "Username='" + Username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
