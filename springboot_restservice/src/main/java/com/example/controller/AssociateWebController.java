package com.example.controller;

import com.example.commonlib.CommonLib;
import com.example.objects.Associate;
import com.example.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AssociateWebController {
    CommonLib cml = new CommonLib();
    @Autowired
    AssociateService associateService;

    //Add associate POST request
    @RequestMapping(
            value = "/api/v1/associate/add",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String addAssociate(@RequestBody String addAssociateTemplate) {
        List<Associate> associates = cml.convertJsonToObject(addAssociateTemplate);
        System.out.println("List associates: " + associates);
        String responseMessage = "";
        for (Associate anAssociate : associates) {
            if (associateService.addAssociate(anAssociate)) {
                responseMessage = "Add associate successfully";
            } else {
                responseMessage = "Associate's code existed";
                break;
            }
        }
        //return code and message
        //return ResponseEntity.status(HttpStatus.OK).body(cml.convertObjectToJson(associates));
        return responseMessage;
    }

    //Get list associates
    @GetMapping("/api/v1/associate/listassociates")
    public ResponseEntity<String> listAssociates() {
        return ResponseEntity.status(HttpStatus.OK).body(cml.convertObjectToJson(associateService.getAllAssociates()));
    }

    @RequestMapping(
            value = "/api/v1/associate/{code}",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String updateAssociate(@RequestBody String addAssociateTemplate, @PathVariable String code) {
        List<Associate> associates = cml.convertJsonToObject(addAssociateTemplate);
        String responseMessage = "";
        for (Associate anAssociate : associates) {
            if (associateService.updateAssociateWithCode(anAssociate, code)) {
                responseMessage = "Update associate successfully";
            } else {
                responseMessage = "Associate code " + anAssociate.getCode() + " is incorrect";
                break;
            }
        }
        return responseMessage;
    }

    //Filter associate has birthday in requested month POST request
    @RequestMapping(
            value = "/api/v1/associate/filter-by-month",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity filterAssociateByMonth(@RequestBody String monthTemplate) {
        Map<String, Integer> monthKeyValue = cml.convertJsonToMap(monthTemplate);
        Integer month = monthKeyValue.get("month");
        List<Associate> associateList = associateService.filterAssociatesHaveBirthdayMappingSelectedMonth(month);
        return ResponseEntity.ok().body(cml.convertObjectToJson(associateList));
    }

    //Delete associate POST request
    @RequestMapping(
            value = "/api/v1/associate/{code}",
            method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String deleteAssociateWithCode(@PathVariable String code) {
        String responseMessage = "";
        if (associateService.deleteAssociateWithCode(code)) {
            responseMessage = "Delete associate with code " + code + "successfully";
        } else {
            responseMessage = "Associate code " + code + " is incorrect";
        }
        return responseMessage;
    }
}
