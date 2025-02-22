package project.org.techshop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.org.techshop.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/assign/role/{userId}")
    public ResponseEntity<Void> assignRole(@PathVariable String userId,@RequestParam String roleName) {
        roleService.assignRole(userId, roleName);
        return ResponseEntity.ok().build();
    }
}
