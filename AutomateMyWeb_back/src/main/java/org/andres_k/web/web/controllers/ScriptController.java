package org.andres_k.web.web.controllers;

import org.andres_k.web.web.models.auth.User;
import org.andres_k.web.web.models.items.jobs.Script;
import org.andres_k.web.web.models.items.jobs.ScriptRepository;
import org.andres_k.web.web.services.AuthService;
import org.andres_k.web.web.services.JobService;
import org.andres_k.web.web.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ScriptController {
    @Autowired
    private JobService<Script> jobService;
    @Autowired
    private ScriptRepository scriptRepository;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/script/all", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse getScripts(@RequestParam String token, @RequestParam Integer page, @RequestParam Integer pageSize) {
        HttpResponse response = new HttpResponse();

        try {
            Page<Script> scripts = this.scriptRepository.findAll(PageRequest.of(page, pageSize));

            response.addResult(scripts.getContent());
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/script", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse getScript(@RequestParam String token, @RequestParam Long scriptId) {
        HttpResponse response = new HttpResponse();

        try {
            Optional<Script> script = this.scriptRepository.findById(scriptId);

            if (!script.isPresent())
                throw new NullPointerException("The script {" + scriptId + "} doesn't exist");

            response.addResult(script.get());
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = {"/script/add", "/script/update"}, method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public HttpResponse updateScript(@RequestParam String token, @RequestBody Script script) {
        HttpResponse response = new HttpResponse();

        try {
            if (script.getName() == null || script.getValue() == null || script.getOwnerId() == null || script.getUserId() == null)
                throw new NullPointerException("The script's name OR description is null");
            this.scriptRepository.save(script);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error creating the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/script/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpResponse deleteScript(@RequestParam String token, @RequestBody Long id) {
        HttpResponse response = new HttpResponse();

        try {
            this.scriptRepository.deleteById(id);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/myscript", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse getMyScript(@RequestParam String token, @RequestParam Long scriptId) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.getUserByToken(token);
            Script script = this.jobService.find(scriptId, user.getId());

            response.addResult(script);
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/myscript/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse addMyScript(@RequestParam String token, @RequestBody Script script) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.getUserByToken(token);
            if (script.getName() == null || script.getValue() == null)
                throw new NullPointerException("The script's name OR description is null");
            if (script.getOwnerId() == null)
                script.setOwnerId(user.getId());
            script.setUserId(user.getId());
            this.scriptRepository.save(script);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error creating the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/myscript/update", method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateMyScript(@RequestParam String token, @RequestBody Script script) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.getUserByToken(token);

            Script target = this.jobService.find(script.getId(), user.getId());
            if (target == null)
                throw new SecurityException("The script {" + script.getId() + "} already exist, please use 'myscripts/update'}");
            if (script.getName() == null || script.getValue() == null)
                throw new NullPointerException("The script's name OR description is null");

            target.setDescription(script.getDescription());
            target.setName(script.getName());
            target.setValue(script.getValue());
            this.scriptRepository.save(target);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error creating the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/myscript/all", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse getMyScripts(@RequestParam String token) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.getUserByToken(token);
            List<Script> scripts = this.scriptRepository.getAllByUserId(user.getId());

            response.addResult(scripts);
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/myscript/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpResponse deleteMyScript(@RequestParam String token, @RequestBody Long id) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.getUserByToken(token);

            this.scriptRepository.deleteByIdAndUserId(id, user.getId());
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/script/sharedLink", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse getScriptFromLink(@RequestParam String token, @RequestParam String sharedLink) {
        HttpResponse response = new HttpResponse();

        try {
            Script script = this.jobService.getJobFromLink(sharedLink);

            response.addResult(script);
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/script/sharedLink/create", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse createLinkFromScript(@RequestParam String token, @RequestParam Long scriptId) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.getUserByToken(token);

            Script script = this.jobService.find(scriptId, user.getId());
            if (script.getOwnerId() != user.getId())
                throw new SecurityException("You do not have the ownership of this script");

            response.addResult(this.jobService.createSharedLink(script));
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return response;
    }
}