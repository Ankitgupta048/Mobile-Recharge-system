package com.example.mobilerecharge.service;
import com.example.mobilerecharge.model.RechargePlan; import com.example.mobilerecharge.model.Transaction;
import com.example.mobilerecharge.repository.RechargePlanRepository; import com.example.mobilerecharge.repository.TransactionRepository;
import org.springframework.stereotype.Service; import java.util.List;
@Service public class RechargeService {
    private final RechargePlanRepository planRepo; private final TransactionRepository txnRepo;
    public RechargeService(RechargePlanRepository p, TransactionRepository t){this.planRepo=p; this.txnRepo=t;}
    public List<RechargePlan> allPlans(){return planRepo.findAll();} public RechargePlan savePlan(RechargePlan p){return planRepo.save(p);} public Transaction saveTxn(Transaction t){return txnRepo.save(t);} public List<Transaction> history(Long userId){return txnRepo.findByUserId(userId);}
}
