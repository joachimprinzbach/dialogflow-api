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
public class PreviewService {

    public GoogleCloudDialogflowV2WebhookResponse createPreview(GoogleCloudDialogflowV2WebhookRequest request) {
        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
        response.setFulfillmentText("Preview not implemented yet!");
        return response;
    }
}
