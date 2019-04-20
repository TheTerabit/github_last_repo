package bs.pl.github_last_repo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.*;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubResponse {

    private final Long totalCount;
    private final List<GithubRepository> githubRepos;

    public GithubResponse(@JsonProperty("total_count") Long totalCount,
                          @JsonProperty("items") List<GithubRepository> githubRepos) {
        this.totalCount = totalCount;
        this.githubRepos = githubRepos;
    }
}
