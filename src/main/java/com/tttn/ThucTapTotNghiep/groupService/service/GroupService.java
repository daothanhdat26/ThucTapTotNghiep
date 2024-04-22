package com.tttn.ThucTapTotNghiep.groupService.service;

import com.tttn.ThucTapTotNghiep.groupService.model.Group;
import com.tttn.ThucTapTotNghiep.groupService.model.GroupMember;
import com.tttn.ThucTapTotNghiep.groupService.repository.GroupMemberRepository;
import com.tttn.ThucTapTotNghiep.groupService.repository.GroupRepository;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.GroupInfo;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.MemberInfo;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class GroupService {
    private GroupRepository groupRepository;
    private GroupMemberRepository groupMemberRepository;

    public GroupService(GroupRepository groupRepository, GroupMemberRepository groupMemberRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
    }
    public ResponseEntity<String>createGroupForClass(List<GroupInfo> groupList){
        for(GroupInfo groupInfo:groupList){
            Group newGroup=new Group();
            newGroup.setGroupName(groupInfo.getGroupName());
            newGroup.setClassId(groupInfo.getClassId());

            groupRepository.save(newGroup);
        }
        return new ResponseEntity<>("SUCCES", HttpStatus.CREATED);
    }
    public List<Group> getGroupListByClass(int classId){
        return groupRepository.getGroupByClassId(classId);
    }
    public ResponseEntity<String>addGroupMemberFromList(List<MemberInfo> memberList){
        for(MemberInfo member:memberList){
            GroupMember newMember=new GroupMember();
            newMember.setMemberId(member.getAccountId());

            Group groupInfo = groupRepository.findGroupByGroupName(member.getGroupName());
            newMember.setGroupId(groupInfo.getGroupId());


            groupMemberRepository.save(newMember);
        }

        return new ResponseEntity<>("SUCCES", HttpStatus.CREATED);
    }
    public ResponseEntity<String>updateGroupLeader(int classId,int groupId,int userId){
        groupRepository.updateGroupLeader(userId,classId,groupId);
        return new ResponseEntity<>("SUCCES",HttpStatus.OK);
    }
}
