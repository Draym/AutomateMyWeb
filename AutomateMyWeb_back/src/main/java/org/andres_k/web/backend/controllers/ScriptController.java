package org.andres_k.web.backend.controllers;

import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.models.items.jobs.Script;
import org.andres_k.web.backend.models.items.jobs.ScriptRepository;
import org.andres_k.web.backend.services.AuthService;
import org.andres_k.web.backend.services.JobService;
import org.andres_k.web.backend.config.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @RequestMapping(value = "/script/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse addScript(@RequestParam String token, @RequestBody Script script) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.getUserByToken(token);

            if (script.getName() == null || script.getValue() == null || script.getUserId() == null)
                throw new NullPointerException("The script's name/value/userId is null.");

            script.setOwnerId(user.getId());
            this.scriptRepository.save(script);
            response.addResult(script);
        } catch (Exception ex) {
            response.addError("Error creating the script:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/script/update", method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateScript(@RequestParam String token, @RequestBody Script script) {
        HttpResponse response = new HttpResponse();

        try {
            if (script.getId() == null)
                throw new EntityNotFoundException("The script should contains it's original id.");
            Optional<Script> optScript = this.scriptRepository.findById(script.getId());

            if (!optScript.isPresent())
                throw new EntityNotFoundException("The script has not been found with the given id. please use script/add");
            Script newScript = optScript.get();
            newScript.copy(script);
            this.scriptRepository.save(newScript);
            response.addResult(newScript);
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
                throw new NullPointerException("The script's name OR value is null");
            script.setOwnerId(script.getOwnerId() != null ? script.getOwnerId() : user.getId());
            script.setUserId(user.getId());
            this.scriptRepository.save(script);
            response.addResult(script);
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
                throw new SecurityException("The script {" + script.getId() + "} doesn't exist for this user, please use 'myscript/add'}");

            target.copy(script);
            this.scriptRepository.save(target);
            response.addResult(target);
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