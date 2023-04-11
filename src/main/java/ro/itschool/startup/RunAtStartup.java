package ro.itschool.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.MyRole;
import ro.itschool.entity.RoleName;
import ro.itschool.repository.RoleRepository;

@Component
public class RunAtStartup {

    @Autowired
    private RoleRepository roleRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        roleRepository.save(new MyRole(RoleName.ROLE_USER));
        roleRepository.save(new MyRole(RoleName.ROLE_ADMIN));

    }
}
