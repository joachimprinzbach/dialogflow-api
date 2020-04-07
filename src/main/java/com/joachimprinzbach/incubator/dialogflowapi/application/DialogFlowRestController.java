package com.joachimprinzbach.incubator.dialogflowapi.application;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class DialogFlowRestController {

    private final DeployService deployService;
    private final PreviewService previewService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DialogFlowRestController.class);
    private static JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();

    @PostMapping(value = "api/webhook")
    public GoogleCloudDialogflowV2WebhookResponse perform(@RequestBody String stringRequest) throws IOException {
        GoogleCloudDialogflowV2WebhookRequest parsedRequest = jacksonFactory.createJsonParser(stringRequest).parse(GoogleCloudDialogflowV2WebhookRequest.class);
        String intent = parsedRequest.getQueryResult().getIntent().getDisplayName();
        LOGGER.info("Handling intent: " + intent);
        if ("deploy".equals(intent)) {
            return deployService.deploy(parsedRequest);
        } else if ("preview".equals(intent)) {
            return previewService.createPreview(parsedRequest);
        }
        throw new RuntimeException("Don't know what you want! " + intent);
    }
}
