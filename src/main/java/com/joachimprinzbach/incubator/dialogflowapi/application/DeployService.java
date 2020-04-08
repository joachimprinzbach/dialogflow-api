package com.joachimprinzbach.incubator.dialogflowapi.application;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2Context;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.joachimprinzbach.incubator.dialogflowapi.domain.PullRequest;
import com.joachimprinzbach.incubator.dialogflowapi.domain.PullRequestComment;
import com.joachimprinzbach.incubator.dialogflowapi.infrastructure.github.GitHubPullRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeployService {

    private final GitHubPullRequestService gitHubPullRequestService;

    public GoogleCloudDialogflowV2WebhookResponse deploy(GoogleCloudDialogflowV2WebhookRequest request) {
        GoogleCloudDialogflowV2Context context = getRelevantContext(request);
        Map<String, Object> parameters = context.getParameters();
        BigDecimal prId = (BigDecimal) parameters.get("number");
        String repository = (String) parameters.get("repository");
        String organisation = (String) parameters.get("organisation");
        PullRequest pullRequest = new PullRequest(prId.intValue(), organisation, repository);

        URL pullRequestComment = gitHubPullRequestService.createPullRequestComment(new PullRequestComment("/preview", pullRequest));
        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
        response.setFulfillmentText("Successfully deployed!");
        return response;
    }

    private GoogleCloudDialogflowV2Context getRelevantContext(GoogleCloudDialogflowV2WebhookRequest request) {
        List<GoogleCloudDialogflowV2Context> outputContexts = request.getQueryResult().getOutputContexts();
        int paramSize = 0;
        GoogleCloudDialogflowV2Context context = null;
        for (GoogleCloudDialogflowV2Context outputContext : outputContexts) {
            if (outputContext.getParameters().size() > paramSize) {
                paramSize = outputContext.getParameters().size();
                context = outputContext;
            }
        }
        return context;
    }
}
