package com.example.a2048;

import java.io.Serializable;

public class Data implements Serializable {
    private int jazyk = 2;
    private boolean existujeHra=false;
    private int[][] Hra;
    private int hraSize=0;
    private int Score = 0;
    private int high2x2=0;
    private int high3x3=0;
    private int high4x4=0;

    void setJazyk(int tmp ){jazyk=tmp;}
    int getJazyk(){return jazyk;}

    void setExituje(boolean tmp ){existujeHra=tmp;}
    boolean getExistuje(){return existujeHra;}

    void setScore(int tmp){
        this.Score=tmp;
    }

    int getScore(){
        return this.Score;
    }

    void setHigh2x2(int tmp){
        this.high2x2=tmp;
    }

    int getHigh2x2(){
        return this.high2x2;
    }

    void setHigh3x3(int tmp){
        this.high3x3=tmp;
    }

    int getHigh3x3(){
        return this.high3x3;
    }

    void setHigh4x4(int tmp){
        this.high4x4=tmp;
    }

    int getHigh4x4(){
        return this.high4x4;
    }

    void setType(int druh){
        this.hraSize=druh;
    }

    int getType(){
        return this.hraSize;
    }


    void vytvorPoleHry(){
        int gameSize = hraSize;
        Hra = new int [gameSize][gameSize];
    }
    void setPrvokPola(int Hodnota,int x,int y){
        this.Hra[x][y]=Hodnota;
    }

    int getHraProk(int x,int y){
        if(x<hraSize && y<hraSize)
            return this.Hra[x][y];
        else
            return 0;
    }

}

