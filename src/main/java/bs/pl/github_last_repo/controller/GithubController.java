package bs.pl.github_last_repo.controller;

import bs.pl.github_last_repo.service.GithubService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class GithubController {



        @Autowired
        private GithubService githubService;

        @RequestMapping(value="/repo",method = RequestMethod.GET)
        public String getAllStudents() throws IOException, JSONException {
            return githubService.getRepo();
        }

    /*
    @RequestMapping(value="/github",method = RequestMethod.GET)
    public String getGithub(){
        String json = ClientBuilder.newClient().target("https://api.github.com/users/allegro/repos").request().accept(MediaType.APPLICATION_JSON).get(String.class);

        return json;
    }
*/
}
