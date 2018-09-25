package tally.com.tally_software.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tally.com.tally_software.model.Voucher;
import tally.com.tally_software.repository.VoucherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {

    @Autowired
    VoucherRepository  voucherRepository;


    public List<Voucher> getAllVoucher(){
        return voucherRepository.findAllByOrderByVoucherNameAsc();
    }

    public Optional<Voucher> getOne(int id){
        return voucherRepository.findById(id);
    }


    public Voucher save(Voucher voucher){
        return voucherRepository.save(voucher);
    }

    public Voucher update(Voucher voucher){
        return voucherRepository.save(voucher);
    }
}
