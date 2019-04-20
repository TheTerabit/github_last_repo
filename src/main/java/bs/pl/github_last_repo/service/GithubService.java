package bs.pl.github_last_repo.service;

import bs.pl.github_last_repo.model.GithubResponse;
import bs.pl.github_last_repo.model.GithubRepository;
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
                .max(Comparator.comparing(GithubRepository::getPushedAt))
                .map(GithubRepository::getName)
                .orElse(null);
    }

    private List<GithubRepository> findAll() {
        final List<GithubRepository> result = new ArrayList<>();
        final GithubResponse response = githubClient.getData();
        if (response != null) {
            result.addAll(response.getGithubRepos());
            if (response.getTotalCount() == result.size()) {
                return result;
            }
            if (response.getGithubRepos().size() == 0) {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

}
