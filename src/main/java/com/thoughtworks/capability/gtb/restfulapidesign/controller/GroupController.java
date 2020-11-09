package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/regroup")
    public void regroup(@RequestParam(defaultValue = "6") int total) {
        groupService.regroup(total);
    }

    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }
}
