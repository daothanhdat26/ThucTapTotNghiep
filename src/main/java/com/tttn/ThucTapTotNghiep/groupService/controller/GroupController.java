package com.tttn.ThucTapTotNghiep.groupService.controller;


import com.tttn.ThucTapTotNghiep.groupService.service.GroupService;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.GroupInfo;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GroupController {
    @Autowired
    GroupService groupService;

    //tạo nhóm bằng danh sách
    @PostMapping("/api/class/create-groups")
    public ResponseEntity<String>createGroupFromList(@RequestBody List<GroupInfo> groupInfoList){
        return groupService.createGroupForClass(groupInfoList);
    }
    //thêm thành viên theo danh sách
    @PostMapping("/api/class/add-member")
    public ResponseEntity<String>addMemberIntoGroup(@RequestBody List<MemberInfo> memberList){
        return groupService.addGroupMemberFromList(memberList);
    }
    //cập nhật leader
    @PutMapping("/api/class/{classId}/group/{groupId}/set-leader/{userId}")
    public ResponseEntity<String>updateGroupLeader(@PathVariable Integer classId,@PathVariable Integer groupId,@PathVariable Integer userId){
        return groupService.updateGroupLeader(classId,groupId,userId);
    }
    @PostMapping("api/class/create-a-group")
    public ResponseEntity<String>createSingleGroup(@RequestBody GroupInfo groupInfo){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
