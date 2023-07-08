package com.example.shopapplication;

import java.util.Random;

public final class Captcha {
    private long expiredTimeNano;
    private int characters;
    private String code;
    private long startTime;

    public Captcha(long expiredTimeMilli, int characters) { // CODE must be created
        setExpiredTimeNano(expiredTimeMilli);
        setCharacters(characters);
        newCode();
    }

    public void setExpiredTimeNano(long expiredTimeMilli) {
        if (expiredTimeNano < 0) {
            throw new ArithmeticException("expired time cannot be negative!");
        }
        this.expiredTimeNano = expiredTimeMilli * 1000;
    }

    public void setCharacters(int characters) {
        if (characters < 1) {
            throw new ArithmeticException("captcha characters cannot be lower than 1!");
        }
        this.characters = characters;
    }

    public int getCharacters() {
        return characters;
    }

    public long getExpiredTimeNano() {
        return expiredTimeNano;
    }

    public String getCode() {
        return code;
    }

    public void newCode() { // create and set CODE ,& set startTime
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        int holder;
        char ch;

        for (int i = 0; i < characters; i++) { // random characters
            holder = random.nextInt(1000);
            ch = 0;
            switch (holder % 2) {
                case 0 -> // number 0-9
                        ch = (char) ('0' + (holder % 10));
                case 1 -> // character A-Z
                        ch = (char) ('A' + (holder % 26));
            }
            code.append(ch);
        }

        this.code = code.toString();
        startTime = System.nanoTime();
    }

    public boolean isExpired() {
        return (System.nanoTime() - startTime) > expiredTimeNano;
    }
}
