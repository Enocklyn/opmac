

package com.lyns.period;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public interface PeriodRepository extends JpaRepository<Period,Long> {
    
}
