package com.myhealth.oHealth.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "manager")
public class FrontDeskOfficer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id")
        private AppUser user;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "manager_roles",
                joinColumns = @JoinColumn(name = "manager_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private List<Role> roles = new ArrayList<>();


}
