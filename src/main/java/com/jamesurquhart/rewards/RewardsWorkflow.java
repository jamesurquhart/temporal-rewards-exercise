package com.jamesurquhart.rewards;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.QueryMethod;
 
/**
 *
 * @author jamesurquhart
 */
@WorkflowInterface
public interface RewardsWorkflow {
    /*
    Workflow Interface for rewards exercise
    */
    
    @WorkflowMethod
    public void createRewardsProgram(RewardsAccount account);
    
    @SignalMethod
    public void addPoints(long earnedPoints);
    
    @SignalMethod
    public void cancelRewardsProgram();
    
    @QueryMethod
    public RewardsAccount getRewardsProgramData();
    
}
