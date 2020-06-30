package com.wrpxcx.POJO;

public class GoodsKind {

    private int kindId;
    private String kindName;

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    @Override
    public String toString() {
        return "GoodsKind{" +
                "kindId=" + kindId +
                ", kindName='" + kindName + '\'' +
                '}';
    }
}
