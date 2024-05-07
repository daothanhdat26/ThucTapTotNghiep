package com.tttn.ThucTapTotNghiep.groupService.repository;

import com.tttn.ThucTapTotNghiep.groupService.model.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember,Integer> {
    public List<GroupMember>findAllByGroupId(Integer groupId);
}
