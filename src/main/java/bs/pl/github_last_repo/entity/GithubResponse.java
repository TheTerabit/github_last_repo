package bs.pl.github_last_repo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.*;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubResponse {

    private final Long totalCount;
    private final List<Repository> gitHubRepos;

    public GithubResponse(@JsonProperty("total_count") Long totalCount,
                          @JsonProperty("items") List<Repository> gitHubRepos) {
        this.totalCount = totalCount;
        this.gitHubRepos = gitHubRepos;
    }
}
