package com.spring.app.price.controllers;

import com.spring.app.helper.csv.CSVHelper;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.payload.CustomPageResponse;
import com.spring.app.payload.FilterRequest;
import com.spring.app.payload.MessageResponse;
import com.spring.app.price.models.Rule;
import com.spring.app.price.models.RuleArea;
import com.spring.app.price.models.RulePriceList;
import com.spring.app.price.models.repository.RuleRepository;
import com.spring.app.price.payload.RuleAreaData;
import com.spring.app.price.payload.RuleData;
import com.spring.app.price.payload.RulePriceListData;
import com.spring.app.price.payload.request.PriceRuleImportRequest;
import com.spring.app.price.service.RuleService;
import com.spring.app.region.models.Province;
import com.spring.app.region.models.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/office/price")
public class PriceRuleController {
    @Autowired
    RuleService ruleService;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    CSVHelper csvHelper = new CSVHelper();

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long id) {
        try {
            Rule rule = ruleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rule not found!"));
            Set<RuleArea> ruleArea = rule.getRuleAreas();
            List<RuleAreaData> ruleAreaDataList = new ArrayList<>();

            for (RuleArea ra : ruleArea) {
                ruleAreaDataList.add(new RuleAreaData(
                        ra.getId(),
                        rule.getId(),
                        ra.getProvinceId(),
                        ra.getProvinceName(),
                        ra.getAreaCode(),
                        ra.getCenterAreaShippingTime(),
                        ra.getOutskirtsAreaShippingTime(),
                        DateFormatHelper.dateToString(ra.getCreatedAt()),
                        DateFormatHelper.dateToString(ra.getUpdatedAt())
                ));
            }

            Set<RulePriceList> rulePriceLists = rule.getRulePriceLists();
            List<RulePriceListData> rulePriceListData = new ArrayList<>();

            for (RulePriceList rp : rulePriceLists) {
                rulePriceListData.add(new RulePriceListData(
                        rp.getId(),
                        rule.getId(),
                        rp.getAreaCode(),
                        rp.getPriceList(),
                        rp.getAdvancePrice(),
                        rp.getWeightFactor(),
                        rp.getAdvanceWeightFactor(),
                        DateFormatHelper.dateToString(rp.getCreatedAt()),
                        DateFormatHelper.dateToString(rp.getUpdatedAt())
                ));
            }

            RuleData ruleAreaData = new RuleData(
                    rule.getId(),
                    rule.getName(),
                    rule.getDescription(),
                    ruleAreaDataList,
                    rulePriceListData,
                    DateFormatHelper.dateToString(rule.getCreatedAt()),
                    DateFormatHelper.dateToString(rule.getUpdatedAt())
            );

            return ResponseEntity.ok(ruleAreaData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/area/sample")
    public ResponseEntity<?> downloadAreaSample() {
        try {
            String filename = "areaConfig.csv";
            List<String> header = new ArrayList<>(Arrays.asList(
                    "STT","Id","Province","Code","TimeAtCenter","TimeAtOutskirts"
            ));
            csvHelper.setHeader(header);

            List<Province> provinces = provinceRepository.findAll();
            List<List<String>> provinceStringData = new ArrayList<>();
            int i = 1;

            for (Province p : provinces) {
                provinceStringData.add(new ArrayList<>(Arrays.asList(
                        Integer.toString(i),
                        Long.toString(p.getId()),
                        p.getProvinceName(),
                        "",
                        "",
                        ""
                )));
                i++;
            }

            InputStreamResource file = new InputStreamResource(csvHelper.loadFile(provinceStringData));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv;charset=UTF-8"))
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/price/sample")
    public ResponseEntity<?> downloadPriceSample() {
        try {
            String filename = "priceConfig.csv";
            csvHelper.setHeader(
                    new ArrayList<>(Arrays.asList(
                            "Weight Factor","A","B","C","F"
                    ))
            );
            List<Province> provinces = provinceRepository.findAll();
            List<List<String>> priceStringData = new ArrayList<List<String>>() {{
                add(new ArrayList<>(Arrays.asList("0.5","0","0","0","0")));
            }};

            InputStreamResource file = new InputStreamResource(csvHelper.loadFile(priceStringData));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/import")
    public ResponseEntity<?> importPriceConfigData(@Valid PriceRuleImportRequest request) {
        try {
            ruleService.saveRule(request);
            return ResponseEntity.ok(new MessageResponse("Import rule data success!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/all/page")
    public ResponseEntity<?> getAllPriceRule(@Valid FilterRequest fR) {
        Pageable pageable = PageRequest.of(fR.getPage(), fR.getSize());
        Page<Rule> rules = ruleRepository.findAll(pageable);
        CustomPageResponse response = new CustomPageResponse(rules);
        List<RuleData> ruleData = new ArrayList<>();

        for (Rule r : rules) {
            ruleData.add(new RuleData(
                    r.getId(),
                    r.getName(),
                    r.getDescription(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    DateFormatHelper.dateToString(r.getCreatedAt()),
                    DateFormatHelper.dateToString(r.getUpdatedAt())
            ));
        }

        response.setContent(ruleData);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPriceRule() {
        List<Rule> rules = ruleRepository.findAll();
        List<RuleData> ruleData = new ArrayList<>();

        for (Rule r : rules) {
            ruleData.add(new RuleData(
                    r.getId(),
                    r.getName(),
                    r.getDescription(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    DateFormatHelper.dateToString(r.getCreatedAt()),
                    DateFormatHelper.dateToString(r.getUpdatedAt())
            ));
        }

        return ResponseEntity.ok(ruleData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        try {
            Rule rule = ruleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rule not found!"));
            ruleRepository.delete(rule);

            return ResponseEntity.ok(new MessageResponse("Delete success!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
