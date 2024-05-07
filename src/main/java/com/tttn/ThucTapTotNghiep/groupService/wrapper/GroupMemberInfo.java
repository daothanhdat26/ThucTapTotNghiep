package com.tttn.ThucTapTotNghiep.groupService.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupMemberInfo {
    private  int groupId;
    private String groupName;
    private int accountId;
    private String memberName;
    private String studentId;
}
