package bs.pl.github_last_repo.controller;

import bs.pl.github_last_repo.service.GithubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class GithubController {

        @Autowired
        private GithubService githubService;

        @Autowired
        public GithubController(GithubService githubService) {
                this.githubService = githubService;
        }

        @RequestMapping(value="/repo",method = RequestMethod.GET)
        public String getLastUpdatedName() {
            return githubService.findLastUpdated();
        }
}
