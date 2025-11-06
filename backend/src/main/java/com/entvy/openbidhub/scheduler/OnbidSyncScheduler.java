package com.entvy.openbidhub.scheduler;

import com.entvy.openbidhub.service.OnbidSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OnbidSyncScheduler {
    private final OnbidSyncService syncService;

    @Scheduled(cron = "0 0 3 * * *") // 매일 새벽 3시
    public void runDailySync() {
        log.info("온비드 전체 동기화 시작");
        syncService.syncAll();
        log.info("온비드 전체 동기화 완료");
    }
}
