package az.atl.auto_mart.service;

import az.atl.auto_mart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ScheduledMethods {

    private final UserRepository userRepository;

    @Transactional
    @Scheduled(cron = "0 0 2 1,10,20 * *")
    public void runOnSpecificDays() {
        userRepository.deleteNotVerifiedUsers();
    }
}
