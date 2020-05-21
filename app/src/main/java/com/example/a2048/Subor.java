package com.example.a2048;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Subor {
    private Data dt;

    private String File2x2 = "game2x2.txt";
    private String File3x3 = "game3x3.txt";
    private String File4x4 = "game4x4.txt";
    private String Default = "def.txt";
    private String High = "hig.txt";
    private String Jazyk = "jaz.txt";

    public Subor(Data tmp){
        this.dt = tmp;
    }

    void nacitajJazyk(){//Naprogramuj
         }
    void ulozJazyk(){//Naprogramuj
         }

    void nacitajDefaultne(){
        try{
        int zvolene = 0;
        zvolene = this.nacitajDefault();
        this.nacitajPreCislo(zvolene);
        this.nacitajHigh();
        }catch(Exception e){
            this.nacitajPreCislo(0);
            this.nacitajHigh();
        }
    }

    void nacitajPreCislo(int tmp) {
        String tmpS;
        switch (tmp) {
            case 2:
                dt.setType(tmp);
                dt.vytvorPoleHry();
                tmpS = File2x2;
                break;
            case 3:
                dt.setType(tmp);
                dt.vytvorPoleHry();
                tmpS = File3x3;
                break;
            case 4:
                dt.setType(tmp);
                dt.vytvorPoleHry();
                tmpS = File4x4;
                break;
            default:
                tmp = 2;
                dt.setType(tmp);
                dt.vytvorPoleHry();
                for (int i = 0; i < tmp; i++) {
                    for (int j = 0; j < tmp; j++) {
                        dt.setPrvokPola(0, i, j);
                    }
                }
                tmp = 0;
            break;
        }
        if(tmp != 0) {
            int[][] matTMP = new int[tmp][tmp];
            dt.setType(tmp);
            dt.vytvorPoleHry();
            nacitajMaticu(tmp);
            for (int i = 0; i < tmp; i++) {
                for (int j = 0; j < tmp; j++) {
                    dt.setPrvokPola(matTMP[i][j], i, j);
                }
            }
        }
    }

    void nacitajMaticu(int tmp){
        try{
            String tmpS;
            BufferedReader br = null;
            StringBuffer output = new StringBuffer();
            String fpath ="/sdcard/"+Default;
            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"n");
            }
            tmpS = output.toString();
            int pocitadlo =0;
            for (int i = 0; i < tmp; i++) {
                for (int j = 0; j < tmp; j++) {
                    dt.setPrvokPola(tmpS.charAt(pocitadlo)-48, i, j);
                    pocitadlo+=2;
                }
            }
            dt.setExituje(true);
        }catch (Exception e){
            dt.setExituje(false);
            for (int i = 0; i < tmp; i++) {
                for (int j = 0; j < tmp; j++) {
                    dt.setPrvokPola(0, i, j);
                }
            }
        }
    }

    void ulozMaticu(){
        try{
            int druh = dt.getType();
            String tmpS=null;
            for (int i = 0; i < druh; i++) {
                for (int j = 0; j < druh; j++) {
                    tmpS=tmpS+String.valueOf(dt.getHraProk(i,j))+"*";
                }
            }
            String Subor="";
            switch (druh){
                case 2:
                    Subor = File2x2;
                    break;
                case 3:
                    Subor = File3x3;
                    break;
                case 4:
                    Subor = File4x4;
                    break;
            }
            String fpath ="/sdcard/"+Subor;
            File file = new File(fpath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(tmpS);
            bw.close();

        }catch(Exception e){ }
    }

    void nacitajHigh(){
        try {
            String tmpS;
            BufferedReader br = null;
            StringBuffer output = new StringBuffer();
            String fpath ="/sdcard/"+High;
            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"n");
            }
            tmpS = output.toString();
            dt.setHigh2x2(tmpS.charAt(0)-48);
            dt.setHigh3x3(tmpS.charAt(2)-48);
            dt.setHigh4x4(tmpS.charAt(4)-48);
        }catch(Exception e){
            saveHigh();
            dt.setHigh2x2(0);
            dt.setHigh3x3(0);
            dt.setHigh4x4(0);
        }
    }

    void saveHigh(){
        try {
            String tmpS="";
            tmpS=String.valueOf(dt.getHigh2x2())+"*"+String.valueOf(dt.getHigh3x3())+"*"+String.valueOf(dt.getHigh4x4());
            String fpath ="/sdcard/"+High;
            File file = new File(fpath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(tmpS);
            bw.close();

        }catch(Exception e){

        }
    }

    int nacitajDefault(){
        int tmpI =0;
        String tmpS;
        try{
            BufferedReader br = null;
            StringBuffer output = new StringBuffer();
            String fpath ="/sdcard/"+Default;
            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"n");
            }
            tmpS = output.toString();
            tmpI = tmpS.charAt(0)-48;
            return tmpI;
        }catch(Exception e){
            try {
                String fpath = "/sdcard/" + Default;
                File file = new File(fpath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(String.valueOf(tmpI));
                bw.close();
                return tmpI;
            }catch (Exception b){
                return 0;
            }
        }
    }

    void saveDefault(){
        try {
            String fpath ="/sdcard/"+Default;
            File file = new File(fpath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(dt.getType()));
            bw.close();
        }catch(Exception e){
        }
    }

}
