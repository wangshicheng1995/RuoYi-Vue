package com.ruoyi.web.controller.dataEntry;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.web.Entity.IndividualFree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Anonymous
@RestController
@RequestMapping("/dataEntry")
public class DataEntryController {

    /**
     * 查询已有的 Master Data
     */
    @GetMapping(value = "/getOldMasterData")
    public List<IndividualFree> getOldMasterData() {
        IndividualFree o1 = new IndividualFree("1", "Blood", "血常规", 1.0, "Y", "N");
        IndividualFree o2 = new IndividualFree("2", "Blood chemistry", "血生化", 3.0, "Y", "N");
        IndividualFree o3 = new IndividualFree("3", "Coagulation test", "凝血检查", 5.0, "Y", "N");
        IndividualFree o4 = new IndividualFree("4", "Urine routine", "尿常规", 7.0, "Y", "N");

        return new ArrayList<>(Arrays.asList(o1, o2, o3, o4));
    }

    /**
     * 查询用户导入的 Master Data
     */
    @GetMapping(value = "/getNewMasterData")
    public List<IndividualFree> getNewMasterData() {
        IndividualFree n2 = new IndividualFree("2", "Blood chemistry", "血生化", 3.0, "Y", "N");
        IndividualFree n3 = new IndividualFree("3","Coagulation test", "凝血检查", 5.0, "Y", "N");
        IndividualFree n4 = new IndividualFree("4", "Urine routine", "尿常规", 12.0, "Y", "N");
        IndividualFree n5 = new IndividualFree("5", "Bone routine", "骨常规", 5.0, "Y", "N");

        return new ArrayList<>(Arrays.asList(n2,n3, n4, n5));
    }
}
