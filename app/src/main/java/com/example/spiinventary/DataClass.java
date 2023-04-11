package com.example.spiinventary;

public class DataClass {

    private String dataHpsId;
    private String dataHpsNombre;
    private String dataHpsCav;
    private String dataHpsMaterial;
    private String dataHpsImg;

    public String getDataHpsId() {
        return dataHpsId;
    }

    public String getDataHpsNombre() {
        return dataHpsNombre;
    }

    public String getDataHpsCav() {
        return dataHpsCav;
    }

    public String getDataHpsMaterial() {
        return dataHpsMaterial;
    }

    public String getDataHpsImg() {
        return dataHpsImg;
    }

    public DataClass(String dataHpsId, String dataHpsNombre, String dataHpsCav, String dataHpsMaterial, String dataHpsImg) {
        this.dataHpsId = dataHpsId;
        this.dataHpsNombre = dataHpsNombre;
        this.dataHpsCav = dataHpsCav;
        this.dataHpsMaterial = dataHpsMaterial;
        this.dataHpsImg = dataHpsImg;
    }
}
