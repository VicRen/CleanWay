package com.juphoon.domain.entity;

public class User {

    public static final int USER_TYPE_DEFAULT = 0;
    public static final int USER_TYPE_CONTACT = 1;
    public static final int USER_TYPE_FREE_CONTACT = 2;
    public static final int USER_TYPE_CERTIFIED_USER = 4;
    public static final int USER_TYPE_CLOUD_USER = 8;
    public static final int USER_TYPE_CLOUD_FRIEND = 24;

    public static final int USER_GENDER_UNKNOWN = -1;
    public static final int USER_GENDER_FEMALE = 0;
    public static final int USER_GENDER_MALE = 1;

    // default
    private final String phone;
    private int type;
    private String nickname;
    private String displayName;
    private String certifiedName;
    private String avatarUrl;
    private String avatarThumbnailUrl;
    private int gender;
    private String certificationType;
    private String university;
    private String school;
    private String clazz;
    private String studentNumber;
    private String year;
    private String month;
    private String province;
    private String city;
    private String district;
    private boolean isGzMobile;
    private String schoolBadgeUrl;
    private int welfareCount;
    private int certificationLogCount;
    private String extraInfo;

    public User(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isContact() {
        return (type & USER_TYPE_CONTACT) != 0;
    }

    public boolean isFreeContact() {
        return (type & USER_TYPE_FREE_CONTACT) != 0;
    }

    public boolean isCertifiedUser() {
        return (type & USER_TYPE_CERTIFIED_USER) != 0;
    }

    public boolean isCloudUser() {
        return (type & USER_TYPE_CLOUD_USER) != 0;
    }

    public boolean isCloudFriend() {
        return (type & USER_TYPE_CLOUD_FRIEND) != 0;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCertifiedName() {
        return certifiedName;
    }

    public void setCertifiedName(String certifiedName) {
        this.certifiedName = certifiedName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarThumbnailUrl() {
        return avatarThumbnailUrl;
    }

    public void setAvatarThumbnailUrl(String avatarThumbnailUrl) {
        this.avatarThumbnailUrl = avatarThumbnailUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCertificationType() {
        return certificationType;
    }

    public void setCertificationType(String certificationType) {
        this.certificationType = certificationType;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public boolean isGzMobile() {
        return isGzMobile;
    }

    public void setGzMobile(boolean gzMobile) {
        isGzMobile = gzMobile;
    }

    public String getSchoolBadgeUrl() {
        return schoolBadgeUrl;
    }

    public void setSchoolBadgeUrl(String schoolBadgeUrl) {
        this.schoolBadgeUrl = schoolBadgeUrl;
    }

    public int getWelfareCount() {
        return welfareCount;
    }

    public void setWelfareCount(int welfareCount) {
        this.welfareCount = welfareCount;
    }

    public int getCertificationLogCount() {
        return certificationLogCount;
    }

    public void setCertificationLogCount(int certificationLogCount) {
        this.certificationLogCount = certificationLogCount;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
