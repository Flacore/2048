package com.example.a2048;

public class gameLogic {

    public gameLogic(Data tmp){
        dt = tmp;
    }

    private Data dt;
    private static int paScore = 0;

    //Test na koniec hry
    public boolean testKoniecHry(int paMatica [][]){
        for (int i = 0; i < dt.getType(); i++) {
            for (int j = 0; j < dt.getType(); j++) {
                if(dt.getHraProk(i,j)==0){
                    return false;
                }
            }
        }
        return true;
    }

    //Skontrolavanie ci moze vykonat pohyb v danom smere
    public boolean swictcher(int paVolba){
        boolean paMoze=false;
        switch(paVolba){
            case 8:
                paMoze=smerHoreTest();
                break;
            case 2:
                paMoze=smerDoleTest();
                break;
            case 6:
                paMoze=smerPravaTest();
                break;
            case 4:
                paMoze=smerLavaTest();
                break;
        }
        return paMoze;
    }


    private boolean smerHoreTest(){
        boolean moznPohyb = false;
        for (int i = 0; i < dt.getType()-1; i++) {
            for (int j = 0; j < dt.getType(); j++) {
                if((dt.getHraProk(i,j) == dt.getHraProk(i+1,j) && dt.getHraProk(i,j)!=0)||(dt.getHraProk(i,j) == 0 && dt.getHraProk(i+1,j)>0)){
                    moznPohyb = true;
                }
            }
        }
        return moznPohyb;
    }

    private boolean smerDoleTest(){
        boolean moznPohyb = false;
        for (int i = dt.getType()-1; i > 0; i--) {
            for (int j = 0; j < dt.getType(); j++) {
                if((dt.getHraProk(i,j) == dt.getHraProk(i-1,j) && dt.getHraProk(i,j) != 0) || (dt.getHraProk(i,j) == 0 && dt.getHraProk(i-1,j)>0)){
                    moznPohyb=true;
                }
            }
        }
        return moznPohyb;
    }

    private boolean smerPravaTest(){
        boolean moznPohyb = false;
        for (int i = 0; i < dt.getType(); i++) {
            for (int j = dt.getType()-1; j >0; j--) {
                if((dt.getHraProk(i,j) == dt.getHraProk(i,j-1) && dt.getHraProk(i,j)!=0) || (dt.getHraProk(i,j) ==0  && dt.getHraProk(i,j-1)>0)){
                    moznPohyb = true;
                }
            }
        }
        return moznPohyb;
    }

    private boolean smerLavaTest(){
        boolean moznPohyb = false;
        for (int i = 0; i < dt.getType(); i++) {
            for (int j = 0; j < dt.getType()-1; j++) {
                if((dt.getHraProk(i,j) == dt.getHraProk(i,j+1) && dt.getHraProk(i,j)!= 0) ||( dt.getHraProk(i,j) == 0 && dt.getHraProk(i,j+1)>0)){
                    moznPohyb=true;
                }
            }
        }
        return moznPohyb;
    }

    //Uprava smery
    public void doSmer(int paVolba){
        switch(paVolba){
            case 8:
                smerHore();
                break;
            case 2:
                smerDole();
                break;
            case 6:
                smerPrava();
                break;
            case 4:
                smerLava();
                break;
        }
    }

    private void smerHore(){
        for(int opakuj = 0;opakuj<dt.getType()-1;opakuj++){
            for (int i = 0; i < dt.getType()-1; i++) {
                for (int j = 0; j < dt.getType(); j++) {
                    if(dt.getHraProk(i,j) == dt.getHraProk(i+1,j) || dt.getHraProk(i,j) == 0){
                        if(dt.getHraProk(i,j) == dt.getHraProk(i+1,j)){
                            upravScore(dt.getHraProk(i,j) , dt.getHraProk(i+1,j));
                        }
                        dt.setPrvokPola(dt.getHraProk(i,j)+ dt.getHraProk(i+1,j),i,j);
                        dt.setPrvokPola(0,i+1,j);
                    }
                }
            }
        }
    }

    private void smerDole(){
        for(int opakuj = 0;opakuj<dt.getType()-1;opakuj++){
            for (int i = dt.getType()-1; i > 0; i--) {
                for (int j = 0; j < dt.getType(); j++) {
                    if(dt.getHraProk(i,j) == dt.getHraProk(i-1,j) || dt.getHraProk(i,j) == 0){
                        if(dt.getHraProk(i,j) == dt.getHraProk(i-1,j)){
                            upravScore(dt.getHraProk(i,j) , dt.getHraProk(i-1,j));
                        }
                        dt.setPrvokPola(dt.getHraProk(i,j)+ dt.getHraProk(i-1,j),i,j);
                        dt.setPrvokPola(0,i-1,j);
                    }
                }
            }
        }
    }

    private void smerPrava(){
        for(int opakuj = 0;opakuj<dt.getType()-1;opakuj++){
            for (int i = 0; i < dt.getType(); i++) {
                for (int j = dt.getType()-1; j >0; j--) {
                    if(dt.getHraProk(i,j) == dt.getHraProk(i,j-1) || dt.getHraProk(i,j) == 0){
                        if(dt.getHraProk(i,j) == dt.getHraProk(i,j-1)){
                            upravScore(dt.getHraProk(i,j), dt.getHraProk(i,j-1));
                        }
                        dt.setPrvokPola(dt.getHraProk(i,j)+ dt.getHraProk(i,j-1),i,j);
                        dt.setPrvokPola(0,i,j-1);
                    }
                }
            }
        }
    }

    private void smerLava(){
        for(int opakuj = 0;opakuj<dt.getType()-1;opakuj++){
            for (int i = 0; i < dt.getType(); i++) {
                for (int j = 0; j < dt.getType()-1; j++) {
                    if(dt.getHraProk(i,j) == dt.getHraProk(i,j+1) || dt.getHraProk(i,j) == 0){
                        if(dt.getHraProk(i,j) == dt.getHraProk(i,j+1)){
                            upravScore(dt.getHraProk(i,j) , dt.getHraProk(i,j+1));
                        }
                        dt.setPrvokPola(dt.getHraProk(i,j)+ dt.getHraProk(i,j+1),i,j);
                        dt.setPrvokPola(0,i,j+1);
                    }
                }
            }
        }
    }

    //Ziskanie skore
    private void upravScore(int paA, int paB){
        if(paA != 0 && paB != 0)
            upravScore(paA+paB);
    }

    public void upravScore(int paZvacsiO){
        int tmp = dt.getScore();
        tmp+=paZvacsiO;
        dt.setScore(tmp);
    }
}
