package com.spring.app.price.service;

import com.spring.app.helper.csv.CSVHelper;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.price.models.Rule;
import com.spring.app.price.models.RuleArea;
import com.spring.app.price.models.RulePriceList;
import com.spring.app.price.models.repository.RuleRepository;
import com.spring.app.price.payload.RuleAreaData;
import com.spring.app.price.payload.RuleData;
import com.spring.app.price.payload.RulePriceListData;
import com.spring.app.price.payload.request.PriceRuleImportRequest;
import com.spring.app.region.models.Province;
import com.spring.app.region.models.repository.ProvinceRepository;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    CSVHelper csvHelper = new CSVHelper();

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    public void saveRule(PriceRuleImportRequest request) {
        Rule rule;
        if (request.getId() != null) {
            rule = ruleRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Rule not found"));
        } else {
            rule = new Rule();
        }

        rule.setName(request.getName())
                .setDescription(request.getDescription());

        importCSVRuleArea(rule, request.getAreaConfig());
        importCSVRulePriceList(rule, request.getPriceConfig());

        ruleRepository.save(rule);
    }

    public void importCSVRuleArea(Rule rule, MultipartFile file) {
        csvHelper.setFile(file);
        List<CSVRecord> data = csvHelper.getCSVRecord();
        List<RuleArea> areas = new ArrayList<>();

        for (CSVRecord csvRecord : data) {
            Province province = provinceRepository.findById(Long.parseLong(csvRecord.get("Id")))
                    .orElseThrow(() -> new RuntimeException("Province id not found!: " + csvRecord.get("Province")));

            areas.add(new RuleArea(
                    //Id,Province,Code,TimeAtCenter,TimeAtOutskirts
                    rule,
                    province.getProvinceId(),
                    province.getProvinceName(),
                    csvRecord.get("Code"),
                    Integer.parseInt(csvRecord.get("TimeAtCenter")),
                    Integer.parseInt(csvRecord.get("TimeAtOutskirts"))
            ));
        }

        rule.getRuleAreas().clear();
        rule.getRuleAreas().addAll(areas);
    }

    public void importCSVRulePriceList(Rule rule, MultipartFile file) {
        csvHelper.setFile(file);
        List<CSVRecord> data = csvHelper.getCSVRecord();
        List<RulePriceList> priceLists = new ArrayList<>();
        List<String> headers = csvHelper.getHeader();

        for (int i = 1; i < headers.size(); ++i) {
            String h = headers.get(i);
            List<Double> priceList = new ArrayList<>();
            int dataSize = data.size();

            for (int j = 0; j < dataSize - 1; ++j) {
                CSVRecord csvRecord = data.get(j);
                priceList.add(Double.parseDouble(csvRecord.get(h)));
            }

            priceLists.add(
                    new RulePriceList(
                            rule,
                            h,
                            priceList,
                            Double.parseDouble(data.get(dataSize - 1).get(h)),
                            0.5,
                            0.5
                    )
            );
        }

        rule.getRulePriceLists().clear();
        rule.getRulePriceLists().addAll(priceLists);
    }

    public RuleData processRuleDataResponse(Rule rule) {
        List<RuleAreaData> ruleAreaData = new ArrayList<>();
        for (RuleArea ra : rule.getRuleAreas())
            ruleAreaData.add(processRuleAreaDataResponse(ra));

        List<RulePriceListData> rulePriceListData = new ArrayList<>();
        for (RulePriceList rpl : rule.getRulePriceLists())
            rulePriceListData.add(processRulePriceListResponse(rpl));

        return new RuleData(
                rule.getId(),
                rule.getName(),
                rule.getDescription(),
                ruleAreaData,
                rulePriceListData,
                DateFormatHelper.dateToString(rule.getCreatedAt()),
                DateFormatHelper.dateToString(rule.getUpdatedAt())
        );
    }

    public RuleAreaData processRuleAreaDataResponse(RuleArea ruleArea) {
        return new RuleAreaData(
                ruleArea.getId(),
                ruleArea.getRule().getId(),
                ruleArea.getProvinceId(),
                ruleArea.getProvinceName(),
                ruleArea.getAreaCode(),
                ruleArea.getCenterAreaShippingTime(),
                ruleArea.getOutskirtsAreaShippingTime(),
                DateFormatHelper.dateToString(ruleArea.getCreatedAt()),
                DateFormatHelper.dateToString(ruleArea.getUpdatedAt())
        );
    }

    public RulePriceListData processRulePriceListResponse(RulePriceList rp) {
        return new RulePriceListData(
                rp.getId(),
                rp.getRule().getId(),
                rp.getAreaCode(),
                rp.getPriceList(),
                rp.getAdvancePrice(),
                rp.getWeightFactor(),
                rp.getAdvanceWeightFactor(),
                DateFormatHelper.dateToString(rp.getCreatedAt()),
                DateFormatHelper.dateToString(rp.getUpdatedAt())
        );
    }
}
