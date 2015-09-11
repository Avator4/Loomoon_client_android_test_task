package com.sem.loomoon;

public class DataUser {

    private String title;
    private String firstName;
    private String mobilePhoneNumber;
    private String birthday;
    private String id;
    private String mariageStatus;
    private String sex;
    private String dateRegistered;
    private String documentType;

    public String getFirstName() {
        return firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday.trim();
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id.trim();
    }

    public String getMariageStatus() {
        return mariageStatus;
    }

    public void setMariageStatus(String mariageStatus) {
        this.mariageStatus = mariageStatus.trim();
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex.trim();
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered.trim();
    }
    public String getDateRegistered() {
        return dateRegistered;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Обращение: ");
        sb.append(title);
        sb.append('\n');
        sb.append("Имя: ");
        sb.append(firstName);
        sb.append('\n');
        sb.append("День рождения: ");
        sb.append(birthday);
        sb.append('\n');
        sb.append("Мобильный номер: ");
        sb.append(mobilePhoneNumber);
        sb.append('\n');
        sb.append("Номер: ");
        sb.append(id);
        sb.append('\n');
        sb.append("Семейное положение: ");
        sb.append(mariageStatus);
        sb.append('\n');
        sb.append("Пол: ");
        sb.append(sex);
        sb.append('\n');
        sb.append("Дата регистрации: ");
        sb.append(dateRegistered);
        sb.append('\n');
        sb.append("Тип документа: ");
        sb.append(documentType);
        return sb.toString();
    }
}
