package com.icannhas.readysaster;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table PERSONAL_DETAILS.
 */
public class PersonalDetails {

    private Long id;
    private String name;
    private Long contact_number;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public PersonalDetails() {
    }

    public PersonalDetails(Long id) {
        this.id = id;
    }

    public PersonalDetails(Long id, String name, Long contact_number) {
        this.id = id;
        this.name = name;
        this.contact_number = contact_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContact_number() {
        return contact_number;
    }

    public void setContact_number(Long contact_number) {
        this.contact_number = contact_number;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
