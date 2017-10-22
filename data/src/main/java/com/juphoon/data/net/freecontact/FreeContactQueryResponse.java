package com.juphoon.data.net.freecontact;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.juphoon.data.entity.FreeContactEntity;
import com.juphoon.data.net.CMCCResultInfo;
import com.juphoon.data.net.NetResponse;
import com.juphoon.domain.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FreeContactQueryResponse extends NetResponse<FreeContactQueryResponse.QueryResponse> {

    public boolean isResultSuccess() {
        if (code != CODE_SUCCESS) {
            return false;
        }
        try {
            int retType = data.getQueryResponseContent().getMessageHeader().getResultInfo().getResultType();
            return retType == 0;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getResultMessage() {
        if (code == CODE_FAIL) {
            return error;
        }
        try {
            return data.getQueryResponseContent().getMessageHeader().getResultInfo().getResultMessage();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    List<FreeContactEntity> getFreeContactList(String phone) {
        List<FreeContactEntity> list = new ArrayList<>();
        boolean isThisArray = false;
        try {
            GroupItem groupItem = data.getQueryResponseContent().getMessageBody().groupItem;
            List<MemberList> memberLists = data.getQueryResponseContent().getMessageBody().memberLists;
            if (groupItem != null) {
                MemberList memberList = groupItem.memberList;
                if (StringUtils.equals(memberList.prodId, "85817")) {
                    for (FreeContactEntity freeContactEntity : memberList.freeContactEntities) {
                        if (StringUtils.equals(freeContactEntity.getPhone(), phone) && freeContactEntity.isOwner()) {
                            isThisArray = true;
                        } else {
                            list.add(freeContactEntity);
                        }
                    }
                    if (!isThisArray) {
                        list.clear();
                    }
                }
            } else if (memberLists != null && !memberLists.isEmpty()) {
                for (MemberList memberList : memberLists) {
                    if (StringUtils.equals(memberList.prodId, "85817")) {
                        for (FreeContactEntity freeContactEntity : memberList.freeContactEntities) {
                            if (StringUtils.equals(freeContactEntity.getPhone(), phone) && freeContactEntity.isOwner()) {
                                isThisArray = true;
                            } else {
                                list.add(freeContactEntity);
                            }
                        }
                        if (isThisArray) {
                            break;
                        } else {
                            list.clear();
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    MessageBody getMessageBody() {
        try {
            return data.getQueryResponseContent().getMessageBody();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    static final class QueryResponse {

        @SerializedName("richmanmemberqueyresp")
        private QueryResponseContent queryResponseContent;

        QueryResponseContent getQueryResponseContent() {
            return queryResponseContent;
        }
    }

    private static final class QueryResponseContent {

        @SerializedName("msgheader")
        private MessageHeader messageHeader;

        @SerializedName("msgbody")
        private MessageBody messageBody;

        MessageHeader getMessageHeader() {
            return messageHeader;
        }

        MessageBody getMessageBody() {
            return messageBody;
        }
    }

    private static final class MessageHeader {

        @SerializedName("retinfo")
        private CMCCResultInfo resultInfo;

        CMCCResultInfo getResultInfo() {
            return resultInfo;
        }
    }

    static final class MessageBody {

        @SerializedName("grouplist")
        private JsonElement groupObj;

        private GroupItem groupItem;

        private List<MemberList> memberLists;

        JsonElement getGroupObj() {
            return groupObj;
        }

        void setGroupItem(GroupItem groupItem) {
            this.groupItem = groupItem;
        }

        void setMemberLists(List<MemberList> memberLists) {
            this.memberLists = memberLists;
        }
    }

    static final class GroupItem {

        @SerializedName("group")
        private MemberList memberList;
    }

    static final class MemberList {

        @SerializedName("prodid")
        private String prodId;

        @SerializedName("memberlist")
        private List<FreeContactEntity> freeContactEntities;
    }
}
