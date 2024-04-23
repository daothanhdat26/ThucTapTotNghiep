package com.tttn.ThucTapTotNghiep.groupService.controller;


import com.tttn.ThucTapTotNghiep.groupService.model.Group;
import com.tttn.ThucTapTotNghiep.groupService.model.Student;
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
        //return new ResponseEntity<String>(groupService.createGroupForClass(groupInfoList),HttpStatus.OK);
    }
    //thêm thành viên theo danh sách
    @PostMapping("/api/class/add-member")
    public ResponseEntity<String>addMemberIntoGroup(@RequestBody List<MemberInfo> memberList){
        return groupService.addGroupMemberFromList(memberList);
    }
    //cập nhật leader
    //tam thoi tkhong dung dc
    @PutMapping("/api/class/{classId}/group/{groupId}/set-leader/{userId}")
    public ResponseEntity<String>updateGroupLeader(@PathVariable Integer classId,@PathVariable Integer groupId,@PathVariable Integer userId){
        return groupService.updateGroupLeader(classId,groupId,userId);
    }
    //tao mot group
    @PostMapping("api/class/create-a-group")
    public ResponseEntity<String>createSingleGroup(@RequestBody GroupInfo groupInfo){
        return groupService.createSingleGroup(groupInfo);
    }
    //lấy danh saách học sinh của lớp theo classId
    @GetMapping("/api/class/{classId}/student-list")
    public ResponseEntity<List<Student>>getStudentListOfClass(@PathVariable Integer classId){
        return groupService.getStudentOfClass(classId);
    }
    //lay danh sach lop da join
    @GetMapping("/api/user/{userId}/joined-class")
    public ResponseEntity<List<Student>>getJoinedClassOfUser(@PathVariable Integer userId){
        return groupService.findJoinedClassById(userId);
    }

    //Lay danh sach nhóm của lớp do
    @GetMapping("/api/class/{classId}/group-list")
    public ResponseEntity<List<Group>>getGroupListOfClass(@PathVariable Integer classId){
        return groupService.findGroupListByClassId(classId);
    }

}
