package com.boco.whl.funddemo.entity;


import com.boco.whl.funddemo.data.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;


/**
 * Created by xxx on 2017/6/22.
 */

@Table(database = AppDatabase.class)
public class ChatErrorBean {

    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private int fid;
    @Column
    private int jid;
    @Column
    private long time;
    @Column
    private int status;
    @Column
    private long chatId;
    @Column
    private long serverTime;
    @Column
    private int cause;
    @Column
    private String badTimes;

    public ChatErrorBean() {
    }

    public ChatErrorBean(int fid, int jid, long time, int status, long chatId) {
        this.fid = fid;
        this.jid = jid;
        this.time = time;
        this.status = status;
        this.chatId = chatId;
    }

    public ChatErrorBean(int fid, int jid, long time, int status, long chatId, long serverTime, int cause, String badTimes) {
        this.fid = fid;
        this.jid = jid;
        this.time = time;
        this.status = status;
        this.chatId = chatId;
        this.serverTime = serverTime;
        this.cause = cause;
        this.badTimes = badTimes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public int getCause() {
        return cause;
    }

    public void setCause(int cause) {
        this.cause = cause;
    }

    public String getBadTimes() {
        return badTimes;
    }

    public void setBadTimes(String badTimes) {
        this.badTimes = badTimes;
    }

    @Override
    public String toString() {
        return "ChatErrorBean{" +
                "id=" + id +
                ", fid=" + fid +
                ", jid=" + jid +
                ", time=" + time +
                ", status=" + status +
                ", chatId=" + chatId +
                ", serverTime=" + serverTime +
                ", cause=" + cause +
                ", badTimes='" + badTimes + '\'' +
                '}';
    }
}
