package org.sahni.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sahni.models.db.Users;

@ApplicationScoped
public class UsersRepository implements PanacheRepository<Users> {
}
