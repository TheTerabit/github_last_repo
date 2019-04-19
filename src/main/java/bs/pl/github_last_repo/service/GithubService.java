package bs.pl.github_last_repo.service;

import bs.pl.github_last_repo.entity.GithubResponse;
import bs.pl.github_last_repo.entity.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GithubService {

    private final GithubClient githubClient;

    @Autowired
    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public String findLastUpdated() {
        return this.findAll()
                .stream()
                .max(Comparator.comparing(Repository::getPushedAt))
                .map(Repository::getName)
                .orElse(null);
    }

    private List<Repository> findAll() {
        final List<Repository> result = new ArrayList<>();
        final GithubResponse response = githubClient.getRepo();
        if (response != null) {
            result.addAll(response.getGitHubRepos());
            if (response.getTotalCount() == result.size()) {
                return result;
            }
            if (response.getGitHubRepos().size() == 0) {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

}
