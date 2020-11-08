package com.doruk.blacklist.application;

import com.doruk.blacklist.interfaces.request.AddBlacklistRequest;
import com.doruk.blacklist.interfaces.request.UpdateBlacklistRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Slf4j
public class BlacklistController {

    private final BlacklistService blacklistService;

    public BlacklistController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @GetMapping(value = "/v1/blacklist/check/{identity}")
    public ResponseEntity<Object> checkApplication(@PathVariable @NotBlank String identity) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/v1/blacklist/{identity}")
    public ResponseEntity<Object> getBlacklistInformation(@PathVariable @NotBlank String identity) {
        return ResponseEntity.ok(blacklistService.getBlacklistInformation(identity));
    }

    @PostMapping(value = "/v1/blacklist/add")
    public ResponseEntity<Object> addApplication(@RequestBody @Valid AddBlacklistRequest addBlacklistRequest) {
        blacklistService.AddBlackList(addBlacklistRequest);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/v1/blacklist/update}")
    public ResponseEntity<Object> updateApplication(@RequestBody @Valid UpdateBlacklistRequest updateBlacklistRequest) {
        blacklistService.updateBlackList(updateBlacklistRequest);

        return ResponseEntity.noContent().build();
    }

}
