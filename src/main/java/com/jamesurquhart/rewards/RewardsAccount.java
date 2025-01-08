/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jamesurquhart.rewards;

import java.util.LinkedList;
//import java.util.UUID;

/**
 *
 * @author jamesurquhart
 */
public class RewardsAccount {

    enum Status {
        BASIC, GOLD, PLATINUM
    }

    long id;
    String name;
    String joinDate;
    Status status;
    long points;
    boolean isCancelled;

    LinkedList<ActivityPair> activityList = new LinkedList<>();

    void setId(long id) {
        this.id = id;
    }

    long getId() {
        return this.id;
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    String getJoinDate() {
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

    void addActivity(String activityName, String arguments) {
        ActivityPair pair = new ActivityPair(activityName, arguments);
        this.activityList.add(pair);
    }

    ActivityPair getNextActivity() {
        return this.activityList.removeFirst();
    }
}

