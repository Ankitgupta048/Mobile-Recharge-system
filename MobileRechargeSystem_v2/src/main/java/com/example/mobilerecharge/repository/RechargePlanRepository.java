package com.example.mobilerecharge.repository;
import com.example.mobilerecharge.model.RechargePlan; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface RechargePlanRepository extends JpaRepository<RechargePlan, Long> { List<RechargePlan> findByOperator(String operator); }
