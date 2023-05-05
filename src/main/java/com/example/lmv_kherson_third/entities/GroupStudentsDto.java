package com.example.lmv_kherson_third.entities;

public class GroupStudentsDto {
    private int groupId;
    private String groupName;

    public GroupStudentsDto(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Група №" + groupId + ", назва=" + groupName;
    }
}
