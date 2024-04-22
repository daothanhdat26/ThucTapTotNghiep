package com.tttn.ThucTapTotNghiep.groupService.controller;


import com.tttn.ThucTapTotNghiep.groupService.service.GroupService;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.GroupInfo;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping("/api/class/create-groups")
    public ResponseEntity<String>createGroupFromList(@RequestBody List<GroupInfo> groupInfoList){
        return groupService.createGroupForClass(groupInfoList);
    }
    @PostMapping("/api/class/add-member")
    public ResponseEntity<String>addMemberIntoGroup(@RequestBody List<MemberInfo> memberList){
        return groupService.addGroupMemberFromList(memberList);
    }
    @PutMapping("/api/class/{classId}/group/{groupId}/set-leader/{userId}")
    public ResponseEntity<String>updateGroupLeader(@PathVariable Integer classId,@PathVariable Integer groupId,@PathVariable Integer userId){
        return groupService.updateGroupLeader(classId,groupId,userId);
    }

}
