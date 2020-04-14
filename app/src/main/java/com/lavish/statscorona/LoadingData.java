package com.lavish.statscorona;

public class LoadingData {
    private String worldtotal,worlddeaths,worldrecovered,indiatotal,indiadeaths,indiarecovered,indianewcases;
    public LoadingData(String worldtotal, String worlddeaths,String worldrecovered, String indiatotal, String indiadeaths, String indiarecovered,String indianewcases)
    {
        this.worldtotal=worldtotal;
        this.worlddeaths=worlddeaths;
        this.worldrecovered=worldrecovered;
        this.indiatotal=indiatotal;
        this.indiadeaths=indiadeaths;
        this.indiarecovered=indiarecovered;
        this.indianewcases=indianewcases;

    }

    public String getWorldtotal()
    {
        return worldtotal;
    }

    public String getWorldrecovered()
    {
        return worldrecovered;
    }

    public String getWorlddeaths()
    {
        return worlddeaths;
    }

    public String getIndiatotal()
    {
        return indiatotal;
    }

    public String getIndiadeaths()
    {
        return indiadeaths;
    }

    public String getIndiarecovered()
    {
        return indiarecovered;
    }

    public String getIndianewcases()
    {
        return indianewcases;
    }

}
