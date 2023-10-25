package io.dmullandev.service.customer.model;

public record CustomerRegistrationRequest(
                String firstName,
                String lastName,
                String email) {

}
