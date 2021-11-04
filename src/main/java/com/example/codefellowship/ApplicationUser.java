package com.example.codefellowship;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
//    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;


    private String  bio;

    public ApplicationUser(int id, String username, String password, String firstName, String lastName, Date  dateOfBirth, String bio) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Post> postsForAll;




    public ApplicationUser(){

    }

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;

    }


    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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




    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Post> getPostsForAll() {
        return postsForAll;
    }

    public void setPostsForAll(List<Post> postsForAll) {
        this.postsForAll = postsForAll;
    }
    @ManyToMany
    @JoinTable(
            name="user_user",
            joinColumns = {@JoinColumn(name="from_id")},
            inverseJoinColumns = {@JoinColumn(name="to_id")}
    )
    public List<ApplicationUser> following;

    @ManyToMany(mappedBy="following", fetch = FetchType.EAGER)
    public List<ApplicationUser> follower;

    public List<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(ApplicationUser addfollowing) {
        this.following.add(addfollowing);
    }

    public List<ApplicationUser> getFollower() {
        return follower;
    }

    public void setFollower(List<ApplicationUser> follower) {
        this.follower = follower;
    }
}
