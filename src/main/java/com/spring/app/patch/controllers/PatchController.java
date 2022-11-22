package com.spring.app.patch.controllers;

import com.spring.app.patch.models.Patch;
import com.spring.app.patch.models.repository.PatchRepository;
import com.spring.app.patch.payload.RemoveRequest;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/patch")
public class PatchController {
    @Autowired
    PatchRepository patchRepository;

    @DeleteMapping("/remove")
    public ResponseEntity<?> removePatch(@Valid @RequestBody RemoveRequest request) {
        Boolean patchExist = patchRepository.existsByPatch(request.getPatch());

        if (patchExist) {
            try {
                Patch patch = patchRepository.findByPatch(request.getPatch())
                        .orElseThrow(() -> new RuntimeException("Error when trying remove patch!"));
                patchRepository.delete(patch);
                return ResponseEntity.ok(new MessageResponse("Remove patch data success!"));
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
            }
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Patch not found!"));
    }
}
