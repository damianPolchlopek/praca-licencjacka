package com.polchlopek.validation;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class PersonToUpdatePassword {

    private int idPerson;

    @NotEmpty(message="is required")
    @Size(min=4, max=45, message="min 4 characters, max 45 characters")
    private String oldPassword;

    @NotEmpty(message="is required")
    @Size(min=4, max=45, message="min 4 characters, max 45 characters")
    private String newPassword1;

    @NotEmpty(message="is required")
    @Size(min=4, max=45, message="min 4 characters, max 45 characters")
    private String newPassword2;

    public PersonToUpdatePassword() {
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
