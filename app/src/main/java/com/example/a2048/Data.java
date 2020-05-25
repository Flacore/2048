package com.example.a2048;

import java.io.Serializable;

/**
 *Trieda uchováva všetky trvalé dáta zabezpečujúce beh aplikácie.
 * @Autor Michal Molitoris
 */
public class Data implements Serializable {
    private int jazyk = 2;
    private boolean pouzitieZolika = false;
    private boolean existujeHra=false;
    private int[][] Hra;
    private int hraSize=2;
    private int Score = 0;
    private int high2x2=0;
    private int high3x3=0;
    private int high4x4=0;

    /**
     * Nastavenie parametra jazyk z množiny [1..2].
     * @param tmp Prvok ktorý predstavuje nami zvolený druh jazyka.
     */
    void setJazyk(int tmp ){jazyk=tmp;}

    /**
     * Funkcia získa nastavený jazyk.
     * @return Návratová hodnota nami zvoleného jazyka.
     */
    int getJazyk(){return jazyk;}

    /**
     * Funkcia nastavuje parameter existuje ktorý predstavuje existenciu hry ktorú môzme načítať.
     * @param tmp Boolean hodnota.
     */
    void setExituje(boolean tmp ){existujeHra=tmp;}

    /**
     * Vracia hodnotu existuje hra, a tým pádom nam vraví či je možné načítať hru alebo nie
     * @return Boolean hodnota.
     */
    boolean getExistuje(){return existujeHra;}

    /**
     * Nastavuje hodnotu akltuálneho nahraného skóre
     * @param tmp Nahrané skóre.
     */
    void setScore(int tmp){
        this.Score=tmp;
    }

    /**
     * Funkcia vracia doteraz nahrané skóre,
     * @return Návratova hodnota int predstavujúca skóre.
     */
    int getScore(){
        return this.Score;
    }

    /**
     * Funkcia nastavuje najväčšie skóre pre danú hru.
     * @param tmp Hodnota rekordu pre danú hru.
     */
    void setHigh2x2(int tmp){
        this.high2x2=tmp;
    }

    /**
     *
     * @return Int predstavuje doteraz najväčšie nahrané skóre.
     */
    int getHigh2x2(){
        return this.high2x2;
    }

    /**
     * Funkcia nastavuje najväčšie skóre pre danú hru.
     * @param tmp Hodnota rekordu pre danú hru.
     */
    void setHigh3x3(int tmp){
        this.high3x3=tmp;
    }

    /**
     * Získavame pomocou nej rekordnú hodnotu ktorú sme do dannej chvíle nahrali.
     * @return Int predstavuje doteraz najväčšie nahrané skóre.
     */
    int getHigh3x3(){
        return this.high3x3;
    }

    /**
     * Funkcia nastavuje najväčšie skóre pre danú hru.
     * @param tmp Hodnota rekordu pre danú hru.
     */
    void setHigh4x4(int tmp){
        this.high4x4=tmp;
    }

    /**
     * Získavame pomocou nej rekordnú hodnotu ktorú sme do dannej chvíle nahrali.
     * @return Int predstavuje doteraz najväčšie nahrané skóre.
     */
    int getHigh4x4(){
        return this.high4x4;
    }

    /**
     * Získavame pomocou nej rekordnú hodnotu ktorú sme do dannej chvíle nahrali.
     * @param druh
     */
    void setType(int druh){
        this.hraSize=druh;
    }

    /**
     * Ziskavame cez túto funkcie hodnotu velkosti poľa ktorú nastavujeme buď defaultne alebo
     * cez aktivitu Nastavenia.
     * @return Vracia velkosť hry v tvare int.
     */
    int getType(){
        return this.hraSize;
    }

    /**
     * Nastavenie hodnoty čí bol daný žolík použitý alebo nie.
     * @param tmp Nastavenie žolíka.
     */
    void setZolik(boolean tmp){ this.pouzitieZolika=tmp;}

    /**
     *Vratí hodnotu predstavujúcu či bol daný žolík použitý alebo nie.
     * @return Navratova hodnota žolíka,
     */
    boolean getZolik(){return pouzitieZolika;}

    /**
     *Nastavenie velkosti hracieho pola
     */
    void vytvorPoleHry(){
        int gameSize = hraSize;
        Hra = new int [gameSize][gameSize];
    }

    /**
     * Nastavý na daný riadok a stĺpec nami zadanú hodnotu.
     * @param Hodnota Hodnota prvku.
     * @param x Riadok.
     * @param y Stĺpec.
     */
    void setPrvokPola(int Hodnota,int x,int y){

        if(getType()>x && getType()>y)
            this.Hra[x][y]=Hodnota;
    }

    /**
     * Funkcia vracia hodnotu prvku na danej pozícii.
     * @param x Riadok.
     * @param y Stĺpec.
     * @return Vráti hodnotu prvku.
     */
    int getHraProk(int x,int y){
        if(x<hraSize && y<hraSize)
            return this.Hra[x][y];
        else
            return 0;
    }

}

