package com.example.shopapplication;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static void allCommodities() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        File e = new File("sounds\\AllCommodities.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void breakFast() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\breakFast.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void grocery() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\grocery.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void protein() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\protein.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void dairy() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\dairy.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void fruitAndVegetables() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\fruitAndVegetables.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void snacks() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\snacks.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void basket() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\basket.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void basketError() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\basketError.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void auction() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\auction.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void login() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\login.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void userInfo() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\userInfo.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void productsManaging() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\productsManaging.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
    public static void productRegistration() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File e = new File("sounds\\productRegistration.wav");
        AudioInputStream i= AudioSystem.getAudioInputStream(e);
        Clip clip =AudioSystem.getClip();
        clip.open(i);
        clip.start();
    }
}
