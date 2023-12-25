package com.onlineestore.user.infra.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineestore.user.domain.User;

@Repository
public interface IMySQLRepository extends JpaRepository<User, Long> {

}