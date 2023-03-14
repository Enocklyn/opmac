/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyns.security;

/**
 *
 * @author enock
 */

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
 
public interface UserRepository extends CrudRepository<users, Long> {
 
    @Query("SELECT u FROM users u WHERE u.username = :username")
    public users getUserByUsername(@Param("username") String username);
}
