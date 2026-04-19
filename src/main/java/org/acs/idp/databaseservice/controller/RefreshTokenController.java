package org.acs.idp.databaseservice.controller;

import org.acs.idp.databaseservice.model.dto.RefreshTokenDto;
import org.acs.idp.databaseservice.model.request.SaveRefreshTokenRequest;
import org.acs.idp.databaseservice.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refresh-tokens")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SaveRefreshTokenRequest request) {
        refreshTokenService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<RefreshTokenDto> findByToken(@RequestParam String token) {
        RefreshTokenDto dto = refreshTokenService.findByToken(token);

        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByToken(@RequestParam String token) {
        refreshTokenService.deleteByToken(token);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllByEmail(@RequestParam String email) {
        refreshTokenService.deleteAllByEmail(email);
        return ResponseEntity.ok().build();
    }
}
