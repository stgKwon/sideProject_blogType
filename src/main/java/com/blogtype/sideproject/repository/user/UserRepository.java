package com.blogtype.sideproject.repository.user;

import com.blogtype.sideproject.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>, UserCustomRepository {

}
