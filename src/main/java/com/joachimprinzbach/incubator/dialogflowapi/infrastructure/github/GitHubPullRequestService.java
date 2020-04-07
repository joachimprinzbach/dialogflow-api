package com.joachimprinzbach.incubator.dialogflowapi.infrastructure.github;

import com.joachimprinzbach.incubator.dialogflowapi.domain.PullRequest;
import com.joachimprinzbach.incubator.dialogflowapi.domain.PullRequestComment;
import com.joachimprinzbach.incubator.dialogflowapi.domain.PullRequestService;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class GitHubPullRequestService implements PullRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubPullRequestService.class);

    private final GitHub gitHub;

    @Override
    public URL createPullRequestComment(PullRequestComment pullRequestComment) {
        LOGGER.info("Commenting Pull Request {}", pullRequestComment);
        PullRequest pullRequest = pullRequestComment.getPullRequest();
        try {
            GHIssueComment comment = gitHub.getRepository(pullRequest.getFullName()).getPullRequest(pullRequest.getId()).comment(pullRequestComment.getCommentText());
            LOGGER.info("Successfully commented on Pull Request: " + comment);
            return comment.getHtmlUrl();
        } catch (IOException e) {
            LOGGER.error("Error creating Pull Request comment: {}, Error: {}", pullRequestComment, e);
            throw new RuntimeException("Error creating Pull Request comment", e);
        }
    }

}
