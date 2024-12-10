package com.jamesurquhart.rewards;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.util.Random;

/**
 *
 * @author jamesurquhart
 */
public class SimpleRewardsProgramStart {
        
    public static void main(String[] args) throws Exception {
        
        Random random = new Random();
        
        //Create an account for the demonstration
        RewardsAccount account = new RewardsAccount();
        account.name = "James";
        account.ID = random.nextLong();
        account.joinDate = "10/01/2024";
        account.points = 0;
        account.status=RewardsAccount.Status.BASIC;
        account.isCancelled = false;

        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.REWARDS_TASK_QUEUE)
                // A WorkflowId prevents this it from having duplicate instances, remove it to duplicate.
                .setWorkflowId(String.valueOf(account.ID))
                .build();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        RewardsWorkflow workflow = client.newWorkflowStub(RewardsWorkflow.class, options);
        
        // Asynchronous execution. This process will exit after making this call.
        WorkflowExecution we = WorkflowClient.start(workflow::createRewardsProgram, account);
        System.out.printf("\nNew Rewards Program Created for %s, ID %s\n", account.name, String.valueOf(account.ID));
        System.out.printf("\nWorkflowID: %s RunID: %s", we.getWorkflowId(), we.getRunId());
        System.exit(0);
    }
}
