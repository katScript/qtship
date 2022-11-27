package com.spring.app.region.components;

import com.spring.app.patch.models.Patch;
import com.spring.app.patch.models.repository.PatchRepository;
import com.spring.app.region.models.District;
import com.spring.app.region.models.Province;
import com.spring.app.region.models.Ward;
import com.spring.app.region.models.repository.DistrictRepository;
import com.spring.app.region.models.repository.ProvinceRepository;
import com.spring.app.region.models.repository.WardRepository;
import com.spring.app.region.payload.DistrictData;
import com.spring.app.region.payload.ProvinceData;
import com.spring.app.region.payload.WardData;
import com.spring.app.region.payload.seed.DistrictSeed;
import com.spring.app.region.payload.seed.ProvinceSeed;
import com.spring.app.region.payload.seed.WardSeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;

@Component
public class RegionImport implements CommandLineRunner {
    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    PatchRepository patchRepository;

    private final String PATCH = "RegionDataSeeder";

    @Override
    public void run(String... args) throws Exception {
        Boolean patch = patchRepository.existsByPatch(PATCH);

        if (!patch) {
            System.out.println("Cleaning region data...");
            provinceRepository.deleteAll();

            System.out.println("Importing region data...");
            importRegionData();

            patchRepository.save(new Patch(PATCH));
            System.out.println("Import region done.");
        }
    }

    private void importRegionData() {
        String url = "https://vapi.vnappmob.com/api/province/";
        RestTemplate restTemplate = new RestTemplate();

        ProvinceSeed provinceSeed = restTemplate.getForObject(url, ProvinceSeed.class);

        if (provinceSeed != null) {
            for (ProvinceData pd : provinceSeed.getResults()) {
                Province province = new Province(
                        pd.getProvince_id(),
                        pd.getProvince_name(),
                        pd.getProvince_type(),
                        new HashSet<>()
                );

                importDistrictData(province);
                provinceRepository.save(province);
            }
        }
    }

    private void importDistrictData(Province province) {
        String url = "https://vapi.vnappmob.com/api/province/district/" + province.getProvinceId();
        RestTemplate restTemplate = new RestTemplate();

        DistrictSeed districtSeed = restTemplate.getForObject(url, DistrictSeed.class);

        if (districtSeed != null) {
            for (DistrictData dd : districtSeed.getResults()) {
                District district = new District(
                        dd.getDistrict_id(),
                        dd.getDistrict_name(),
                        dd.getDistrict_type(),
                        dd.getLat(),
                        dd.getLng(),
                        province,
                        new HashSet<>()
                );

                province.getDistricts().add(district);
                importWardData(district);
            }
        }
    }

    private void importWardData(District district) {
        String url = "https://vapi.vnappmob.com/api/province/ward/" + district.getDistrictId();
        RestTemplate restTemplate = new RestTemplate();

        WardSeed wardSeed = restTemplate.getForObject(url, WardSeed.class);

        if (wardSeed != null) {
            for (WardData wd : wardSeed.getResults()) {
                Ward ward = new Ward(
                        wd.getWard_id(),
                        wd.getWard_name(),
                        wd.getWard_type(),
                        district
                );

                district.getWards().add(ward);
            }
        }
    }
}
