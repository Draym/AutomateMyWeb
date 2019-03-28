package org.andres_k.web.backend.controllers.user;

import org.andres_k.web.backend.config.HttpResponse;
import org.andres_k.web.backend.config.Restricted;
import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.models.items.jobs.Script;
import org.andres_k.web.backend.models.items.jobs.ScriptRepository;
import org.andres_k.web.backend.services.JobService;
import org.andres_k.web.backend.services.UserService;
import org.andres_k.web.backend.utils.tools.TJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Restricted
@Controller
public class ScriptController {
    @Autowired
    private JobService<Script> jobService;
    @Autowired
    private ScriptRepository scriptRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/script", method = RequestMethod.GET)
    @ResponseBody
    public String getMyScript(@RequestParam String token, @RequestParam Long scriptId) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.userService.getUserByToken(token);
            Script script = this.jobService.find(scriptId, user.getId());

            response.addResult(script);
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/add", method = RequestMethod.POST)
    @ResponseBody
    public String addMyScript(@RequestParam String token, @RequestBody Script script) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.userService.getUserByToken(token);

            if (script.getName() == null || script.getValue() == null)
                throw new NullPointerException("The script's name OR value is null");
            script.setOwnerId(script.getOwnerId() != null ? script.getOwnerId() : user.getId());
            script.setUserId(user.getId());
            this.scriptRepository.save(script);
            response.addResult(script);
        } catch (Exception ex) {
            response.addError("Error creating the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/update", method = RequestMethod.PUT)
    @ResponseBody
    public String updateMyScript(@RequestParam String token, @RequestBody Script script) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.userService.getUserByToken(token);

            Script target = this.jobService.find(script.getId(), user.getId());
            if (target == null)
                throw new SecurityException("The script {" + script.getId() + "} doesn't exist for this user, please use 'myscript/add'}");

            target.copy(script);
            this.scriptRepository.save(target);
            response.addResult(target);
        } catch (Exception ex) {
            response.addError("Error creating the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/all", method = RequestMethod.GET)
    @ResponseBody
    public String getMyScripts(@RequestParam String token) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.userService.getUserByToken(token);
            List<Script> scripts = this.scriptRepository.getAllByUserId(user.getId());

            response.addResult(scripts);
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMyScript(@RequestParam String token, @RequestBody Long id) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.userService.getUserByToken(token);

            this.scriptRepository.deleteByIdAndUserId(id, user.getId());
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/sharedLink", method = RequestMethod.GET)
    @ResponseBody
    public String getScriptFromLink(@RequestParam String token, @RequestParam String sharedLink) {
        HttpResponse response = new HttpResponse();

        try {
            Script script = this.jobService.getJobFromLink(sharedLink);

            response.addResult(script);
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/sharedLink/create", method = RequestMethod.GET)
    @ResponseBody
    public String createLinkFromScript(@RequestParam String token, @RequestParam Long scriptId) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.userService.getUserByToken(token);

            Script script = this.jobService.find(scriptId, user.getId());
            if (script.getOwnerId() != user.getId())
                throw new SecurityException("You do not have the ownership of this script");

            response.addResult(this.jobService.createSharedLink(script));
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return TJson.toString(response);
    }
}