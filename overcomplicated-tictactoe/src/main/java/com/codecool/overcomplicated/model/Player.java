package com.codecool.overcomplicated.model;

import java.net.URI;

public class Player {

    private String userName;
    private String sign;
    private URI avatarURI;

    private Player(String userName, String sign, String uriString) {
        this.userName = userName;
        this.sign = sign;
        this.avatarURI = uriString != null ? URI.create(uriString) : null;
    }

    public Player() {
    }

    public static Player createPlayerOne(String uri) {
        return new Player("AnonymousOne", "O", uri);
    }

    public static Player createPlayerTwo(String uri) {
        return new Player("AnonymousTwo", "X", uri);
    }

    public static Player createAiPlayer() {
        return new Player("Computer", "X", null);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public URI getAvatarURI() {
        return avatarURI;
    }

    public void setAvatarURI(URI avatarURI) {
        this.avatarURI = avatarURI;
    }

    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

}
