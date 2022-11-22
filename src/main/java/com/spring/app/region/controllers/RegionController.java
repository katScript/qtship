package com.spring.app.region.controllers;

import com.spring.app.region.models.District;
import com.spring.app.region.models.Province;
import com.spring.app.region.models.Ward;
import com.spring.app.region.models.repository.DistrictRepository;
import com.spring.app.region.models.repository.ProvinceRepository;
import com.spring.app.region.models.repository.WardRepository;
import com.spring.app.region.payload.DistrictData;
import com.spring.app.region.payload.ProvinceData;
import com.spring.app.region.payload.WardData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/region")
public class RegionController {
    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WardRepository wardRepository;

    @GetMapping("/province")
    public ResponseEntity<?> getAllProvince() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceData> provinceDataList = new ArrayList<>();

        for (Province p : provinces) {
            provinceDataList.add(new ProvinceData(
                    p.getProvinceId(),
                    p.getProvinceName(),
                    p.getProvinceType()
            ));
        }

        return ResponseEntity.ok(provinceDataList);
    }

    @GetMapping("/province/district/{provinceId}")
    public ResponseEntity<?> getAllDistrictByProvinceId(@Valid @PathVariable String provinceId) {
        List<District> districts = districtRepository.findByProvinceId(provinceId);
        List<DistrictData> districtData = new ArrayList<>();

        for (District d : districts) {
            districtData.add(new DistrictData(
                    d.getDistrictId(),
                    d.getDistrictName(),
                    d.getDistrictType(),
                    d.getLatitude(),
                    d.getLongitude(),
                    d.getProvince().getProvinceId()
            ));
        }

        return ResponseEntity.ok(districtData);
    }

    @GetMapping("/province/ward/{districtId}")
    public ResponseEntity<?> getAllWardByDistrictId(@Valid @PathVariable String districtId) {
        List<Ward> wards = wardRepository.findByDistrictId(districtId);
        List<WardData> wardData = new ArrayList<>();

        for (Ward w : wards) {
            wardData.add(new WardData(
                    w.getWardId(),
                    w.getWardName(),
                    w.getWardType(),
                    w.getDistrict().getDistrictId()
            ));
        }

        return ResponseEntity.ok(wardData);
    }
}
