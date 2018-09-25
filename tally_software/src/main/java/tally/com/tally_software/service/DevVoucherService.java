package tally.com.tally_software.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.DevVoucher;
import tally.com.tally_software.repository.DevGroupRepository;
import tally.com.tally_software.repository.DevVoucherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DevVoucherService {

    @Autowired
    DevVoucherRepository devVoucherRepository;

    public List<DevVoucher> devVoucherList(){
        return devVoucherRepository.findAllByOrderByVoucherNameAsc();
    }

    public DevVoucher save(DevVoucher devVoucher){
        return devVoucherRepository.save(devVoucher);
    }

    public DevVoucher update(DevVoucher devVoucher){
        return devVoucherRepository.save(devVoucher);
    }

    public Optional<DevVoucher> getDevVoucher(int id){
        return devVoucherRepository.findById(id);
    }

}
