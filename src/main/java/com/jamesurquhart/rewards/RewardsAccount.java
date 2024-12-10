/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jamesurquhart.rewards;

import java.util.UUID;

/**
 *
 * @author jamesurquhart
 */
public class RewardsAccount {
    enum Status {BASIC,GOLD,PLATINUM}
    
    long ID;
    String name;
    String joinDate;
    Status status;
    long points;
    boolean isCancelled;
    
    void setID(long ID) {
        this.ID = ID;
    }
    
    long getID() {
        return this.ID;
    }
    
    void setName (String name) {
        this.name = name;
    }
    
    String getName() {
        return this.name;
    }
    
    void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
    
    String getJoinDate () {
        return this.joinDate;
    }
    
    void setStatus(Status status) {
        this.status = status;
    }
    
    Status getStatus() {
        return this.status;
    }
    
    void setPoints(long points) {
        this.points = points;
    }
    
    long getPoints() {
        return this.points;
    }
    
    long addPoints(long earnedPoints) {
        this.setPoints(this.getPoints() + earnedPoints);
        return this.getPoints();
    }
    
    long removePoints(long lostPoints) {
        this.setPoints(this.getPoints() - lostPoints);
        return this.getPoints();
    }
}
