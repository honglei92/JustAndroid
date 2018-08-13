package com.boco.whl.funddemo.entity;

/**
 * @author:honglei92
 * @time:2018/7/23
 */
public class MineOperationItem {
    public String getMineOperationItemName() {
        return mineOperationItemName;
    }

    public int getMineOperationItemImage() {
        return mineOperationItemImage;
    }

    private String mineOperationItemName;
    private int mineOperationItemImage;

    public void setMineOperationItemName(String mineOperationItemName) {
        this.mineOperationItemName = mineOperationItemName;
    }

    public void setMineOperationItemImage(int mineOperationItemImage) {
        this.mineOperationItemImage = mineOperationItemImage;
    }
}
