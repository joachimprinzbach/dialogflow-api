package com.joachimprinzbach.incubator.dialogflowapi.infrastructure.github;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubApiConfiguration {

    @Value("${dialogflow.gittoken}")
    private String apiToken;

    @Bean
    public GitHub createGitHub() throws IOException {
        return new GitHubBuilder().withOAuthToken(apiToken).build();
    }
}
