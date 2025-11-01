package mack.ps2.estagios.estagios.controller;

import mack.ps2.estagios.estagios.model.Area;
import mack.ps2.estagios.estagios.repositories.AreaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {
    @Autowired
    private AreaRepo areaRepo;

    @GetMapping
    public List<Area> getAllAreas() {
        return areaRepo.findAll();
    }

    @PostMapping
    public Area createArea(@RequestBody Area area) {
        return areaRepo.save(area);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable Long id) {
        return areaRepo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable Long id) {
        if (!areaRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        areaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}