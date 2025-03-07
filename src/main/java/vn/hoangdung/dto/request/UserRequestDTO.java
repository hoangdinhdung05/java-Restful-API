package vn.hoangdung.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import vn.hoangdung.util.EnumPattern;
import vn.hoangdung.util.EnumValue;
import vn.hoangdung.util.Gender;
import vn.hoangdung.util.GenderSubset;
import vn.hoangdung.util.PhoneNumber;
import vn.hoangdung.util.UserStatus;
import vn.hoangdung.util.UserType;

import java.util.Set;
import java.util.Date;



public class UserRequestDTO implements Serializable{
    
    @NotBlank(message = "First name is required")
    @Size(min = 1, max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    // @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone is not valid")
    //Viết validate phone bằng annotation
    @PhoneNumber
    private String phone;

    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "dateOfBirth must be not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    // @Pattern(regexp = "^ACTIVE|INACTIVE|NONE$", message = "status must be one in {ACTIVE, INACTIVE, NONE}")
    @EnumPattern(regexp = "^ACTIVE|INACTIVE|NONE$", name = "status")
    private UserStatus status;

    @GenderSubset(anyOf = {Gender.MALE, Gender.FEMALE, Gender.OTHER}, message = "gender must be any of {anyOf}")
    private Gender gender;

    @NotNull(message = "type must be not null")
    @EnumValue(name = "type", enumClass = UserType.class)
    private String type;
    
    @NotEmpty(message = "addresses can not empty")
    private Set<Address> addresses;



    public UserRequestDTO(String firstName, String lastName, String phone, String email, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }


    public static class Address {
        private String apartmentNumber;
        private String floor;
        private String building;
        private String streetNumber;
        private String street;
        private String city;
        private String country;
        private Integer addressType;

        public String getApartmentNumber() {
            return apartmentNumber;
        }

        public void setApartmentNumber(String apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getAddressType() {
            return addressType;
        }

        public void setAddressType(Integer addressType) {
            this.addressType = addressType;
        }
    }


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Gender getGender() {
        return gender;
    }

    public String getType() {
        return type;
    }

}
