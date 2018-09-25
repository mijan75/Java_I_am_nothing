package tally.com.tally_software;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tally.com.tally_software.config.SecurityUtility;
import tally.com.tally_software.domain.Role;
import tally.com.tally_software.domain.UserRole;
import tally.com.tally_software.model.StockItems;
import tally.com.tally_software.model.User;
import tally.com.tally_software.service.StockItemsService;
import tally.com.tally_software.service.UserService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

@SpringBootApplication
@EnableAsync
public class TallySoftwareApplication implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Autowired
    StockItemsService stockItemsService
            ;
    public static void main(String[] args) {
        SpringApplication.run(TallySoftwareApplication.class, args);


    }

    public void run(String[] args)throws Exception{
        User user = new User();

        user.setName("Nazmul Huda");
        user.setUsername("Nazmul42");
        user.setPassword(SecurityUtility.passwordEncoder().encode("nazmul"));
        user.setCreated_time(new Date());
        user.setCreated_by("Nazmul42");
        user.setEmail("nazmul8842@gmail.com");

        user.setPhoneNumber("01767888842");
        Set<UserRole> roleSet = new HashSet<>();
        Role role = new Role();
        role.setRoleId(1);
        role.setRole("ROLE_DEVELOPER");
        roleSet.add(new UserRole(user, role));
        userService.createUser(user, roleSet);

        roleSet.clear();


    }
}
