package com.restful.restfulapi.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDetails {

    @NotNull(message = "First name cannot be null")
    @Size(min=2, max=20, message = "Size problems")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @Size(min=2, max=20, message = "Size problems")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}