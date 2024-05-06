package com.tttn.ThucTapTotNghiep.groupService.service;


import com.tttn.ThucTapTotNghiep.accountservice.model.StudentDetail;
import com.tttn.ThucTapTotNghiep.accountservice.repository.StudentDetailRepository;
import com.tttn.ThucTapTotNghiep.groupService.model.Group;
import com.tttn.ThucTapTotNghiep.groupService.model.GroupMember;
import com.tttn.ThucTapTotNghiep.groupService.model.Student;
import com.tttn.ThucTapTotNghiep.groupService.repository.GroupMemberRepository;
import com.tttn.ThucTapTotNghiep.groupService.repository.GroupRepository;
import com.tttn.ThucTapTotNghiep.groupService.repository.StudentRepository;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.GroupInfo;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.MemberInfo;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@Transactional
public class GroupService {
    private GroupRepository groupRepository;
    private GroupMemberRepository groupMemberRepository;
    private StudentRepository studentRepository;


    public GroupService(GroupRepository groupRepository, GroupMemberRepository groupMemberRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<String> createGroupForClass(List<GroupInfo> groupList) {
        for (GroupInfo groupInfo : groupList) {
            Group newGroup = new Group();
            newGroup.setGroupName(groupInfo.getGroupName());
            newGroup.setClassId(groupInfo.getClassId());
            newGroup.setLeaderId(groupInfo.getLeaderId());


            groupRepository.save(newGroup);
        }
        return new ResponseEntity<>("SUCCES", HttpStatus.OK);
    }

    public ResponseEntity<String> addGroupMemberFromList(List<MemberInfo> memberList) {
        for (MemberInfo member : memberList) {
            GroupMember newMember = new GroupMember();
            newMember.setMemberId(member.getAccountId());

            Group groupInfo = groupRepository.findGroupByGroupName(member.getGroupName());
            newMember.setGroupId(groupInfo.getGroupId());


            groupMemberRepository.save(newMember);
        }

        return new ResponseEntity<>("SUCCES", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateGroupLeader(int classId, int groupId, int userId) {
        //groupRepository.updateGroupLeader(userId,classId,groupId);
        return new ResponseEntity<>("SUCCES", HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> findJoinedClassById(int userId) {
        return new ResponseEntity<>(studentRepository.getStudentsByStudentId(userId), HttpStatus.OK);
    }

    public ResponseEntity<String> createSingleGroup(GroupInfo groupInfo) {
        Group newGroup = new Group();
        newGroup.setGroupName(groupInfo.getGroupName());
        newGroup.setClassId(groupInfo.getClassId());
        newGroup.setLeaderId(groupInfo.getLeaderId());

        groupRepository.save(newGroup);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<Group>> findGroupListByClassId(Integer classId) {
        List<Group> searchResult = new ArrayList<>();
        searchResult = groupRepository.findAllByClassId(classId);
        if (searchResult != null) {
            return new ResponseEntity<>(searchResult, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void deleteSVByClassIdAndStudentId(int classId, int studentId) {
        studentRepository.deleteByClassIdAndStudentId(classId, studentId);
    }

    public ResponseEntity<String> addMemberIntoGroup(int classId, int groupId, int accountId) {
        GroupMember newMember = new GroupMember(groupId, accountId);
        groupMemberRepository.save(newMember);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    public void assignStudentsToRandomGroups ( int classId, int numberOfGroup, int memberPerGroup){
            List<Student> studentList = studentRepository.getStudentsByClassId(classId);
            for (int i = 1; i <= numberOfGroup; i++) {
                String groupName = "Nhóm " + i;
                Group group = new Group(0, classId, groupName);
                Group newGroup = groupRepository.save(group);
                for (int j = 1; j <= memberPerGroup; j++) {
                    if (studentList.isEmpty()) {
                        break;
                    }
                    int index = randomNumber(studentList.size());
                    Student student = studentList.get(index);
                    GroupMember newMember = new GroupMember(newGroup.getGroupId(), student.getStudentId());
                    groupMemberRepository.save(newMember);
                    studentList.remove(student);
                }
            }
            //return new ResponseEntity<>(HttpStatus.CREATED);
        }


        public static Integer randomNumber ( int max){
            if (max <= 0) {
                return 0;
            }
            Random rand = new Random();
            int randomNumber = rand.nextInt(max);
            return randomNumber;

        }


    public void deleteGroupById(Integer id) {
        groupRepository.deleteById(id);
    }

}
