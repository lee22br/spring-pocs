package com.example.scope.application;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    private final GlobalAnnouncement announcement;

    public AnnouncementController(GlobalAnnouncement announcement) {
        this.announcement = announcement;
    }

    @GetMapping
    public String getAnnouncement() {
        return announcement.getCurrentMessage();
    }

    @PostMapping
    public void updateAnnouncement(@RequestParam String message) {
        announcement.setCurrentMessage(message);
    }
}
