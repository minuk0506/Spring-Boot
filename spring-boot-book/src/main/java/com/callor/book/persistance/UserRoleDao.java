package com.callor.book.persistance;

import com.callor.book.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {
}
