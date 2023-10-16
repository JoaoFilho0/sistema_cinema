package com.system.movietheater.domain.model;

import com.system.movietheater.application.dto.user.ListingUserDto;
import com.system.movietheater.application.dto.user.RegisterUserDto;
import com.system.movietheater.application.dto.user.UpdateUserDto;
import com.system.movietheater.domain.Enum.TypeUserEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;

    @Column(name = "usu_nome")
    private String name;

    @Column(name = "usu_email", unique = true)
    private String email;

    @Column(name = "usu_senha")
    private String password;

    @Column(name = "usu_ativo")
    private Boolean active = true;

    @OneToMany(mappedBy = "user")
    private List<MovieTheater> movieTheater;

    @OneToMany
    @JoinTable(
            name = "usuario_sessao",
            joinColumns = @JoinColumn(name = "fk_usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_sessao_id")
    )
    private List<Session> session;

    @Column(name = "usu_tipo")
    @Enumerated(EnumType.STRING)
    private List<TypeUserEnum> type;

    public User(RegisterUserDto data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.type = List.of(TypeUserEnum.ROLE_USER);
    }

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.active = user.getActive();
        this.movieTheater = user.getMovieTheater();
    }

    public User(ListingUserDto user) {
        this.id = user.id();
        this.name = user.name();
        this.email = user.email();
    }

    public void updateData(UpdateUserDto data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
    }

    public void statusAccount(Boolean status) {
        this.active = !status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return type.stream()
                .map(p -> new SimpleGrantedAuthority(p.name()))
                .toList();
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
