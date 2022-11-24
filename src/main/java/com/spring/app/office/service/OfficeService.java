package com.spring.app.office.service;

import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.office.models.Office;
import com.spring.app.office.models.repository.OfficeRepository;
import com.spring.app.office.payload.OfficeData;
import com.spring.app.price.models.Rule;
import com.spring.app.price.models.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {
    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    RuleRepository ruleRepository;

    public OfficeData processOfficeDataResponse(Office of) {
        return new OfficeData(
                of.getId(),
                of.getName(),
                of.getProvince(),
                of.getProvinceId(),
                of.getDistrict(),
                of.getDistrictId(),
                of.getWard(),
                of.getWardId(),
                of.getStreet(),
                of.getPhone(),
                of.getPriceRule() != null ? of.getPriceRule().getId() : null,
                DateFormatHelper.dateToString(of.getCreatedAt()),
                DateFormatHelper.dateToString(of.getUpdatedAt())
        );
    }

    public void saveOfficeData(OfficeData od) {
        Office office;

        if (od.getId() != null) {
            office = officeRepository.findById(od.getId()).orElseThrow(() -> new RuntimeException("Can not find office!"));
        } else {
            office = new Office();
        }

        Rule rule = null;
        if (od.getPriceRuleId() != null) {
            rule = ruleRepository.findById(od.getPriceRuleId()).orElseThrow(() -> new RuntimeException("Price rule not found!"));
        }

        office.setName(od.getName())
                .setPhone(od.getPhone())
                .setProvince(od.getProvince())
                .setProvinceId(od.getProvinceId())
                .setDistrict(od.getDistrict())
                .setDistrictId(od.getDistrictId())
                .setWard(od.getWard())
                .setWardId(od.getWardId())
                .setStreet(od.getStreet())
                .setPriceRule(rule);

        officeRepository.save(office);
    }
}
