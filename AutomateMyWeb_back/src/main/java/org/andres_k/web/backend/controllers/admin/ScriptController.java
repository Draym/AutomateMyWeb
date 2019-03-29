package org.andres_k.web.backend.controllers.admin;

import org.andres_k.web.backend.config.Restricted;
import org.andres_k.web.backend.models.auth.ERoles;
import org.andres_k.web.backend.models.auth.UserRepository;
import org.andres_k.web.backend.models.items.jobs.Script;
import org.andres_k.web.backend.models.items.jobs.ScriptRepository;
import org.andres_k.web.backend.config.HttpResponse;
import org.andres_k.web.backend.utils.tools.TJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Restricted(tokenRequired = false, roles = ERoles.ADMIN)
@Controller("AdminScriptController")
@RequestMapping(value = "/admin")
public class ScriptController {
    @Autowired
    private ScriptRepository scriptRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/script/all", method = RequestMethod.GET)
    @ResponseBody
    public String getScripts(@RequestParam Integer page, @RequestParam Integer pageSize) {
        HttpResponse response = new HttpResponse();

        try {
            Page<Script> scripts = this.scriptRepository.findAll(PageRequest.of(page, pageSize));

            response.addResult(scripts.getContent());
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script", method = RequestMethod.GET)
    @ResponseBody
    public String getScript(@RequestParam Long scriptId) {
        HttpResponse response = new HttpResponse();

        try {
            Optional<Script> optScript = this.scriptRepository.findById(scriptId);

            if (!optScript.isPresent())
                throw new NullPointerException("The script {" + scriptId + "} doesn't exist");

            response.addResult(optScript.get());
        } catch (Exception ex) {
            response.addError("Error searching the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/add", method = RequestMethod.POST)
    @ResponseBody
    public String addScript(@RequestParam Long userId, @RequestBody Script script) {
        HttpResponse response = new HttpResponse();

        try {
            if (!this.userRepository.existsById(userId))
                throw new EntityNotFoundException("The user {" + userId + "} has not been found.");
            if (script.getName() == null || script.getValue() == null || script.getUserId() == null)
                throw new NullPointerException("The script's name/value/userId is null.");

            script.setOwnerId(userId);
            this.scriptRepository.save(script);
            response.addResult(script);
        } catch (Exception ex) {
            response.addError("Error creating the script:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/update", method = RequestMethod.PUT)
    @ResponseBody
    public String updateScript(@RequestBody Script script) {
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
        return TJson.toString(response);
    }

    @RequestMapping(value = "/script/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteScript(@RequestBody Long id) {
        HttpResponse response = new HttpResponse();

        try {
            this.scriptRepository.deleteById(id);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the script:" + ex.toString());
        }
        return TJson.toString(response);
    }
}