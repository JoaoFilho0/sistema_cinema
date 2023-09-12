package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.MovieTheater;
import com.system.movietheater.domain.session.Session;
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
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;

    @Column(name = "usu_nome")
    private String name;

    @Column(name = "usu_email")
    private String email;

    @Column(name = "usu_senha")
    private String password;

    @OneToOne
    @JoinColumn(name = "fk_cinema_id")
    private MovieTheater movieTheater;

    @OneToMany
    @JoinTable(
            name = "usuario_sessao",
            joinColumns = @JoinColumn(name = "fk_usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_sessao_id")
    )
    private List<Session> session;

    public User(DataRegisterUser data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
    }

    public void updateData(DataUpdateUser data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
