package com.tttn.ThucTapTotNghiep.groupService.repository;

import com.tttn.ThucTapTotNghiep.groupService.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    List<Group>getGroupByClassId(Integer classId);
    Group findGroupByGroupName(String groupName);

    @Modifying
    @Query("UPDATE Group g SET g.leaderId= :userId WHERE g.classId = :classId AND g.groupId = :groupId")
    void updateGroupLeader(@Param(value = "userId")Integer userId,@Param(value = "classId")Integer classId,@Param(value = "groupId")Integer groupId);
}
