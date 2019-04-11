package bs.pl.github_last_repo.service;

import bs.pl.github_last_repo.dao.GithubDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GithubService {

    @Autowired
    private GithubDao githubDao;

    public String getRepo() throws IOException, JSONException {
        return this.githubDao.getRepo();
    }
}
