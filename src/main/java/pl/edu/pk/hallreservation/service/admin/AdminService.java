package pl.edu.pk.hallreservation.service.admin;

import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.ClassesPeriod;
import pl.edu.pk.hallreservation.repository.AdminRepository;
import pl.edu.pk.hallreservation.repository.HallRepository;
import pl.edu.pk.hallreservation.service.admin.dto.ClassesPeriodDTO;
import pl.edu.pk.hallreservation.service.admin.mapper.AdminDTOMapper;


@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminDTOMapper adminDTOMapper;

    public AdminService(AdminRepository adminRepository, AdminDTOMapper adminDTOMapper) {
        this.adminRepository = adminRepository;
        this.adminDTOMapper = adminDTOMapper;
    }

    public void create(ClassesPeriodDTO classesPeriodDTO) {
        ClassesPeriod classesPeriod = new ClassesPeriod(classesPeriodDTO.getDateFrom(), classesPeriodDTO.getDateTo());
        adminRepository.save(classesPeriod);
    }
}
